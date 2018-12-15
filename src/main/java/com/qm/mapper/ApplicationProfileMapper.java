package com.qm.mapper;

import com.qm.domain.ApplicationProfile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplicationProfileMapper {
    int deleteById(Integer id);

    int insert(ApplicationProfile record);

    int insertSelective(ApplicationProfile record);

    ApplicationProfile getById(Integer id);

    int updateByIdSelective(ApplicationProfile record);

    int updateById(ApplicationProfile record);

    int logicDelete(String appName);

    /**
     * 根据应用名查询
     * @param appName 应用英文名
     * @return
     */
    List<ApplicationProfile> listByAppName(String appName);

    int countByAppName(String appName);

    ApplicationProfile queryByAppNameProfile(@Param("appName") String appName, @Param("profile") String profile);
}