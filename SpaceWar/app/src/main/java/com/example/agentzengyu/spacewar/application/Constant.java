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
public class Constant {
    /**
     * 广播
     */
    public static class BroadCast {
        public final static String STATE = "STATE";
        public final static String LOADING = "LOADING";
    }

    /**
     * 游戏
     */
    public static class Game {
        /**
         * 类型
         */
        public static class Type {
            public final static String MAP = "MAP";
            public final static String PLAYER = "Player";
            public final static String ENEMY = "ENEMY";
            public final static String NOTIFY = "NOTIFY";
            public final static String STATUS = "STATUS";
        }

        /**
         * 地图
         */
        public static class Map {

        }

        /**
         * 玩家
         */
        public static class Player {
            public final static String LOCATION = "LOCATION";
            public final static String X = "X";
            public final static String Y = "Y";
            public final static String BULLET = "BULLET";
            public final static String SHIELD_OPEN = "SHIELD_OPEN";
            public final static String SHIELD_CLOSE = "SHIELD_CLOSE";
            public final static String LASER_START = "LASER_START";
            public final static String LASER_STOP = "LASER_STOP";
            public final static String DESTROY = "DESTROY";
        }

        /**
         * 敌人
         */
        public static class Enemy {
            public final static String BULLET = "BULLET";
        }
    }

    /**
     * 菜单
     */
    public static class Menu {
        public final static int MAIN = 0;
        public final static int GAME = 1;
        public final static int SHOP = 2;
        public final static int SETTING = 3;
    }

    /**
     * 数据库
     */
    public static class Database {
        /**
         * 商店
         */
        public static class Shop {
            public final static String DBName = "DB_SHOP";

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
            public @interface TableName {
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
            public @interface ColumnName {
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
        public static class Player {
            public final static String DBName = "DB_PLAYER";

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
            public @interface TableName {
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
        public static class Enemy {
            public final static String DBName = "DB_ENEMY";

            @StringDef({
                    TableName.NORMAL,
                    TableName.BOSS
            })
            @Retention(RetentionPolicy.SOURCE)
            public @interface TableName {
                String NORMAL = "NORMAL";
                String BOSS = "BOSS";
            }

            @Retention(RetentionPolicy.SOURCE)
            public @interface ColumnName {
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
        public static class Map {
            public final static String DBName = "DB_MAP";

            @StringDef({
                    TableName.MAP
            })
            public @interface TableName {
                String MAP = "MAP";
            }

            @Retention(RetentionPolicy.SOURCE)
            public @interface ColumnName {
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
                String BOSSNAME = "BOSSNAME";
                String MAPNAME = "MAPNAME";
                String IMAGE = "IMAGE";
                String MUSIC = "MUSIC";
            }
        }
    }
}
