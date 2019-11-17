package com.zengyu.spacewar.game.manager;

import android.graphics.Canvas;
import android.graphics.Point;

import com.zengyu.spacewar.game.bean.AbsBean;
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
            mNuclear.init(mPlayer);
            mNuclear.activate();
        }
    }

    public void createEnemy() {
        EnemyModel model = ModelManager.getInstance().getRandomEnemyModel();
        Enemy enemy = mEnemyCache.poll();
        if (enemy == null) {
            enemy = EnemyFactory.build(mBorder, model);
        } else {
            enemy.init(new Random().nextInt(mBorder.x), 0, model);
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
            block.init(new Random().nextInt(mBorder.x), 0, model);
            block.activate();
        }
        mBlocks.add(block);
    }

    public void createPlayerBullet() {
        Bullet bullet = mBulletCache.poll();
        if (bullet == null) {
            bullet = BulletFactory.build(mBorder, mPlayer);
        } else {
            bullet.init(mPlayer);
            bullet.activate();
        }
        mPlayerBullets.add(bullet);
    }

    public void createEnemyBullet(Enemy enemy) {
        Bullet bullet = mBulletCache.poll();
        if (bullet == null) {
            bullet = BulletFactory.build(mBorder, enemy);
        } else {
            bullet.init(enemy);
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
            if (enemy.isVisible()) {
                enemy.draw(canvas);
            } else {
                iterator.remove();
                mEnemyCache.offer(enemy);
            }
        }
    }

    public void drawBlocks(Canvas canvas) {
        Iterator<Block> iterator = mBlocks.iterator();
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (block.isVisible()) {
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
            if (bullet.isVisible()) {
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
            if (bullet.isVisible()) {
                bullet.draw(canvas);
            } else {
                iterator.remove();
                mBulletCache.offer(bullet);
            }
        }
    }

    public void checkCollision() {
        mPlayerBullets.parallelStream().forEach(bullet -> {
            mEnemies.parallelStream().anyMatch(enemy -> {
                if (bullet.isAlive() && isCollision(enemy, bullet)) {
                    enemy.decreaseHp(bullet.getDamage());
                    bullet.setAlive(false);
                    return true;
                } else {
                    return false;
                }
            });
            mBlocks.parallelStream().anyMatch(block -> {
                if (bullet.isAlive() && isCollision(block, bullet)) {
                    block.decreaseHp(bullet.getDamage());
                    bullet.setAlive(false);
                    return true;
                } else {
                    return false;
                }
            });
        });
        mEnemyBullets.parallelStream().forEach(bullet -> {
            if (bullet.isAlive() && isCollision(mPlayer, bullet)) {
                mPlayer.decreaseHp(bullet.getDamage());
                bullet.setAlive(false);
            }
        });
    }

    private boolean isCollision(AbsBean bean1, AbsBean bean2) {
        int distanceX, distanceY;
        if (bean2 instanceof Bullet) {
            distanceX = bean1.getWidth() / 2;
            distanceY = bean1.getHeight() / 2;
        } else {
            distanceX = (bean1.getWidth() + bean2.getWidth()) / 2;
            distanceY = (bean1.getHeight() + bean2.getHeight()) / 2;
        }
        return (Math.abs(bean1.getX() - bean2.getX()) < distanceX)
                && (Math.abs(bean1.getY() - bean2.getY()) < distanceY);
    }

    /******************************* Factory *********************************/

    private static class PlayerFactory {
        private static Player build(Point border, PlayerModel model) {
            Player player = new Player(border);
            player.init(model);
            return player;
        }
    }

    private static class EnemyFactory {
        private static Enemy build(Point border, EnemyModel model) {
            Enemy enemy = new Enemy(border);
            enemy.init(new Random().nextInt(border.x), 0, model);
            return enemy;
        }
    }

    private static class BlockFactory {
        private static Block build(Point border, BlockModel model) {
            Block block = new Block(border);
            block.init(new Random().nextInt(border.x), 0, model);
            return block;
        }
    }

    private static class BulletFactory {
        private static Bullet build(Point border, IPlane plane) {
            Bullet bullet = new Bullet(border);
            bullet.init(plane);
            return bullet;
        }
    }

    private static class NuclearFactory {
        private static Nuclear build(Point border, Player player) {
            Nuclear nuclear = new Nuclear(border);
            nuclear.init(player);
            return nuclear;
        }
    }
}
