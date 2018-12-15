package com.qm;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@SpringBootApplication
@MapperScan("com.qm.**")
public class ResultDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(ResultDemoApplication.class, args);
    }


}
