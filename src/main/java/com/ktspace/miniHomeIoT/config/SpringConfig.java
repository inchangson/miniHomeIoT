//package com.ktspace.miniHomeIoT.config;
//
//import com.ktspace.miniHomeIoT.service.HomeIoTService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SpringConfig {
//    private DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public HomeIoTService homeIoTService() {
//        return new HomeIoTService(homeIoTRepository());
//    }
//
//}
