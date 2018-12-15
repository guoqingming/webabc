package com.qm.web;

import com.qm.utils.AesUtils;
import com.qm.utils.CheckUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: result-demo
 * @description:
 * @author: guoqingming
 * @create: 2018-12-12 15:25
 **/
@RestController
@Api(tags = "测试")
public class TestController  {

    @GetMapping("/test")
    @ApiOperation(value = "测试请求1")
    public String test(){

        CheckUtil.isBlank("","校验失败");
        return "hello world";
    }

    @GetMapping("/test1")
    @ApiOperation(value = "测试请求2")
    public void test1(@ApiParam(value = "姓名") @RequestParam(required = false) String name){

    }

    @GetMapping("/encript")
    public String encript(String value) {
       return AesUtils.encript(value);
    }

    @GetMapping("/decript")
    public String decript(String value) {
        return AesUtils.decript(value);
    }

    public static void main(String[] args) {

    }
}
