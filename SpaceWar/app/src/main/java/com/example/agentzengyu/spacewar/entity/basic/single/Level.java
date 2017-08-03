package com.example.agentzengyu.spacewar.entity.basic.single;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 地图
 */
public class Level implements Serializable {
    private String levelName = "";
    private int image = 0;
    private int music = 0;
    private String bossName = "";
    private Enemy boss = null;
    private List<Enemy> enemies = new ArrayList<>();

    private Level() {

    }

    public Level(String levelName, int image, int music, String bossName) {
        this.levelName = levelName;
        this.image = image;
        this.music = music;
        this.bossName = bossName;
    }

    public void setBoss(Enemy boss) {
        this.boss = boss;
    }

    public String getLevelName() {
        return levelName;
    }

    public int getImage() {
        return image;
    }

    public int getMusic() {
        return music;
    }

    public String getBossName() {
        return bossName;
    }

    public Enemy getBoss() {
        return boss;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
