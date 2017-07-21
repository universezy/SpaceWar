package com.example.agentzengyu.spacewar.database.level;

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
public class LevelHelper extends SQLiteOpenHelper {
    public final static String TABLE_NAME = Constant.Database.Level.TableName.LEVEL;

    public LevelHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + "("
                + Constant.Database.Level.ColumnName.LEVEL_NAME + " varchar(20) primary key,"
                + Constant.Database.Level.ColumnName.IMAGE + " integer,"
                + Constant.Database.Level.ColumnName.MUSIC + " integer,"
                + Constant.Database.Level.ColumnName.BOSS_NAME + " varchar(20)"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
