package com.example.agentzengyu.spacewar.entity.game;

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
    private Paint paintShield, paintLaser;
    private final static int colorShield = Color.parseColor("#881e90ff");
    private final static int colorLaser = Color.parseColor("#bbff0000");
    private boolean shield = false;
    private boolean laser = false;
    private float acceleratedX = 0.0f, acceleratedY = 0.0f;
    private float radius = 1.0f;
    private List<PlayerBullet> bullets = new ArrayList<>();


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

    public void setAccelerated(float X, float Y) {
        acceleratedX = X;
        acceleratedY = Y;
    }

    @Override
    public void onDraw(Canvas canvas) {
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
        }
        canvas.restore();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public boolean crash(GameComponent target) {
        return false;
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

    public void shotEnemy(GameComponentFactory factory, int objectResId) {
        bullets.add(factory.createPlayerBullet(objectResId, coordX, coordY));
    }

    public void openShield(boolean shield) {
        this.shield = shield;
    }

    public void launchLaser(boolean laser) {
        this.laser = laser;
    }
}
