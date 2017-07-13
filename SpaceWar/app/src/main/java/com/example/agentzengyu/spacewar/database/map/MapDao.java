package com.example.agentzengyu.spacewar.database.map;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

import com.example.agentzengyu.spacewar.entity.set.MapLibrary;
import com.example.agentzengyu.spacewar.entity.single.MapItem;

/**
 * 地图数据库接口
 */
public interface MapDao {
    /**
     * 插入一张地图数据
     *
     * @param item
     */
    void insert(MapItem item);

    /**
     * 更新一张地图数据
     *
     * @param item
     */
    void update(MapItem item);

    /**
     * 删除一张地图数据
     *
     * @param item
     */
    void delete(MapItem item);

    /**
     * 查找全部地图数据
     *
     * @return
     */
    MapLibrary findAll();

    /**
     * 关闭连接
     */
    void close();
}
