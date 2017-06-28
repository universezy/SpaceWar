package com.example.agentzengyu.spacewar.entity;

import java.util.ArrayList;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 商店数据类
 */
public class ShopData {
    /********** Spaceship initialize **********/
    private ArrayList<ShopItem> lives = new ArrayList<>();
    private ArrayList<ShopItem> defenses = new ArrayList<>();
    private ArrayList<ShopItem> agilities = new ArrayList<>();
    private ArrayList<ShopItem> shields = new ArrayList<>();

    /********** Weapon initialize **********/
    private ArrayList<ShopItem> powers = new ArrayList<>();
    private ArrayList<ShopItem> speeds = new ArrayList<>();
    private ArrayList<ShopItem> ranges = new ArrayList<>();
    private ArrayList<ShopItem> bombs = new ArrayList<>();

    /********** Spaceship getter **********/
    public ArrayList<ShopItem> getLives() {
        return lives;
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

    /********** Weapon getter **********/
    public ArrayList<ShopItem> getPowers() {
        return powers;
    }

    public ArrayList<ShopItem> getSpeeds() {
        return speeds;
    }

    public ArrayList<ShopItem> getRanges() {
        return ranges;
    }

    public ArrayList<ShopItem> getBombs() {
        return bombs;
    }

}
