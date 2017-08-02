package com.example.agentzengyu.spacewar.entity.game;

import android.content.res.Resources;

import com.example.agentzengyu.spacewar.entity.basic.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.basic.single.Enemy;
import com.example.agentzengyu.spacewar.entity.basic.single.Level;

import java.util.Random;

/**
 * Created by Agent ZengYu on 2017/8/2.
 */

/**
 * 游戏组件工厂
 */
public class GameComponentFactory {
    private float screenWidth, screenHeight;
    private Resources resources;
    private PlayerData data;
    private Level level;

    private GameComponentFactory() {
    }

    public GameComponentFactory(float screenWidth, float screenHeight, Resources resources, PlayerData data, Level level) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.resources = resources;
        this.data = data;
        this.level = level;
    }

    /**
     * 创建玩家飞船
     *
     * @return
     */
    public PlayerShip createPlayerShip() {
        PlayerShip ship = new PlayerShip(resources, data.getPlayer().getImage(), data.getPlayer().getCrash());
        ship.setParams(data.getLife().getValue(), data.getDefense().getValue(), data.getPower().getValue(), data.getVelocity().getValue());
        ship.setScreenSize(screenWidth, screenHeight);
        ship.setCoordX(screenWidth / 2);
        ship.setCoordY(screenHeight);
        return ship;
    }

    /**
     * 创建敌人飞船
     *
     * @return
     */
    public EnemyShip createEnemyShip(Enemy enemy) {
        EnemyShip ship = new EnemyShip(resources, enemy.getImage(), enemy.getCrash());
        ship.setParams(enemy.getLife(), enemy.getDefense(), enemy.getPower(), enemy.getVelocity());
        ship.setScreenSize(screenWidth, screenHeight);
        ship.setCoordX(new Random((long) screenWidth).nextFloat());
        ship.setCoordY(new Random((long) (-20)).nextFloat());
        return ship;
    }

    /**
     * 创建玩家子弹
     *
     * @param objectResId
     * @param X
     * @param Y
     * @return
     */
    public PlayerBullet createPlayerBullet(int objectResId, float X, float Y) {
        PlayerBullet bullet = new PlayerBullet(resources, objectResId, 0);
        bullet.setCoordX(X);
        bullet.setCoordY(Y);
        return bullet;
    }

    /**
     * 创建敌人子弹
     *
     * @param objectResId
     * @param X
     * @param Y
     * @return
     */
    public EnemyBullet createEnemyBullet(int objectResId, float X, float Y) {
        EnemyBullet bullet = new EnemyBullet(resources, objectResId, 0);
        bullet.setCoordX(X);
        bullet.setCoordY(Y);
        return bullet;
    }
}
