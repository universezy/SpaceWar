package com.example.agentzengyu.spacewar.entity.basic.single;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

import java.io.Serializable;

/**
 * 商品
 */
public class Article implements Serializable {
    private String name = "";
    private int image = 0;
    private int value = 0;
    private int grade = 0;
    private int price = 0;

    private Article() {
    }

    public Article(String name, int image, int value, int grade, int price) {
        this.name = name;
        this.image = image;
        this.value = value;
        this.grade = grade;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getValue() {
        return value;
    }

    public int getGrade() {
        return grade;
    }

    public int getPrice() {
        return price;
    }
}
