package com.qm.mapper;

import com.qm.domain.ConfigItem;
import org.apache.ibatis.annotations.Param;

public interface ConfigItemMapper {
    int deleteById(Integer id);

    int insert(ConfigItem record);

    int insertSelective(ConfigItem record);

    ConfigItem getById(Integer id);

    int updateByIdSelective(ConfigItem record);

    int updateById(ConfigItem record);

    /**
     * 根据ID逻辑删除配置项
     * @param id
     * @return
     */
    int logicDelete(Integer id);

    /**
     * 根据应用名逻辑删除配置项
     * @param appName  应用英文名
     * @return
     */
    int logicDeleteByAppName(String appName);

    /**
     * 配置项加密
     * @param id
     * @param encriptValue 加密后的字符串
     * @return
     */
    int encriptValue(@Param("id") Integer id, @Param("encriptValue") String encriptValue);

    /**
     * 配置项解密
     * @param id
     * @param decriptValue
     * @return
     */
    int decriptValue(@Param("id") Integer id, @Param("decriptValue") String decriptValue);

    /**
     *
     * @param appId
     * @param key
     * @return
     */
    int countByAppIdKey(@Param("appId") Integer appId, @Param("key") String key);

    Integer queryItemIdByAppIdKey(@Param("appId") Integer appId, @Param("key") String key);

    ConfigItem queryItemByAppIdKey(@Param("appId") Integer appId, @Param("key") String key);
    /**
     * 更新配置项的值
     * @param configItem
     * @return
     */
    int updateItemValue(ConfigItem configItem);



}