package com.jedrek.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 授权缓存类，用于保存moduleCode和licenceCode键值对
 * 缓存不存在，则会去授权文件中查找
 * Created by wangjie22438 on 2017/8/31.
 */
@Component
public class LicenceCache {

    private LoadingCache<String,String> cache = CacheBuilder.newBuilder()
            .maximumSize(100)  //最大容量
            .expireAfterAccess(10, TimeUnit.HOURS) //上一次访问超过10小时清除
            .expireAfterWrite(10, TimeUnit.HOURS) //上一次更改超过10小时清除
            .recordStats() //记录状态
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    //缓存未找到去文件中查找
                    LicenceFile licenceFile = new LicenceFile();
                    return licenceFile.getLicenceCode(key);
                }
            });

    /**
     * 向缓存中取出value
     * @param key
     * @return
     */
    public String get(String key) {
        try {
            String value = cache.get(key);
            return value;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向缓存存入键值对
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        cache.put(key,value);
    }
}
