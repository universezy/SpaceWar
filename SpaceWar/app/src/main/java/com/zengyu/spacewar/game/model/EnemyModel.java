package com.zengyu.spacewar.game.model;

import com.zengyu.spacewar.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnemyModel implements IModel {
    @Type
    private int type;
    @Src
    private int src;
    @Velocity
    private int velocity;
    @BulletSrc
    private int bulletSrc;
    @BulletVelocity
    private int bulletVelocity;
    @Damage
    private int damage;
    @Hp
    private int hp;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
        int NORMAL = 0;
        int BOSS = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Src {
        // Level low
        int ENEMY1 = R.drawable.ic_enemy1;
        int ENEMY2 = R.drawable.ic_enemy2;

        // Level middle
        int ENEMY3 = R.drawable.ic_enemy3;
        int ENEMY4 = R.drawable.ic_enemy4;
        int ENEMY5 = R.drawable.ic_enemy5;
        int ENEMY6 = R.drawable.ic_enemy6;

        // Level high
        int BOSS1 = R.drawable.ic_boss1;
        int BOSS2 = R.drawable.ic_boss2;
        int BOSS3 = R.drawable.ic_boss3;
        int BOSS4 = R.drawable.ic_boss4;
        int BOSS5 = R.drawable.ic_boss5;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Velocity {
        int VELOCITY1 = 1;
        int VELOCITY2 = 2;
        int VELOCITY3 = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BulletSrc {
        int BULLET1 = R.drawable.ic_enemy_bullet1;
        int BULLET2 = R.drawable.ic_enemy_bullet2;
        int BULLET3 = R.drawable.ic_enemy_bullet3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BulletVelocity {
        int VELOCITY1 = 5;
        int VELOCITY2 = 8;
        int VELOCITY3 = 10;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Damage {
        int DAMAGE1 = 10;
        int DAMAGE2 = 20;
        int DAMAGE3 = 30;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Hp {
        int HP1 = 100;
        int HP2 = 300;
        int HP3 = 500;
        int HP4 = 800;
    }
}
