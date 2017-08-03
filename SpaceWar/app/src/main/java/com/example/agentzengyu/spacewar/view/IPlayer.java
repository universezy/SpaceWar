package com.example.agentzengyu.spacewar.view;

/**
 * Created by Agent ZengYu on 2017/7/4.
 */

/**
 * 玩家接口
 */
public interface IPlayer {
    /**
     * 设置加速度
     *
     * @param X
     * @param Y
     */
    void setAccelerated(float X, float Y);

    /**
     * 射击敌人
     */
    void shootEnemy();

    /**
     * 开启护盾
     */
    void openShield();

    /**
     * 发射激光
     */
    void openLaser();
}
