package com.example.salvo.dto;
import com.example.salvo.model.Game;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class GameDTO {
    private Game game;
    private GamePlayerDTO gamePlayerDTO;

    public GameDTO(Game game) {
        this.game = game;
    }

    public GameDTO() {
    }

    public Map<String, Object> makeGameDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();

        dto.put("id", this.game.getId());
        dto.put("created", this.game.getCreated());
        dto.put("gamePlayers", this.game.getGamePlayers()
                .stream()
                .map(gamePlayer -> {
                    GamePlayerDTO gamePlayerDto = new GamePlayerDTO(gamePlayer);
                    return gamePlayerDto.makeGamePlayerDTO();
                })
                .collect(toList()));
        dto.put("scores", this.game.getScores()
                .stream()
        .map(score -> {
            ScoreDTO scoreDTO = new ScoreDTO(score);
            return scoreDTO.makeScoreDTO();
        }).collect(Collectors.toList()));
        return dto;
    }
}