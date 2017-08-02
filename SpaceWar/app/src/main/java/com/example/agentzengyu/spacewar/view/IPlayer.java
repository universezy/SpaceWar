package com.example.agentzengyu.spacewar.view;

/**
 * Created by Agent ZengYu on 2017/7/4.
 */

/**
 * 玩家接口
 */
public interface IPlayer {
    /**
     * 射击敌人
     */
    void shotEnemy();

    /**
     * 开启护盾
     */
    void openShield();

    /**
     * 发射激光
     */
    void launchLaser();
}
