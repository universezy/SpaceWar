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

    public ShopLibrary() {

    }

    public ShopLibrary(boolean init) {
        if (!init) new ShopLibrary();
        lives = new ArrayList<>();
        defenses = new ArrayList<>();
        agilities = new ArrayList<>();
        shields = new ArrayList<>();
        powers = new ArrayList<>();
        speeds = new ArrayList<>();
        ranges = new ArrayList<>();
        lasers = new ArrayList<>();
    }

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

    public boolean setLives(List<ShopItem> lives) {
        if (lives == null) {
            return false;
        } else {
            this.lives = lives;
            return true;
        }
    }

    public boolean setDefenses(List<ShopItem> defenses) {
        if (defenses == null) {
            return false;
        } else {
            this.defenses = defenses;
            return true;
        }
    }

    public boolean setAgilities(List<ShopItem> agilities) {
        if (agilities == null) {
            return false;
        } else {
            this.agilities = agilities;
            return true;
        }
    }

    public boolean setShields(List<ShopItem> shields) {
        if (shields == null) {
            return false;
        } else {
            this.shields = shields;
            return true;
        }
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

    public boolean setPowers(List<ShopItem> powers) {
        if (powers == null) {
            return false;
        } else {
            this.powers = powers;
            return true;
        }
    }

    public boolean setSpeeds(List<ShopItem> speeds) {
        if (speeds == null) {
            return false;
        } else {
            this.speeds = speeds;
            return true;
        }
    }

    public boolean setRanges(List<ShopItem> ranges) {
        if (ranges == null) {
            return false;
        } else {
            this.ranges = ranges;
            return true;
        }
    }

    public boolean setLasers(List<ShopItem> lasers) {
        if (lasers == null) {
            return false;
        } else {
            this.lasers = lasers;
            return true;
        }
    }
}
