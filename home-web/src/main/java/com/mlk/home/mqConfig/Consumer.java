package com.mlk.home.mqConfig;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by malikai on 2018/4/22.
 */
@Component
public class Consumer {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
   /* @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到queue的报文为:"+text);
    }
    @JmsListener(destination = "mytest.queue1")
    public void receiveQueue1(String text) {
        System.out.println("Consumer收到queue1的报文为:"+text);
    }*/
}