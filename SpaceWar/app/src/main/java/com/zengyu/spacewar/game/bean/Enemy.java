package com.zengyu.spacewar.game.bean;

import android.graphics.Point;

import com.zengyu.spacewar.game.model.EnemyModel;
import com.zengyu.spacewar.game.manager.RuntimeManager;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Enemy extends AbsModelBean<EnemyModel> implements IPlane {
    private static final int RATE = 50;
    @EnemyModel.Type
    private int type;
    @EnemyModel.BulletSrc
    private int bulletSrc;
    @EnemyModel.BulletVelocity
    private int bulletVelocity;
    private int counter = RATE - 1;

    public Enemy(Point border) {
        super(border);
    }

    @Override
    public void init(int x, int y, EnemyModel model) {
        super.init(x, y, model);
        type = model.getType();
        velocityY = model.getVelocity();
        bulletSrc = model.getBulletSrc();
        bulletVelocity = model.getBulletVelocity();
        damage = model.getDamage();
        hp = model.getHp();
        direction = Direction.DOWNWARD;
    }

    @Override
    protected boolean updateCoordinate() {
        if (!super.updateCoordinate()) return false;
        y += velocityY;
        // TODO
        if (y > border.y + height) {
            visible = false;
        }
        counter++;
        if (counter % RATE == 0) {
            RuntimeManager.getInstance().createEnemyBullet(this);
            counter = 0;
        }
        return true;
    }
}
