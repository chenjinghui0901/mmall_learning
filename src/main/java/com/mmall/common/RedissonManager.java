package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by geely
 */
@Component
@Slf4j
public class RedissonManager {

    private Config config = new Config();

    private Redisson redisson = null;

    public Redisson getRedisson() {
        return redisson;
    }

    private static String redisIp = PropertiesUtil.getProperty("redis.ip");
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));
    private static String redisPassword = PropertiesUtil.getProperty("redis.password");

    // 在bean初始化之后会调用
    @PostConstruct
    private void init(){
        try {
            config.useSingleServer().
                    setAddress(redisIp+ ":" + redisPort).setPassword(redisPassword);
            redisson = (Redisson) Redisson.create(config);

            log.info("初始化Redisson结束");

        } catch (Exception e) {
            log.error("redisson init error",e);
        }
    }



}
