package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import java.io.Serializable;

/**
 * 敌人类
 */
public class EnemyItem implements Serializable {
    private String name = "";
    private int image = 0;
    private int life = 0;
    private int defense = 0;
    private int agility = 0;
    private int power = 0;
    private int speed = 0;
    private int range = 0;

    private EnemyItem() {

    }

    public EnemyItem(String name, int image, int life, int defense, int agility, int power, int speed, int range) {
        this.name = name;
        this.image = image;
        this.life = life;
        this.defense = defense;
        this.agility = agility;
        this.power = power;
        this.speed = speed;
        this.range = range;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
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

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
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
