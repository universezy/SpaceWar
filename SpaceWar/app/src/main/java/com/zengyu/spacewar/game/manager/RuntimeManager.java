package com.zengyu.spacewar.game.manager;

import android.graphics.Canvas;
import android.graphics.Point;

import com.zengyu.spacewar.game.model.BlockModel;
import com.zengyu.spacewar.game.model.EnemyModel;
import com.zengyu.spacewar.game.model.PlayerModel;
import com.zengyu.spacewar.game.bean.Block;
import com.zengyu.spacewar.game.bean.Bullet;
import com.zengyu.spacewar.game.bean.Enemy;
import com.zengyu.spacewar.game.bean.IPlane;
import com.zengyu.spacewar.game.bean.Nuclear;
import com.zengyu.spacewar.game.bean.Player;

import java.util.Iterator;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RuntimeManager {
    private static final String TAG = "RuntimeManager";

    private static volatile RuntimeManager sInstance = null;

    /***************************** data *****************************/
    private Point mBorder;

    /***************************** data *****************************/
    private final Queue<Enemy> mEnemies = new ConcurrentLinkedQueue<>();
    private final Queue<Block> mBlocks = new ConcurrentLinkedQueue<>();
    private final Queue<Bullet> mPlayerBullets = new ConcurrentLinkedQueue<>();
    private final Queue<Bullet> mEnemyBullets = new ConcurrentLinkedQueue<>();
    private Player mPlayer;
    private Nuclear mNuclear;

    /***************************** Cache *****************************/
    private final Queue<Enemy> mEnemyCache = new ConcurrentLinkedQueue<>();
    private final Queue<Block> mBlockCache = new ConcurrentLinkedQueue<>();
    private final Queue<Bullet> mBulletCache = new ConcurrentLinkedQueue<>();

    private RuntimeManager() {
    }

    public static RuntimeManager getInstance() {
        if (sInstance == null) {
            synchronized (RuntimeManager.class) {
                if (sInstance == null) {
                    sInstance = new RuntimeManager();
                }
            }
        }
        return sInstance;
    }

    public void init(Point border) {
        mBorder = new Point(border);
        createPlayer();
    }

    public void destroy() {
        mEnemies.clear();
        mBlocks.clear();
        mPlayerBullets.clear();
        mEnemyBullets.clear();
        mEnemyCache.clear();
        mBlockCache.clear();
        mBulletCache.clear();
    }

    private void createPlayer() {
        mPlayer = Optional.ofNullable(mPlayer)
                .orElseGet(() -> PlayerFactory.build(mBorder, ModelManager.getInstance().getPlayerModel()));
    }

    public void createNuclear() {
        if (mNuclear == null) {
            mNuclear = NuclearFactory.build(mBorder, mPlayer);
        } else {
            mNuclear.from(mPlayer);
            mNuclear.activate();
        }
    }

    public void createEnemy() {
        EnemyModel model = ModelManager.getInstance().getRandomEnemyModel();
        Enemy enemy = mEnemyCache.poll();
        if (enemy == null) {
            enemy = EnemyFactory.build(mBorder, model);
        } else {
            enemy.from(new Random().nextInt(mBorder.x), 0, model);
            enemy.activate();
        }
        mEnemies.add(enemy);
    }

    public void createBlock() {
        BlockModel model = ModelManager.getInstance().getRandomBlockModel();
        Block block = mBlockCache.poll();
        if (block == null) {
            block = BlockFactory.build(mBorder, model);
        } else {
            block.from(new Random().nextInt(mBorder.x), 0, model);
            block.activate();
        }
        mBlocks.add(block);
    }

    public void createPlayerBullet() {
        Bullet bullet = mBulletCache.poll();
        if (bullet == null) {
            bullet = BulletFactory.build(mBorder, mPlayer);
        } else {
            bullet.from(mPlayer);
            bullet.activate();
        }
        mPlayerBullets.add(bullet);
    }

    public void createEnemyBullet(Enemy enemy) {
        Bullet bullet = mBulletCache.poll();
        if (bullet == null) {
            bullet = BulletFactory.build(mBorder, enemy);
        } else {
            bullet.from(enemy);
            bullet.activate();
        }
        mEnemyBullets.add(bullet);
    }

    public Player getPlayer() {
        return mPlayer;
    }

    public void drawPlayer(Canvas canvas) {
        drawPlayerBullets(canvas);
        Optional.ofNullable(mNuclear).ifPresent(nuclear -> nuclear.draw(canvas));
        mPlayer.draw(canvas);
    }

    public void drawEnemies(Canvas canvas) {
        drawEnemyBullets(canvas);
        Iterator<Enemy> iterator = mEnemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (enemy.isValid()) {
                enemy.draw(canvas);
            } else {
                iterator.remove();
                mEnemyCache.offer(enemy);
            }
        }
    }

    public void drawBlocks(Canvas canvas){
        Iterator<Block> iterator = mBlocks.iterator();
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (block.isValid()) {
                block.draw(canvas);
            } else {
                iterator.remove();
                mBlockCache.offer(block);
            }
        }
    }

    private void drawPlayerBullets(Canvas canvas) {
        Iterator<Bullet> iterator = mPlayerBullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet.isValid()) {
                bullet.draw(canvas);
            } else {
                iterator.remove();
                mBulletCache.offer(bullet);
            }
        }
    }

    private void drawEnemyBullets(Canvas canvas) {
        Iterator<Bullet> iterator = mEnemyBullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet.isValid()) {
                bullet.draw(canvas);
            } else {
                iterator.remove();
                mBulletCache.offer(bullet);
            }
        }
    }

    private void checkCollision() {

    }

    /******************************* Factory *********************************/

    private static class PlayerFactory {
        private static Player build(Point border, PlayerModel model) {
            Player player = new Player(model);
            player.init(border);
            return player;
        }
    }

    private static class NuclearFactory {
        private static Nuclear build(Point border, Player player) {
            Nuclear nuclear = new Nuclear(player);
            nuclear.init(border);
            return nuclear;
        }
    }

    private static class EnemyFactory {
        private static Enemy build(Point border, EnemyModel model) {
            Enemy enemy = new Enemy(new Random().nextInt(border.x), 0, model);
            enemy.init(border);
            return enemy;
        }
    }

    private static class BulletFactory {
        private static Bullet build(Point border, IPlane plane) {
            Bullet bullet = new Bullet(plane);
            bullet.init(border);
            return bullet;
        }
    }

    private static class BlockFactory {
        private static Block build(Point border, BlockModel model) {
            Block block = new Block(new Random().nextInt(border.x), 0, model);
            block.init(border);
            return block;
        }
    }
}
