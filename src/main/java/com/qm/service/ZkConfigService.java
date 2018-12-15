package com.qm.service;

import com.qm.domain.ConfigItem;
import com.qm.enums.Profile;
import com.qm.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: result-demo
 * @description: 维护配置
 * @author: guoqingming
 * @create: 2018-12-15 10:58
 **/
@Service
@Slf4j
public class ZkConfigService {

    public static final String PATH_PREFIX = "/config/";
    @Autowired
    private CuratorFramework client;

    /**
     * 创建项目
     * @param appName
     * @throws Exception
     */
    public void addProject(String appName,String appDesc) throws Exception {
        CheckUtil.isBlank(appName,"传入的appName为空");
        client.create().forPath(PATH_PREFIX + appName);
        client.create().forPath(PATH_PREFIX + appName+"/"+ Profile.DEV.name());
        client.create().forPath(PATH_PREFIX + appName+"/"+ Profile.TEST.name());
        client.create().forPath(PATH_PREFIX + appName+"/"+ Profile.PROD.name());

    }

    /**
     * 删除项目
     * @param appName
     * @throws Exception
     */
    public void deleteProject(String appName) throws Exception {
        CheckUtil.isBlank(appName,"传入的appName为空");
        client.delete().forPath(PATH_PREFIX + appName);

    }

    public void addConfigItem(ConfigItem item) throws Exception {
        String path = PATH_PREFIX + item.getAppName() +"/"+ item.getProfile() + "/" +item.getKey();
        client.create().forPath(path);
        client.setData().forPath(path,item.getValue().getBytes());
    }

    public void deleteConfigItem(String appName,String profile,String key) throws Exception {
        String path = PATH_PREFIX + appName +"/"+ profile + "/" +key;
        client.delete().forPath(path);
    }

    public void updateItemValue(ConfigItem item) throws Exception {
        String path = PATH_PREFIX + item.getAppName() +"/"+ item.getProfile() + "/" +item.getKey();
        client.setData().forPath(path,item.getValue().getBytes());
    }

}
