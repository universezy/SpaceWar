package com.example.agentzengyu.spacewar.entity.single;

import java.io.Serializable;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 玩家
 */
public class Player implements Serializable {
    //玩家名
    private String name = "";
    //金币
    private int money = 0;
    //飞船图片
    private int image = 0;
    //飞船坠毁图片
    private int crash = 0;
    //子弹图片
    private int bullet = 0;

    private Player() {

    }

    public Player(String name, int money, int image, int crash, int bullet) {
        this.name = name;
        this.money = money;
        this.image = image;
        this.crash = crash;
        this.bullet = bullet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setCrash(int crash) {
        this.crash = crash;
    }

    public int getCrash() {
        return crash;
    }

    public void setBullet(int bullet) {
        this.bullet = bullet;
    }

    public int getBullet() {
        return bullet;
    }
}
