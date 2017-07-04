package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/7/4.
 */

public interface IGameToDo {
    /**
     * 射击敌人
     */
    void shotEnemy();

    /**
     * 开启护盾
     */
    void openShield();

    /**
     * 开启护盾
     */
    void launchBomb();
}
