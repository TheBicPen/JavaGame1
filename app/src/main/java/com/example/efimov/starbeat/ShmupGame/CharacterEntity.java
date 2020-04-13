package com.example.efimov.starbeat.ShmupGame;

import com.example.efimov.starbeat.engine.Coordinates2D;
import com.example.efimov.starbeat.engine.IntegerCoords2D;

import Exceptions.InvalidHealthOperationException;

public class CharacterEntity {
    private int health;
    private int maxHealth;
    private float speed;
    private int angle;
    private Coordinates2D coords2D;

    protected boolean alive = true;

    public CharacterEntity(int health, int maxHealth, Coordinates2D coords, float speed, int angle) throws InvalidHealthOperationException {

        if(health < 1 || health > maxHealth) throw new InvalidHealthOperationException();

        this.health = health;
        this.maxHealth = maxHealth;
        this.coords2D = coords;
        this.speed = speed;
        this.angle = angle;
    }

    public boolean takeDamage(int damage) throws InvalidHealthOperationException {
        if(damage < 0) throw new InvalidHealthOperationException();
        if(health-damage < 0){
            this.alive = false;
            return false;
        }
        return true;

    }

    public void heal(int healAmount) throws InvalidHealthOperationException {
        if(healAmount < 0) throw new InvalidHealthOperationException();
        health = Math.max(healAmount + health, maxHealth);
    }
}
