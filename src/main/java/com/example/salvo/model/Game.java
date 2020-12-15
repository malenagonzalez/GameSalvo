package com.example.salvo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Date created;

    @OneToMany(mappedBy = "game")
    private Set<GamePlayer> gamePlayers;

    @OneToMany (mappedBy = "game", fetch = FetchType.EAGER)
    private Set<Score> scores;

    public Game() {
        this.created = new Date();
    }

    public Game(long id, Date created, Set<GamePlayer> gamePlayers, Set<Score> scores) {
        this.id = id;
        this.created = created;
        this.gamePlayers = gamePlayers;
        this.scores = scores;
    }


    public void setId(long id) {
        this.id = id;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public List<Player> getPlayers() {
        return getGamePlayers().stream()
                .map(sub -> sub.getPlayer())
                .collect(Collectors.toList());
    }


}