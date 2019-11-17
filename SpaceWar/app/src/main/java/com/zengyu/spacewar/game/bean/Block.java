package com.zengyu.spacewar.game.bean;

import android.graphics.Point;

import com.zengyu.spacewar.game.model.BlockModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Block extends AbsModelBean<BlockModel> {
    private static final int VELOCITY = 1;

    public Block(Point border) {
        super(border);
    }

    @Override
    public void init(int x, int y, BlockModel model) {
        super.init(x, y, model);
        velocityY = model.getVelocity();
        hp = model.getHp();
        direction = Direction.DOWNWARD;
    }

    @Override
    protected boolean updateCoordinate() {
        if (!super.updateCoordinate()) return false;
        y += velocityY;
        if (y > border.y + height) {
            visible = false;
        }
        return true;
    }
}
