package com.example.agentzengyu.spacewar.util;

import android.util.Xml;

import com.example.agentzengyu.spacewar.application.Config;
import com.example.agentzengyu.spacewar.entity.PlayerData;
import com.example.agentzengyu.spacewar.entity.ShopItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Agent ZengYu on 2017/6/21.
 */


/**
 * 玩家数据处理类
 */
public class PlayerDataHandler {
    private PlayerData playerData = null;
    private File file = null;

    /**
     * 设置处理的数据参数
     *
     * @param playerData 玩家数据对象
     * @param file 文件
     * @return
     */
    public PlayerDataHandler setResource(PlayerData playerData, File file) {
        this.playerData = playerData;
        this.file = file;
        return this;
    }

    /**
     * 存档
     *
     * @param callBack 消息回调
     * @return
     */
    public void save(final DataHandlerCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    XmlSerializer xmlSerializer = Xml.newSerializer();
                    xmlSerializer.setOutput(fos, "utf-8");
                    xmlSerializer.startDocument("utf-8", true);
                    callBack.onStart("Start to save playerData data:");
                    xmlSerializer.startTag(null, Config.TAG_PLAYER);

                    //战舰开始
                    xmlSerializer.startTag(null, Config.SHIP);

                    //生命
                    xmlSerializer.startTag(null, Config.TAG_LIFE);
                    setAttributes(xmlSerializer, playerData.getLife());
                    xmlSerializer.endTag(null, Config.TAG_LIFE);

                    //防御
                    xmlSerializer.startTag(null, Config.TAG_DEFENSE);
                    setAttributes(xmlSerializer, playerData.getDefense());
                    xmlSerializer.endTag(null, Config.TAG_DEFENSE);

                    //敏捷
                    xmlSerializer.startTag(null, Config.TAG_AGILITY);
                    setAttributes(xmlSerializer, playerData.getAgility());
                    xmlSerializer.endTag(null, Config.TAG_AGILITY);

                    //护盾
                    xmlSerializer.startTag(null, Config.TAG_SHIELD);
                    setAttributes(xmlSerializer, playerData.getShield());
                    xmlSerializer.endTag(null, Config.TAG_SHIELD);

                    //战舰结束
                    xmlSerializer.endTag(null, Config.SHIP);

                    //武器开始
                    xmlSerializer.startTag(null, Config.TAG_WEAPON);

                    //力量
                    xmlSerializer.startTag(null, Config.TAG_POWER);
                    setAttributes(xmlSerializer, playerData.getPower());
                    xmlSerializer.endTag(null, Config.TAG_POWER);

                    //速度
                    xmlSerializer.startTag(null, Config.TAG_SPEED);
                    setAttributes(xmlSerializer, playerData.getSpeed());
                    xmlSerializer.endTag(null, Config.TAG_SPEED);

                    //范围
                    xmlSerializer.startTag(null, Config.TAG_RANGE);
                    setAttributes(xmlSerializer, playerData.getRange());
                    xmlSerializer.endTag(null, Config.TAG_RANGE);

                    //核弹
                    xmlSerializer.startTag(null, Config.TAG_BOMB);
                    setAttributes(xmlSerializer, playerData.getBomb());
                    xmlSerializer.endTag(null, Config.TAG_BOMB);

                    //武器结束
                    xmlSerializer.endTag(null, Config.TAG_WEAPON);

                    xmlSerializer.endTag(null, Config.TAG_PLAYER);

                    xmlSerializer.endDocument();
                    fos.close();
                    callBack.onSuccess("Save playerData data successful.");
                } catch (FileNotFoundException e) {
                    callBack.onFailure("Save playerData data abortively.", e);
                } catch (IOException e) {
                    callBack.onFailure("Save playerData data abortively.", e);
                }
            }
        }).start();
    }

    /**
     * 存档时设置属性
     *
     * @param xmlSerializer 解析器对象
     * @param item          存档商品
     */
    private void setAttributes(XmlSerializer xmlSerializer, ShopItem item) {
        try {
            xmlSerializer.attribute(null, Config.TAG_NAME, item.getName());
            xmlSerializer.attribute(null, Config.TAG_VALUE, "" + item.getValue());
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
     * @param callBack 消息回调
     * @return
     */
    public void read(final DataHandlerCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    XmlPullParser xmlPullParser = Xml.newPullParser();
                    xmlPullParser.setInput(fis, "utf-8");
                    int eventType = xmlPullParser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                callBack.onStart("Start to read playerData data:");
                                break;
                            case XmlPullParser.START_TAG:
                                String tagName = xmlPullParser.getName();
                                ShopItem item;
                                switch (tagName) {
                                    case Config.TAG_LIFE:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setLife(item);
                                        break;
                                    case Config.TAG_DEFENSE:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setDefense(item);
                                        break;
                                    case Config.TAG_AGILITY:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setAgility(item);
                                        break;
                                    case Config.TAG_SHIELD:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setShield(item);
                                        break;
                                    case Config.TAG_POWER:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setPower(item);
                                        break;
                                    case Config.TAG_SPEED:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setSpeed(item);
                                        break;
                                    case Config.TAG_RANGE:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setRange(item);
                                        break;
                                    case Config.TAG_BOMB:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setBomb(item);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case XmlPullParser.TEXT:
//                                Log.e("PlayerDataHandler", xmlPullParser.getText() + "");
                                break;
                            case XmlPullParser.END_TAG:
                                break;
                            default:
                                break;
                        }
                        eventType = xmlPullParser.next();
                    }
                    callBack.onSuccess("Read playerData data successful.");
                } catch (FileNotFoundException e) {
                    callBack.onFailure("Read playerData data abortively.", e);
                } catch (XmlPullParserException e) {
                    callBack.onFailure("Read playerData data abortively.", e);
                } catch (IOException e) {
                    callBack.onFailure("Read playerData data abortively.", e);
                }
            }
        }).start();
    }

    /**
     * 读档时获取属性
     *
     * @param xmlPullParser 解析器
     * @param item          商品对象
     */
    private void getAttributes(XmlPullParser xmlPullParser, ShopItem item) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            switch (attributeName) {
                case Config.TAG_NAME:
                    item.setName(attributeValue);
                    break;
                case Config.TAG_VALUE:
                    item.setValue(Integer.parseInt(attributeValue));
                    break;
                case Config.TAG_LEVEL:
                    item.setLevel(Integer.parseInt(attributeValue));
                    break;
                case Config.TAG_FEE:
                    item.setFee(Integer.parseInt(attributeValue));
                    break;
                case Config.TAG_IMAGE:
                    item.setImage(Integer.parseInt(attributeValue));
                    break;
                default:
                    break;
            }
        }
    }
}
