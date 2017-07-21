package com.example.agentzengyu.spacewar.database.relevancy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.agentzengyu.spacewar.application.Constant;

/**
 * Created by Agent ZengYu on 2017/7/20.
 */

/**
 * 关联数据库管理
 */
public class RelevancyHelper extends SQLiteOpenHelper {
    public final static String TABLE_NAME = Constant.Database.Relevancy.TableName.RELEVANCY;

    public RelevancyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + "("
                + Constant.Database.Relevancy.ColumnName.LEVEL_NAME + " varchar(20),"
                + Constant.Database.Relevancy.ColumnName.ENEMY_NAME + " varchar(20),"
                + Constant.Database.Relevancy.ColumnName.ENEMY_COUNT + " integer,"
                + Constant.Database.Relevancy.ColumnName.MD5 + " text"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
