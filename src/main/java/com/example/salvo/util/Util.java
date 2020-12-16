package com.example.salvo.util;

import com.example.salvo.dto.HitsDTO;
import com.example.salvo.model.GamePlayer;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class Util {

    public static boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    public static Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }


    public static Optional<GamePlayer> getOpponent(GamePlayer gamePlayer){
       return gamePlayer.getGame().getGamePlayers()
               .stream()
               .filter(gp -> gp.getId() != gamePlayer.getId())
               .findFirst();
    }

   public static Map<String, Integer> shipTypes = Stream.of(
            new Object[][]{
                    {"carrier", 5},
                    {"battleship", 4},
                    {"submarine", 3},
                    {"destroyer", 3},
                    {"petrolboat", 2}
            }).collect(toMap(data -> (String)data[0], data -> (Integer)data[1]));


    public static List<String> getLocationsByType(String type, GamePlayer self){
        return  self.getShips().size()  ==  0 ? new ArrayList<>() : self.getShips()
                .stream()
                .filter(ship -> ship.getType().equals(type))
                .findFirst().get()
                .getLocations();
    }

    public static String gameState(GamePlayer gamePlayer){
        Map<String, Object> hits = new LinkedHashMap<>();

        if(gamePlayer.getShips().isEmpty()){
            return "PLACESHIPS";
        }

        if(gamePlayer.getGame().getGamePlayers().size() == 1 || Util.getOpponent(gamePlayer).get().getShips().size() == 0){
            return "WAITINGFOROPP";
        }


        long myTurn = gamePlayer.getSalvo().size();
        long oppTurn = Util.getOpponent(gamePlayer).get().getSalvo().size();

        if(myTurn>oppTurn)
        {
            return "WAIT";
        }

        if(gamePlayer.getGame().getGamePlayers().size() == 2){
            HitsDTO hitsDTO = new HitsDTO();

            int myImpact = hitsDTO.makeImpacts(gamePlayer);
            int oppImpact = hitsDTO.makeImpacts(Util.getOpponent(gamePlayer).get());


            if(myImpact == 17 && oppImpact == 17 ) {
                return "TIE";
            } else if(myImpact == 17  && gamePlayer.getSalvo().size() == Util.getOpponent(gamePlayer).get().getSalvo().size()){
                return "LOST";

            }else if (oppImpact == 17 && gamePlayer.getSalvo().size() == Util.getOpponent(gamePlayer).get().getSalvo().size()){
                return "WON";
            }
        }
        return "PLAY";
    }

}

