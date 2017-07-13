package com.example.agentzengyu.spacewar.database.shop;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.set.ShopLibrary;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 商店数据库接口
 */
public interface ShopDao {
    /**
     * 插入一件商品数据
     *
     * @param tableName
     * @param item
     */
    void insert(@Constant.Database.Shop.TableName String tableName, ShopItem item);

    /**
     * 更新一件商品数据
     *
     * @param tableName
     * @param item
     */
    void update(@Constant.Database.Shop.TableName String tableName, ShopItem item);

    /**
     * 删除一件商品数据
     *
     * @param tableName
     * @param item
     */
    void delete(@Constant.Database.Shop.TableName String tableName, ShopItem item);

    /**
     * 查找全部商品数据
     *
     * @return
     */
    ShopLibrary findAll();

    /**
     * 关闭连接
     */
    void close();
}
