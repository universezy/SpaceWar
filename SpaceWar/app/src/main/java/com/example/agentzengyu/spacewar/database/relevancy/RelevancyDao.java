package com.example.agentzengyu.spacewar.database.relevancy;

import com.example.agentzengyu.spacewar.entity.basic.set.RelevancyLibrary;
import com.example.agentzengyu.spacewar.entity.basic.single.Relevancy;

/**
 * Created by Agent ZengYu on 2017/7/20.
 */

/**
 * 关联数据库接口
 */
public interface RelevancyDao {
    /**
     * 插入一条关联数据
     *
     * @param relevancy
     */
    void insert(Relevancy relevancy);

    /**
     * 更新一条关联数据
     *
     * @param relevancy
     */
    void update(Relevancy relevancy);

    /**
     * 删除一条关联数据
     *
     * @param relevancy
     */
    void delete(Relevancy relevancy);

    /**
     * 查找全部关联数据
     *
     * @return
     */
    RelevancyLibrary findAll();

    /**
     * 关闭连接
     */
    void close();

    /**
     * 销毁数据库
     */
    void destroy();
}
