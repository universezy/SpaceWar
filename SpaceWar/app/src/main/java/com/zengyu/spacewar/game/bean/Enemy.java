package com.zengyu.spacewar.game.bean;

import com.zengyu.spacewar.game.model.EnemyModel;
import com.zengyu.spacewar.game.manager.RuntimeManager;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Enemy extends AbsBean implements IPlane {
    private static final int RATE = 50;
    @EnemyModel.Type
    private int type;
    @EnemyModel.Bullet
    private int bulletSrc;
    @EnemyModel.BulletVelocity
    private int bulletVelocity;
    private int counter = RATE - 1;

    public Enemy(int x, int y, EnemyModel model) {
        init(x, y, model);
        direction = Direction.DOWNWARD;
    }

    private void init(int x, int y, EnemyModel model) {
        this.x = x;
        this.y = y;
        type = model.getType();
        src = model.getSrc();
        velocityY = model.getVelocity();
        bulletSrc = model.getBulletSrc();
        bulletVelocity = model.getBulletVelocity();
        damage = model.getDamage();
        hp = model.getHp();
        update();
    }

    @Override
    protected void updateCoordinate() {
        y += velocityY;
        // TODO
        if (y > border.y + height) {
            valid = false;
        }
        counter++;
        if (counter % RATE == 0) {
            RuntimeManager.getInstance().createEnemyBullet(this);
            counter = 0;
        }
    }

    public void from(int x, int y, EnemyModel model) {
        init(x, y, model);
    }
}
