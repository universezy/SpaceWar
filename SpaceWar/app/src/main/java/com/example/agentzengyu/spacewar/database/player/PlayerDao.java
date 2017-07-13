package com.example.agentzengyu.spacewar.database.player;

import com.example.agentzengyu.spacewar.entity.set.PlayerData;

/**
 * Created by Agent ZengYu on 2017/7/12.
 */

/**
 * 玩家数据库接口
 */
public interface PlayerDao {
    /**
     * 查找全部数据
     *
     * @return
     */
    PlayerData findAll();

    /**
     * 更新玩家数据
     *
     * @param data 玩家数据
     */
    void update(PlayerData data);

    /**
     * 关闭连接
     */
    void close();
}
