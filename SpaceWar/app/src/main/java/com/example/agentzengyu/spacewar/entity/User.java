package com.example.agentzengyu.spacewar.entity;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 玩家对象
 */
public class User {
    /********** Spaceship **********/
    private SpaceshipLife life = null;
    private SpaceshipDefense defense = null;
    private SpaceshipAgility agility = null;
    private SpaceshipShield shield = null;

    /********** Weapon **********/
    private WeaponPower power = null;
    private WeaponSpeed speed = null;
    private WeaponRange range = null;
    private WeaponNuclear nuclear = null;

    /********** Spaceship **********/
    public void setLife(SpaceshipLife life) {
        this.life = life;
    }

    public void setDefense(SpaceshipDefense defense) {
        this.defense = defense;
    }

    public void setAgility(SpaceshipAgility agility) {
        this.agility = agility;
    }

    public void setShield(SpaceshipShield shield) {
        this.shield = shield;
    }

    public SpaceshipLife getLife() {
        return life;
    }

    public SpaceshipDefense getDefense() {
        return defense;
    }

    public SpaceshipAgility getAgility() {
        return agility;
    }

    public SpaceshipShield getShield() {
        return shield;
    }

    /********** Weapon **********/
    public void setPower(WeaponPower power) {
        this.power = power;
    }

    public void setSpeed(WeaponSpeed speed) {
        this.speed = speed;
    }

    public void setRange(WeaponRange range) {
        this.range = range;
    }

    public void setNuclear(WeaponNuclear nuclear) {
        this.nuclear = nuclear;
    }

    public WeaponPower getPower() {
        return power;
    }

    public WeaponSpeed getSpeed() {
        return speed;
    }

    public WeaponRange getRange() {
        return range;
    }

    public WeaponNuclear getNuclear() {
        return nuclear;
    }
}
