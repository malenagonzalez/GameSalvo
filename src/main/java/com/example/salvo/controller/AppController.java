package com.example.salvo.controller;
import com.example.salvo.dto.*;
import com.example.salvo.model.*;
import com.example.salvo.repository.*;
import com.example.salvo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GamePlayerRepository gamePlayerRepository;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    SalvoRepository salvoRepository;

    @Autowired
    ScoreRepository scoreRepository;


    /*@RequestMapping("/games")
    public Map<String, Object> getGameAll(Authentication authentication) {
        Map<String, Object> dto = new LinkedHashMap<>();

        if (Util.isGuest(authentication)) {
            dto.put("player", "Guest");
        } else {
            Player player = playerRepository.findByEmail(authentication.getName());
            PlayerDTO playerDTO = new PlayerDTO(player);
            dto.put("player", playerDTO.makePlayerDTO()); }
             dto.put("games", gameRepository.findAll()
                .stream()
                .map(game -> {
                    GameDTO gameDTO = new GameDTO(game);
                    return gameDTO.makeGameDTO();
                })
                .collect(Collectors.toList()));
        return dto;
    }*/

    /*@RequestMapping("/players")
    public List<Map<String, Object>> getPlayerAll() {
        return playerRepository.findAll()
                .stream()
                .map(player -> {
                    PlayerDTO playerDto = new PlayerDTO(player);
                    return playerDto.makePlayerDTO();
                })
                .collect(Collectors.toList());
    }*/

    @RequestMapping("/ships")
    public List<Map<String, Object>> getPShipAll() {
        return shipRepository.findAll()
                .stream()
                .map(ship -> {
                    ShipDTO shipDto = new ShipDTO(ship);
                    return shipDto.makeShipDTO();
                })
                .collect(Collectors.toList());
    }

    @RequestMapping("/salvoes")
    public List<Map<String, Object>> getSalvoAll() {
        return salvoRepository.findAll()
                .stream()
                .map(salvo -> {
                    SalvoDTO salvoDTO = new SalvoDTO(salvo);
                    return salvoDTO.makeSalvoDTO();
                })
                .collect(Collectors.toList());
    }

    @RequestMapping("/scores")
    public List<Map<String, Object>> getScoresAll() {
        return scoreRepository.findAll()
                .stream()
                .map(score -> {
                    ScoreDTO scoreDTO = new ScoreDTO(score);
                    return scoreDTO.makeScoreDTO();
                })
                .collect(Collectors.toList());
    }

    @RequestMapping("/leaderBoard")
    public List<Map<String, Object>> leaderBoard() {
        return playerRepository.findAll()
                .stream()
                .map(player -> {
                    PlayerDTO playerDTO = new PlayerDTO(player);
                    return playerDTO.makePlayerScoreDTO();
                })
                .collect(Collectors.toList());
    }


    @RequestMapping(path ="/game_view/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getGameView(@PathVariable  long  id, Authentication authentication){
        Player player = playerRepository.findByEmail(authentication.getName());
        GamePlayer  gamePlayer  = gamePlayerRepository.findById(id).get();

        if (Util.isGuest(authentication)){
            return new ResponseEntity<>(Util.makeMap("Error", "not Logged in"), HttpStatus.UNAUTHORIZED);
        }

        if (player.getId() != gamePlayer.getPlayer().getId()){
            return new ResponseEntity<>(Util.makeMap("Error", "This isn't  your game"), HttpStatus.UNAUTHORIZED);
        }
        GamePlayerDTO dtoGame_View = new GamePlayerDTO(gamePlayer);
        if(Util.gameState(gamePlayer) == "WON"){
            if(gamePlayer.getGame().getScores().size()<2) {
                Set<Score> scores = new HashSet<>();
                Score score1 = new Score();
                score1.setPlayer(gamePlayer.getPlayer());
                score1.setGame(gamePlayer.getGame());
                score1.setFinishDate(Date.from(Instant.now()));
                score1.setScore(1D);
                scoreRepository.save(score1);
                Score score2 = new Score();
                score2.setPlayer(Util.getOpponent(gamePlayer).get().getPlayer());
                score2.setGame(gamePlayer.getGame());
                score2.setFinishDate(Date.from(Instant.now()));
                score2.setScore(0D);
                scoreRepository.save(score2);
                scores.add(score1);
                scores.add(score2);

                Util.getOpponent(gamePlayer).get().getGame().setScores(scores);
            }
        }
        if(Util.gameState(gamePlayer) == "TIE"){
            if(gamePlayer.getGame().getScores().size()<2) {
                Set<Score> scores = new HashSet<Score>();
                Score score1 = new Score();
                score1.setPlayer(gamePlayer.getPlayer());
                score1.setGame(gamePlayer.getGame());
                score1.setFinishDate(Date.from(Instant.now()));
                score1.setScore(0.5D);
                scoreRepository.save(score1);
                Score score2 = new Score();
                score2.setPlayer(Util.getOpponent(gamePlayer).get().getPlayer());
                score2.setGame(gamePlayer.getGame());
                score2.setFinishDate(Date.from(Instant.now()));
                score2.setScore(0.5D);
                scoreRepository.save(score2);
                scores.add(score1);
                scores.add(score2);

                Util.getOpponent(gamePlayer).get().getGame().setScores(scores);
            }
        }


        GamePlayerDTO gamePlayerDTO = new GamePlayerDTO(gamePlayer);
        return new ResponseEntity<>(gamePlayerDTO.makeGameViewDTO(), HttpStatus.ACCEPTED);
    }

}