package com.example.agentzengyu.spacewar.application;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 常量
 */
public class Constant {
    //广播
    public static class BroadCast {
        public final static String STATE = "STATE";
        public final static String LOADING = "LOADING";
    }

    //游戏
    public static class Game{
        //类型
        public static class Type{
            public final static String MAP = "MAP";
            public final static String PLAYER = "Player";
            public final static String ENEMY = "ENEMY";
            public final static String NOTIFY = "NOTIFY";
            public final static String STATUS = "STATUS";
        }

        //地图
        public static class Map{

        }

        //玩家
        public static class Player {
            public final static String LOCATION = "LOCATION";
            public final static String X = "X";
            public final static String Y = "Y";
            public final static String BULLET = "BULLET";
            public final static String SHIELD_OPEN = "SHIELD_OPEN";
            public final static String SHIELD_CLOSE = "SHIELD_CLOSE";
            public final static String LASER_START = "LASER_START";
            public final static String LASER_STOP = "LASER_STOP";
            public final static String DESTROY= "DESTROY";
        }

        //敌人
        public static class Enemy{
            public final static String BULLET = "BULLET";
        }
    }

    //菜单
    public static class Menu {
        public final static int MAIN = 0;
        public final static int GAME = 1;
        public final static int SHOP = 2;
        public final static int SETTING = 3;
    }

    public static class Database{

        public static class Shop{
            public final static String DBName = "DB_SHOP";

            public static class TableName{
                public final static String TABLE_LIFE = "TABLE_LIFE";
                public final static String TABLE_AGILITY = "TABLE_AGILITY";
                public final static String TABLE_DEFENSE = "TABLE_DEFENSE";
                public final static String TABLE_SHIELD = "TABLE_SHIELD";
                public final static String TABLE_POWER = "TABLE_POWER";
                public final static String TABLE_SPEED = "TABLE_SPEED";
                public final static String TABLE_RANGE = "TABLE_RANGE";
                public final static String TABLE_LASER = "TABLE_LASER";
            }

            public static class ColumnName{
                public final static String COLUMN_LEVEL = "COLUMN_LEVEL";
                public final static String COLUMN_NAME = "COLUMN_NAME";
                public final static String COLUMN_VALUE = "COLUMN_VALUE";
                public final static String COLUMN_FEE = "COLUMN_FEE";
                public final static String COLUMN_IMAGE = "COLUMN_IMAGE";
            }
        }

        public static class Player {
            public final static String DBName = "DB_PLAYER";

            public static class TableName{
                public final static String TABLE_LIFE = "TABLE_LIFE";
                public final static String TABLE_AGILITY = "TABLE_AGILITY";
                public final static String TABLE_DEFENSE = "TABLE_DEFENSE";
                public final static String TABLE_SHIELD = "TABLE_SHIELD";
                public final static String TABLE_POWER = "TABLE_POWER";
                public final static String TABLE_SPEED = "TABLE_SPEED";
                public final static String TABLE_RANGE = "TABLE_RANGE";
                public final static String TABLE_LASER = "TABLE_LASER";
                public final static String TABLE_INFO = "TABLE_INFO";
            }
        }

        public static class Map{
            public final static String DBName = "DB_MAP";

            public static class TableName{

            }

            public static class ColumnName{

            }
        }
    }
}
