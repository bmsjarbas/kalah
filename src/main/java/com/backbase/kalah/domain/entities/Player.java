package com.backbase.kalah.domain.entities;

import java.util.UUID;

/**
 * Created by js on 9/19/16.
 */
public class Player {
    private UUID id;
    private String name;

    public Player(UUID id, String name){
        this.id = id;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
