package com.mlk.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by malikai on 2018-4-17.
 */
@ImportResource(value = { "classpath:spring/applicationContext.xml" })
@EnableTransactionManagement // 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
//注册动态多数据源
//@Import({DynamicDataSourceRegister.class})
@SpringBootApplication(scanBasePackages = {"com.mlk"})
public class ApplicationService {
    public static void main(String[] args){
        SpringApplication.run(ApplicationService.class);
    }
}
