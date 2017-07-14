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
                + Constant.Database.Map.ColumnName.MAPNAME + " varchar(20) primary key,"
                + Constant.Database.Map.ColumnName.IMAGE + " integer,"
                + Constant.Database.Map.ColumnName.MUSIC + " integer,"
                + Constant.Database.Map.ColumnName.BOSSNAME + " varchar(20),"
                + Constant.Database.Map.ColumnName.NAME1 + " varchar(20),"
                + Constant.Database.Map.ColumnName.COUNT1 + " integer,"
                + Constant.Database.Map.ColumnName.NAME2 + " varchar(20),"
                + Constant.Database.Map.ColumnName.COUNT2 + " integer,"
                + Constant.Database.Map.ColumnName.NAME3 + " varchar(20),"
                + Constant.Database.Map.ColumnName.COUNT3 + " integer,"
                + Constant.Database.Map.ColumnName.NAME4 + " varchar(20),"
                + Constant.Database.Map.ColumnName.COUNT4 + " integer,"
                + Constant.Database.Map.ColumnName.NAME5 + " varchar(20),"
                + Constant.Database.Map.ColumnName.COUNT5 + " integer,"
                + Constant.Database.Map.ColumnName.NAME6 + " varchar(20),"
                + Constant.Database.Map.ColumnName.COUNT6 + " integer,"
                + Constant.Database.Map.ColumnName.NAME7 + " varchar(20),"
                + Constant.Database.Map.ColumnName.COUNT7 + " integer,"
                + Constant.Database.Map.ColumnName.NAME8 + " varchar(20),"
                + Constant.Database.Map.ColumnName.COUNT8 + " integer,"
                + Constant.Database.Map.ColumnName.NAME9 + " varchar(20),"
                + Constant.Database.Map.ColumnName.COUNT9 + " integer,"
                + Constant.Database.Map.ColumnName.NAME10 + " varchar(20),"
                + Constant.Database.Map.ColumnName.COUNT10 + " integer"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
