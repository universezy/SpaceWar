package com.example.agentzengyu.spacewar.entity.set;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

import com.example.agentzengyu.spacewar.entity.single.Article;
import com.example.agentzengyu.spacewar.entity.single.UserInfo;

import java.io.Serializable;

/**
 * 玩家数据
 */
public class PlayerData implements Serializable {
    /********** Ship **********/
    private Article life = null;
    private Article defense = null;
    private Article velocity = null;
    private Article shield = null;

    /********** Weapon **********/
    private Article power = null;
    private Article speed = null;
    private Article range = null;
    private Article laser = null;

    /********** UserInfo **********/
    private UserInfo info = null;

    public PlayerData() {

    }

    /********** Ship **********/
    public boolean setLife(Article life) {
        if (life == null) {
            return false;
        } else {
            this.life = life;
            return true;
        }
    }

    public boolean setDefense(Article defense) {
        if (defense == null) {
            return false;
        } else {
            this.defense = defense;
            return true;
        }
    }

    public boolean setVelocity(Article velocity) {
        if (velocity == null) {
            return false;
        } else {
            this.velocity = velocity;
            return true;
        }
    }

    public boolean setShield(Article shield) {
        if (shield == null) {
            return false;
        } else {
            this.shield = shield;
            return true;
        }
    }

    public Article getLife() {
        return life;
    }

    public Article getDefense() {
        return defense;
    }

    public Article getVelocity() {
        return velocity;
    }

    public Article getShield() {
        return shield;
    }

    /********** Weapon **********/
    public boolean setPower(Article power) {
        if (power == null) {
            return false;
        } else {
            this.power = power;
            return true;
        }
    }

    public boolean setSpeed(Article speed) {
        if (speed == null) {
            return false;
        } else {
            this.speed = speed;
            return true;
        }
    }

    public boolean setRange(Article range) {
        if (range == null) {
            return false;
        } else {
            this.range = range;
            return true;
        }
    }

    public boolean setLaser(Article laser) {
        if (laser == null) {
            return false;
        } else {
            this.laser = laser;
            return true;
        }
    }

    public Article getPower() {
        return power;
    }

    public Article getSpeed() {
        return speed;
    }

    public Article getRange() {
        return range;
    }

    public Article getLaser() {
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
