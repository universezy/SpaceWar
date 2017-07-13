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
    public void insert(String tableName, ShopItem item) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Shop.ColumnName.COLUMN_LEVEL, item.getLevel());
        values.put(Constant.Database.Shop.ColumnName.COLUMN_NAME, item.getName());
        values.put(Constant.Database.Shop.ColumnName.COLUMN_VALUE, item.getValue());
        values.put(Constant.Database.Shop.ColumnName.COLUMN_FEE, item.getFee());
        values.put(Constant.Database.Shop.ColumnName.COLUMN_IMAGE, item.getImage());
        database.insert(tableName, null, values);
    }

    @Override
    public void update(String tableName, ShopItem item) {
        ContentValues values = new ContentValues();
        values.put(Constant.Database.Shop.ColumnName.COLUMN_LEVEL, item.getLevel());
        values.put(Constant.Database.Shop.ColumnName.COLUMN_NAME, item.getName());
        values.put(Constant.Database.Shop.ColumnName.COLUMN_VALUE, item.getValue());
        values.put(Constant.Database.Shop.ColumnName.COLUMN_FEE, item.getFee());
        values.put(Constant.Database.Shop.ColumnName.COLUMN_IMAGE, item.getImage());
        String[] whereArgs = new String[]{String.valueOf(item.getLevel())};
        database.update(tableName, values, Constant.Database.Shop.ColumnName.COLUMN_LEVEL + "=?", whereArgs);
    }

    @Override
    public void delete(String tableName, ShopItem item) {
        String[] whereArgs = new String[]{String.valueOf(item.getLevel())};
        database.delete(tableName, Constant.Database.Shop.ColumnName.COLUMN_LEVEL + "=?", whereArgs);
    }

    @Override
    public ShopLibrary findAll() {
        ShopLibrary library = new ShopLibrary();
        library.setLives(findEachTable(Constant.Database.Shop.TableName.TABLE_LIFE));
        library.setDefenses(findEachTable(Constant.Database.Shop.TableName.TABLE_DEFENSE));
        library.setAgilities(findEachTable(Constant.Database.Shop.TableName.TABLE_AGILITY));
        library.setShields(findEachTable(Constant.Database.Shop.TableName.TABLE_SHIELD));
        library.setPowers(findEachTable(Constant.Database.Shop.TableName.TABLE_POWER));
        library.setSpeeds(findEachTable(Constant.Database.Shop.TableName.TABLE_SPEED));
        library.setRanges(findEachTable(Constant.Database.Shop.TableName.TABLE_RANGE));
        library.setLasers(findEachTable(Constant.Database.Shop.TableName.TABLE_LASER));
        return library;
    }

    @Override
    public void close() {
        if (database != null) {
            database.close();
        }
    }

    private List<ShopItem> findEachTable(String tableName) {
        List<ShopItem> items = null;
        Cursor cursor = database.query(tableName, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            items = new ArrayList<>();
            while (cursor.moveToNext()) {
                ShopItem item = new ShopItem();
                item.setLevel(cursor.getInt(cursor.getColumnIndex(Constant.Database.Shop.ColumnName.COLUMN_LEVEL)));
                item.setName(cursor.getString(cursor.getColumnIndex(Constant.Database.Shop.ColumnName.COLUMN_NAME)));
                item.setValue(cursor.getInt(cursor.getColumnIndex(Constant.Database.Shop.ColumnName.COLUMN_VALUE)));
                item.setFee(cursor.getInt(cursor.getColumnIndex(Constant.Database.Shop.ColumnName.COLUMN_FEE)));
                item.setImage(cursor.getString(cursor.getColumnIndex(Constant.Database.Shop.ColumnName.COLUMN_IMAGE)));
                items.add(item);
            }
        }
        return items;
    }
}
