package com.example.agentzengyu.spacewar.entity;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 玩家对象
 */
public class PlayerData {
    /********** Spaceship **********/
    private ShopItem life = null;
    private ShopItem defense = null;
    private ShopItem agility = null;
    private ShopItem shield = null;

    /********** Weapon **********/
    private ShopItem power = null;
    private ShopItem speed = null;
    private ShopItem range = null;
    private ShopItem bomb = null;

    private int money = 0;

    /********** Spaceship **********/
    public void setLife(ShopItem life) {
        this.life = life;
    }

    public void setDefense(ShopItem defense) {
        this.defense = defense;
    }

    public void setAgility(ShopItem agility) {
        this.agility = agility;
    }

    public void setShield(ShopItem shield) {
        this.shield = shield;
    }

    public ShopItem getLife() {
        return life;
    }

    public ShopItem getDefense() {
        return defense;
    }

    public ShopItem getAgility() {
        return agility;
    }

    public ShopItem getShield() {
        return shield;
    }

    /********** Weapon **********/
    public void setPower(ShopItem power) {
        this.power = power;
    }

    public void setSpeed(ShopItem speed) {
        this.speed = speed;
    }

    public void setRange(ShopItem range) {
        this.range = range;
    }

    public void setBomb(ShopItem bomb) {
        this.bomb = bomb;
    }

    public ShopItem getPower() {
        return power;
    }

    public ShopItem getSpeed() {
        return speed;
    }

    public ShopItem getRange() {
        return range;
    }

    public ShopItem getBomb() {
        return bomb;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
