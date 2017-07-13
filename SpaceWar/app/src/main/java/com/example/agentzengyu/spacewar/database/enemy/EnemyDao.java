package com.example.agentzengyu.spacewar.database.enemy;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.set.EnemyLibrary;
import com.example.agentzengyu.spacewar.entity.single.EnemyItem;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 敌人数据库接口
 */
public interface EnemyDao {
    /**
     * 插入一条敌人数据
     *
     * @param tableName
     * @param item
     */
    void insert(@Constant.Database.Enemy.TableName String tableName, EnemyItem item);

    /**
     * 更新一条敌人数据
     *
     * @param tableName
     * @param item
     */
    void update(@Constant.Database.Enemy.TableName String tableName, EnemyItem item);

    /**
     * 删除一条敌人数据
     *
     * @param tableName
     * @param item
     */
    void delete(@Constant.Database.Enemy.TableName String tableName, EnemyItem item);

    /**
     * 查找全部敌人数据
     *
     * @return
     */
    EnemyLibrary findAll();

    /**
     * 关闭连接
     */
    void close();
}
