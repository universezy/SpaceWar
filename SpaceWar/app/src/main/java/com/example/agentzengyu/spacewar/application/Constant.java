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
        int PLAYER = 0;
        int GAME = 1;
        int ARTICLE = 2;
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
                Type.ARTICLE, Type.PLAYER, Type.ENEMY, Type.LEVEL, Type.RELEVANCY, Type.ERROR,Type.BIND
        })
        @Retention(RetentionPolicy.SOURCE)
        @interface Type {
            String ARTICLE = "ARTICLE";
            String PLAYER = "PLAYER";
            String ENEMY = "ENEMY";
            String LEVEL = "LEVEL";
            String RELEVANCY = "RELEVANCY";
            String BIND = "BIND";
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
            String LEVEL = "LEVEL";
            String PLAYER = "PLAYER";
            String ENEMY = "ENEMY";
            String NOTIFY = "NOTIFY";
            String STATUS = "STATUS";
        }

        /**
         * 地图
         */
        @interface Level {

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
        @interface Article {
            String DatabaseName = "ARTICLE";

            @StringDef({
                    TableName.LIFE,
                    TableName.VELOCITY,
                    TableName.DEFENSE,
                    TableName.SHIELD,
                    TableName.POWER,
                    TableName.SPEED,
                    TableName.LASER})
            @Retention(RetentionPolicy.SOURCE)
            @interface TableName {
                String LIFE = "LIFE";
                String VELOCITY = "VELOCITY";
                String DEFENSE = "DEFENSE";
                String SHIELD = "SHIELD";
                String POWER = "POWER";
                String SPEED = "SPEED";
                String LASER = "LASER";
            }

            @Retention(RetentionPolicy.SOURCE)
            @interface ColumnName {
                String GRADE = "GRADE";
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
            String DatabaseName = "PLAYER";

            @StringDef({
                    TableName.LIFE,
                    TableName.VELOCITY,
                    TableName.DEFENSE,
                    TableName.SHIELD,
                    TableName.POWER,
                    TableName.SPEED,
                    TableName.LASER,
                    TableName.INFO})
            @Retention(RetentionPolicy.SOURCE)
            @interface TableName {
                String LIFE = "LIFE";
                String VELOCITY = "VELOCITY";
                String DEFENSE = "DEFENSE";
                String SHIELD = "SHIELD";
                String POWER = "POWER";
                String SPEED = "SPEED";
                String LASER = "LASER";
                String INFO = "INFO";
            }
        }

        /**
         * 敌人
         */
        @interface Enemy {
            String DatabaseName = "ENEMY";

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
                String IMAGE = "IMAGE";
                String CRASH = "CRASH";
                String BULLET = "BULLET";
                String LIFE = "LIFE";
                String DEFENSE = "DEFENSE";
                String VELOCITY = "VELOCITY";
                String POWER = "POWER";
                String SPEED = "SPEED";
            }
        }

        /**
         * 地图
         */
        @interface Level {
            String DatabaseName = "LEVEL";

            @StringDef({
                    TableName.LEVEL
            })
            @Retention(RetentionPolicy.SOURCE)
            @interface TableName {
                String LEVEL = "LEVEL";
            }

            @Retention(RetentionPolicy.SOURCE)
            @interface ColumnName {
                String LEVEL = "LEVEL";
                String IMAGE = "IMAGE";
                String MUSIC = "MUSIC";
                String BOSS = "BOSS";
            }
        }

        @interface Relevancy {
            String DatabaseName = "RELEVANCY";

            @StringDef({
                    TableName.RELEVANCY
            })
            @Retention(RetentionPolicy.SOURCE)
            @interface TableName {
                String RELEVANCY = "RELEVANCY";
            }

            @Retention(RetentionPolicy.SOURCE)
            @interface ColumnName {
                String LEVEL_NAME = "LEVEL_NAME";
                String ENEMY_NAME = "ENEMY_NAME";
                String ENEMY_COUNT = "ENEMY_COUNT";
                String MD5 = "MD5";
            }
        }
    }
}
