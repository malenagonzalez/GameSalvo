package com.example.salvo.model;

import com.example.salvo.model.GamePlayer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private String email;
    private String password;

    @OneToMany (mappedBy = "player", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    @OneToMany (mappedBy = "player", fetch = FetchType.EAGER)
    private Set<Score> scores;

    //Constructores
    public Player(){}

    public Player(String email, String name, String password){
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Player(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Getters
    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public String getPassword() {
        return password;
    }


    //Setters

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    //metodos para los scores
    public long getWon(){
        return this.getScores().stream()
                .filter(score -> score.getScore() == 1.0D)
                .count();
    }

    public long getLost(){
        return this.getScores().stream()
                .filter(score -> score.getScore() == 0.0D)
                .count();
    }

    public long getTied(){
        return this.getScores().stream()
                .filter(score -> score.getScore() == 0.5D)
                .count();
    }

    public double getTotal(){
        return getWon() * 1.0 + getTied() * 0.5D;
    }

    public Score getScore(Game game){
        return scores.stream().filter(score -> score.getGame()
        .getId()==getId()).findFirst().orElse(null);
    }

    public void addScore(Score score){
        score.setPlayer(this);
        scores.add(score);
    }

}
