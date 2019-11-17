package com.zengyu.spacewar.game.manager;

import android.util.SparseArray;

import com.zengyu.spacewar.game.model.BlockModel;
import com.zengyu.spacewar.game.model.EnemyModel;
import com.zengyu.spacewar.game.model.PlayerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ModelManager {
    private static final String TAG = "ModelManager";

    private static final int RATIO_LOW = 6;
    private static final int RATIO_MIDDLE = 3;
    private static final int RATIO_HIGH = 1;
    private static final int RATIO_TOTAL = RATIO_LOW + RATIO_MIDDLE + RATIO_HIGH;
    private static final int[] ENEMIES_LOW = new int[]{
            EnemyModel.Src.ENEMY1,
            EnemyModel.Src.ENEMY2
    };
    private static final int[] ENEMIES_MIDDLE = new int[]{
            EnemyModel.Src.ENEMY3,
            EnemyModel.Src.ENEMY4,
            EnemyModel.Src.ENEMY5,
            EnemyModel.Src.ENEMY6
    };
    private static final int[] ENEMIES_HIGH = new int[]{
            EnemyModel.Src.BOSS1,
            EnemyModel.Src.BOSS2,
            EnemyModel.Src.BOSS3,
            EnemyModel.Src.BOSS4,
            EnemyModel.Src.BOSS5
    };
    private static volatile ModelManager sInstance = null;
    private PlayerModel mPlayerModel;
    private final SparseArray<EnemyModel> mEnemyModelMap = new SparseArray<>();
    private final List<BlockModel> mBlockModelList = new ArrayList<>();

    private ModelManager() {
        mPlayerModel = new PlayerModel();
        mPlayerModel.test();
        initEnemyModelMap();
        initBlockModelMap();
    }

    public static ModelManager getInstance() {
        if (sInstance == null) {
            synchronized (ModelManager.class) {
                if (sInstance == null) {
                    sInstance = new ModelManager();
                }
            }
        }
        return sInstance;
    }

    public PlayerModel getPlayerModel() {
        return mPlayerModel;
    }

    public EnemyModel getRandomEnemyModel() {
        int[] target;
        int random = new Random().nextInt(RATIO_TOTAL);
        if (random < RATIO_LOW) {
            target = ENEMIES_LOW;
        } else if (random < RATIO_LOW + RATIO_MIDDLE) {
            target = ENEMIES_MIDDLE;
        } else {
            target = ENEMIES_HIGH;
        }
        int index = new Random().nextInt(target.length);
        return Optional.ofNullable(mEnemyModelMap.get(target[index]))
                .orElse(mEnemyModelMap.valueAt(0));
    }

    public BlockModel getRandomBlockModel() {
        int index = new Random().nextInt(mBlockModelList.size());
        return mBlockModelList.get(index);
    }

    private void initEnemyModelMap() {
        addEnemyModel(new EnemyModel(
                EnemyModel.Type.NORMAL,
                EnemyModel.Src.ENEMY1,
                EnemyModel.Velocity.VELOCITY3,
                EnemyModel.BulletSrc.BULLET1,
                EnemyModel.BulletVelocity.VELOCITY3,
                EnemyModel.Damage.DAMAGE1,
                EnemyModel.Hp.HP1
        ));
        addEnemyModel(new EnemyModel(
                EnemyModel.Type.NORMAL,
                EnemyModel.Src.ENEMY2,
                EnemyModel.Velocity.VELOCITY3,
                EnemyModel.BulletSrc.BULLET1,
                EnemyModel.BulletVelocity.VELOCITY2,
                EnemyModel.Damage.DAMAGE2,
                EnemyModel.Hp.HP2
        ));
        addEnemyModel(new EnemyModel(
                EnemyModel.Type.NORMAL,
                EnemyModel.Src.ENEMY3,
                EnemyModel.Velocity.VELOCITY2,
                EnemyModel.BulletSrc.BULLET2,
                EnemyModel.BulletVelocity.VELOCITY3,
                EnemyModel.Damage.DAMAGE2,
                EnemyModel.Hp.HP3
        ));
        addEnemyModel(new EnemyModel(
                EnemyModel.Type.NORMAL,
                EnemyModel.Src.ENEMY4,
                EnemyModel.Velocity.VELOCITY2,
                EnemyModel.BulletSrc.BULLET2,
                EnemyModel.BulletVelocity.VELOCITY2,
                EnemyModel.Damage.DAMAGE2,
                EnemyModel.Hp.HP3
        ));
        addEnemyModel(new EnemyModel(
                EnemyModel.Type.NORMAL,
                EnemyModel.Src.ENEMY5,
                EnemyModel.Velocity.VELOCITY2,
                EnemyModel.BulletSrc.BULLET2,
                EnemyModel.BulletVelocity.VELOCITY3,
                EnemyModel.Damage.DAMAGE2,
                EnemyModel.Hp.HP3
        ));
        addEnemyModel(new EnemyModel(
                EnemyModel.Type.NORMAL,
                EnemyModel.Src.ENEMY6,
                EnemyModel.Velocity.VELOCITY2,
                EnemyModel.BulletSrc.BULLET2,
                EnemyModel.BulletVelocity.VELOCITY2,
                EnemyModel.Damage.DAMAGE2,
                EnemyModel.Hp.HP3
        ));
        addEnemyModel(new EnemyModel(
                EnemyModel.Type.BOSS,
                EnemyModel.Src.BOSS1,
                EnemyModel.Velocity.VELOCITY1,
                EnemyModel.BulletSrc.BULLET3,
                EnemyModel.BulletVelocity.VELOCITY1,
                EnemyModel.Damage.DAMAGE3,
                EnemyModel.Hp.HP4
        ));
        addEnemyModel(new EnemyModel(
                EnemyModel.Type.BOSS,
                EnemyModel.Src.BOSS2,
                EnemyModel.Velocity.VELOCITY1,
                EnemyModel.BulletSrc.BULLET3,
                EnemyModel.BulletVelocity.VELOCITY1,
                EnemyModel.Damage.DAMAGE3,
                EnemyModel.Hp.HP4
        ));
        addEnemyModel(new EnemyModel(
                EnemyModel.Type.BOSS,
                EnemyModel.Src.BOSS3,
                EnemyModel.Velocity.VELOCITY1,
                EnemyModel.BulletSrc.BULLET3,
                EnemyModel.BulletVelocity.VELOCITY1,
                EnemyModel.Damage.DAMAGE3,
                EnemyModel.Hp.HP4
        ));
        addEnemyModel(new EnemyModel(
                EnemyModel.Type.BOSS,
                EnemyModel.Src.BOSS4,
                EnemyModel.Velocity.VELOCITY1,
                EnemyModel.BulletSrc.BULLET3,
                EnemyModel.BulletVelocity.VELOCITY1,
                EnemyModel.Damage.DAMAGE3,
                EnemyModel.Hp.HP4
        ));
        addEnemyModel(new EnemyModel(
                EnemyModel.Type.BOSS,
                EnemyModel.Src.BOSS5,
                EnemyModel.Velocity.VELOCITY1,
                EnemyModel.BulletSrc.BULLET3,
                EnemyModel.BulletVelocity.VELOCITY1,
                EnemyModel.Damage.DAMAGE3,
                EnemyModel.Hp.HP4
        ));
    }

    private void initBlockModelMap() {
        addBlockModel(new BlockModel(
                BlockModel.Src.BLOCK1,
                BlockModel.Velocity.VELOCITY4,
                BlockModel.Hp.HP1
        ));
        addBlockModel(new BlockModel(
                BlockModel.Src.BLOCK2,
                BlockModel.Velocity.VELOCITY3,
                BlockModel.Hp.HP2
        ));
        addBlockModel(new BlockModel(
                BlockModel.Src.BLOCK3,
                BlockModel.Velocity.VELOCITY2,
                BlockModel.Hp.HP3
        ));
        addBlockModel(new BlockModel(
                BlockModel.Src.BLOCK4,
                BlockModel.Velocity.VELOCITY1,
                BlockModel.Hp.HP4
        ));
    }

    private void addEnemyModel(EnemyModel model) {
        mEnemyModelMap.put(model.getSrc(), model);
    }

    private void addBlockModel(BlockModel model) {
        mBlockModelList.add(model);
    }
}
