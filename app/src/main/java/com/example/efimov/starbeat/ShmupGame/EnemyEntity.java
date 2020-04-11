package com.example.efimov.starbeat.ShmupGame;

import Exceptions.InvalidHealthOperationException;

public class EnemyEntity extends CharacterEntity {

    public EnemyEntity(int health, int maxHealth, int posX, float posY, float speed, int angle) throws InvalidHealthOperationException {
        super(health, maxHealth, posX, posY, speed, angle);
    }

}
