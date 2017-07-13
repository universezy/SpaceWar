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
    private int life = 0;
    private int defense = 0;
    private int agility = 0;
    private int power = 0;
    private int speed = 0;
    private int range = 0;
    private String image = "";

    public EnemyItem() {

    }

    public void setName(String name) {
        this.name = name;
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

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
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

    public String getImage() {
        return image;
    }
}
