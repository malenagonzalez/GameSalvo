package com.example.salvo.dto;

import com.example.salvo.model.Ship;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShipDTO {

    private Ship ship;

    //constructores
    public ShipDTO(Ship ship) {

        this.ship = ship;
    }

    public ShipDTO() {
    }

    //comentario
    public Map<String, Object> makeShipDTO() { //metodo makeShipDTO
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", ship.getId()); //para acceder al dato o a la informacion tengo que acceder por la palabra reservada this
        dto.put("type", ship.getType());
        dto.put("locations" , ship.getLocations());
        return dto;

    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

}
