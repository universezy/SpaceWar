package com.zengyu.spacewar.game.model;

import com.zengyu.spacewar.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerModel {
    @Src
    private int src;
    @Shield
    private int shieldSrc;
    @Bullet
    private int bulletSrc;
    @BulletVelocity
    private int bulletVelocity;
    @Damage
    private int damage;
    @Hp
    private int hp;
    @Nuclear
    private int nuclearSrc;

    public void test() {
        src = Src.PLANE1;
        shieldSrc = Shield.PLANE1_SHIELD;
        bulletSrc = Bullet.BULLET1;
        bulletVelocity = BulletVelocity.VELOCITY1;
        damage = Damage.DAMAGE1;
        hp = Hp.HP1;
        nuclearSrc = Nuclear.NUCLEAR1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Src {
        int PLANE1 = R.drawable.ic_plane1;
        int PLANE2 = R.drawable.ic_plane2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Shield {
        int PLANE1_SHIELD = R.drawable.ic_plane1_shield;
        int PLANE2_SHIELD = R.drawable.ic_plane2_shield;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Nuclear {
        int NUCLEAR1 = R.drawable.ic_nuclear1;
        int NUCLEAR2 = R.drawable.ic_nuclear2;
        int NUCLEAR3 = R.drawable.ic_nuclear3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Bullet {
        int BULLET1 = R.drawable.ic_bullet1;
        int BULLET2 = R.drawable.ic_bullet2;
        int BULLET3 = R.drawable.ic_bullet3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BulletVelocity {
        int VELOCITY1 = 10;
        int VELOCITY2 = 12;
        int VELOCITY3 = 15;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Damage {
        int DAMAGE1 = 20;
        int DAMAGE2 = 40;
        int DAMAGE3 = 50;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Hp {
        int HP1 = 300;
        int HP2 = 500;
        int HP3 = 800;
        int HP4 = 1000;
    }
}
