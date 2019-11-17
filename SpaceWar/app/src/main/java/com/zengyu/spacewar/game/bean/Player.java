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
public class Player extends AbsEntityBean<PlayerModel> implements IPlane, Destructible, IPlayer {
    private static final float RATIO_ACCELERATE_RATE = 1.4F;
    private static final int MAX_VELOCITY = 15;
    @PlayerModel.ShieldSrc
    private int shieldSrc;
    @PlayerModel.NuclearSrc
    private int nuclearSrc;
    @PlayerModel.BulletSrc
    private int bulletSrc;
    @PlayerModel.BulletVelocity
    private int bulletVelocity;
    private boolean useShield;
    private boolean useLaser;
    private Bitmap shieldBitmap;

    public Player(Point border) {
        super(border);
        x = border.x / 2;
        y = border.y - height / 2;
    }

    @Override
    public void init(PlayerModel model) {
        src = model.getSrc();
        shieldSrc = model.getShieldSrc();
        nuclearSrc = model.getNuclearSrc();
        bulletSrc = model.getBulletSrc();
        bulletVelocity = model.getBulletVelocity();
        hp = model.getHp();
        damage = model.getDamage();
        shieldBitmap = BitmapManager.getInstance().getSrc(shieldSrc);
        initGraphic();
    }

    @Override
    protected boolean updateCoordinate() {
        if (!super.updateCoordinate()) return false;
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
        return true;
    }

    @Override
    public Bitmap getBitmap() {
        return useShield ? shieldBitmap : bitmap;
    }

    @Override
    public boolean draw(Canvas canvas) {
        if (!super.draw(canvas)) return false;
        // TODO
        return true;
    }

    @Override
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

    @Override
    public void decreaseHp(int damage) {
        if (useShield) return;
        hp -= damage;
//        if (hp <= 0) {
//            alive = false;
//        }
    }

    @Override
    public void setShieldState(boolean useShield) {
        this.useShield = useShield;
    }

    @Override
    public void setLaserState(boolean useLaser) {
        this.useLaser = useLaser;
    }
}
