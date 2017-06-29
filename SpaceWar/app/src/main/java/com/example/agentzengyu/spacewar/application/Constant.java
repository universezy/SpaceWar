package com.example.agentzengyu.spacewar.application;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 常量
 */
public class Constant {
    //广播
    public static class BroadCast{
        public final static String STATE = "STATE";
        public final static String LOADING = "LOADING";
        public final static String GAME = "GAME";
    }

    //菜单
    public static class Menu {
        public final static int MAIN = 0;
        public final static int GAME = 1;
        public final static int SHOP = 2;
        public final static int SETTING = 3;
    }

    //存档文件名
    public static class FileName {
        public final static String PLAYER = "player.xml";
        public final static String SHOP = "shop.xml";
        public final static String MAP = "map.xml";
        public final static String ENEMY = "enemy.xml";
    }

    //存档状态
    public static class Status {
        //进度
        public final static String PROGRESS = "PROGRESS";
        //损坏
        public final static String DESTROY = "DESTROY";
        //商店
        public final static String SHOP = "SHOP";
        //玩家
        public final static String PLAYER = "PLAYER";
        //地图
        public final static String MAP = "MAP";
        //敌人
        public final static String ENEMY = "ENEMY";
    }

    //基础数据存档
    public static class Basic {
        //存档类型
        public static class Type {
            public final static String SHOP = "shop";
            public final static String PLAYER = "player";
            public final static String SHIP = "ship";
            public final static String WEAPON = "weapon";
            public final static String MONEY = "money";
            public final static String SUCCESSFUL = "successful";
        }

        //存档item属性
        public static class Attribution {
            public final static String NAME = "name";
            public final static String VALUE = "value";
            public final static String LEVEL = "level";
            public final static String FEE = "fee";
            public final static String IMAGE = "image";
        }

        //存档item数组
        public static class Array {
            public final static String LIFE = "array-life";
            public final static String AGILITY = "array-agility";
            public final static String DEFENSE = "array-defense";
            public final static String SHIELD = "array-shield";
            public final static String POWER = "array-power";
            public final static String SPEED = "array-speed";
            public final static String RANGE = "array-range";
            public final static String BOMB = "array-bomb";
        }

        //存档Item
        public static class Item {
            public final static String LIFE = "life";
            public final static String AGILITY = "agility";
            public final static String DEFENSE = "defense";
            public final static String SHIELD = "shield";
            public final static String POWER = "power";
            public final static String SPEED = "speed";
            public final static String RANGE = "range";
            public final static String BOMB = "bomb";
        }
    }

    //地图存档
    public static class Map {

    }

    //敌军存档
    public static class Enemy {

    }

}
