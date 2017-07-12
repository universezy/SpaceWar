package com.example.agentzengyu.spacewar.database;

import com.example.agentzengyu.spacewar.entity.set.PlayerData;

/**
 * Created by Agent ZengYu on 2017/7/12.
 */

public interface PlayerDao {
    PlayerData read();

    void save(PlayerData data);

    void close();
}
