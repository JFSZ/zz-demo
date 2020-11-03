package com.example.kafkademo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *  * @Description: kafka消费者
 *  * @Author: chenxue
 *  * @Date: 2020-10-31 14:51
 *  * @since
 *  
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "kafka-demo")
    public void consumerMsg(ConsumerRecord<?,?> consumerRecord){
        Object value = consumerRecord.value();
        log.info("kafka 消费者接受的消息为:{}",value);
    }

}
