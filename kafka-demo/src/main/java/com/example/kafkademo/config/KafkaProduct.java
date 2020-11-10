package com.example.kafkademo.config;

import com.alibaba.fastjson.JSON;
import com.example.kafkademo.bean.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 *  * @Description: kafka生产者
 *  * @Author: chenxue
 *  * @Date: 2020-10-31 14:36
 *  * @since
 *  
 */
@Component
@Slf4j
public class KafkaProduct {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public static String topic = "kafka-demo";

    public void sendMsg() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserEntity user = createUser();
        String s = JSON.toJSONString(user);
        kafkaTemplate.send(topic,"test");
        log.info("kafkaProduct 发送消息,发送消息为:{}",objectMapper.writeValueAsString("test"));

    }

    private UserEntity createUser(){
        Random random = new Random();
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Tom" + random.nextInt(10));
        userEntity.setAddress("北京第" + random.nextInt(100) + "大道");
        userEntity.setAge(random.nextInt(100));
        userEntity.setPhoneNum(random.nextInt(10000));
        return userEntity;
    }
}
