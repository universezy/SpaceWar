package com.example.agentzengyu.spacewar.entity.basic.set;

import com.example.agentzengyu.spacewar.entity.basic.single.Article;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 商品库
 */
public class ArticleLibrary implements Serializable {
    /********** Spaceship **********/
    private List<Article> lives = null;
    private List<Article> defenses = null;
    private List<Article> velocities = null;
    private List<Article> shields = null;

    /********** Weapon **********/
    private List<Article> powers = null;
    private List<Article> speeds = null;
    private List<Article> ranges = null;
    private List<Article> lasers = null;

    public ArticleLibrary() {

    }

    /********** Spaceship **********/
    public List<Article> getLives() {
        return lives;
    }

    public List<Article> getDefenses() {
        return defenses;
    }

    public List<Article> getVelocities() {
        return velocities;
    }

    public List<Article> getShields() {
        return shields;
    }

    public boolean setLives(List<Article> lives) {
        if (lives == null) {
            return false;
        } else {
            this.lives = lives;
            return true;
        }
    }

    public boolean setDefenses(List<Article> defenses) {
        if (defenses == null) {
            return false;
        } else {
            this.defenses = defenses;
            return true;
        }
    }

    public boolean setAgilities(List<Article> agilities) {
        if (agilities == null) {
            return false;
        } else {
            this.velocities = agilities;
            return true;
        }
    }

    public boolean setShields(List<Article> shields) {
        if (shields == null) {
            return false;
        } else {
            this.shields = shields;
            return true;
        }
    }

    /********** Weapon **********/
    public List<Article> getPowers() {
        return powers;
    }

    public List<Article> getSpeeds() {
        return speeds;
    }

    public List<Article> getRanges() {
        return ranges;
    }

    public List<Article> getLasers() {
        return lasers;
    }

    public boolean setPowers(List<Article> powers) {
        if (powers == null) {
            return false;
        } else {
            this.powers = powers;
            return true;
        }
    }

    public boolean setSpeeds(List<Article> speeds) {
        if (speeds == null) {
            return false;
        } else {
            this.speeds = speeds;
            return true;
        }
    }

    public boolean setRanges(List<Article> ranges) {
        if (ranges == null) {
            return false;
        } else {
            this.ranges = ranges;
            return true;
        }
    }

    public boolean setLasers(List<Article> lasers) {
        if (lasers == null) {
            return false;
        } else {
            this.lasers = lasers;
            return true;
        }
    }
}
