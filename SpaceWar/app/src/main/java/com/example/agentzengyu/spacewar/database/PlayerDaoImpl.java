package com.example.agentzengyu.spacewar.database;

import android.content.Context;
import android.util.Log;

import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;
import com.snappydb.DB;
import com.snappydb.SnappyDB;
import com.snappydb.SnappydbException;

/**
 * Created by Agent ZengYu on 2017/7/12.
 */

public class PlayerDaoImpl implements PlayerDao{
    private static PlayerDaoImpl instance = null;
    private DB snappyDB =null;

    private PlayerDaoImpl(Context context){
        try {
            snappyDB = new SnappyDB.Builder(context)
                    .directory(context.getFilesDir().getAbsolutePath())
                    .name("player_data")
                    .build();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public static PlayerDaoImpl getInstance(Context context){
        if (instance==null){
            synchronized (PlayerDaoImpl.class){
                if (instance==null) {
                    instance = new PlayerDaoImpl(context);
                }
            }
        }
        return instance;
    }

    @Override
    public PlayerData read() {
        Log.e("read", "read");
        try {
            PlayerData data = new PlayerData();
            data.setLife(snappyDB.getObject("life", ShopItem.class));
            data.setDefense(snappyDB.getObject("defense", ShopItem.class));
            data.setAgility(snappyDB.getObject("agility", ShopItem.class));
            data.setShield(snappyDB.getObject("shield", ShopItem.class));
            data.setPower(snappyDB.getObject("power", ShopItem.class));
            data.setSpeed(snappyDB.getObject("speed", ShopItem.class));
            data.setRange(snappyDB.getObject("range", ShopItem.class));
            data.setLaser(snappyDB.getObject("laser", ShopItem.class));
            data.setMoney(snappyDB.getInt("money"));
            return data;
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(PlayerData data) {
        try {
            snappyDB.put("life",data.getLife());
            snappyDB.put("defense",data.getDefense());
            snappyDB.put("agility",data.getAgility());
            snappyDB.put("shield",data.getShield());
            snappyDB.put("power",data.getPower());
            snappyDB.put("speed",data.getSpeed());
            snappyDB.put("range",data.getRange());
            snappyDB.put("laser",data.getLaser());
            snappyDB.putInt("money",data.getMoney());
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (snappyDB!=null){
            try {
                snappyDB.close();
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }
}
