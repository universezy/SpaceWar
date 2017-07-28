package com.example.agentzengyu.spacewar.entity.basic.single;

import java.io.Serializable;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 玩家
 */
public class Player implements Serializable {
    private String name = "";
    private int money = 0;
    private int image = 0;
    private int crash = 0;

    private Player(){

    }

    public Player(String name, int money,int image,int crash) {
        this.name = name;
        this.money = money;
        this.image = image;
        this.crash = crash;
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
}
