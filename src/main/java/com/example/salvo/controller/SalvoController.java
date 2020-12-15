package com.example.salvo.controller;

import com.example.salvo.model.GamePlayer;
import com.example.salvo.model.Player;
import com.example.salvo.model.Salvo;
import com.example.salvo.model.Ship;
import com.example.salvo.repository.GamePlayerRepository;
import com.example.salvo.repository.PlayerRepository;
import com.example.salvo.repository.SalvoRepository;
import com.example.salvo.repository.ShipRepository;
import com.example.salvo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SalvoController {
    @Autowired
    GamePlayerRepository gamePlayerRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    SalvoRepository salvoRepository;

    @RequestMapping(path = "/games/players/{gpID}/salvoes", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addSalvoes(@PathVariable Long gpID, @RequestBody Salvo salvo, Authentication authentication) {
        GamePlayer gamePlayer = gamePlayerRepository.findById(gpID).orElse(null);

        GamePlayer opponent = new GamePlayer();
        if (Util.isGuest(authentication)) {
            return new ResponseEntity<>(Util.makeMap("error", "you must log in"), HttpStatus.UNAUTHORIZED);
        }

        if (gamePlayer == null) {
            return new ResponseEntity<>(Util.makeMap("error", "player does not exist"), HttpStatus.UNAUTHORIZED);
        }

        if (Util.getOpponent(gamePlayer).isPresent()) {
            opponent = Util.getOpponent(gamePlayer).get();
        }else
        {
            return new ResponseEntity<>(Util.makeMap("error", "No hay oponente"), HttpStatus.FORBIDDEN);
        }

        long myTurn = gamePlayer.getSalvo().size();
        long player2Turn = opponent.getSalvo().size();

        if (myTurn > player2Turn) {
            return new ResponseEntity<>(Util.makeMap("error", "not your turn"), HttpStatus.FORBIDDEN);
        }
            salvo.setTurn(myTurn+1);
            gamePlayer.addSalvo(salvo);
            salvo.setGamePlayer(gamePlayer);
            salvoRepository.save(salvo);

        return new ResponseEntity<>(Util.makeMap("OK","Added Salvo!"), HttpStatus.CREATED);

    }

}


