package com.example.agentzengyu.spacewar.game;

import android.content.res.Resources;

import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.Enemy;

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
    private final static float BULLET_LIFE = 10000;
    private final static float BULLET_DEFENSE = 10000;

    private GameComponentFactory() {
    }

    public GameComponentFactory(float screenWidth, float screenHeight, Resources resources, PlayerData data) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.resources = resources;
        this.data = data;
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
        ship.coordX = screenWidth / 2;
        ship.coordY = screenHeight;
        return ship;
    }

    /**
     * 创建敌人飞船
     *
     * @param enemy
     * @return
     */
    public EnemyShip createEnemyShip(Enemy enemy) {
        EnemyShip ship = new EnemyShip(resources, enemy.getImage(), enemy.getCrash(), enemy.getBullet(), enemy.getSpeed());
        ship.setParams(enemy.getLife(), enemy.getDefense(), enemy.getPower(), enemy.getVelocity());
        ship.setScreenSize(screenWidth, screenHeight);
        ship.coordX = (float) (screenWidth / 2 + screenWidth / 2 * Math.sin(new Random().nextInt(1000)));
        ship.coordY = (float) (-1500.0f + 1300 * Math.cos(new Random().nextInt(1000)));
        ship.setAccelerated(0.1f, 0.01f);
        return ship;
    }

    /**
     * 创建boss飞船
     *
     * @param boss
     * @return
     */
    public EnemyShip createBossShip(Enemy boss) {
        EnemyShip ship = new EnemyShip(resources, boss.getImage(), boss.getCrash(), boss.getBullet(), boss.getSpeed());
        ship.setParams(boss.getLife(), boss.getDefense(), boss.getPower(), boss.getVelocity());
        ship.setScreenSize(screenWidth, screenHeight);
        ship.isBoss = true;
        ship.coordX = screenWidth / 2;
        ship.coordY = -1300.0f;
        ship.setAccelerated(0.1f, 0.01f);
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
        bullet.setParams(BULLET_LIFE, BULLET_DEFENSE, data.getPower().getValue(), data.getSpeed().getValue());
        bullet.setScreenSize(screenWidth, screenHeight);
        bullet.coordX = X;
        bullet.coordY = Y;
        return bullet;
    }

    /**
     * 创建敌人子弹
     *
     * @param objectResId
     * @param X
     * @param Y
     * @param power
     * @param speed
     * @return
     */
    public EnemyBullet createEnemyBullet(int objectResId, float X, float Y, float power, float speed) {
        EnemyBullet bullet = new EnemyBullet(resources, objectResId, 0);
        bullet.setParams(BULLET_LIFE, BULLET_DEFENSE, power, speed);
        bullet.setScreenSize(screenWidth, screenHeight);
        bullet.coordX = X;
        bullet.coordY = Y;
        return bullet;
    }
}
