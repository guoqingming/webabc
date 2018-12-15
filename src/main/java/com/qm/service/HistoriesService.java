package com.qm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qm.domain.History;
import com.qm.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: zkui1
 * @description:
 * @author: guoqingming
 * @create: 2018-08-03 23:00
 **/

@Service
public class HistoriesService {

    @Resource
    private HistoryMapper historyMapper;


    public void insertHistory(String user, String ipAddress, String summary) {
            //To avoid errors due to truncation.
            if (summary.length() >= 500) {
                summary = summary.substring(0, 500);
            }
            History history = new History();
            history.setChangeUser(user);
            history.setChangeIp(ipAddress);
            history.setChangeSummary(summary);
            history.setChangeDate(new Date());
            historyMapper.insertSelective(history);

    }

    public List<History> fetchHistoryRecords() {
        return historyMapper.findAll(null);

    }

    public List<History> fetchHistoryRecordsByNode(String historyNode) {

        return historyMapper.findAll(historyNode);
    }

    /**
     * 分页查询历史
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<History> queryHistory(Integer pageNum, Integer pageSize,String keyWord) {
        PageHelper.startPage(pageNum, pageSize);
        List<History> list = historyMapper.findAll(keyWord);
        return new PageInfo<>(list);
    }
}
