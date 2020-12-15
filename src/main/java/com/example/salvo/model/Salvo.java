package com.example.salvo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER) //One gamePlayer has many salvoes, so many(salvoes) to One(gamePlayer)
    @JoinColumn(name="salvoId")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name="location")
    private List<String> locations;

    private long turn;

    public Salvo() {
        locations = new ArrayList<String>();
    }

    public Salvo(GamePlayer gamePlayer, List  location, int turn) {
        this.gamePlayer = gamePlayer;
        this.locations = location;
        this.turn = turn;
    }

    public Long getId() {
        return id;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public long getTurn() {
        return turn;
    }

    public void setTurn(long turn) {
        this.turn = turn;
    }

    public List<String> getLocations() {
        return locations;
    }

    public boolean addLocation(String newLocation){
        return locations.add(newLocation);
    }
}
