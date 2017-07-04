package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/7/4.
 */

/**
 * 游戏内容回调
 */
public interface IEventCallback {
    /**
     * 更新地图
     */
    void updateMap();

    /**
     * 更新敌人
     */
    void updateEnemy();

    /**
     * 更新玩家
     *
     * @param x
     * @param y
     */
    void updatePlayer(float x, float y);

    /**
     * 设置护盾
     *
     * @param open
     */
    void setShield(boolean open);

    /**
     * 更新炸弹
     *
     * @param x
     * @param y
     */
    void updateBomb(float x, float y);

    /**
     * 摧毁玩家
     */
    void destroyPlayer();
}
