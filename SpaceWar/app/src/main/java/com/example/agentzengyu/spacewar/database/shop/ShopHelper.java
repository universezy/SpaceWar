package com.example.agentzengyu.spacewar.database.shop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.agentzengyu.spacewar.application.Constant;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

public class ShopHelper extends SQLiteOpenHelper {
    private final static String TableAttr = "("
            + Constant.Database.Shop.ColumnName.COLUMN_LEVEL + " integer primary key autoincrement,"
            + Constant.Database.Shop.ColumnName.COLUMN_NAME + " varchar(20),"
            + Constant.Database.Shop.ColumnName.COLUMN_VALUE + " integer,"
            + Constant.Database.Shop.ColumnName.COLUMN_FEE + " integer,"
            + Constant.Database.Shop.ColumnName.COLUMN_IMAGE + " varchar(20)"
            + ")";

    public ShopHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableLife(db);
        createTableDefense(db);
        createTableAgility(db);
        createTableAgility(db);
        createTableShield(db);
        createTablePower(db);
        createTableSpeed(db);
        createTableRange(db);
        createTableLaser(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTableLife(SQLiteDatabase db) {
        String sql = "create table " + Constant.Database.Shop.TableName.TABLE_LIFE + TableAttr;
        db.execSQL(sql);
    }

    private void createTableDefense(SQLiteDatabase db) {
        String sql = "create table " + Constant.Database.Shop.TableName.TABLE_DEFENSE + TableAttr;
        db.execSQL(sql);
    }

    private void createTableAgility(SQLiteDatabase db) {
        String sql = "create table " + Constant.Database.Shop.TableName.TABLE_AGILITY + TableAttr;
        db.execSQL(sql);
    }

    private void createTableShield(SQLiteDatabase db) {
        String sql = "create table " + Constant.Database.Shop.TableName.TABLE_SHIELD + TableAttr;
        db.execSQL(sql);
    }

    private void createTablePower(SQLiteDatabase db) {
        String sql = "create table " + Constant.Database.Shop.TableName.TABLE_POWER + TableAttr;
        db.execSQL(sql);
    }

    private void createTableSpeed(SQLiteDatabase db) {
        String sql = "create table " + Constant.Database.Shop.TableName.TABLE_SPEED + TableAttr;
        db.execSQL(sql);
    }

    private void createTableRange(SQLiteDatabase db) {
        String sql = "create table " + Constant.Database.Shop.TableName.TABLE_RANGE + TableAttr;
        db.execSQL(sql);
    }

    private void createTableLaser(SQLiteDatabase db) {
        String sql = "create table " + Constant.Database.Shop.TableName.TABLE_LASER + TableAttr;
        db.execSQL(sql);
    }
}
