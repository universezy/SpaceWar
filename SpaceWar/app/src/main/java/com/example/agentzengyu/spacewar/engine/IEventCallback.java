package com.example.agentzengyu.spacewar.engine;

/**
 * Created by Agent ZengYu on 2017/7/4.
 */

import com.example.agentzengyu.spacewar.entity.single.Bullet;

import java.util.List;

/**
 * 游戏内容回调
 */
public interface IEventCallback {
    /**
     * 更新地图位置
     */
    void updateMapLocation();

    /**
     * 更新敌人位置
     */
    void updateEnemyLocation();

    /**
     * 更新敌人子弹位置
     *
     * @param bullets
     */
    void updateEnemyBullets(List<Bullet> bullets);

    /**
     * 更新玩家位置
     *
     * @param x
     * @param y
     */
    void updatePlayerLocation(float x, float y);

    /**
     * 更新玩家子弹位置
     *
     * @param bullets
     */
    void updatePlayerBullets(List<Bullet> bullets);

    /**
     * 设置护盾
     * @param open 状态
     * @param cold 冷却
     */
    void setShield(boolean open, int cold);

    /**
     * 设置炸弹
     *
     * @param start 状态
     * @param cold 冷却
     */
    void setLaser(boolean start, int cold);

    /**
     * 摧毁玩家
     */
    void destroyPlayer();
}
