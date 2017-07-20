package com.example.agentzengyu.spacewar.database.map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.agentzengyu.spacewar.application.Constant;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 地图数据库管理
 */
public class MapHelper extends SQLiteOpenHelper {
    public final static String TABLE_NAME = Constant.Database.Map.TableName.MAP;

    public MapHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + "("
                + Constant.Database.Map.ColumnName.MAP_NAME + " varchar(20) primary key,"
                + Constant.Database.Map.ColumnName.IMAGE + " integer,"
                + Constant.Database.Map.ColumnName.MUSIC + " integer,"
                + Constant.Database.Map.ColumnName.BOSS_NAME + " varchar(20)"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
