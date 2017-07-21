package com.example.agentzengyu.spacewar.entity.single;

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

    private Player(){

    }

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
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

}
