package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import java.io.Serializable;

/**
 * 地图类
 */
public class MapItem implements Serializable {
    private String mapName = "";
    private int image = 0;
    private int music = 0;
    private String bossName = "";

    private String name1 = "";
    private int count1 = 0;
    private String name2 = "";
    private int count2 = 0;
    private String name3 = "";
    private int count3 = 0;
    private String name4 = "";
    private int count4 = 0;
    private String name5 = "";
    private int count5 = 0;
    private String name6 = "";
    private int count6 = 0;
    private String name7 = "";
    private int count7 = 0;
    private String name8 = "";
    private int count8 = 0;
    private String name9 = "";
    private int count9 = 0;
    private String name10 = "";
    private int count10 = 0;

    public MapItem() {

    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public void setNormal1(String normalName1, int normalCount1) {
        this.name1 = normalName1;
        this.count1 = normalCount1;
    }

    public void setNormal2(String normalName2, int normalCount2) {
        this.name2 = normalName2;
        this.count2 = normalCount2;
    }

    public void setNormal3(String normalName3, int normalCount3) {
        this.name3 = normalName3;
        this.count3 = normalCount3;
    }

    public void setNormal4(String normalName4, int normalCount4) {
        this.name4 = normalName4;
        this.count4 = normalCount4;
    }

    public void setNormal5(String normalName5, int normalCount5) {
        this.name5 = normalName5;
        this.count5 = normalCount5;
    }

    public void setNormal6(String normalName6, int normalCount6) {
        this.name6 = normalName6;
        this.count6 = normalCount6;
    }

    public void setNormal7(String normalName7, int normalCount7) {
        this.name7 = normalName7;
        this.count7 = normalCount7;
    }

    public void setNormal8(String normalName8, int normalCount8) {
        this.name8 = normalName8;
        this.count8 = normalCount8;
    }

    public void setNormal9(String normalName9, int normalCount9) {
        this.name9 = normalName9;
        this.count9 = normalCount9;
    }

    public void setNormal10(String normalName10, int normalCount10) {
        this.name10 = normalName10;
        this.count10 = normalCount10;
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

    public String getName1() {
        return name1;
    }

    public int getCount1() {
        return count1;
    }

    public String getName2() {
        return name2;
    }

    public int getCount2() {
        return count2;
    }

    public String getName3() {
        return name3;
    }

    public int getCount3() {
        return count3;
    }

    public String getName4() {
        return name4;
    }

    public int getCount4() {
        return count4;
    }

    public String getName5() {
        return name5;
    }

    public int getCount5() {
        return count5;
    }

    public String getName6() {
        return name6;
    }

    public int getCount6() {
        return count6;
    }

    public String getName7() {
        return name7;
    }

    public int getCount7() {
        return count7;
    }

    public String getName8() {
        return name8;
    }

    public int getCount8() {
        return count8;
    }

    public String getName9() {
        return name9;
    }

    public int getCount9() {
        return count9;
    }

    public String getName10() {
        return name10;
    }

    public int getCount10() {
        return count10;
    }
}
