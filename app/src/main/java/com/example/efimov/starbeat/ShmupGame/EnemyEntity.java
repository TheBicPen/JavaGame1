package com.example.efimov.starbeat.ShmupGame;

import com.example.efimov.starbeat.engine.Coordinates2D;

import Exceptions.InvalidHealthOperationException;

public class EnemyEntity extends CharacterEntity {

    public EnemyEntity(int health, int maxHealth, Coordinates2D coords, float speed, int angle) throws InvalidHealthOperationException {
        super(health, maxHealth, coords, speed, angle);
    }

}
