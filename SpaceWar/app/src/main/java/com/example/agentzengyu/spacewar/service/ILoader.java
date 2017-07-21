package com.example.agentzengyu.spacewar.service;

import com.example.agentzengyu.spacewar.application.Constant;

/**
 * Created by Agent ZengYu on 2017/7/21.
 */

/**
 * 加载接口
 */
public interface ILoader {
    /**
     * 加载数据
     */
    void loadData();

    /**
     * 加载商店数据
     */
    boolean loadArticleData();

    /**
     * 加载玩家数据
     */
    boolean loadPlayerData();

    /**
     * 加载敌人数据
     */
    boolean loadEnemyData();

    /**
     * 加载地图数据
     */
    boolean loadLevelData();

    /**
     * 加载关联数据
     */
    boolean loadRelevancyData();

    /**
     * 初始化商店数据
     *
     * @return
     */
    void initArticleData();

    /**
     * 初始化玩家数据
     *
     * @return
     */
    void initPlayerData();

    /**
     * 初始化地图数据
     *
     * @return
     */
    /**
     * 初始化敌人数据
     *
     * @return
     */
    void initEnemyData();

    /**
     * 初始化地图数据
     *
     * @return
     */
    void initLevelData();

    /**
     * 初始化关联数据
     *
     * @return
     */
    void initRelevancy();

    /**
     * 绑定数据
     */
    boolean bindData();

    /**
     * 发送通知
     *
     * @param target 广播目标
     */
    void sendNotify(@Constant.Init.Type String target);
}
