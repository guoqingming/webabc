package com.qm.web;

//import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInfo;
import com.qm.domain.History;
import com.qm.service.HistoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: result-demo
 * @description: 操作历史相关请求
 * @author: guoqingming
 * @create: 2018-12-14 23:22
 **/
@RestController
@Api(tags = "历史操作信息相关请求")
public class HistoryController {

    @Autowired
    private HistoriesService historiesService;

    @ApiOperation(value = "分页查询历史")
    @GetMapping("/historyList")
    public PageInfo<History> queryHistoryList(@ApiParam(value = "页码") @RequestParam(required = false)Integer pageNum, @ApiParam(value = "每页多少条") @RequestParam(required = false)Integer pageSize) {
        return historiesService.queryHistory(pageNum, pageSize);
    }
}
