package com.example.agentzengyu.spacewar.database.enemy;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.basic.set.EnemyLibrary;
import com.example.agentzengyu.spacewar.entity.basic.single.Enemy;

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
     * @param enemy
     */
    void insert(@Constant.Database.Enemy.TableName String tableName, Enemy enemy);

    /**
     * 更新一条敌人数据
     *
     * @param tableName
     * @param enemy
     */
    void update(@Constant.Database.Enemy.TableName String tableName, Enemy enemy);

    /**
     * 删除一条敌人数据
     *
     * @param tableName
     * @param enemy
     */
    void delete(@Constant.Database.Enemy.TableName String tableName, Enemy enemy);

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

    /**
     * 销毁数据库
     *
     * @param tableName
     */
    void destroy(@Constant.Database.Enemy.TableName String tableName);
}
