package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/7/4.
 */

/**
 * 事件接口
 */
public interface IEvent {
    /**
     * 更新地图位置
     */
    void updateLevelCoord();

    /**
     * 更新敌人位置
     */
    void updateEnemyCoord();

    /**
     * 更新玩家位置
     *
     * @param x
     * @param y
     */
    void updatePlayerCoord(float x, float y);

    /**
     * 更新玩家子弹位置
     *
     */
    void updatePlayerBullets();

    /**
     * 更新敌人子弹位置
     *
     */
    void updateEnemyBullets();

    /**
     * 设置护盾
     */
    void setShield();

    /**
     * 设置炸弹
     */
    void setLaser();

    /**
     * 摧毁玩家
     */
    void destroyPlayer();
}
