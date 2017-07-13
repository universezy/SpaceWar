package com.example.agentzengyu.spacewar.database.player;

import android.content.Context;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;
import com.example.agentzengyu.spacewar.entity.single.UserInfo;
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
                    .name(Constant.Database.Player.DBName)
                    .build();
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
        try {
            PlayerData data = new PlayerData();
            data.setLife(snappyDB.getObject(Constant.Database.Player.TableName.LIFE, ShopItem.class));
            data.setDefense(snappyDB.getObject(Constant.Database.Player.TableName.DEFENSE, ShopItem.class));
            data.setAgility(snappyDB.getObject(Constant.Database.Player.TableName.AGILITY, ShopItem.class));
            data.setShield(snappyDB.getObject(Constant.Database.Player.TableName.SHIELD, ShopItem.class));
            data.setPower(snappyDB.getObject(Constant.Database.Player.TableName.POWER, ShopItem.class));
            data.setSpeed(snappyDB.getObject(Constant.Database.Player.TableName.SPEED, ShopItem.class));
            data.setRange(snappyDB.getObject(Constant.Database.Player.TableName.RANGE, ShopItem.class));
            data.setLaser(snappyDB.getObject(Constant.Database.Player.TableName.LASER, ShopItem.class));
            data.setInfo(snappyDB.getObject(Constant.Database.Player.TableName.INFO, UserInfo.class));
            return data;
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(PlayerData data) {
        try {
            snappyDB.put(Constant.Database.Player.TableName.LIFE, data.getLife());
            snappyDB.put(Constant.Database.Player.TableName.DEFENSE, data.getDefense());
            snappyDB.put(Constant.Database.Player.TableName.AGILITY, data.getAgility());
            snappyDB.put(Constant.Database.Player.TableName.SHIELD, data.getShield());
            snappyDB.put(Constant.Database.Player.TableName.POWER, data.getPower());
            snappyDB.put(Constant.Database.Player.TableName.SPEED, data.getSpeed());
            snappyDB.put(Constant.Database.Player.TableName.RANGE, data.getRange());
            snappyDB.put(Constant.Database.Player.TableName.LASER, data.getLaser());
            snappyDB.put(Constant.Database.Player.TableName.INFO, data.getInfo());
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (snappyDB != null) {
            try {
                snappyDB.close();
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }
}
