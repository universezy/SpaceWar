package com.example.agentzengyu.spacewar.loader;

import android.util.Xml;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.set.AbstractLibrary;
import com.example.agentzengyu.spacewar.entity.set.ShopLibrary;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;

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
 * 商品加载器
 */
public class ShopLoader extends AbstractLoader {
    private ShopLibrary shopLibrary = null;

    public ShopLoader(AbstractLibrary abstractLibrary, File file, InputStream inputStream) {
        super(abstractLibrary, file, inputStream);
        this.shopLibrary = (ShopLibrary) abstractLibrary;
    }

    /**
     * 存档
     *
     * @param callBack 消息回调
     * @return
     */
//    private void save(final ILoaderCallback callBack) {
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
//                    for (ShopItem setShield : data.getShields()) {
//                        xmlSerializer.startTag(null, Constant.TAG_SHIELD);
//                        setAttributes(xmlSerializer, setShield);
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
//                    for (ShopItem nuclear : data.getLasers()) {
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
     * @param iLoaderCallback 消息回调
     */
    public void read(final ILoaderCallback iLoaderCallback) {
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
                                iLoaderCallback.onStart("Start to read shop data:");
                                break;
                            case XmlPullParser.START_TAG:
                                String startName = xmlPullParser.getName();
                                switch (startName) {
                                    case Constant.Basic.Item.LIFE:
                                        getAttributes(xmlPullParser, shopLibrary.getLives(), startName);
                                        break;
                                    case Constant.Basic.Item.DEFENSE:
                                        getAttributes(xmlPullParser, shopLibrary.getDefenses(), startName);
                                        break;
                                    case Constant.Basic.Item.AGILITY:
                                        getAttributes(xmlPullParser, shopLibrary.getAgilities(), startName);
                                        break;
                                    case Constant.Basic.Item.SHIELD:
                                        getAttributes(xmlPullParser, shopLibrary.getShields(), startName);
                                        break;
                                    case Constant.Basic.Item.POWER:
                                        getAttributes(xmlPullParser, shopLibrary.getPowers(), startName);
                                        break;
                                    case Constant.Basic.Item.SPEED:
                                        getAttributes(xmlPullParser, shopLibrary.getSpeeds(), startName);
                                        break;
                                    case Constant.Basic.Item.RANGE:
                                        getAttributes(xmlPullParser, shopLibrary.getRanges(), startName);
                                        break;
                                    case Constant.Basic.Item.LASER:
                                        getAttributes(xmlPullParser, shopLibrary.getLasers(), startName);
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
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.DEFENSE:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.AGILITY:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.SHIELD:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.POWER:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.SPEED:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.RANGE:
                                        iLoaderCallback.onProcess(++count);
                                        break;
                                    case Constant.Basic.Array.LASER:
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
                    iLoaderCallback.onSuccess("Read shop data successful.");
                } catch (FileNotFoundException e) {
                    iLoaderCallback.onFailure("Read shop data abortively.", e);
                } catch (XmlPullParserException e) {
                    iLoaderCallback.onFailure("Read shop data abortively.", e);
                } catch (IOException e) {
                    iLoaderCallback.onFailure("Read shop data abortively.", e);
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
            Thread.sleep(50);
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
