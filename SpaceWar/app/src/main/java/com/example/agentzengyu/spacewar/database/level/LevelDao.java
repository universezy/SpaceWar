package com.example.agentzengyu.spacewar.database.level;

import com.example.agentzengyu.spacewar.entity.basic.set.LevelLibrary;
import com.example.agentzengyu.spacewar.entity.basic.single.Level;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 地图数据库接口
 */
public interface LevelDao {
    /**
     * 插入一张地图数据
     *
     * @param level
     */
    void insert(Level level);

    /**
     * 更新一张地图数据
     *
     * @param level
     */
    void update(Level level);

    /**
     * 删除一张地图数据
     *
     * @param level
     */
    void delete(Level level);

    /**
     * 查找全部地图数据
     *
     * @return
     */
    LevelLibrary findAll();

    /**
     * 关闭连接
     */
    void close();

    /**
     * 销毁数据库
     */
    void destroy();
}
