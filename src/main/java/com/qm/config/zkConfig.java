package com.qm.config;

import cn.hutool.setting.dialect.Props;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: result-demo
 * @description:
 * @author: guoqingming
 * @create: 2018-12-15 10:40
 **/
@Configuration
public class zkConfig {

    /**
     *
     */
    @Value("${zk.url}")
    private String zkurl;

    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkurl, retryPolicy);
        client.start();
        return client;
    }
}
