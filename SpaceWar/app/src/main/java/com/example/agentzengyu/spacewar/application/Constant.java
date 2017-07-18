package com.example.agentzengyu.spacewar.application;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 常量
 */
public @interface Constant {
    /**
     * 广播
     */
    @interface BroadCast {
        String TARGET = "TARGET";
    }

    /**
     * 菜单
     */
    @interface Menu {
        int MAIN = 0;
        int GAME = 1;
        int SHOP = 2;
        int SETTING = 3;
    }

    /**
     * 初始化
     */
    @interface Init {
        String TAG = "LOADING";

        /**
         * 类型
         */
        @StringDef({
                Type.SHOP, Type.PLAYER, Type.ENEMY, Type.MAP, Type.ERROR
        })
        @interface Type {
            String SHOP = "SHOP";
            String PLAYER = "PLAYER";
            String ENEMY = "ENEMY";
            String MAP = "MAP";
            String ERROR = "ERROR";
        }
    }

    /**
     * 游戏
     */
    @interface Game {
        /**
         * 类型
         */
        @interface Type {
            String MAP = "MAP";
            String PLAYER = "Player";
            String ENEMY = "ENEMY";
            String NOTIFY = "NOTIFY";
            String STATUS = "STATUS";
        }

        /**
         * 地图
         */
        @interface Map {

        }

        /**
         * 玩家
         */
        @interface Player {
            String COORD = "COORD";
            String X = "X";
            String Y = "Y";
            String BULLET = "BULLET";
            String SHIELD_OPEN = "SHIELD_OPEN";
            String SHIELD_CLOSE = "SHIELD_CLOSE";
            String LASER_START = "LASER_START";
            String LASER_STOP = "LASER_STOP";
            String DESTROY = "DESTROY";
        }

        /**
         * 敌人
         */
        @interface Enemy {
            String BULLET = "BULLET";
        }
    }

    /**
     * 数据库
     */
    @interface Database {
        /**
         * 商店
         */
        @interface Shop {
            String DBName = "DB_SHOP";

            @StringDef({
                    TableName.LIFE,
                    TableName.AGILITY,
                    TableName.DEFENSE,
                    TableName.SHIELD,
                    TableName.POWER,
                    TableName.SPEED,
                    TableName.RANGE,
                    TableName.LASER})
            @Retention(RetentionPolicy.RUNTIME)
            @interface TableName {
                String LIFE = "LIFE";
                String AGILITY = "AGILITY";
                String DEFENSE = "DEFENSE";
                String SHIELD = "SHIELD";
                String POWER = "POWER";
                String SPEED = "SPEED";
                String RANGE = "RANGE";
                String LASER = "LASER";
            }

            @Retention(RetentionPolicy.SOURCE)
            @interface ColumnName {
                String LEVEL = "LEVEL";
                String NAME = "NAME";
                String VALUE = "VALUE";
                String PRICE = "PRICE";
                String IMAGE = "IMAGE";
            }
        }

        /**
         * 玩家
         */
        @interface Player {
            String DBName = "DB_PLAYER";

            @StringDef({
                    TableName.LIFE,
                    TableName.AGILITY,
                    TableName.DEFENSE,
                    TableName.SHIELD,
                    TableName.POWER,
                    TableName.SPEED,
                    TableName.RANGE,
                    TableName.LASER,
                    TableName.INFO})
            @Retention(RetentionPolicy.SOURCE)
            @interface TableName {
                String LIFE = "LIFE";
                String AGILITY = "AGILITY";
                String DEFENSE = "DEFENSE";
                String SHIELD = "SHIELD";
                String POWER = "POWER";
                String SPEED = "SPEED";
                String RANGE = "RANGE";
                String LASER = "LASER";
                String INFO = "INFO";
            }
        }

        /**
         * 敌人
         */
        @interface Enemy {
            String DBName = "DB_ENEMY";

            @StringDef({
                    TableName.NORMAL,
                    TableName.BOSS
            })
            @Retention(RetentionPolicy.SOURCE)
            @interface TableName {
                String NORMAL = "NORMAL";
                String BOSS = "BOSS";
            }

            @Retention(RetentionPolicy.SOURCE)
            @interface ColumnName {
                String NAME = "NAME";
                String LIFE = "LIFE";
                String DEFENSE = "DEFENSE";
                String AGILITY = "AGILITY";
                String POWER = "POWER";
                String SPEED = "SPEED";
                String RANGE = "RANGE";
                String IMAGE = "IMAGE";
            }
        }

        /**
         * 地图
         */
        @interface Map {
            String DBName = "DB_MAP";

            @StringDef({
                    TableName.MAP
            })
            @interface TableName {
                String MAP = "MAP";
            }

            @Retention(RetentionPolicy.SOURCE)
            @interface ColumnName {
                String MAP_NAME = "MAP_NAME";
                String IMAGE = "IMAGE";
                String MUSIC = "MUSIC";
                String BOSS_NAME = "BOSS_NAME";

                String NAME1 = "NAME1";
                String COUNT1 = "COUNT1";
                String NAME2 = "NAME2";
                String COUNT2 = "COUNT2";
                String NAME3 = "NAME3";
                String COUNT3 = "COUNT3";
                String NAME4 = "NAME4";
                String COUNT4 = "COUNT4";
                String NAME5 = "NAME5";
                String COUNT5 = "COUNT5";
                String NAME6 = "NAME6";
                String COUNT6 = "COUNT6";
                String NAME7 = "NAME7";
                String COUNT7 = "COUNT7";
                String NAME8 = "NAME8";
                String COUNT8 = "COUNT8";
                String NAME9 = "NAME9";
                String COUNT9 = "COUNT9";
                String NAME10 = "NAME10";
                String COUNT10 = "COUNT10";
            }
        }
    }
}
