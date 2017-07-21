package com.example.agentzengyu.spacewar.entity.single;

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
    private String mapName = "";
    private int image = 0;
    private int music = 0;
    private String bossName = "";
    private Enemy boss = null;
    private List<Enemy> enemys = new ArrayList<>();

    private Level() {

    }

    public Level(String mapName, int image, int music, String bossName) {
        this.mapName = mapName;
        this.image = image;
        this.music = music;
        this.bossName = bossName;
    }

    public void setBoss(Enemy boss) {
        this.boss = boss;
    }

    public void setEnemys(List<Enemy> enemys) {
        this.enemys = enemys;
    }

    public String getMapName() {
        return mapName;
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

    public List<Enemy> getEnemys() {
        return enemys;
    }
}
