package com.example.agentzengyu.spacewar.loader;

import android.util.Xml;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.set.AbstractLibrary;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.set.ShopLibrary;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Agent ZengYu on 2017/6/21.
 */

/**
 * 玩家加载器
 */
public class PlayerLoader extends AbstractLoader {
    private PlayerData playerData = null;

    public PlayerLoader(AbstractLibrary abstractLibrary, File file, InputStream inputStream) {
        super(abstractLibrary, file, inputStream);
        this.playerData = (PlayerData) abstractLibrary;
    }

    /**
     * 存档
     *
     * @param callBack 消息回调
     * @return
     */
    public void save(final ILoaderCallback callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    XmlSerializer xmlSerializer = Xml.newSerializer();
                    xmlSerializer.setOutput(fos, "utf-8");
                    xmlSerializer.startDocument("utf-8", true);
                    int count = 0;
                    callBack.onStart("Start to save player data:");
                    xmlSerializer.startTag(null, Constant.Basic.Type.PLAYER);

                    //战舰开始
                    xmlSerializer.startTag(null, Constant.Basic.Type.SHIP);

                    //生命
                    xmlSerializer.startTag(null, Constant.Basic.Item.LIFE);
                    setAttributes(xmlSerializer, playerData.getLife());
                    xmlSerializer.endTag(null, Constant.Basic.Item.LIFE);
                    callBack.onProcess(++count);
                    //防御
                    xmlSerializer.startTag(null, Constant.Basic.Item.DEFENSE);
                    setAttributes(xmlSerializer, playerData.getDefense());
                    xmlSerializer.endTag(null, Constant.Basic.Item.DEFENSE);
                    callBack.onProcess(++count);
                    //敏捷
                    xmlSerializer.startTag(null, Constant.Basic.Item.AGILITY);
                    setAttributes(xmlSerializer, playerData.getAgility());
                    xmlSerializer.endTag(null, Constant.Basic.Item.AGILITY);
                    callBack.onProcess(++count);
                    //护盾
                    xmlSerializer.startTag(null, Constant.Basic.Item.SHIELD);
                    setAttributes(xmlSerializer, playerData.getShield());
                    xmlSerializer.endTag(null, Constant.Basic.Item.SHIELD);
                    callBack.onProcess(++count);
                    //战舰结束
                    xmlSerializer.endTag(null, Constant.Basic.Type.SHIP);

                    //武器开始
                    xmlSerializer.startTag(null, Constant.Basic.Type.WEAPON);

                    //力量
                    xmlSerializer.startTag(null, Constant.Basic.Item.POWER);
                    setAttributes(xmlSerializer, playerData.getPower());
                    xmlSerializer.endTag(null, Constant.Basic.Item.POWER);
                    callBack.onProcess(++count);
                    //速度
                    xmlSerializer.startTag(null, Constant.Basic.Item.SPEED);
                    setAttributes(xmlSerializer, playerData.getSpeed());
                    xmlSerializer.endTag(null, Constant.Basic.Item.SPEED);
                    callBack.onProcess(++count);
                    //范围
                    xmlSerializer.startTag(null, Constant.Basic.Item.RANGE);
                    setAttributes(xmlSerializer, playerData.getRange());
                    xmlSerializer.endTag(null, Constant.Basic.Item.RANGE);
                    callBack.onProcess(++count);
                    //核弹
                    xmlSerializer.startTag(null, Constant.Basic.Item.LASER);
                    setAttributes(xmlSerializer, playerData.getLaser());
                    xmlSerializer.endTag(null, Constant.Basic.Item.LASER);
                    callBack.onProcess(++count);
                    //武器结束
                    xmlSerializer.endTag(null, Constant.Basic.Type.WEAPON);

                    //金币
                    xmlSerializer.startTag(null, Constant.Basic.Type.MONEY);
                    xmlSerializer.attribute(null, Constant.Basic.Type.MONEY, "" + playerData.getMoney());
                    xmlSerializer.endTag(null, Constant.Basic.Type.MONEY);
                    callBack.onProcess(++count);
                    xmlSerializer.endTag(null, Constant.Basic.Type.PLAYER);

                    //校验位
                    xmlSerializer.startTag(null, Constant.Basic.Type.SUCCESSFUL);
                    xmlSerializer.attribute(null, Constant.Basic.Type.SUCCESSFUL, Constant.Basic.Type.SUCCESSFUL);
                    xmlSerializer.endTag(null, Constant.Basic.Type.SUCCESSFUL);

                    xmlSerializer.endDocument();
                    fos.close();
                    callBack.onSuccess("Save player data successful.");
                } catch (FileNotFoundException e) {
                    callBack.onFailure("Save player data abortively.", e);
                } catch (IOException e) {
                    callBack.onFailure("Save player data abortively.", e);
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
            xmlSerializer.attribute(null, Constant.Basic.Attribution.NAME, item.getName());
            xmlSerializer.attribute(null, Constant.Basic.Attribution.VALUE, "" + item.getValue());
            xmlSerializer.attribute(null, Constant.Basic.Attribution.LEVEL, "" + item.getLevel());
            xmlSerializer.attribute(null, Constant.Basic.Attribution.FEE, "" + item.getFee());
            xmlSerializer.attribute(null, Constant.Basic.Attribution.IMAGE, "" + item.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化玩家数据
     *
     * @param callBack 消息回调
     * @param data     商店数据
     */
    public void init(final ILoaderCallback callBack, final ShopLibrary data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    callBack.onStart("Start to initialize player data:");
                    int count = 0;
                    playerData.setLife(data.getLives().get(0));
                    callBack.onProcess(++count);
                    Thread.sleep(200);
                    playerData.setDefense(data.getDefenses().get(0));
                    callBack.onProcess(++count);
                    Thread.sleep(200);
                    playerData.setAgility(data.getAgilities().get(0));
                    callBack.onProcess(++count);
                    Thread.sleep(200);
                    playerData.setShield(data.getShields().get(0));
                    callBack.onProcess(++count);
                    Thread.sleep(200);
                    playerData.setPower(data.getPowers().get(0));
                    callBack.onProcess(++count);
                    Thread.sleep(200);
                    playerData.setSpeed(data.getSpeeds().get(0));
                    callBack.onProcess(++count);
                    Thread.sleep(200);
                    playerData.setRange(data.getRanges().get(0));
                    callBack.onProcess(++count);
                    Thread.sleep(200);
                    playerData.setLaser(data.getLasers().get(0));
                    callBack.onProcess(++count);
                    Thread.sleep(200);
                    playerData.setMoney(1000);
                    callBack.onProcess(++count);
                    Thread.sleep(200);
                    callBack.onSuccess("Initialize player data successful.");
                } catch (InterruptedException e) {
                    callBack.onFailure("Initialize player data abortively.", e);
                }
            }
        }).start();
    }

    /**
     * 读档
     *
     * @param iLoaderCallback 消息回调
     * @return
     */
    public void read(final ILoaderCallback iLoaderCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    XmlPullParser xmlPullParser = Xml.newPullParser();
                    xmlPullParser.setInput(fis, "utf-8");
                    int eventType = xmlPullParser.getEventType();
                    int count = 0;
                    boolean success = false;
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                iLoaderCallback.onStart("Start to read player data:");
                                break;
                            case XmlPullParser.START_TAG:
                                String startName = xmlPullParser.getName();
                                ShopItem item;
                                switch (startName) {
                                    case Constant.Basic.Item.LIFE:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setLife(item);
                                        break;
                                    case Constant.Basic.Item.DEFENSE:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setDefense(item);
                                        break;
                                    case Constant.Basic.Item.AGILITY:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setAgility(item);
                                        break;
                                    case Constant.Basic.Item.SHIELD:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setShield(item);
                                        break;
                                    case Constant.Basic.Item.POWER:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setPower(item);
                                        break;
                                    case Constant.Basic.Item.SPEED:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setSpeed(item);
                                        break;
                                    case Constant.Basic.Item.RANGE:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setRange(item);
                                        break;
                                    case Constant.Basic.Item.LASER:
                                        item = new ShopItem();
                                        getAttributes(xmlPullParser, item);
                                        playerData.setLaser(item);
                                        break;
                                    case Constant.Basic.Type.MONEY:
                                        playerData.setMoney(Integer.parseInt(xmlPullParser.getAttributeValue(0)));
                                        break;
                                    case Constant.Basic.Type.SUCCESSFUL:
                                        if (Constant.Basic.Type.SUCCESSFUL.equals(xmlPullParser.getAttributeValue(0))) {
                                            success = true;
                                        }
                                    default:
                                        break;
                                }
                                break;
                            case XmlPullParser.TEXT:
//                                Log.e("PlayerLoader", xmlPullParser.getText() + "");
                                break;
                            case XmlPullParser.END_TAG:
                                String endName = xmlPullParser.getName();
                                switch (endName) {
                                    case Constant.Basic.Item.LIFE:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Item.DEFENSE:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Item.AGILITY:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Item.SHIELD:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Item.POWER:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Item.SPEED:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Item.RANGE:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Item.LASER:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Type.MONEY:
                                        playerData.setMoney(Integer.parseInt(xmlPullParser.getAttributeValue(0)));
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            default:
                                break;
                        }
                        eventType = xmlPullParser.next();
                    }
                    if (success) {
                        iLoaderCallback.onSuccess("Read player data successful.");
                    } else {
                        iLoaderCallback.onFailure(Constant.Status.DESTROY, null);
                    }
                } catch (FileNotFoundException e) {
                    iLoaderCallback.onFailure("Read player data abortively.", e);
                } catch (XmlPullParserException e) {
                    iLoaderCallback.onFailure("Read player data abortively.", e);
                } catch (IOException e) {
                    iLoaderCallback.onFailure("Read player data abortively.", e);
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
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            switch (attributeName) {
                case Constant.Basic.Attribution.NAME:
                    item.setName(attributeValue);
                    break;
                case Constant.Basic.Attribution.VALUE:
                    item.setValue(Integer.parseInt(attributeValue));
                    break;
                case Constant.Basic.Attribution.LEVEL:
                    item.setLevel(Integer.parseInt(attributeValue));
                    break;
                case Constant.Basic.Attribution.FEE:
                    item.setFee(Integer.parseInt(attributeValue));
                    break;
                case Constant.Basic.Attribution.IMAGE:
                    item.setImage(Integer.parseInt(attributeValue));
                    break;
                default:
                    break;
            }
        }
    }
}
