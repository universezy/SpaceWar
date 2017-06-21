package com.example.agentzengyu.spacewar.util;

import android.util.Log;
import android.util.Xml;

import com.example.agentzengyu.spacewar.application.Config;
import com.example.agentzengyu.spacewar.entity.BasicData;
import com.example.agentzengyu.spacewar.entity.ShopItem;
import com.example.agentzengyu.spacewar.entity.SpaceshipAgility;
import com.example.agentzengyu.spacewar.entity.SpaceshipDefense;
import com.example.agentzengyu.spacewar.entity.SpaceshipLife;
import com.example.agentzengyu.spacewar.entity.SpaceshipShield;
import com.example.agentzengyu.spacewar.entity.WeaponNuclear;
import com.example.agentzengyu.spacewar.entity.WeaponPower;
import com.example.agentzengyu.spacewar.entity.WeaponRange;
import com.example.agentzengyu.spacewar.entity.WeaponSpeed;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Agent ZengYu on 2017/6/21.
 */

/**
 * 基础数据处理类
 */
public class BasicDataHandler {
    private BasicData data = null;
    private File file = null;

    /**
     * 设置处理的数据参数
     *
     * @param data 基础数据对象
     * @param file 文件
     * @return
     */
    public BasicDataHandler setResource(BasicData data, File file) {
        this.data = data;
        this.file = file;
        return this;
    }

    /**
     * 存档
     *
     * @return
     */
    public boolean save() {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            xmlSerializer.setOutput(fos, "utf-8");
            xmlSerializer.startDocument("utf-8", true);

            xmlSerializer.startTag(null, Config.TAG_SHOP);

            //战舰库开始
            xmlSerializer.startTag(null, Config.TAG_SPACESHIP);

            //生命库
            for (SpaceshipLife life : data.getLifes()) {
                xmlSerializer.startTag(null, Config.TAG_LIFE);
                setAttributes(xmlSerializer, life);
                xmlSerializer.endTag(null, Config.TAG_LIFE);
            }

            //防御库
            for (SpaceshipDefense defense : data.getDefenses()) {
                xmlSerializer.startTag(null, Config.TAG_DEFENSE);
                setAttributes(xmlSerializer, defense);
                xmlSerializer.endTag(null, Config.TAG_DEFENSE);
            }

            //敏捷库
            for (SpaceshipAgility agility : data.getAgilities()) {
                xmlSerializer.startTag(null, Config.TAG_AGILITY);
                setAttributes(xmlSerializer, agility);
                xmlSerializer.endTag(null, Config.TAG_AGILITY);
            }

            //护盾库
            for (SpaceshipShield shield : data.getShields()) {
                xmlSerializer.startTag(null, Config.TAG_SHIELD);
                setAttributes(xmlSerializer, shield);
                xmlSerializer.endTag(null, Config.TAG_SHIELD);
            }

            //战舰库结束
            xmlSerializer.endTag(null, Config.TAG_SPACESHIP);

            //武器库开始
            xmlSerializer.startTag(null, Config.TAG_WEAPON);

            //力量库
            for (WeaponPower power : data.getPowers()) {
                xmlSerializer.startTag(null, Config.TAG_POWER);
                setAttributes(xmlSerializer, power);
                xmlSerializer.endTag(null, Config.TAG_POWER);
            }

            //速度库
            for (WeaponSpeed speed : data.getSpeeds()) {
                xmlSerializer.startTag(null, Config.TAG_SPEED);
                setAttributes(xmlSerializer, speed);
                xmlSerializer.endTag(null, Config.TAG_SPEED);
            }

            //范围库
            for (WeaponRange range : data.getRanges()) {
                xmlSerializer.startTag(null, Config.TAG_RANGE);
                setAttributes(xmlSerializer, range);
                xmlSerializer.endTag(null, Config.TAG_RANGE);
            }

            //核弹库
            for (WeaponNuclear nuclear : data.getNuclears()) {
                xmlSerializer.startTag(null, Config.TAG_NUCLEAR);
                setAttributes(xmlSerializer, nuclear);
                xmlSerializer.endTag(null, Config.TAG_NUCLEAR);
            }

            //武器库结束
            xmlSerializer.endTag(null, Config.TAG_WEAPON);

            xmlSerializer.endTag(null, Config.TAG_SHOP);

            xmlSerializer.endDocument();
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存档时设置属性
     *
     * @param xmlSerializer 解析器对象
     * @param item          存档item
     */
    private void setAttributes(XmlSerializer xmlSerializer, ShopItem item) {
        try {
            xmlSerializer.attribute(null, Config.TAG_NAME, item.getName());
            xmlSerializer.attribute(null, Config.TAG_DETAIL, item.getDetail());
            xmlSerializer.attribute(null, Config.TAG_LEVEL, "" + item.getLevel());
            xmlSerializer.attribute(null, Config.TAG_FEE, "" + item.getFee());
            xmlSerializer.attribute(null, Config.TAG_IMAGE, "" + item.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读档
     *
     * @return
     */
    public boolean read() {
        try {
            FileInputStream fis = new FileInputStream(file);
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(fis, "utf-8");
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String tagName = xmlPullParser.getName();
                        ShopItemFactory factory = new ShopItemFactory(tagName);
                        ShopItem item = factory.createShopItem();
                        switch (tagName) {
                            case Config.TAG_LIFE:
                                getAttributes(xmlPullParser, data.getLifes(), item);
                                break;
                            case Config.TAG_DEFENSE:
                                getAttributes(xmlPullParser, data.getDefenses(), item);
                                break;
                            case Config.TAG_AGILITY:
                                getAttributes(xmlPullParser, data.getAgilities(), item);
                                break;
                            case Config.TAG_SHIELD:
                                getAttributes(xmlPullParser, data.getShields(), item);
                                break;
                            case Config.TAG_POWER:
                                getAttributes(xmlPullParser, data.getPowers(), item);
                                break;
                            case Config.TAG_SPEED:
                                getAttributes(xmlPullParser, data.getSpeeds(), item);
                                break;
                            case Config.TAG_RANGE:
                                getAttributes(xmlPullParser, data.getRanges(), item);
                                break;
                            case Config.TAG_NUCLEAR:
                                getAttributes(xmlPullParser, data.getNuclears(), item);
                                break;
                            default:
                                break;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        Log.e("BasicDataHandler", xmlPullParser.getText() + "");
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读档时获取属性
     *
     * @param xmlPullParser 解析器
     * @param items         item数组
     * @param item          item对象
     */
    private void getAttributes(XmlPullParser xmlPullParser, ArrayList<?> items, ShopItem item) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            switch (attributeName) {
                case Config.TAG_NAME:
                    item.setName(xmlPullParser.getAttributeValue(i));
                    break;
                case Config.TAG_DETAIL:
                    item.setDetail(xmlPullParser.getAttributeValue(i));
                    break;
                case Config.TAG_LEVEL:
                    item.setLevel(Integer.parseInt(xmlPullParser.getAttributeValue(i)));
                    break;
                case Config.TAG_FEE:
                    item.setFee(Integer.parseInt(xmlPullParser.getAttributeValue(i)));
                    break;
                case Config.TAG_IMAGE:
                    item.setImage(Integer.parseInt(xmlPullParser.getAttributeValue(i)));
                    break;
                default:
                    break;
            }
        }
        items.add(item);
    }
}
