package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

import java.io.Serializable;

/**
 * 商品类
 */
public class ShopItem implements Serializable{
    private String name = "";
    private int value = 0;
    private int level = 0;
    private int price = 0;
    private String image = "";

    public ShopItem(){
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(String  image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getLevel() {
        return level;
    }

    public int getPrice() {
        return price;
    }

    public String  getImage() {
        return image;
    }
}
