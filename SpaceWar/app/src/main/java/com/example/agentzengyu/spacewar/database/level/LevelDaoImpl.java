package com.example.agentzengyu.spacewar.database.level;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.database.enemy.EnemyDaoImpl;
import com.example.agentzengyu.spacewar.entity.basic.set.LevelLibrary;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 地图数据库调用接口
 */
public class LevelDaoImpl implements LevelDao {
    private static LevelDaoImpl instance = null;
    private LevelHelper helper = null;
    private SQLiteDatabase database = null;

    private LevelDaoImpl(Context context) {
        Log.e("LevelDaoImpl", "new");
        if (helper == null) {
            helper = new LevelHelper(context, Constant.Database.Level.DatabaseName, null, 1);
            database = helper.getWritableDatabase();
        }
    }

    public static LevelDaoImpl getInstance(Context context) {
        Log.e("LevelDaoImpl", "getInstance");
        if (instance == null) {
            synchronized (EnemyDaoImpl.class) {
                if (instance == null) {
                    instance = new LevelDaoImpl(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void insert(com.example.agentzengyu.spacewar.entity.basic.single.Level level) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Level.ColumnName.LEVEL, level.getLevelName());
        values.put(Constant.Database.Level.ColumnName.IMAGE, level.getImage());
        values.put(Constant.Database.Level.ColumnName.MUSIC, level.getMusic());
        values.put(Constant.Database.Level.ColumnName.BOSS, level.getBossName());
        database.insert(helper.TABLE_NAME, null, values);
    }

    @Override
    public void update(com.example.agentzengyu.spacewar.entity.basic.single.Level level) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Level.ColumnName.LEVEL, level.getLevelName());
        values.put(Constant.Database.Level.ColumnName.IMAGE, level.getImage());
        values.put(Constant.Database.Level.ColumnName.MUSIC, level.getMusic());
        values.put(Constant.Database.Level.ColumnName.BOSS, level.getBossName());
        String[] whereArgs = new String[]{String.valueOf(level.getLevelName())};
        database.update(helper.TABLE_NAME, values, Constant.Database.Level.ColumnName.LEVEL + "=?", whereArgs);
    }

    @Override
    public void delete(com.example.agentzengyu.spacewar.entity.basic.single.Level level) {
        String[] whereArgs = new String[]{String.valueOf(level.getLevelName())};
        database.delete(helper.TABLE_NAME, Constant.Database.Level.ColumnName.LEVEL + "=?", whereArgs);
    }

    @Override
    public LevelLibrary findAll() {
        LevelLibrary library = null;
        Cursor cursor = database.query(helper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            library = new LevelLibrary();
            while (cursor.moveToNext()) {
                String mapName = cursor.getString(cursor.getColumnIndex(Constant.Database.Level.ColumnName.LEVEL));
                int image = cursor.getInt(cursor.getColumnIndex(Constant.Database.Level.ColumnName.IMAGE));
                int music = cursor.getInt(cursor.getColumnIndex(Constant.Database.Level.ColumnName.MUSIC));
                String bossName = cursor.getString(cursor.getColumnIndex(Constant.Database.Level.ColumnName.BOSS));
                if (!"".equals(mapName) && image > 0 && music > 0 && !"".equals(bossName)) {
                    com.example.agentzengyu.spacewar.entity.basic.single.Level level = new com.example.agentzengyu.spacewar.entity.basic.single.Level(mapName, image, music, bossName);
                    library.getLevels().put(mapName,level);
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
