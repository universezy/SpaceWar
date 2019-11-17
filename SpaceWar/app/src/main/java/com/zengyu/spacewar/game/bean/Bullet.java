package com.zengyu.spacewar.game.bean;

public class Bullet extends AbsBean {

    public Bullet(IPlane plane) {
        init(plane);
    }

    private void init(IPlane plane) {
        x = plane.getX();
        y = plane.getY();
        src = plane.getBulletSrc();
        velocityY = plane.getBulletVelocity();
        damage = plane.getDamage();
        direction = plane.getDirection();
        update();
    }

    @Override
    protected void updateCoordinate() {
        if (direction == Direction.UPWARD) {
            y -= velocityY;
            if (y + height < 0) {
                valid = false;
            }
        } else {
            y += velocityY;
            if (y > border.y + height) {
                valid = false;
            }
        }
    }

    public void from(IPlane plane) {
        init(plane);
    }
}
