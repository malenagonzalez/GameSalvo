package com.example.salvo.controller;

import com.example.salvo.model.GamePlayer;
import com.example.salvo.model.Player;
import com.example.salvo.model.Ship;
import com.example.salvo.repository.GamePlayerRepository;
import com.example.salvo.repository.PlayerRepository;
import com.example.salvo.repository.ShipRepository;
import com.example.salvo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ShipController {
    @Autowired
    GamePlayerRepository gamePlayerRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ShipRepository shipRepository;


    @RequestMapping(value="/games/players/{gpID}/ships", method= RequestMethod.POST)
    public ResponseEntity<Map> addShip(@PathVariable long gpID, @RequestBody List<Ship> shipList, Authentication authentication) {
        GamePlayer gamePlayer = gamePlayerRepository.findById(gpID).orElse(null);

        if(Util.isGuest(authentication)){
            return new ResponseEntity<>(Util.makeMap("error", "you must log in"), HttpStatus.UNAUTHORIZED);
        }

        if(gamePlayer == null ){
            return new ResponseEntity<>(Util.makeMap("error", "player does not exist"), HttpStatus.UNAUTHORIZED);
        }
        String playerEmail = authentication.getName();
        if(gamePlayer.getPlayer().getId() != playerRepository.findByEmail(playerEmail).getId()){
            return new ResponseEntity<>(Util.makeMap("error", "This is not your game"), HttpStatus.UNAUTHORIZED);
        }

        if(gamePlayer.getShips().size()>5){
            int shipSize = shipList.size();
            return new ResponseEntity<>(Util.makeMap("error", shipSize + " all ships have been placed"), HttpStatus.FORBIDDEN);
        }

        List newShipList = shipList.stream().map(ship -> {ship.setGamePlayer(gamePlayer);
        return ship;})
                .collect(Collectors.toList());

        shipRepository.saveAll(newShipList);
        return new ResponseEntity<>(Util.makeMap("OK", "Ships created"), HttpStatus.CREATED);
    }
    }

