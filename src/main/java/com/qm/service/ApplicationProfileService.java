package com.qm.service;

import cn.hutool.core.collection.CollectionUtil;
import com.qm.domain.ApplicationProfile;
import com.qm.domain.ConfigItem;
import com.qm.enums.Profile;
import com.qm.mapper.ApplicationProfileMapper;
import com.qm.mapper.ConfigItemMapper;
import com.qm.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: result-demo
 * @description:
 * @author: guoqingming
 * @create: 2018-12-15 14:56
 **/
@Service
@Slf4j
public class ApplicationProfileService {

    @Resource
    private ApplicationProfileMapper applicationProfileMapper;

    @Resource
    private ConfigItemMapper configItemMapper;

    /**
     * 新增应用
     * @param appName
     * @param appDesc
     */
    public void addApp(String appName,String appDesc) {

        CheckUtil.isBlank(appName, "应用英文名为空");
        CheckUtil.isBlank(appDesc, "应用中文名为空");
        int count = applicationProfileMapper.countByAppName(appName);
        CheckUtil.isTrue(count >0,"应用名【"+appName+"】已存在");
        ApplicationProfile applicationProfile = new ApplicationProfile();
        applicationProfile.setAppName(appName);
        applicationProfile.setAppDesc(appDesc);
        applicationProfile.setCreateAt(new Date());
        for (Profile profile : Profile.values()) {
            applicationProfile.setProfile(profile.name().toLowerCase());
            applicationProfileMapper.insertSelective(applicationProfile);
        }

    }

    /**
     * 删除应用
     * @param appName
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteApp(String appName) {
        CheckUtil.isBlank(appName,"传入应用名为空");
        applicationProfileMapper.logicDelete(appName);
        List<ApplicationProfile> apList = applicationProfileMapper.listByAppName(appName);
        configItemMapper.logicDeleteByAppName(appName);
    }
}
