package com.example.agentzengyu.spacewar.database.shop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.agentzengyu.spacewar.application.Constant;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 商店数据库管理
 */
public class ShopHelper extends SQLiteOpenHelper {
    private final static String TableAttr = "("
            + Constant.Database.Shop.ColumnName.LEVEL + " integer primary key,"
            + Constant.Database.Shop.ColumnName.NAME + " varchar(20),"
            + Constant.Database.Shop.ColumnName.VALUE + " integer,"
            + Constant.Database.Shop.ColumnName.PRICE + " integer,"
            + Constant.Database.Shop.ColumnName.IMAGE + " integer"
            + ")";

    public ShopHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db, Constant.Database.Shop.TableName.LIFE);
        createTable(db, Constant.Database.Shop.TableName.DEFENSE);
        createTable(db, Constant.Database.Shop.TableName.AGILITY);
        createTable(db, Constant.Database.Shop.TableName.SHIELD);
        createTable(db, Constant.Database.Shop.TableName.POWER);
        createTable(db, Constant.Database.Shop.TableName.SPEED);
        createTable(db, Constant.Database.Shop.TableName.RANGE);
        createTable(db, Constant.Database.Shop.TableName.LASER);
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
    private void createTable(SQLiteDatabase db, @Constant.Database.Shop.TableName String tableName) {
        String sql = "create table " + tableName + TableAttr;
        db.execSQL(sql);
    }
}
