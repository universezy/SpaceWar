package com.example.agentzengyu.spacewar.util;

import com.example.agentzengyu.spacewar.application.Config;
import com.example.agentzengyu.spacewar.entity.ShopItem;
import com.example.agentzengyu.spacewar.entity.SpaceshipAgility;
import com.example.agentzengyu.spacewar.entity.SpaceshipDefense;
import com.example.agentzengyu.spacewar.entity.SpaceshipLife;
import com.example.agentzengyu.spacewar.entity.SpaceshipShield;
import com.example.agentzengyu.spacewar.entity.WeaponNuclear;
import com.example.agentzengyu.spacewar.entity.WeaponPower;
import com.example.agentzengyu.spacewar.entity.WeaponRange;
import com.example.agentzengyu.spacewar.entity.WeaponSpeed;

/**
 * Created by Agent ZengYu on 2017/6/21.
 */

public class ShopItemFactory {
    String type = null;

    private ShopItemFactory() {

    }

    public ShopItemFactory(String type) {
        this.type = type;
    }

    public ShopItem createShopItem() {
        switch (type) {
            case Config.SPACESHIP_LIFE:
                return new SpaceshipLife(type);
            case Config.SPACESHIP_DEFENSE:
                return new SpaceshipDefense(type);
            case Config.SPACESHIP_AGILITY:
                return new SpaceshipAgility(type);
            case Config.SPACESHIP_SHIELD:
                return new SpaceshipShield(type);
            case Config.WEAPON_POWER:
                return new WeaponPower(type);
            case Config.WEAPON_SPEED:
                return new WeaponSpeed(type);
            case Config.WEAPON_RANGE:
                return new WeaponRange(type);
            case Config.WEAPON_NUCLEAR:
                return new WeaponNuclear(type);
            default:
                return null;
        }
    }
}
