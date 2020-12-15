package com.example.salvo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name  = "native", strategy = "native")
    private long  id;
    private Date joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "playerID")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gameID")
    private Game game;

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    private Set<Ship> ships;

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    private Set<Salvo> salvos;


    public void addShip(Ship ship){
        this.ships.add(ship);
    }

    public GamePlayer(){}

    public GamePlayer(Player player, Game game) {
        this.player = player;
        this.game = game;
        this.joinDate = new Date();
    }

    public long getId() {
        return id;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Ship> getShips() {
        return ships;
    }

    public Set<Salvo> getSalvo(){return salvos;}

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }




    public GamePlayer(long id, Date joinDate, Player player, Game game, Set<Ship> ships) {
        this.id = id;
        this.joinDate = joinDate;
        this.player = player;
        this.game = game;
        this.ships = ships;
    }

    public GamePlayer(Date joinDate, Player player, Game game) {
        this.joinDate = new Date();
        this.player = player;
        this.game = game;
    }

    public Score getScore(double score){
        if(score < 0)
            return null;
        return new Score(score, player, game);
    }

    public void addSalvo(Salvo salvo){
        salvo.setGamePlayer(this);
        salvos.add(salvo);
    }



}