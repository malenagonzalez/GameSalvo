package com.example.salvo.dto;

import com.example.salvo.model.GamePlayer;
import com.example.salvo.model.Salvo;
import com.example.salvo.util.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class HitsDTO {

    private Map<String, Object> dto;

    public HitsDTO(Map<String, Object> dto) {
        this.dto = dto;
    }

    public HitsDTO() {
        this.dto = new LinkedHashMap<>();
    }

    public List<Map<String, Object>> makeHitsDTO(GamePlayer gamePlayer) {
       
        List<Map<String, Object>> hits = new ArrayList<>();

        List<String>carrierLocations = Util.getLocationsByType("carrier", gamePlayer);
        List<String>battleShipLocations = Util.getLocationsByType("battleship", gamePlayer);
        List<String>submarineLocations = Util.getLocationsByType("submarine", gamePlayer);
        List<String>destroyerLocations = Util.getLocationsByType("destroyer", gamePlayer);
        List<String>patrolBoatLocations = Util.getLocationsByType("patrolboat", gamePlayer);


            long carrierDamage = 0;
            long battleShipDamage = 0;
            long submarineDamage = 0;
            long destroyerDamage = 0;
            long patrolBoatDamage = 0;

        for(Salvo salvoShot : Util.getOpponent(gamePlayer).get().getSalvo()){

            Map<String, Object> damageMapPerTurn = new LinkedHashMap<>();
            Map<String, Object> hitsMapPerTurn = new LinkedHashMap<>();
            List<String>hitCellsList = new ArrayList<>();


            long missedShots = salvoShot.getLocations().size();//tiene el total de locations

            long carrierHitsInTurn = 0;
            long battleShipHitsInTurn = 0;
            long submarineHitsInTurn = 0;
            long destroyerHitsInTurn = 0;
            long patrolBoatHitsInTurn = 0;

            for(String location : salvoShot.getLocations()){

                if(carrierLocations.contains(location)){
                    hitCellsList.add(location);
                    carrierDamage++;
                    carrierHitsInTurn++;
                    missedShots--;
                }

                if(battleShipLocations.contains(location)){
                    hitCellsList.add(location);
                    battleShipDamage++;
                    battleShipHitsInTurn++;
                    missedShots--;
                }


                if(submarineLocations.contains(location)){
                    hitCellsList.add(location);
                    submarineDamage++;
                    submarineHitsInTurn++;
                    missedShots--;
                }

                if(destroyerLocations.contains(location)){
                    hitCellsList.add(location);
                    destroyerDamage++;
                    destroyerHitsInTurn++;
                    missedShots--;
                }

                if(patrolBoatLocations.contains(location)){
                    hitCellsList.add(location);
                    patrolBoatDamage++;
                    patrolBoatHitsInTurn++;
                    missedShots--;
                }

            }
            damageMapPerTurn.put("carrierHits", carrierHitsInTurn);
            damageMapPerTurn.put("battleshipHits", battleShipHitsInTurn);
            damageMapPerTurn.put("submarineHits", submarineHitsInTurn);
            damageMapPerTurn.put("destroyerHits", destroyerHitsInTurn );
            damageMapPerTurn.put("patrolboatHits", patrolBoatHitsInTurn);
            hitsMapPerTurn.put("turn", salvoShot.getTurn());
            hitsMapPerTurn.put("hitLocations", hitCellsList);
            hitsMapPerTurn.put("damages", damageMapPerTurn);
            hitsMapPerTurn.put("missed", missedShots);
            hits.add(hitsMapPerTurn);
            damageMapPerTurn.put("carrier", carrierDamage);
            damageMapPerTurn.put("battleship", battleShipDamage);
            damageMapPerTurn.put("submarine", submarineDamage);
            damageMapPerTurn.put("destroyer", destroyerDamage);
            damageMapPerTurn.put("patrolboat", patrolBoatDamage);
        }

        return hits;
    }

    public int makeImpacts(GamePlayer gamePlayer){
        List<String>carrierLocations= new ArrayList<String>();
        List<String>battleshipLocations = new ArrayList<>();
        List<String>submarineLocations = new ArrayList<>();
        List<String>destroyerLocations = new ArrayList<>();
        List<String>patrolBoatLocations = new ArrayList<>();

        carrierLocations= Util.getLocationsByType("carrier", gamePlayer);
        battleshipLocations=Util.getLocationsByType("battleship", gamePlayer);
        submarineLocations=Util.getLocationsByType("submarine", gamePlayer);
        destroyerLocations=Util.getLocationsByType("destroyer",gamePlayer);
        patrolBoatLocations=Util.getLocationsByType("patrolboat", gamePlayer);

        int countImpact=0;

        for(Salvo salvoShot: Util.getOpponent(gamePlayer).get().getSalvo()){

            for(String location: salvoShot.getLocations()){
                if (carrierLocations.contains(location)){
                    countImpact++;
                }
                if (battleshipLocations.contains(location)){
                    countImpact++;
                }
                if (submarineLocations.contains(location)){
                    countImpact++;
                }
                if (destroyerLocations.contains(location)){
                    countImpact++;
                }
                if (patrolBoatLocations.contains(location)){
                    countImpact++;
                }
            }

        }
        return countImpact++;

    }
}