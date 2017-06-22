package com.example.agentzengyu.spacewar.util;

import android.util.Xml;

import com.example.agentzengyu.spacewar.application.Config;
import com.example.agentzengyu.spacewar.entity.ShopItem;
import com.example.agentzengyu.spacewar.entity.User;

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
 * 用户数据处理类
 */
public class UserDataHandler {
    private User user = null;
    private File file = null;

    /**
     * 设置处理的数据参数
     *
     * @param user 用户对象
     * @param file 文件
     * @return
     */
    public UserDataHandler setResource(User user, File file) {
        this.user = user;
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
                    callBack.onStart("Start to save user data:");
                    xmlSerializer.startTag(null, Config.TAG_USER);

                    //战舰开始
                    xmlSerializer.startTag(null, Config.TAG_SPACESHIP);

                    //生命
                    xmlSerializer.startTag(null, Config.TAG_LIFE);
                    setAttributes(xmlSerializer, user.getLife());
                    xmlSerializer.endTag(null, Config.TAG_LIFE);

                    //防御
                    xmlSerializer.startTag(null, Config.TAG_DEFENSE);
                    setAttributes(xmlSerializer, user.getDefense());
                    xmlSerializer.endTag(null, Config.TAG_DEFENSE);

                    //敏捷
                    xmlSerializer.startTag(null, Config.TAG_AGILITY);
                    setAttributes(xmlSerializer, user.getAgility());
                    xmlSerializer.endTag(null, Config.TAG_AGILITY);

                    //护盾
                    xmlSerializer.startTag(null, Config.TAG_SHIELD);
                    setAttributes(xmlSerializer, user.getShield());
                    xmlSerializer.endTag(null, Config.TAG_SHIELD);

                    //战舰结束
                    xmlSerializer.endTag(null, Config.TAG_SPACESHIP);

                    //武器开始
                    xmlSerializer.startTag(null, Config.TAG_WEAPON);

                    //力量
                    xmlSerializer.startTag(null, Config.TAG_POWER);
                    setAttributes(xmlSerializer, user.getPower());
                    xmlSerializer.endTag(null, Config.TAG_POWER);

                    //速度
                    xmlSerializer.startTag(null, Config.TAG_SPEED);
                    setAttributes(xmlSerializer, user.getSpeed());
                    xmlSerializer.endTag(null, Config.TAG_SPEED);

                    //范围
                    xmlSerializer.startTag(null, Config.TAG_RANGE);
                    setAttributes(xmlSerializer, user.getRange());
                    xmlSerializer.endTag(null, Config.TAG_RANGE);

                    //核弹
                    xmlSerializer.startTag(null, Config.TAG_NUCLEAR);
                    setAttributes(xmlSerializer, user.getNuclear());
                    xmlSerializer.endTag(null, Config.TAG_NUCLEAR);

                    //武器结束
                    xmlSerializer.endTag(null, Config.TAG_WEAPON);

                    xmlSerializer.endTag(null, Config.TAG_SHOP);

                    xmlSerializer.endDocument();
                    fos.close();
                    callBack.onSuccess("Save user data successful.");
                } catch (FileNotFoundException e) {
                    callBack.onFailure("Save user data abortively.", e);
                } catch (IOException e) {
                    callBack.onFailure("Save user data abortively.", e);
                }
            }
        }).start();
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
            xmlSerializer.attribute(null, Config.TAG_DETAIL, "" + item.getDetail());
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
                                callBack.onStart("Start to read user data:");
                                break;
                            case XmlPullParser.START_TAG:
                                String tagName = xmlPullParser.getName();
                                ShopItem item = new ShopItem();
                                getAttributes(xmlPullParser, item);
                                switch (tagName) {
                                    case Config.TAG_LIFE:
                                        user.setLife(item);
                                        break;
                                    case Config.TAG_DEFENSE:
                                        user.setDefense(item);
                                        break;
                                    case Config.TAG_AGILITY:
                                        user.setAgility(item);
                                        break;
                                    case Config.TAG_SHIELD:
                                        user.setShield(item);
                                        break;
                                    case Config.TAG_POWER:
                                        user.setPower(item);
                                        break;
                                    case Config.TAG_SPEED:
                                        user.setSpeed(item);
                                        break;
                                    case Config.TAG_RANGE:
                                        user.setRange(item);
                                        break;
                                    case Config.TAG_NUCLEAR:
                                        user.setNuclear(item);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case XmlPullParser.TEXT:
//                                Log.e("UserDataHandler", xmlPullParser.getText() + "");
                                break;
                            case XmlPullParser.END_TAG:
                                break;
                            default:
                                break;
                        }
                        eventType = xmlPullParser.next();
                    }
                    callBack.onSuccess("Read user data successful.");
                } catch (FileNotFoundException e) {
                    callBack.onFailure("Read user data abortively.", e);
                } catch (XmlPullParserException e) {
                    callBack.onFailure("Read user data abortively.", e);
                } catch (IOException e) {
                    callBack.onFailure("Read user data abortively.", e);
                }
            }
        }).start();
    }

    /**
     * 读档时获取属性
     *
     * @param xmlPullParser 解析器
     * @param item          item对象
     */

    private void getAttributes(XmlPullParser xmlPullParser, ShopItem item) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            switch (attributeName) {
                case Config.TAG_NAME:
                    item.setName(attributeValue);
                    break;
                case Config.TAG_DETAIL:
                    item.setDetail(Integer.parseInt(attributeValue));
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
