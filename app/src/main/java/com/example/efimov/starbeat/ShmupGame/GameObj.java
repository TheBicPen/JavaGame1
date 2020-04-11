package com.example.efimov.starbeat.ShmupGame;

public class GameObj {

    private PlayerEntity player;
    private EnemyList enemyList;

    public GameObj(PlayerEntity player, EnemyList enemyList){
        this.player = player;
        this.enemyList = enemyList;
    }
}
