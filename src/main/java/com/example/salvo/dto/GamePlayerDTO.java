package com.example.salvo.dto;

import com.example.salvo.model.GamePlayer;
import com.example.salvo.util.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GamePlayerDTO {

    private GamePlayer gamePlayer;

    public GamePlayerDTO(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }


    public Map<String, Object> makeGamePlayerDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        PlayerDTO playerDto = new PlayerDTO(gamePlayer.getPlayer());
        dto.put("id", this.gamePlayer.getId());
        dto.put("player", playerDto.makePlayerDTO());
        return dto;
    }


    public Map<String,  Object> makeGameViewDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();

        HitsDTO hitsDTO = new HitsDTO();


        dto.put("id", this.gamePlayer.getGame().getId());
        dto.put("created", this.gamePlayer.getGame().getCreated());
        dto.put("gamePlayers", this.gamePlayer.getGame().getGamePlayers()
                .stream()
                .map(gamep -> {
                    GamePlayerDTO gamePlayerDto = new GamePlayerDTO(gamep);
                    return gamePlayerDto.makeGamePlayerDTO();
                }).collect(Collectors.toList()));
        dto.put("ships", this.gamePlayer.getShips().stream().map(ship -> {
            ShipDTO shipDto = new ShipDTO(ship);
            return shipDto.makeShipDTO();
        }).collect(Collectors.toList()));
        dto.put("salvoes", this.gamePlayer.getGame().getGamePlayers()
                .stream()
                .flatMap(gamePlayer -> gamePlayer.getSalvo()
                        .stream()
                        .map(salvo -> {
                            SalvoDTO salvoDTO = new SalvoDTO(salvo);
                            return salvoDTO.makeSalvoDTO();
                        }))
                .collect(Collectors.toList()));

        Map<String, Object> hits = new LinkedHashMap<>();
        if (gamePlayer.getGame().getGamePlayers().size() == 2) {
            hits.put("self", hitsDTO.makeHitsDTO(this.gamePlayer));
            hits.put("opponent", hitsDTO.makeHitsDTO(Util.getOpponent(gamePlayer).get()));
        } else {
            hits.put("self", new ArrayList<>());
            hits.put("opponent", new ArrayList<>());
        }
        dto.put("hits", hits);
        dto.put("gameState", Util.gameState(gamePlayer));

        return dto;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
}

