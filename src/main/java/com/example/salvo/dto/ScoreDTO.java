package com.example.salvo.dto;

import com.example.salvo.model.Score;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScoreDTO {

    private Score score;

    //constructores
    public ScoreDTO(Score score) {
        this.score = score;
    }

    public ScoreDTO() {
    }
    public Map<String, Object> makeScoreDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", score.getId());
        dto.put("score", score.getScore());
        dto.put("player", score.getPlayer().getId());
        dto.put("game", score.getGame().getId());
        dto.put("finishDate", score.getFinishDate());
        return dto;
    }

    //getter
    public Score getScore() {
        return score;
    }

    //setter
    public void setScore(Score score) {
        this.score = score;
    }
}
