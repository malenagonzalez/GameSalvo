package com.example.salvo.dto;

import com.example.salvo.model.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerDTO {

    private Player player;

    public PlayerDTO(Player player) {
        this.player = player;
    }

    public PlayerDTO() {
    }

    public Map<String, Object> makePlayerDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();

        dto.put("id", this.player.getId());
        dto.put("name", this.player.getName());
        dto.put("email", this.player.getEmail());

        return dto;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public Map<String, Object> makePlayerScoreDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        Map<String, Object> score = new LinkedHashMap<>();

        dto.put("id", this.player.getId());
        dto.put("email", this.player.getEmail());
        dto.put("score", score);
            score.put("total", this.player.getTotal());
            score.put("won", this.player.getWon());
            score.put("tied", this.player.getTied());
            score.put("lost", this.player.getLost());
        return dto;
    }
}

