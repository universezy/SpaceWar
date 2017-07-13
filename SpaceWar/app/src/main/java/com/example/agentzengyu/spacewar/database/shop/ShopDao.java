package com.example.agentzengyu.spacewar.database.shop;

import com.example.agentzengyu.spacewar.entity.set.ShopLibrary;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

public interface ShopDao {
    void insert(String tableName,ShopItem item);

    void update(String tableName,ShopItem item);

    void delete(String tableName,ShopItem item);

    ShopLibrary findAll();

    void close();
}
