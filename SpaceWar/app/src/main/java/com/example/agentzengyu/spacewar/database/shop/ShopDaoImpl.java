package com.example.agentzengyu.spacewar.database.shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.set.ShopLibrary;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 商品数据库调用接口
 */
public class ShopDaoImpl implements ShopDao {
    private static ShopDaoImpl instance = null;
    private ShopHelper helper = null;
    private SQLiteDatabase database = null;

    private ShopDaoImpl(Context context) {
        if (helper == null) {
            helper = new ShopHelper(context, Constant.Database.Shop.DBName, null, 1);
            database = helper.getWritableDatabase();
        }
    }

    public static ShopDaoImpl getInstance(Context context) {
        if (instance == null) {
            synchronized (ShopDaoImpl.class) {
                if (instance == null) {
                    instance = new ShopDaoImpl(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void insert(@Constant.Database.Shop.TableName String tableName, ShopItem item) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Shop.ColumnName.LEVEL, item.getLevel());
        values.put(Constant.Database.Shop.ColumnName.NAME, item.getName());
        values.put(Constant.Database.Shop.ColumnName.VALUE, item.getValue());
        values.put(Constant.Database.Shop.ColumnName.PRICE, item.getPrice());
        values.put(Constant.Database.Shop.ColumnName.IMAGE, item.getImage());
        database.insert(tableName, null, values);
    }

    @Override
    public void update(@Constant.Database.Shop.TableName String tableName, ShopItem item) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Shop.ColumnName.LEVEL, item.getLevel());
        values.put(Constant.Database.Shop.ColumnName.NAME, item.getName());
        values.put(Constant.Database.Shop.ColumnName.VALUE, item.getValue());
        values.put(Constant.Database.Shop.ColumnName.PRICE, item.getPrice());
        values.put(Constant.Database.Shop.ColumnName.IMAGE, item.getImage());
        String[] whereArgs = new String[]{String.valueOf(item.getLevel())};
        database.update(tableName, values, Constant.Database.Shop.ColumnName.LEVEL + "=?", whereArgs);
    }

    @Override
    public void delete(@Constant.Database.Shop.TableName String tableName, ShopItem item) {
        String[] whereArgs = new String[]{String.valueOf(item.getLevel())};
        database.delete(tableName, Constant.Database.Shop.ColumnName.LEVEL + "=?", whereArgs);
    }

    @Override
    public ShopLibrary findAll() {
        ShopLibrary library = new ShopLibrary();
        if (library.setLives(findEachTable(Constant.Database.Shop.TableName.LIFE)) &&
                library.setDefenses(findEachTable(Constant.Database.Shop.TableName.DEFENSE)) &&
                library.setAgilities(findEachTable(Constant.Database.Shop.TableName.AGILITY)) &&
                library.setShields(findEachTable(Constant.Database.Shop.TableName.SHIELD)) &&
                library.setPowers(findEachTable(Constant.Database.Shop.TableName.POWER)) &&
                library.setSpeeds(findEachTable(Constant.Database.Shop.TableName.SPEED)) &&
                library.setRanges(findEachTable(Constant.Database.Shop.TableName.RANGE)) &&
                library.setLasers(findEachTable(Constant.Database.Shop.TableName.LASER))) {
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
    private List<ShopItem> findEachTable(@Constant.Database.Shop.TableName String tableName) {
        List<ShopItem> items = null;
        Cursor cursor = database.query(tableName, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            items = new ArrayList<>();
            while (cursor.moveToNext()) {
                ShopItem item = new ShopItem();
                item.setLevel(cursor.getInt(cursor.getColumnIndex(Constant.Database.Shop.ColumnName.LEVEL)));
                item.setName(cursor.getString(cursor.getColumnIndex(Constant.Database.Shop.ColumnName.NAME)));
                item.setValue(cursor.getInt(cursor.getColumnIndex(Constant.Database.Shop.ColumnName.VALUE)));
                item.setPrice(cursor.getInt(cursor.getColumnIndex(Constant.Database.Shop.ColumnName.PRICE)));
                item.setImage(cursor.getString(cursor.getColumnIndex(Constant.Database.Shop.ColumnName.IMAGE)));
                items.add(item);
            }
        }
        return items;
    }
}
