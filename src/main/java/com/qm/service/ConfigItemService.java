package com.qm.service;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.qm.domain.ApplicationProfile;
import com.qm.domain.ConfigItem;
import com.qm.mapper.ApplicationProfileMapper;
import com.qm.mapper.ConfigItemMapper;
import com.qm.utils.AesUtils;
import com.qm.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @program: result-demo
 * @description:
 * @author: guoqingming
 * @create: 2018-12-15 14:57
 **/
@Service
@Slf4j
public class ConfigItemService {

    @Resource
    private ConfigItemMapper configItemMapper;

    @Resource
    private ApplicationProfileMapper applicationProfileMapper;

    @Autowired
    private ZkConfigService zkConfigService;

    /**
     * 添加配置项
     * @param configItem
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void addConfigItem(ConfigItem configItem) throws Exception {
        CheckUtil.isNull(configItem,"传入参数为空");
        CheckUtil.isBlank(configItem.getAppName(),"应用名为空");
        CheckUtil.isBlank(configItem.getProfile(),"环境参数为空");
        ApplicationProfile applicationProfile = applicationProfileMapper.queryByAppNameProfile(configItem.getAppName(), configItem.getProfile());
        CheckUtil.isNull(applicationProfile,"未找到相应的应用");
        configItem.setAppId(applicationProfile.getId());
        configItemMapper.insertSelective(configItem);
        zkConfigService.addConfigItem(configItem);
    }

    /**
     * 导入配置项目
     * @param appName 应用名
     * @param profile 环境
     * @param file 文件流
     */
    public void importConfigItems(MultipartFile file,String appName, String profile) throws Exception {
        CheckUtil.isNull(file,"文件为空");
        String fileName = file.getOriginalFilename();
        CheckUtil.isTrue(!fileName.endsWith(".properties"),"文件格式不正确");
        ApplicationProfile applicationProfile = applicationProfileMapper.queryByAppNameProfile(appName, profile);
        CheckUtil.isNull(applicationProfile,"未找到对应的应用");
        File tempFile = new File("temp.properties");
        FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
        PropertiesConfiguration configuration = new PropertiesConfiguration(tempFile);
        configuration.load();
        if (!configuration.isEmpty()) {
            Iterator keys = configuration.getKeys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = configuration.getString(key);
                ConfigItem ci = new ConfigItem();
                ci.setAppId(applicationProfile.getId());
                ci.setKey(key);
                ci.setValue(value);
                int count = configItemMapper.countByAppIdKey(applicationProfile.getId(), key);
                if(count == 1){
                    configItemMapper.updateItemValue(ci);
                }else {
                    configItemMapper.insertSelective(ci);
                }

            }
        }
    }

    /**
     * 删除配置项
     * @param appName
     * @param profile
     * @param key
     */
    public void deleteItem(String appName,String profile,String key) throws Exception {
        CheckUtil.isBlank(appName,"传入应用名为空");
        CheckUtil.isBlank(profile,"传入环境标识为空");
        CheckUtil.isBlank(key,"传入key为空");
        ApplicationProfile applicationProfile = applicationProfileMapper.queryByAppNameProfile(appName, profile);
        CheckUtil.isNull(applicationProfile,"未找到对应的应用");
        Integer itemId = configItemMapper.queryItemIdByAppIdKey(applicationProfile.getId(), key);
        CheckUtil.isNull(itemId,"未找到对应的配置项");
        configItemMapper.logicDelete(itemId);
        zkConfigService.deleteConfigItem(appName,profile,key);

    }

    /**
     * 配置项加密
     * @param id
     * @param value
     */
    public void encriptValue(Integer id, String value) {

        CheckUtil.isNull(id,"传入ID为空");
        CheckUtil.isBlank(value,"传入的value为空");
        String encriptValue = AesUtils.encript(value);
        configItemMapper.encriptValue(id, encriptValue);

    }

    /**
     * 配置项解密
     * @param id
     * @param value
     */
    public void decriptValue(Integer id, String value) {

        CheckUtil.isNull(id,"传入ID为空");
        CheckUtil.isBlank(value,"传入的value为空");
        String encriptValue = AesUtils.decript(value);
        configItemMapper.decriptValue(id, encriptValue);

    }

    /**
     * 修改配置value
     * @param item
     * @throws Exception
     */
    public void updateValue(ConfigItem item) throws Exception {
        CheckUtil.isBlank(item.getAppName(),"传入应用名为空");
        CheckUtil.isBlank(item.getProfile(),"传入环境标识为空");
        CheckUtil.isBlank(item.getKey(),"传入key为空");
        CheckUtil.isBlank(item.getValue(),"传入value为空");
        ApplicationProfile applicationProfile = applicationProfileMapper.queryByAppNameProfile(item.getAppName(), item.getProfile());
        CheckUtil.isNull(applicationProfile,"未找到对应的应用");
        Integer itemId = configItemMapper.queryItemIdByAppIdKey(applicationProfile.getId(), item.getKey());
        CheckUtil.isNull(itemId,"未找到对应的配置项");
        item.setId(itemId);
        configItemMapper.updateItemValue(item);
        zkConfigService.updateItemValue(item);

    }
}
