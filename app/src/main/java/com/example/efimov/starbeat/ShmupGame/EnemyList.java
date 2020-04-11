package com.example.efimov.starbeat.ShmupGame;

import java.util.Map;
import java.util.TreeMap;

import Exceptions.TimeAlreadyPassedException;

public class EnemyList {

    private TreeMap<Long, EnemyEntity> EnemyQueue;

    public EnemyList(Map<Long, EnemyEntity> list) {
        this.EnemyQueue = (TreeMap<Long, EnemyEntity>) list;
    }

    public long timeUntilNextEnemy(long time){
        return EnemyQueue.ceilingKey(time);
    }

    public EnemyEntity nextEnemy(long time){
        return EnemyQueue.ceilingEntry(time).getValue();
    }

    public int numberOfEnemiesRemaining(){
        return EnemyQueue.size();
    }

    public EnemyEntity popEnemy(){
        return EnemyQueue.remove(EnemyQueue.firstKey());
    }

    public EnemyEntity popNextEnemy(long time){
        return EnemyQueue.remove(EnemyQueue.ceilingKey(time));
    }

    public void insertEnemy(long currentTime, long insertTime, EnemyEntity enemy) throws TimeAlreadyPassedException {
        if(insertTime < currentTime) throw new TimeAlreadyPassedException();
        EnemyQueue.put(insertTime, enemy);
    }
}
