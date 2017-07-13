package com.example.agentzengyu.spacewar.entity.set;

import com.example.agentzengyu.spacewar.entity.single.ShopItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 商品库
 */
public class ShopLibrary implements Serializable {
    /********** Spaceship **********/
    private List<ShopItem> lives = null;
    private List<ShopItem> defenses = null;
    private List<ShopItem> agilities = null;
    private List<ShopItem> shields = null;

    /********** Weapon **********/
    private List<ShopItem> powers = null;
    private List<ShopItem> speeds = null;
    private List<ShopItem> ranges = null;
    private List<ShopItem> lasers = null;

    /********** Spaceship **********/
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

    public void setLives(List<ShopItem> lives) {
        this.lives = lives;
    }

    public void setDefenses(List<ShopItem> defenses) {
        this.defenses = defenses;
    }

    public void setAgilities(List<ShopItem> agilities) {
        this.agilities = agilities;
    }

    public void setShields(List<ShopItem> shields) {
        this.shields = shields;
    }

    /********** Weapon **********/
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

    public void setPowers(List<ShopItem> powers) {
        this.powers = powers;
    }

    public void setSpeeds(List<ShopItem> speeds) {
        this.speeds = speeds;
    }

    public void setRanges(List<ShopItem> ranges) {
        this.ranges = ranges;
    }

    public void setLasers(List<ShopItem> lasers) {
        this.lasers = lasers;
    }
}
