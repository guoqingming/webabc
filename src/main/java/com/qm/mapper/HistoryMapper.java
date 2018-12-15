package com.qm.mapper;

import com.qm.domain.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistoryMapper {
    int deleteById(Long id);

    int insert(History record);

    int insertSelective(History record);

    History getById(Long id);

    int updateByIdSelective(History record);

    int updateById(History record);

    List<History> findAll(@Param("historyNode") String historyNode);

//    @Select("select * from histories\n" +
//            "    order by change_date desc")
    List<History> queryHistories();
}