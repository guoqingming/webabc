package com.qm.web;

import com.qm.domain.ApplicationProfile;
import com.qm.service.ApplicationProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: result-demo
 * @description: 配置维护
 * @author: guoqingming
 * @create: 2018-12-15 10:55
 **/
@RestController
@Api(tags = "配置相关")
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationProfileService applicationProfileService;
   

    @PostMapping("/add")
    @ApiOperation(value = "新增应用")
    public void addApp(@ApiParam(value = "应用英文名") @RequestParam(required = false)String appName, @ApiParam(value = "应用中文名") @RequestParam(required = false)String appDesc){
        applicationProfileService.addApp(appName,appDesc);
    }


    @PostMapping("/delete")
    @ApiOperation(value = "删除应用")
    public void deleteApp(@ApiParam(value = "应用英文名") @RequestParam(required = false)String appName) {
        applicationProfileService.deleteApp(appName);
    }
}
