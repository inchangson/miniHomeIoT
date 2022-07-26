package com.ktspace.miniHomeIoT;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = "com.ktspace.miniHomeIoT")
public class MiniHomeIoTApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniHomeIoTApplication.class, args);
    }

}
