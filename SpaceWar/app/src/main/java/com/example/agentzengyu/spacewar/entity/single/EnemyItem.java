package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import java.io.Serializable;

/**
 * 敌人类
 */
public class EnemyItem implements Serializable {
    private int imageResId = 0;
    private boolean isBoss = false;
    private int life = 0;
    private int defense = 0;
    private int agility = 0;
    private int power = 0;
    private int speed = 0;
    private int range = 0;

    public EnemyItem() {

    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setBoss(boolean isBoss) {
        this.isBoss = isBoss;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getImageResId() {
        return imageResId;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public int getLife() {
        return life;
    }

    public int getDefense() {
        return defense;
    }

    public int getAgility() {
        return agility;
    }

    public int getPower() {
        return power;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRange() {
        return range;
    }
}
