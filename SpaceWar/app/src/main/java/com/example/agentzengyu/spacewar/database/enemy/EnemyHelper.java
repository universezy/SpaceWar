package com.example.agentzengyu.spacewar.database.enemy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.agentzengyu.spacewar.application.Constant;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 敌人数据库管理
 */
public class EnemyHelper extends SQLiteOpenHelper {
    private final static String TableAttr = "("
            + Constant.Database.Enemy.ColumnName.NAME + " varchar(20) primary key,"
            + Constant.Database.Enemy.ColumnName.IMAGE + " integer,"
            + Constant.Database.Enemy.ColumnName.CRASH + " integer,"
            + Constant.Database.Enemy.ColumnName.BULLET + " integer,"
            + Constant.Database.Enemy.ColumnName.LIFE + " integer,"
            + Constant.Database.Enemy.ColumnName.DEFENSE + " integer,"
            + Constant.Database.Enemy.ColumnName.VELOCITY + " integer,"
            + Constant.Database.Enemy.ColumnName.POWER + " integer,"
            + Constant.Database.Enemy.ColumnName.SPEED + " integer"
            + ")";

    public EnemyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db, Constant.Database.Enemy.TableName.NORMAL);
        createTable(db, Constant.Database.Enemy.TableName.BOSS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 创建表
     *
     * @param db        数据库
     * @param tableName 表名
     */
    private void createTable(SQLiteDatabase db, @Constant.Database.Enemy.TableName String tableName) {
        String sql = "create table " + tableName + TableAttr;
        db.execSQL(sql);
    }
}
