package com.example.agentzengyu.spacewar.database.map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.database.enemy.EnemyDaoImpl;
import com.example.agentzengyu.spacewar.entity.set.MapLibrary;
import com.example.agentzengyu.spacewar.entity.single.Map;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 地图数据库调用接口
 */
public class MapDaoImpl implements MapDao {
    private static MapDaoImpl instance = null;
    private MapHelper helper = null;
    private SQLiteDatabase database = null;

    private MapDaoImpl(Context context) {
        Log.e("MapDaoImpl", "new");
        if (helper == null) {
            helper = new MapHelper(context, Constant.Database.Map.DatabaseName, null, 1);
            database = helper.getWritableDatabase();
        }
    }

    public static MapDaoImpl getInstance(Context context) {
        Log.e("MapDaoImpl", "getInstance");
        if (instance == null) {
            synchronized (EnemyDaoImpl.class) {
                if (instance == null) {
                    instance = new MapDaoImpl(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void insert(Map map) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Map.ColumnName.MAP_NAME, map.getMapName());
        values.put(Constant.Database.Map.ColumnName.IMAGE, map.getImage());
        values.put(Constant.Database.Map.ColumnName.MUSIC, map.getMusic());
        values.put(Constant.Database.Map.ColumnName.BOSS_NAME, map.getBossName());
        database.insert(helper.TABLE_NAME, null, values);
    }

    @Override
    public void update(Map map) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Map.ColumnName.MAP_NAME, map.getMapName());
        values.put(Constant.Database.Map.ColumnName.IMAGE, map.getImage());
        values.put(Constant.Database.Map.ColumnName.MUSIC, map.getMusic());
        values.put(Constant.Database.Map.ColumnName.BOSS_NAME, map.getBossName());
        String[] whereArgs = new String[]{String.valueOf(map.getMapName())};
        database.update(helper.TABLE_NAME, values, Constant.Database.Map.ColumnName.MAP_NAME + "=?", whereArgs);
    }

    @Override
    public void delete(Map map) {
        String[] whereArgs = new String[]{String.valueOf(map.getMapName())};
        database.delete(helper.TABLE_NAME, Constant.Database.Map.ColumnName.MAP_NAME + "=?", whereArgs);
    }

    @Override
    public MapLibrary findAll() {
        MapLibrary library = null;
        Cursor cursor = database.query(helper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            library = new MapLibrary();
            while (cursor.moveToNext()) {
                String mapName = cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.MAP_NAME));
                int image = cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.IMAGE));
                int music = cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.MUSIC));
                String bossName = cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.BOSS_NAME));
                if (!"".equals(mapName) && image > 0 && music > 0 && !"".equals(bossName)) {
                    Map item = new Map(mapName, image, music, bossName);
                    library.getMaps().add(item);
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
