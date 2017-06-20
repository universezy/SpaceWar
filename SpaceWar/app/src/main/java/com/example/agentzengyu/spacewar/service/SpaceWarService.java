package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.BasicData;
import com.example.agentzengyu.spacewar.entity.SpaceshipAgility;
import com.example.agentzengyu.spacewar.entity.SpaceshipDefense;
import com.example.agentzengyu.spacewar.entity.SpaceshipLife;
import com.example.agentzengyu.spacewar.entity.SpaceshipShield;
import com.example.agentzengyu.spacewar.entity.WeaponNuclear;
import com.example.agentzengyu.spacewar.entity.WeaponPower;
import com.example.agentzengyu.spacewar.entity.WeaponRange;
import com.example.agentzengyu.spacewar.entity.WeaponSpeed;

/**
 * 太空大战服务
 */
public class SpaceWarService extends Service {
    private SpaceWarApp app = null;
    private BasicData data = new BasicData();

    @Override
    public void onCreate() {
        super.onCreate();
        app = (SpaceWarApp) getApplication();
        app.setService(this);
        initUserData();
        initBasicData();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void initUserData() {
        //TODO send broadcast


        //TODO read file
        SpaceshipLife life = new SpaceshipLife();
        SpaceshipDefense defense = new SpaceshipDefense();
        SpaceshipAgility agility = new SpaceshipAgility();
        SpaceshipShield shield = new SpaceshipShield();
        WeaponPower power = new WeaponPower();
        WeaponSpeed speed = new WeaponSpeed();
        WeaponRange range = new WeaponRange();
        WeaponNuclear nuclear = new WeaponNuclear();


        app.getUser().setLife(life);
        app.getUser().setDefense(defense);
        app.getUser().setAgility(agility);
        app.getUser().setShield(shield);
        app.getUser().setPower(power);
        app.getUser().setSpeed(speed);
        app.getUser().setRange(range);
        app.getUser().setNuclear(nuclear);
    }

    public void initBasicData() {
        //TODO send broadcast


        //TODO read file


    }

    public class ServiceBinder extends Binder {
    }
}
