package com.mlk.home.mqConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;


/**
 * Created by malikai on 2018/4/22.
 */
@Component

@EnableScheduling

public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private Queue queue1;

    //@Scheduled(fixedDelay=3000)//每3s执行1次
    public void send() {
        this.jmsMessagingTemplate.convertAndSend(this.queue, "hi,activeMQ-queue");
        this.jmsMessagingTemplate.convertAndSend(this.queue1, "hi,activeMQ-queue1");

    }



}
