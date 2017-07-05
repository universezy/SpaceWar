package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/7/4.
 */

import java.io.Serializable;

/**
 * 子弹
 */
public class Bullet implements Serializable {
    private float X = 0;
    private float Y = 0;
    private float power = 0;
    private float radius = 0;
    private float speed = 0;

    public Bullet(float x, float y, float power,float radius, float speed) {
        this.X = x;
        this.Y = y;
        this.power = power;
        this.radius = radius;
        this.speed = speed;
    }

    public void setX(float x) {
        X = x;
    }

    public void setY(float y) {
        Y = y;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }

    public float getPower() {
        return power;
    }

    public float getRadius() {
        return radius;
    }

    public float getSpeed() {
        return speed;
    }
}
