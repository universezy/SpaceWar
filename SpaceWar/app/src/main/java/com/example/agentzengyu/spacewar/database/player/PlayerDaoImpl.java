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
            data.setLife(snappyDB.getObject(Constant.Database.Player.TableName.TABLE_LIFE, ShopItem.class));
            data.setDefense(snappyDB.getObject(Constant.Database.Player.TableName.TABLE_DEFENSE, ShopItem.class));
            data.setAgility(snappyDB.getObject(Constant.Database.Player.TableName.TABLE_AGILITY, ShopItem.class));
            data.setShield(snappyDB.getObject(Constant.Database.Player.TableName.TABLE_SHIELD, ShopItem.class));
            data.setPower(snappyDB.getObject(Constant.Database.Player.TableName.TABLE_POWER, ShopItem.class));
            data.setSpeed(snappyDB.getObject(Constant.Database.Player.TableName.TABLE_SPEED, ShopItem.class));
            data.setRange(snappyDB.getObject(Constant.Database.Player.TableName.TABLE_RANGE, ShopItem.class));
            data.setLaser(snappyDB.getObject(Constant.Database.Player.TableName.TABLE_LASER, ShopItem.class));
            data.setInfo(snappyDB.getObject(Constant.Database.Player.TableName.TABLE_INFO, UserInfo.class));
            return data;
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(PlayerData data) {
        try {
            snappyDB.put(Constant.Database.Player.TableName.TABLE_LIFE, data.getLife());
            snappyDB.put(Constant.Database.Player.TableName.TABLE_DEFENSE, data.getDefense());
            snappyDB.put(Constant.Database.Player.TableName.TABLE_AGILITY, data.getAgility());
            snappyDB.put(Constant.Database.Player.TableName.TABLE_SHIELD, data.getShield());
            snappyDB.put(Constant.Database.Player.TableName.TABLE_POWER, data.getPower());
            snappyDB.put(Constant.Database.Player.TableName.TABLE_SPEED, data.getSpeed());
            snappyDB.put(Constant.Database.Player.TableName.TABLE_RANGE, data.getRange());
            snappyDB.put(Constant.Database.Player.TableName.TABLE_LASER, data.getLaser());
            snappyDB.put(Constant.Database.Player.TableName.TABLE_INFO, data.getInfo());
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
