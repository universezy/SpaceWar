package com.zengyu.spacewar.game.bean;

import android.graphics.Point;

public class Bullet extends AbsEntityBean<IPlane> {

    public Bullet(Point border) {
        super(border);
    }

    @Override
    public void init(IPlane plane) {
        x = plane.getX();
        y = plane.getY();
        src = plane.getBulletSrc();
        velocityY = plane.getBulletVelocity();
        damage = plane.getDamage();
        direction = plane.getDirection();
        initGraphic();
    }

    @Override
    protected boolean updateCoordinate() {
        if (!super.updateCoordinate()) return false;
        if (direction == Direction.UPWARD) {
            y -= velocityY;
            if (y + height < 0) {
                visible = false;
            }
        } else {
            y += velocityY;
            if (y > border.y + height) {
                visible = false;
            }
        }
        return true;
    }
}
