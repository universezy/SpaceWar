package com.example.agentzengyu.spacewar.entity;

import java.util.ArrayList;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 基础数据类
 */
public class BasicData {
    /********** Spaceship **********/
    private ArrayList<SpaceshipLife> lifes = new ArrayList<>();
    private ArrayList<SpaceshipDefense> defenses = new ArrayList<>();
    private ArrayList<SpaceshipAgility> agilities = new ArrayList<>();
    private ArrayList<SpaceshipShield> shields = new ArrayList<>();

    /********** Weapon **********/
    private ArrayList<WeaponPower> powers = new ArrayList<>();
    private ArrayList<WeaponSpeed> speeds = new ArrayList<>();
    private ArrayList<WeaponRange> ranges = new ArrayList<>();
    private ArrayList<WeaponNuclear> nuclears = new ArrayList<>();

    /********** Spaceship **********/
    public ArrayList<SpaceshipLife> getLifes() {
        return lifes;
    }

    public ArrayList<SpaceshipDefense> getDefenses() {
        return defenses;
    }

    public ArrayList<SpaceshipAgility> getAgilities() {
        return agilities;
    }

    public ArrayList<SpaceshipShield> getShields() {
        return shields;
    }

    /********** Weapon **********/
    public ArrayList<WeaponPower> getPowers() {
        return powers;
    }

    public ArrayList<WeaponSpeed> getSpeeds() {
        return speeds;
    }

    public ArrayList<WeaponRange> getRanges() {
        return ranges;
    }

    public ArrayList<WeaponNuclear> getNuclears() {
        return nuclears;
    }
}
