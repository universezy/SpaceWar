package com.zengyu.spacewar.game.bean;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.zengyu.spacewar.game.model.PlayerModel;
import com.zengyu.spacewar.game.manager.BitmapManager;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Player extends AbsBean implements IPlane {
    private static final float RATIO_ACCELERATE_RATE = 1.4F;
    private static final int MAX_VELOCITY = 15;
    @PlayerModel.Shield
    private int shieldSrc;
    @PlayerModel.Nuclear
    private int nuclearSrc;
    @PlayerModel.Bullet
    private int bulletSrc;
    @PlayerModel.BulletVelocity
    private int bulletVelocity;
    private boolean useShield;
    private boolean useLaser;
    private Bitmap shieldBitmap;

    public Player(PlayerModel model) {
        src = model.getSrc();
        shieldSrc = model.getShieldSrc();
        nuclearSrc = model.getNuclearSrc();
        bulletSrc = model.getBulletSrc();
        bulletVelocity = model.getBulletVelocity();
        hp = model.getHp();
        damage = model.getDamage();
    }

    @Override
    public void init(Point border) {
        super.init(border);
        x = border.x / 2;
        y = border.y - height / 2;
        shieldBitmap = BitmapManager.getInstance().getSrc(shieldSrc);
    }

    @Override
    protected void updateCoordinate() {
        if (x > border.x) {
            x = border.x;
        } else if (x < 0) {
            x = 0;
        } else {
            x += velocityX;
            if (x < 0) {
                x = 0;
            } else if (x > border.x) {
                x = border.x;
            }
        }
        if (y > border.y) {
            y = border.y;
        } else if (y < 0) {
            y = 0;
        } else {
            y += velocityY;
            if (y < 0) {
                y = 0;
            } else if (y > border.y) {
                y = border.y;
            }
        }
    }

    @Override
    public Bitmap getBitmap() {
        return useShield ? shieldBitmap : bitmap;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // TODO
    }

    public void updateVelocity(float acceleratedX, float acceleratedY) {
        if (Float.compare(velocityX, -MAX_VELOCITY) < 0) {
            velocityX = -MAX_VELOCITY;
        } else if (Float.compare(velocityX, MAX_VELOCITY) > 0) {
            velocityX = MAX_VELOCITY;
        } else {
            velocityX += acceleratedX * RATIO_ACCELERATE_RATE;
        }
        if (Float.compare(velocityY, -MAX_VELOCITY) < 0) {
            velocityY = -MAX_VELOCITY;
        } else if (Float.compare(velocityY, MAX_VELOCITY) > 0) {
            velocityY = MAX_VELOCITY;
        } else {
            velocityY += acceleratedY * RATIO_ACCELERATE_RATE;
        }
    }

    public void setShieldState(boolean useShield) {
        this.useShield = useShield;
    }

    public void setLaserState(boolean useLaser) {
        this.useLaser = useLaser;
    }
}
