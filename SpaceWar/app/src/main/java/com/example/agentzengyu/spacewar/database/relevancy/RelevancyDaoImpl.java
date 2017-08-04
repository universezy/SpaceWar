package com.example.agentzengyu.spacewar.database.relevancy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.basic.set.RelevancyLibrary;
import com.example.agentzengyu.spacewar.entity.basic.single.Relevancy;

/**
 * Created by Agent ZengYu on 2017/7/20.
 */

/**
 * 关联数据库调用接口
 */
public class RelevancyDaoImpl implements RelevancyDao {
    private static RelevancyDaoImpl instance = null;
    private RelevancyHelper helper = null;
    private SQLiteDatabase database = null;

    private RelevancyDaoImpl(Context context) {
        if (helper == null) {
            helper = new RelevancyHelper(context, Constant.Database.Relevancy.DatabaseName, null, 1);
            database = helper.getWritableDatabase();
        }
    }

    public static RelevancyDaoImpl getInstance(Context context) {
        if (instance == null) {
            synchronized (RelevancyDaoImpl.class) {
                if (instance == null) {
                    instance = new RelevancyDaoImpl(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void insert(Relevancy relevancy) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Relevancy.ColumnName.LEVEL_NAME, relevancy.getLevelName());
        values.put(Constant.Database.Relevancy.ColumnName.ENEMY_NAME, relevancy.getEnemyName());
        values.put(Constant.Database.Relevancy.ColumnName.ENEMY_COUNT, relevancy.getEnemyCount());
        values.put(Constant.Database.Relevancy.ColumnName.MD5, relevancy.getMD5());
        database.insert(helper.TABLE_NAME, null, values);
    }

    @Override
    public void update(Relevancy relevancy) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Relevancy.ColumnName.LEVEL_NAME, relevancy.getLevelName());
        values.put(Constant.Database.Relevancy.ColumnName.ENEMY_NAME, relevancy.getEnemyName());
        values.put(Constant.Database.Relevancy.ColumnName.ENEMY_COUNT, relevancy.getEnemyCount());
        values.put(Constant.Database.Relevancy.ColumnName.MD5, relevancy.getMD5());
        String[] whereArgs = new String[]{String.valueOf(relevancy.getMD5())};
        database.update(helper.TABLE_NAME, values, Constant.Database.Relevancy.ColumnName.MD5 + "=?", whereArgs);
    }

    @Override
    public void delete(Relevancy relevancy) {
        String[] whereArgs = new String[]{String.valueOf(relevancy.getMD5())};
        database.delete(helper.TABLE_NAME, Constant.Database.Relevancy.ColumnName.MD5 + "=?", whereArgs);
    }

    @Override
    public RelevancyLibrary findAll() {
        RelevancyLibrary library = null;
        Cursor cursor = database.query(helper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            library = new RelevancyLibrary();
            while (cursor.moveToNext()) {
                String mapName = cursor.getString(cursor.getColumnIndex(Constant.Database.Relevancy.ColumnName.LEVEL_NAME));
                String enemyName = cursor.getString(cursor.getColumnIndex(Constant.Database.Relevancy.ColumnName.ENEMY_NAME));
                int enemyCount = cursor.getInt(cursor.getColumnIndex(Constant.Database.Relevancy.ColumnName.ENEMY_COUNT));

                if (!"".equals(mapName) && !"".equals(enemyName) && enemyCount > 0) {
                    Relevancy relevancy = new Relevancy(mapName, enemyName, enemyCount);
                    library.getRelevancies().add(relevancy);
                }
            }
        }
        return library;
    }

    @Override
    public void close() {
        if (database != null) {
            database.close();
        }
    }

    @Override
    public void destroy() {
        String sql = "drop table " + helper.TABLE_NAME;
        database.execSQL(sql);
    }
}
