package com.qm.web;

import com.github.pagehelper.PageInfo;
import com.qm.domain.ConfigItem;
import com.qm.service.ConfigItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: result-demo
 * @description: 配置项维护
 * @author: guoqingming
 * @create: 2018-12-15 16:07
 **/

@RestController
@Slf4j
@Api(tags = "配置项维护")
@RequestMapping("/config")
public class ConfigItemController {

    @Autowired
    private ConfigItemService configItemService;

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public PageInfo<ConfigItem> configItemList(@ApiParam(value = "应用名") @RequestParam(required = false)String appName, @ApiParam(value = "环境标识") @RequestParam(required = false)String profile) {
        return null;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加配置项")
    public void addItem(ConfigItem configItem) {
        configItemService.addConfigItem(configItem);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除配置项")
    public void deleteItem(@ApiParam(value = "配置项ID") @RequestParam(required = false)Integer id) {
        configItemService.deleteItem(id);
    }

    @PostMapping("/encript")
    @ApiOperation(value = "加密配置项")
    public void encriptItem(@ApiParam(value = "配置项ID") @RequestParam(required = false)Integer id, @ApiParam(value = "值") @RequestParam(required = false)String value) {
        configItemService.encriptValue(id,value);
    }

    @PostMapping("/decript")
    @ApiOperation(value = "解密配置项")
    public void decriptItem(@ApiParam(value = "配置项ID") @RequestParam(required = false)Integer id, @ApiParam(value = "值") @RequestParam(required = false)String value) {
        configItemService.decriptValue(id,value);
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入配置")
    public void importConfigFile(@ApiParam(value = "文件") @RequestParam(required = false)MultipartFile file, @ApiParam(value = "应用名") @RequestParam(required = false)String appName, @ApiParam(value = "环境标识") @RequestParam(required = false)String profile) throws Exception {
        configItemService.importConfigItems(file,appName,profile);
    }
}
