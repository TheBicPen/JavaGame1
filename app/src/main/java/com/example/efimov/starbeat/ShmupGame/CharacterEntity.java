package com.example.efimov.starbeat.ShmupGame;

import Exceptions.InvalidHealthOperationException;

public class CharacterEntity {
    private int health;
    private int maxHealth;
    private float speed;
    private int angle;
    private float positionX;
    private float positionY;

    protected boolean alive = true;

    public CharacterEntity(int health, int maxHealth, int posX, float posY, float speed, int angle) throws InvalidHealthOperationException {

        if(health < 1 || health > maxHealth) throw new InvalidHealthOperationException();

        this.health = health;
        this.maxHealth = maxHealth;
        this.positionX = posX;
        this.positionY = posY;
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
