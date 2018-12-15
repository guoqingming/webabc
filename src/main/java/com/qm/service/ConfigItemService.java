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
import org.springframework.stereotype.Service;
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

    public void addConfigItem(ConfigItem configItem) {
        CheckUtil.isNull(configItem,"传入参数为空");
        CheckUtil.isBlank(configItem.getAppName(),"应用名为空");
        CheckUtil.isBlank(configItem.getProfile(),"环境参数为空");
        ApplicationProfile applicationProfile = applicationProfileMapper.queryByAppNameProfile(configItem.getAppName(), configItem.getProfile());
        CheckUtil.isNull(applicationProfile,"未找到相应的应用");
        configItem.setAppId(applicationProfile.getId());
        configItemMapper.insertSelective(configItem);
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
                configItemMapper.insertSelective(ci);

            }
        }
    }

    /**
     * 删除配置项
     * @param id
     */
    public void deleteItem(Integer id) {
        CheckUtil.isNull(id,"传入ID为空");
        configItemMapper.logicDelete(id);
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
}
