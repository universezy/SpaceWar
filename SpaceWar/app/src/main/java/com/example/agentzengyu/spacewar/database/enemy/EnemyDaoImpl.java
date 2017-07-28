package com.example.agentzengyu.spacewar.database.enemy;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.basic.set.EnemyLibrary;
import com.example.agentzengyu.spacewar.entity.basic.single.Enemy;

import java.util.HashMap;
import java.util.Map;

/**
 * 敌人数据库调用接口
 */
public class EnemyDaoImpl implements EnemyDao {
    private static EnemyDaoImpl instance = null;
    private EnemyHelper helper = null;
    private SQLiteDatabase database = null;

    private EnemyDaoImpl(Context context) {
        if (helper == null) {
            helper = new EnemyHelper(context, Constant.Database.Enemy.DatabaseName, null, 1);
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
    public void insert(@Constant.Database.Enemy.TableName String tableName, Enemy enemy) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Enemy.ColumnName.NAME, enemy.getName());
        values.put(Constant.Database.Enemy.ColumnName.IMAGE, enemy.getImage());
        values.put(Constant.Database.Enemy.ColumnName.LIFE, enemy.getLife());
        values.put(Constant.Database.Enemy.ColumnName.DEFENSE, enemy.getDefense());
        values.put(Constant.Database.Enemy.ColumnName.VELOCITY, enemy.getVelocity());
        values.put(Constant.Database.Enemy.ColumnName.POWER, enemy.getPower());
        values.put(Constant.Database.Enemy.ColumnName.SPEED, enemy.getSpeed());
        values.put(Constant.Database.Enemy.ColumnName.RANGE, enemy.getRange());
        database.insert(tableName, null, values);
    }

    @Override
    public void update(@Constant.Database.Enemy.TableName String tableName, Enemy enemy) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Enemy.ColumnName.NAME, enemy.getName());
        values.put(Constant.Database.Enemy.ColumnName.IMAGE, enemy.getImage());
        values.put(Constant.Database.Enemy.ColumnName.LIFE, enemy.getLife());
        values.put(Constant.Database.Enemy.ColumnName.DEFENSE, enemy.getDefense());
        values.put(Constant.Database.Enemy.ColumnName.VELOCITY, enemy.getVelocity());
        values.put(Constant.Database.Enemy.ColumnName.POWER, enemy.getPower());
        values.put(Constant.Database.Enemy.ColumnName.SPEED, enemy.getSpeed());
        values.put(Constant.Database.Enemy.ColumnName.RANGE, enemy.getRange());
        String[] whereArgs = new String[]{String.valueOf(enemy.getName())};
        database.update(tableName, values, Constant.Database.Article.ColumnName.NAME + "=?", whereArgs);
    }

    @Override
    public void delete(@Constant.Database.Enemy.TableName String tableName, Enemy enemy) {
        String[] whereArgs = new String[]{String.valueOf(enemy.getName())};
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

    @Override
    public void destroy(@Constant.Database.Enemy.TableName String tableName) {
        String sql = "drop table " + tableName;
        database.execSQL(sql);
    }

    /**
     * 查找每张表的数据
     *
     * @param tableName 表名
     * @return
     */
    private Map<String, Enemy> findEachTable(@Constant.Database.Enemy.TableName String tableName) {
        Map<String, Enemy> enemyMap = null;
        Cursor cursor = database.query(tableName, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            enemyMap = new HashMap<>();
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.NAME));
                int image = cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.IMAGE));
                int life = cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.LIFE));
                int defense = cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.DEFENSE));
                int velocity = cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.VELOCITY));
                int power = cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.POWER));
                int speed = cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.SPEED));
                int range = cursor.getInt(cursor.getColumnIndex(Constant.Database.Enemy.ColumnName.RANGE));

                if (!"".equals(name) && life > 0 && defense > 0 && velocity > 0 && power > 0 && speed > 0 && range > 0 && image > 0) {
                    Enemy item = new Enemy(name, image, life, defense, velocity, power, speed, range);
                    enemyMap.put(name, item);
                }
            }
        }
        return enemyMap;
    }
}
