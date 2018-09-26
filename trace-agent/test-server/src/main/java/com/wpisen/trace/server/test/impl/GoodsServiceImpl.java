package com.wpisen.trace.server.test.impl;/**
 * Created by Administrator on 2018/6/8.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.wpisen.trace.server.test.Goods;
import com.wpisen.trace.server.test.GoodsService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author wpisen
 *         Created by wpisen on 2018/6/8
 **/
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    JedisPool pool;

    @Override
    public Goods getGoods(Long goodsId) {
        Assert.isTrue(goodsId>10000,"goods id must  less than 10000");
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setGoodsName("路虎");
        goods.setDesc("排量：3.0T");
        try {
            goods.setShopInfo(getStringByRedis("shopInfo_" + goodsId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    }

    public JedisPool getPool() {
        return pool;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    public String getStringByRedis(String key) {
        Jedis redis = pool.getResource();
        try {
            return redis.get(key);
        } finally {
            redis.close();
        }
    }
}
