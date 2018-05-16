package com.mlk.home;

import com.mlk.home.common.cache.RedisUtil;
import com.mlk.home.mqConfig.Producer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.core.JmsMessagingTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.jms.Destination;
import javax.jms.Queue;

/**
 * Created by malikai on 2018-4-17.
 */
@SpringBootApplication
@ImportResource(value = { "classpath:applicationContext.xml" })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class})
@EnableSwagger2
public class ApplicationWeb implements CommandLineRunner {
    /*@Bean
    public Queue queue() {
        return new ActiveMQQueue("mytest.queue");
    }
    @Bean
    public Queue queue1() {
        return new ActiveMQQueue("mytest.queue1");
    }
    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(ActiveMQConnectionFactory connectionFactory){
        System.out.println("get JmsMessagingTemplate");
        return new JmsMessagingTemplate(connectionFactory);
    }*/
   /* @Bean
    public ActiveMQConnectionFactory connectionFactory() {
//此链接信息可放入配置文件中
        return new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
    }*/
    private final static Logger logger = LoggerFactory.getLogger(ApplicationWeb.class);
    public static void main(String[] args){
        SpringApplication.run(ApplicationWeb.class);
    }
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private Producer producer;
    @Override
    public void run(String... args) throws Exception {
        logger.info("开始加载值集存入内存");
        //redisUtil.set("mlk","malikai");
        //logger.info("mlk====="+redisUtil.get("mlk"));
        logger.info("值集加载完成。。。。。");
    }
}
