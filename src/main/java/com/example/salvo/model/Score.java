package com.example.salvo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Date finishDate;
    private double score;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "playerID")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gameID")
    private Game game;


    //Constructores
    public Score() {
    }

    public Score(double score, Date finishDate,  Player player, Game game) {
        this.score = score;
        this.finishDate = finishDate;
        this.player = player;
        this.game = game;
    }

    public Score(double score, Player player, Game game) {
        this.score = score;
        this.finishDate = new Date();
    }

    //Getters
    public long getId() {
        return id;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public double getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }


    //Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
