package com.example.agentzengyu.spacewar.entity.set;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

import com.example.agentzengyu.spacewar.entity.single.ShopItem;
import com.example.agentzengyu.spacewar.entity.single.UserInfo;

import java.io.Serializable;

/**
 * 玩家数据
 */
public class PlayerData implements Serializable {
    /********** Ship **********/
    private ShopItem life = null;
    private ShopItem defense = null;
    private ShopItem agility = null;
    private ShopItem shield = null;

    /********** Weapon **********/
    private ShopItem power = null;
    private ShopItem speed = null;
    private ShopItem range = null;
    private ShopItem laser = null;

    /********** UserInfo **********/
    private UserInfo info = null;

    public PlayerData() {

    }

    /********** Ship **********/
    public boolean setLife(ShopItem life) {
        if (life == null) {
            return false;
        } else {
            this.life = life;
            return true;
        }
    }

    public boolean setDefense(ShopItem defense) {
        if (defense == null) {
            return false;
        } else {
            this.defense = defense;
            return true;
        }
    }

    public boolean setAgility(ShopItem agility) {
        if (agility == null) {
            return false;
        } else {
            this.agility = agility;
            return true;
        }
    }

    public boolean setShield(ShopItem shield) {
        if (shield == null) {
            return false;
        } else {
            this.shield = shield;
            return true;
        }
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
    public boolean setPower(ShopItem power) {
        if (power == null) {
            return false;
        } else {
            this.power = power;
            return true;
        }
    }

    public boolean setSpeed(ShopItem speed) {
        if (speed == null) {
            return false;
        } else {
            this.speed = speed;
            return true;
        }
    }

    public boolean setRange(ShopItem range) {
        if (range == null) {
            return false;
        } else {
            this.range = range;
            return true;
        }
    }

    public boolean setLaser(ShopItem laser) {
        if (laser == null) {
            return false;
        } else {
            this.laser = laser;
            return true;
        }
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

    public ShopItem getLaser() {
        return laser;
    }

    /********** UserInfo **********/
    public boolean setInfo(UserInfo info) {
        if (info == null) {
            return false;
        } else {
            this.info = info;
            return true;
        }
    }

    public UserInfo getInfo() {
        return info;
    }
}
