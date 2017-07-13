package com.example.agentzengyu.spacewar.database.map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.database.enemy.EnemyDaoImpl;
import com.example.agentzengyu.spacewar.entity.set.MapLibrary;
import com.example.agentzengyu.spacewar.entity.single.MapItem;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 地图数据库调用接口
 */
public class MapDaoImpl implements MapDao{
    private static MapDaoImpl instance = null;
    private MapHelper helper = null;
    private SQLiteDatabase database = null;

    private MapDaoImpl(Context context) {
        if (helper == null) {
            helper = new MapHelper(context, Constant.Database.Map.DBName, null, 1);
            database = helper.getWritableDatabase();
        }
    }

    public static MapDaoImpl getInstance(Context context) {
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
    public void insert(MapItem item) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Map.ColumnName.MAPNAME,item.getMapName());
        values.put(Constant.Database.Map.ColumnName.IMAGE,item.getImage());
        values.put(Constant.Database.Map.ColumnName.MUSIC,item.getMusic());
        values.put(Constant.Database.Map.ColumnName.BOSSNAME,item.getBossName());
        values.put(Constant.Database.Map.ColumnName.NAME1,item.getName1());
        values.put(Constant.Database.Map.ColumnName.COUNT1,item.getCount1());
        values.put(Constant.Database.Map.ColumnName.NAME2,item.getName2());
        values.put(Constant.Database.Map.ColumnName.COUNT2,item.getCount2());
        values.put(Constant.Database.Map.ColumnName.NAME3,item.getName3());
        values.put(Constant.Database.Map.ColumnName.COUNT3,item.getCount3());
        values.put(Constant.Database.Map.ColumnName.NAME4,item.getName4());
        values.put(Constant.Database.Map.ColumnName.COUNT4,item.getCount4());
        values.put(Constant.Database.Map.ColumnName.NAME5,item.getName5());
        values.put(Constant.Database.Map.ColumnName.COUNT5,item.getCount5());
        values.put(Constant.Database.Map.ColumnName.NAME6,item.getName6());
        values.put(Constant.Database.Map.ColumnName.COUNT6,item.getCount6());
        values.put(Constant.Database.Map.ColumnName.NAME7,item.getName7());
        values.put(Constant.Database.Map.ColumnName.COUNT7,item.getCount7());
        values.put(Constant.Database.Map.ColumnName.NAME8,item.getName8());
        values.put(Constant.Database.Map.ColumnName.COUNT8,item.getCount8());
        values.put(Constant.Database.Map.ColumnName.NAME9,item.getName9());
        values.put(Constant.Database.Map.ColumnName.COUNT9,item.getCount9());
        values.put(Constant.Database.Map.ColumnName.NAME10,item.getName10());
        values.put(Constant.Database.Map.ColumnName.COUNT10,item.getCount10());
        database.insert(helper.TABLE_NAME, null, values);
    }

    @Override
    public void update(MapItem item) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Map.ColumnName.MAPNAME,item.getMapName());
        values.put(Constant.Database.Map.ColumnName.IMAGE,item.getImage());
        values.put(Constant.Database.Map.ColumnName.MUSIC,item.getMusic());
        values.put(Constant.Database.Map.ColumnName.BOSSNAME,item.getBossName());
        values.put(Constant.Database.Map.ColumnName.NAME1,item.getName1());
        values.put(Constant.Database.Map.ColumnName.COUNT1,item.getCount1());
        values.put(Constant.Database.Map.ColumnName.NAME2,item.getName2());
        values.put(Constant.Database.Map.ColumnName.COUNT2,item.getCount2());
        values.put(Constant.Database.Map.ColumnName.NAME3,item.getName3());
        values.put(Constant.Database.Map.ColumnName.COUNT3,item.getCount3());
        values.put(Constant.Database.Map.ColumnName.NAME4,item.getName4());
        values.put(Constant.Database.Map.ColumnName.COUNT4,item.getCount4());
        values.put(Constant.Database.Map.ColumnName.NAME5,item.getName5());
        values.put(Constant.Database.Map.ColumnName.COUNT5,item.getCount5());
        values.put(Constant.Database.Map.ColumnName.NAME6,item.getName6());
        values.put(Constant.Database.Map.ColumnName.COUNT6,item.getCount6());
        values.put(Constant.Database.Map.ColumnName.NAME7,item.getName7());
        values.put(Constant.Database.Map.ColumnName.COUNT7,item.getCount7());
        values.put(Constant.Database.Map.ColumnName.NAME8,item.getName8());
        values.put(Constant.Database.Map.ColumnName.COUNT8,item.getCount8());
        values.put(Constant.Database.Map.ColumnName.NAME9,item.getName9());
        values.put(Constant.Database.Map.ColumnName.COUNT9,item.getCount9());
        values.put(Constant.Database.Map.ColumnName.NAME10,item.getName10());
        values.put(Constant.Database.Map.ColumnName.COUNT10,item.getCount10());
        String[] whereArgs = new String[]{String.valueOf(item.getMapName())};
        database.update(helper.TABLE_NAME, values, Constant.Database.Map.ColumnName.MAPNAME + "=?", whereArgs);
    }

    @Override
    public void delete(MapItem item) {
        String[] whereArgs = new String[]{String.valueOf(item.getMapName())};
        database.delete(helper.TABLE_NAME, Constant.Database.Map.ColumnName.MAPNAME + "=?", whereArgs);
    }

    @Override
    public MapLibrary findAll() {
        MapLibrary library = null;
        Cursor cursor = database.query(helper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            library = new MapLibrary();
            while (cursor.moveToNext()) {
                MapItem item  =new MapItem();
                item.setMapName(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.MAPNAME)));
                item.setImage(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.IMAGE)));
                item.setMusic(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.MUSIC)));
                item.setBossName(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.BOSSNAME)));
                item.setNormal1(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.NAME1)),cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.COUNT1)));
                item.setNormal2(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.NAME2)),cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.COUNT2)));
                item.setNormal3(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.NAME3)),cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.COUNT3)));
                item.setNormal4(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.NAME4)),cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.COUNT4)));
                item.setNormal5(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.NAME5)),cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.COUNT5)));
                item.setNormal6(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.NAME6)),cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.COUNT6)));
                item.setNormal7(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.NAME7)),cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.COUNT7)));
                item.setNormal8(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.NAME8)),cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.COUNT8)));
                item.setNormal9(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.NAME9)),cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.COUNT9)));
                item.setNormal10(cursor.getString(cursor.getColumnIndex(Constant.Database.Map.ColumnName.NAME10)),cursor.getInt(cursor.getColumnIndex(Constant.Database.Map.ColumnName.COUNT10)));
                library.getMaps().add(item);
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
}
