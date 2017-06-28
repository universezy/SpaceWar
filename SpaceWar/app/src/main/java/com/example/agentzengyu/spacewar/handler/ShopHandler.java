package com.example.agentzengyu.spacewar.handler;

import android.util.Xml;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.Data;
import com.example.agentzengyu.spacewar.entity.ShopData;
import com.example.agentzengyu.spacewar.entity.ShopItem;
import com.example.agentzengyu.spacewar.others.HandlerCallBack;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Agent ZengYu on 2017/6/21.
 */

/**
 * 商店数据处理类
 */
public class ShopHandler extends DataHandler {
    private ShopData shopData = null;

    public ShopHandler(Data data, File file, InputStream inputStream) {
        super(data, file, inputStream);
        this.shopData = (ShopData) data;
    }

    /**
     * 存档
     *
     * @param callBack 消息回调
     * @return
     */
//    private void save(final HandlerCallBack callBack) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    FileOutputStream fos = new FileOutputStream(file);
//                    XmlSerializer xmlSerializer = Xml.newSerializer();
//                    xmlSerializer.setOutput(fos, "utf-8");
//                    xmlSerializer.startDocument("utf-8", true);
//                    xmlSerializer.startTag(null, Constant.TAG_SHOP);
//
//                    //战舰库开始
//                    xmlSerializer.startTag(null, Constant.TAG_SHIP);
//
//                    //生命库
//                    for (ShopItem life : data.getLives()) {
//                        xmlSerializer.startTag(null, Constant.TAG_LIFE);
//                        setAttributes(xmlSerializer, life);
//                        xmlSerializer.endTag(null, Constant.TAG_LIFE);
//                    }
//
//                    //防御库
//                    for (ShopItem defense : data.getDefenses()) {
//                        xmlSerializer.startTag(null, Constant.TAG_DEFENSE);
//                        setAttributes(xmlSerializer, defense);
//                        xmlSerializer.endTag(null, Constant.TAG_DEFENSE);
//                    }
//
//                    //敏捷库
//                    for (ShopItem agility : data.getAgilities()) {
//                        xmlSerializer.startTag(null, Constant.TAG_AGILITY);
//                        setAttributes(xmlSerializer, agility);
//                        xmlSerializer.endTag(null, Constant.TAG_AGILITY);
//                    }
//
//                    //护盾库
//                    for (ShopItem shield : data.getShields()) {
//                        xmlSerializer.startTag(null, Constant.TAG_SHIELD);
//                        setAttributes(xmlSerializer, shield);
//                        xmlSerializer.endTag(null, Constant.TAG_SHIELD);
//                    }
//
//                    //战舰库结束
//                    xmlSerializer.endTag(null, Constant.TAG_SHIP);
//
//                    //武器库开始
//                    xmlSerializer.startTag(null, Constant.TAG_WEAPON);
//
//                    //力量库
//                    for (ShopItem power : data.getPowers()) {
//                        xmlSerializer.startTag(null, Constant.TAG_POWER);
//                        setAttributes(xmlSerializer, power);
//                        xmlSerializer.endTag(null, Constant.TAG_POWER);
//                    }
//
//                    //速度库
//                    for (ShopItem speed : data.getSpeeds()) {
//                        xmlSerializer.startTag(null, Constant.TAG_SPEED);
//                        setAttributes(xmlSerializer, speed);
//                        xmlSerializer.endTag(null, Constant.TAG_SPEED);
//                    }
//
//                    //范围库
//                    for (ShopItem range : data.getRanges()) {
//                        xmlSerializer.startTag(null, Constant.TAG_RANGE);
//                        setAttributes(xmlSerializer, range);
//                        xmlSerializer.endTag(null, Constant.TAG_RANGE);
//                    }
//
//                    //核弹库
//                    for (ShopItem nuclear : data.getBombs()) {
//                        xmlSerializer.startTag(null, Constant.TAG_BOMB);
//                        setAttributes(xmlSerializer, nuclear);
//                        xmlSerializer.endTag(null, Constant.TAG_BOMB);
//                    }
//
//                    //武器库结束
//                    xmlSerializer.endTag(null, Constant.TAG_WEAPON);
//
//                    xmlSerializer.endTag(null, Constant.TAG_SHOP);
//
//                    xmlSerializer.endDocument();
//                    fos.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    /**
//     * 存档时设置属性
//     *
//     * @param xmlSerializer 解析器对象
//     * @param item          存档商品
//     */
//    private void setAttributes(XmlSerializer xmlSerializer, ShopItem item) {
//        try {
//            xmlSerializer.attribute(null, Constant.TAG_NAME, item.getName());
//            xmlSerializer.attribute(null, Constant.TAG_VALUE, ""+item.getValue());
//            xmlSerializer.attribute(null, Constant.TAG_LEVEL, "" + item.getLevel());
//            xmlSerializer.attribute(null, Constant.TAG_FEE, "" + item.getFee());
//            xmlSerializer.attribute(null, Constant.TAG_IMAGE, "" + item.getImage());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 读档
     *
     * @param callBack 消息回调
     */
    public void read(final HandlerCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    XmlPullParser xmlPullParser = Xml.newPullParser();
                    xmlPullParser.setInput(inputStream, "utf-8");
                    int eventType = xmlPullParser.getEventType();
                    int count = 0;
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                callBack.onStart("Start to read shop data:");
                                break;
                            case XmlPullParser.START_TAG:
                                String startName = xmlPullParser.getName();
                                switch (startName) {
                                    case Constant.Basic.Item.LIFE:
                                        getAttributes(xmlPullParser, shopData.getLives(), startName);
                                        break;
                                    case Constant.Basic.Item.DEFENSE:
                                        getAttributes(xmlPullParser, shopData.getDefenses(), startName);
                                        break;
                                    case Constant.Basic.Item.AGILITY:
                                        getAttributes(xmlPullParser, shopData.getAgilities(), startName);
                                        break;
                                    case Constant.Basic.Item.SHIELD:
                                        getAttributes(xmlPullParser, shopData.getShields(), startName);
                                        break;
                                    case Constant.Basic.Item.POWER:
                                        getAttributes(xmlPullParser, shopData.getPowers(), startName);
                                        break;
                                    case Constant.Basic.Item.SPEED:
                                        getAttributes(xmlPullParser, shopData.getSpeeds(), startName);
                                        break;
                                    case Constant.Basic.Item.RANGE:
                                        getAttributes(xmlPullParser, shopData.getRanges(), startName);
                                        break;
                                    case Constant.Basic.Item.BOMB:
                                        getAttributes(xmlPullParser, shopData.getBombs(), startName);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case XmlPullParser.TEXT:
                                break;
                            case XmlPullParser.END_TAG:
                                String endName = xmlPullParser.getName();
                                switch (endName) {
                                    case Constant.Basic.Array.LIFE:
                                        callBack.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.DEFENSE:
                                        callBack.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.AGILITY:
                                        callBack.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.SHIELD:
                                        callBack.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.POWER:
                                        callBack.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.SPEED:
                                        callBack.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.RANGE:
                                        callBack.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.BOMB:
                                        callBack.onProcess(++count);
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
                    callBack.onSuccess("Read shop data successful.");
                } catch (FileNotFoundException e) {
                    callBack.onFailure("Read shop data abortively.", e);
                } catch (XmlPullParserException e) {
                    callBack.onFailure("Read shop data abortively.", e);
                } catch (IOException e) {
                    callBack.onFailure("Read shop data abortively.", e);
                }
            }
        }).start();
    }

    /**
     * 读档时获取属性
     *
     * @param xmlPullParser 解析器
     * @param items         商品数组
     * @param endTagName    结束标志
     */
    private void getAttributes(XmlPullParser xmlPullParser, ArrayList<ShopItem> items, String endTagName) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            String tagName = "";
            while (!endTagName.equals(tagName)) {
                ShopItem item = new ShopItem();
                for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                    String name = xmlPullParser.getAttributeName(i);
                    String value = xmlPullParser.getAttributeValue(i);
                    switch (name) {
                        case Constant.Basic.Attribution.NAME:
                            item.setName(value);
                            break;
                        case Constant.Basic.Attribution.VALUE:
                            item.setValue(Integer.parseInt(value));
                            break;
                        case Constant.Basic.Attribution.LEVEL:
                            item.setLevel(Integer.parseInt(value));
                            break;
                        case Constant.Basic.Attribution.FEE:
                            item.setFee(Integer.parseInt(value));
                            break;
                        case Constant.Basic.Attribution.IMAGE:
                            item.setImage(Integer.parseInt(value));
                            break;
                        default:
                            break;
                    }
                }
                items.add(item);
                xmlPullParser.next();
                tagName = xmlPullParser.getName();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
