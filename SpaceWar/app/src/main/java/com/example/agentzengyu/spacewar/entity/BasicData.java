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
    private ArrayList<ShopItem> lifes = new ArrayList<>();
    private ArrayList<ShopItem> defenses = new ArrayList<>();
    private ArrayList<ShopItem> agilities = new ArrayList<>();
    private ArrayList<ShopItem> shields = new ArrayList<>();

    /********** Weapon **********/
    private ArrayList<ShopItem> powers = new ArrayList<>();
    private ArrayList<ShopItem> speeds = new ArrayList<>();
    private ArrayList<ShopItem> ranges = new ArrayList<>();
    private ArrayList<ShopItem> nuclears = new ArrayList<>();

    /********** Spaceship **********/
    public ArrayList<ShopItem> getLifes() {
        return lifes;
    }

    public ArrayList<ShopItem> getDefenses() {
        return defenses;
    }

    public ArrayList<ShopItem> getAgilities() {
        return agilities;
    }

    public ArrayList<ShopItem> getShields() {
        return shields;
    }

    /********** Weapon **********/
    public ArrayList<ShopItem> getPowers() {
        return powers;
    }

    public ArrayList<ShopItem> getSpeeds() {
        return speeds;
    }

    public ArrayList<ShopItem> getRanges() {
        return ranges;
    }

    public ArrayList<ShopItem> getNuclears() {
        return nuclears;
    }

}
