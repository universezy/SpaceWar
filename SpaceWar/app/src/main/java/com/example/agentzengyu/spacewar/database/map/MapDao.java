package com.example.agentzengyu.spacewar.database.map;

import com.example.agentzengyu.spacewar.entity.set.MapLibrary;
import com.example.agentzengyu.spacewar.entity.single.Map;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 地图数据库接口
 */
public interface MapDao {
    /**
     * 插入一张地图数据
     *
     * @param map
     */
    void insert(Map map);

    /**
     * 更新一张地图数据
     *
     * @param map
     */
    void update(Map map);

    /**
     * 删除一张地图数据
     *
     * @param map
     */
    void delete(Map map);

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

    /**
     * 销毁数据库
     */
    void destroy();
}
