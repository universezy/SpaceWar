package com.example.agentzengyu.spacewar.entity;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

import java.io.Serializable;

/**
 * 所有商店item的抽象父类，禁止被实例化
 */
abstract class ShopItem implements Serializable{
    public String Name = "";
    public String Detail = "";
    public int Level = 0;
    public int Fee = 0;
    public int Image = 0;

    public abstract void setName(String name);

    public abstract void setDetail(String detail);

    public abstract void setLevel(int level);

    public abstract void setFee(int fee);

    public abstract void setImage(int image);

    public abstract String getName();

    public abstract String getDetail();

    public abstract int getLevel();

    public abstract int getFee();

    public abstract int getImage();
}
