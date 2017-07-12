package com.example.agentzengyu.spacewar.entity.set;

import com.example.agentzengyu.spacewar.entity.single.ShopItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 商品库
 */
public class ShopLibrary extends AbstractLibrary implements Serializable{
    /********** Spaceship initialize **********/
    private List<ShopItem> lives = new ArrayList<>();
    private List<ShopItem> defenses = new ArrayList<>();
    private List<ShopItem> agilities = new ArrayList<>();
    private List<ShopItem> shields = new ArrayList<>();

    /********** Weapon initialize **********/
    private List<ShopItem> powers = new ArrayList<>();
    private List<ShopItem> speeds = new ArrayList<>();
    private List<ShopItem> ranges = new ArrayList<>();
    private List<ShopItem> lasers = new ArrayList<>();

    /********** Spaceship getter **********/
    public List<ShopItem> getLives() {
        return lives;
    }

    public List<ShopItem> getDefenses() {
        return defenses;
    }

    public List<ShopItem> getAgilities() {
        return agilities;
    }

    public List<ShopItem> getShields() {
        return shields;
    }

    /********** Weapon getter **********/
    public List<ShopItem> getPowers() {
        return powers;
    }

    public List<ShopItem> getSpeeds() {
        return speeds;
    }

    public List<ShopItem> getRanges() {
        return ranges;
    }

    public List<ShopItem> getLasers() {
        return lasers;
    }

}
