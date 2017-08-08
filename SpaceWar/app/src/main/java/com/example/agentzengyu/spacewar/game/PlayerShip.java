package com.example.agentzengyu.spacewar.game;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/7/28.
 */

/**
 * 玩家飞船
 */
public class PlayerShip extends GameComponent {
    //画笔
    private Paint paintShield, paintLaser;
    //护盾颜色
    private final static int colorShield = Color.parseColor("#881e90ff");
    //激光颜色
    private final static int colorLaser = Color.parseColor("#bbff0000");
    //护盾是否开启
    private boolean shield = false;
    //激光是否开起
    private boolean laser = false;
    //加速度
    private float acceleratedX = 0.0f, acceleratedY = 0.0f;
    //护盾半径
    private float radius = 1.0f;
    //子弹
    private List<PlayerBullet> bullets = new ArrayList<>();
    //敌机
    private List<EnemyShip> enemyShips = null;

    public PlayerShip(Resources resources, int objectResId, int crashResId) {
        super(resources, objectResId, crashResId);

        radius = Math.max(objectWidth, objectHeight) * 2 / 3;

        paintShield = new Paint();
        paintShield.setAntiAlias(true);
        paintShield.setColor(colorShield);

        paintLaser = new Paint();
        paintLaser.setAntiAlias(true);
        paintLaser.setColor(colorLaser);
    }

    @Override
    public void setParams(float life, float defense, float power, float velocity) {
        super.setParams(life, defense, power, velocity);
    }

    @Override
    public void setScreenSize(float screenWidth, float screenHeight) {
        super.setScreenSize(screenWidth, screenHeight);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (!isCrash) {
            if (life > 0) {
                action();
                canvas.save();
                canvas.clipRect(coordX - objectWidth / 2, coordY - objectHeight / 2, coordX + objectWidth / 2, coordY + objectHeight / 2);
                canvas.drawBitmap(objectBitmap, coordX - objectWidth / 2, coordY - objectHeight / 2, paint);
                canvas.restore();

                canvas.save();
                if (shield) {
                    canvas.drawCircle(coordX, coordY, radius, paintShield);
                }
                if (laser) {
                    canvas.drawRect(coordX - objectWidth / 3, 0.0f, coordX + objectWidth / 3, coordY - objectHeight / 2, paintLaser);
                    for (int i = 0; i < enemyShips.size(); i++) {
                        EnemyShip ship = enemyShips.get(i);
                        if (coordX + objectWidth / 3 > ship.coordX - ship.objectWidth / 2 &&
                                coordX - objectWidth / 3 < ship.coordX + ship.objectWidth / 2) {
                            ship.decreaseLife(power * 100 / ship.defense);
                        }
                    }
                }
                canvas.restore();
            } else if (crashTimes > 0) {
                canvas.save();
                canvas.clipRect(coordX - crashWidth / 2, coordY - crashHeight / 2, coordX + crashWidth / 2, coordY + crashHeight / 2);
                canvas.drawBitmap(crashBitmap, coordX - crashWidth / 2, coordY - crashHeight / 2, paint);
                canvas.restore();
                crashTimes--;
            } else if (crashTimes == 0) {
                canvas.save();
                canvas.clipRect(coordX - crashWidth / 2, coordY - crashHeight / 2, coordX + crashWidth / 2, coordY + crashHeight / 2);
                canvas.drawBitmap(crashBitmap, coordX - crashWidth / 2, coordY - crashHeight / 2, paint);
                canvas.restore();
                isCrash = true;
            }
        } else {
            onDestroy();
        }
        for (int i = 0; i < bullets.size(); i++) {
            PlayerBullet bullet = bullets.get(i);
            if (bullet.isCrash) {
                bullets.remove(i);
                bullet.onDestroy();
            } else {
                bullet.onDraw(canvas);
                for (int k = 0; k < enemyShips.size(); k++) {
                    bullet.crash(enemyShips.get(k));
                }
                if (bullet.isOutOfScreen()) {
                    bullets.remove(i);
                    bullet.onDestroy();
                }
            }
        }
        for (int j = 0; j < enemyShips.size(); j++) {
            crash(enemyShips.get(j));
        }
    }

    @Override
    public void onDestroy() {
        if (objectBitmap != null && objectBitmap.isRecycled()) {
            objectBitmap.recycle();
        }
        if (crashBitmap != null && crashBitmap.isRecycled()) {
            crashBitmap.recycle();
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).onDestroy();
        }
    }

    @Override
    public void crash(GameComponent target) {
        if (isCrash || target.isCrash) return;
        if (coordX + objectWidth / 2 > target.coordX - target.objectWidth / 2 &&
                coordX - objectWidth / 2 < target.coordX + target.objectWidth / 2 &&
                coordY + objectHeight / 2 > target.coordY - target.objectHeight / 2 &&
                coordY - objectHeight / 2 < target.coordY + target.objectHeight / 2) {
            if (defense > target.defense)
                target.decreaseLife(defense / target.defense);
        }
    }

    @Override
    public void decreaseLife(float attack) {
        super.decreaseLife(attack);
    }

    @Override
    protected void action() {
        coordX += acceleratedX * velocity;
        if (coordX < 0) {
            coordX = 0;
        } else if (coordX > screenWidth) {
            coordX = screenWidth;
        }
        coordY += acceleratedY * velocity;
        if (coordY < 0) {
            coordY = 0;
        } else if (coordY > screenHeight) {
            coordY = screenHeight;
        }
    }

    @Override
    protected boolean isOutOfScreen() {
        return false;
    }

    /**
     * 获取生命值
     *
     * @return
     */
    public float getLife() {
        if (life < 0) return 0;
        return life;
    }

    /**
     * 设置加速度
     *
     * @param X
     * @param Y
     */
    public void setAccelerated(float X, float Y) {
        acceleratedX = X;
        acceleratedY = Y;
    }

    /**
     * 设置敌机
     *
     * @param ships
     */
    public void setEnemyShips(List<EnemyShip> ships) {
        this.enemyShips = ships;
    }

    /**
     * 射击敌人
     *
     * @param factory
     * @param objectResId
     */
    public void shootEnemy(GameComponentFactory factory, int objectResId) {
        if (isCrash) return;
        bullets.add(factory.createPlayerBullet(objectResId, coordX, coordY));
    }

    /**
     * 开起护盾
     *
     * @param shield
     */
    public void openShield(boolean shield) {
        this.shield = shield;
        if (shield) {
            defense += 300;
        } else {
            defense -= 300;
        }
    }

    /**
     * 发射激光
     *
     * @param laser
     */
    public void openLaser(boolean laser) {
        this.laser = laser;
    }
}
