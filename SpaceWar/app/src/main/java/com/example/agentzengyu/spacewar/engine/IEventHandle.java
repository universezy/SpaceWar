package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/7/4.
 */

/**
 * 游戏操作接口
 */
public interface IEventHandle {
    /**
     * 射击敌人
     */
    void shotEnemy();

    /**
     * 开启护盾
     */
    void openShield();

    /**
     * 发射炸弹
     */
    void launchBomb();
}
