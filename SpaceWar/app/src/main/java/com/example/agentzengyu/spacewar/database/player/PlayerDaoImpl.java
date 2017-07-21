package com.example.agentzengyu.spacewar.database.player;

import android.content.Context;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.Article;
import com.example.agentzengyu.spacewar.entity.single.Player;
import com.snappydb.DB;
import com.snappydb.SnappyDB;
import com.snappydb.SnappydbException;

/**
 * Created by Agent ZengYu on 2017/7/12.
 */

/**
 * 玩家数据库调用接口
 */
public class PlayerDaoImpl implements PlayerDao {
    private static PlayerDaoImpl instance = null;
    private DB snappyDB = null;

    private PlayerDaoImpl(Context context) {
        try {
            snappyDB = new SnappyDB.Builder(context)
                    .directory(context.getFilesDir().getAbsolutePath())
                    .name(Constant.Database.Player.DatabaseName)
                    .build();
            if (snappyDB == null) {
                Log.e("Player snappyDB", "---------> null");
            } else {
                Log.e("Player snappyDB", "---------> create");
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public static PlayerDaoImpl getInstance(Context context) {
        if (instance == null) {
            synchronized (PlayerDaoImpl.class) {
                if (instance == null) {
                    instance = new PlayerDaoImpl(context);
                }
            }
        }
        return instance;
    }

    @Override
    public PlayerData findAll() {
        PlayerData data = new PlayerData();
        try {
            if (data.setLife(snappyDB.getObject(Constant.Database.Player.TableName.LIFE, Article.class)) &&
                    data.setDefense(snappyDB.getObject(Constant.Database.Player.TableName.DEFENSE, Article.class)) &&
                    data.setVelocity(snappyDB.getObject(Constant.Database.Player.TableName.VELOCITY, Article.class)) &&
                    data.setShield(snappyDB.getObject(Constant.Database.Player.TableName.SHIELD, Article.class)) &&
                    data.setPower(snappyDB.getObject(Constant.Database.Player.TableName.POWER, Article.class)) &&
                    data.setSpeed(snappyDB.getObject(Constant.Database.Player.TableName.SPEED, Article.class)) &&
                    data.setRange(snappyDB.getObject(Constant.Database.Player.TableName.RANGE, Article.class)) &&
                    data.setLaser(snappyDB.getObject(Constant.Database.Player.TableName.LASER, Article.class)) &&
                    data.setInfo(snappyDB.getObject(Constant.Database.Player.TableName.INFO, Player.class))) {
                Log.e("snappyDB", "findAll");
                return data;
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        Log.e("snappyDB", "findAll = null");
        return null;
    }

    @Override
    public void update(PlayerData data) {
        try {
            snappyDB.put(Constant.Database.Player.TableName.LIFE, data.getLife());
            snappyDB.put(Constant.Database.Player.TableName.DEFENSE, data.getDefense());
            snappyDB.put(Constant.Database.Player.TableName.VELOCITY, data.getVelocity());
            snappyDB.put(Constant.Database.Player.TableName.SHIELD, data.getShield());
            snappyDB.put(Constant.Database.Player.TableName.POWER, data.getPower());
            snappyDB.put(Constant.Database.Player.TableName.SPEED, data.getSpeed());
            snappyDB.put(Constant.Database.Player.TableName.RANGE, data.getRange());
            snappyDB.put(Constant.Database.Player.TableName.LASER, data.getLaser());
            snappyDB.put(Constant.Database.Player.TableName.INFO, data.getPlayer());
            Log.e("snappyDB", "update");
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (snappyDB != null) {
            try {
                snappyDB.close();
                Log.e("snappyDB", "close");
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        if (snappyDB != null) {
            try {
                snappyDB.destroy();
                Log.e("snappyDB", "destroy");
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }
}
