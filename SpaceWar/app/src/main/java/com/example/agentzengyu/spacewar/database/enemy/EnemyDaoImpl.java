package com.example.agentzengyu.spacewar.database.enemy;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.set.EnemyLibrary;
import com.example.agentzengyu.spacewar.entity.single.EnemyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 敌人数据库调用接口
 */
public class EnemyDaoImpl implements EnemyDao {
    private static EnemyDaoImpl instance = null;
    private EnemyHelper helper = null;
    private SQLiteDatabase database = null;

    private EnemyDaoImpl(Context context) {
        if (helper == null) {
            helper = new EnemyHelper(context, Constant.Database.Enemy.DBName, null, 1);
            database = helper.getWritableDatabase();
        }
    }

    public static EnemyDaoImpl getInstance(Context context) {
        if (instance == null) {
            synchronized (EnemyDaoImpl.class) {
                if (instance == null) {
                    instance = new EnemyDaoImpl(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void insert(@Constant.Database.Enemy.TableName String tableName, EnemyItem item) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Enemy.ColumnName.NAME, item.getName());
        values.put(Constant.Database.Enemy.ColumnName.LIFE, item.getLife());
        values.put(Constant.Database.Enemy.ColumnName.DEFENSE, item.getDefense());
        values.put(Constant.Database.Enemy.ColumnName.AGILITY, item.getAgility());
        values.put(Constant.Database.Enemy.ColumnName.POWER, item.getPower());
        values.put(Constant.Database.Enemy.ColumnName.SPEED, item.getSpeed());
        values.put(Constant.Database.Enemy.ColumnName.RANGE, item.getRange());
        values.put(Constant.Database.Enemy.ColumnName.IMAGE, item.getImage());
        database.insert(tableName, null, values);
    }

    @Override
    public void update(@Constant.Database.Enemy.TableName String tableName, EnemyItem item) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Enemy.ColumnName.NAME, item.getName());
        values.put(Constant.Database.Enemy.ColumnName.LIFE, item.getLife());
        values.put(Constant.Database.Enemy.ColumnName.DEFENSE, item.getDefense());
        values.put(Constant.Database.Enemy.ColumnName.AGILITY, item.getAgility());
        values.put(Constant.Database.Enemy.ColumnName.POWER, item.getPower());
        values.put(Constant.Database.Enemy.ColumnName.SPEED, item.getSpeed());
        values.put(Constant.Database.Enemy.ColumnName.RANGE, item.getRange());
        values.put(Constant.Database.Enemy.ColumnName.IMAGE, item.getImage());
        String[] whereArgs = new String[]{String.valueOf(item.getName())};
        database.update(tableName, values, Constant.Database.Shop.ColumnName.NAME + "=?", whereArgs);
    }

    @Override
    public void delete(@Constant.Database.Enemy.TableName String tableName, EnemyItem item) {
        String[] whereArgs = new String[]{String.valueOf(item.getName())};
        database.delete(tableName, Constant.Database.Enemy.ColumnName.NAME + "=?", whereArgs);
    }

    @Override
    public EnemyLibrary findAll() {
        EnemyLibrary library = new EnemyLibrary();
        if (library.setNormalEmenys(findEachTable(Constant.Database.Enemy.TableName.NORMAL)) &&
                library.setBossEmenys(findEachTable(Constant.Database.Enemy.TableName.BOSS))) {
            return library;
        }
        return null;
    }

    @Override
    public void close() {
        if (database != null) {
            database.close();
        }
    }

    /**
     * 查找每张表的数据
     *
     * @param tableName 表名
     * @return
     */
    private List<EnemyItem> findEachTable(@Constant.Database.Enemy.TableName String tableName) {
        List<EnemyItem> items = null;
        Cursor cursor = database.query(tableName, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            items = new ArrayList<>();
            while (cursor.moveToNext()) {
                EnemyItem item = new EnemyItem();
                item.setName(cursor.getString(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.NAME)));
                item.setLife(cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.LIFE)));
                item.setAgility(cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.AGILITY)));
                item.setDefense(cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.DEFENSE)));
                item.setPower(cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.POWER)));
                item.setSpeed(cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.SPEED)));
                item.setRange(cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.RANGE)));
                item.setImage(cursor.getString(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.IMAGE)));
                items.add(item);
            }
        }
        return items;
    }
}
