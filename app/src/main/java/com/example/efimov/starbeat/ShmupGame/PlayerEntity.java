package com.example.efimov.starbeat.ShmupGame;

import com.example.efimov.starbeat.engine.Coordinates2D;

import Exceptions.InvalidHealthOperationException;

public class PlayerEntity extends CharacterEntity {

    private int numberOfLives;

    public PlayerEntity(int health, int maxHealth, Coordinates2D coords, float speed, int angle, int lives) throws InvalidHealthOperationException {
        super(health, maxHealth, coords, speed, angle);
        this.numberOfLives = lives;
    }

    public boolean loseLife(){
        numberOfLives--;
        this.alive = numberOfLives >= 0;
        return this.alive;
    }
    @Override
    public boolean takeDamage(int damage) throws InvalidHealthOperationException {
        if(!super.takeDamage(damage)){
            return loseLife();
        }
        return true;
    }
}
