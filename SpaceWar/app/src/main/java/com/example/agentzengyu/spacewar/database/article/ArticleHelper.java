package com.example.agentzengyu.spacewar.database.article;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.agentzengyu.spacewar.application.Constant;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 商品数据库管理
 */
public class ArticleHelper extends SQLiteOpenHelper {
    private final static String TableAttr = "("
            + Constant.Database.Article.ColumnName.NAME + " varchar(20) primary key,"
            + Constant.Database.Article.ColumnName.IMAGE + " integer,"
            + Constant.Database.Article.ColumnName.VALUE + " integer,"
            + Constant.Database.Article.ColumnName.LEVEL + " integer,"
            + Constant.Database.Article.ColumnName.PRICE + " integer"
            + ")";

    public ArticleHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db, Constant.Database.Article.TableName.LIFE);
        createTable(db, Constant.Database.Article.TableName.DEFENSE);
        createTable(db, Constant.Database.Article.TableName.VELOCITY);
        createTable(db, Constant.Database.Article.TableName.SHIELD);
        createTable(db, Constant.Database.Article.TableName.POWER);
        createTable(db, Constant.Database.Article.TableName.SPEED);
        createTable(db, Constant.Database.Article.TableName.RANGE);
        createTable(db, Constant.Database.Article.TableName.LASER);
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
    private void createTable(SQLiteDatabase db, @Constant.Database.Article.TableName String tableName) {
        String sql = "create table " + tableName + TableAttr;
        db.execSQL(sql);
    }
}
