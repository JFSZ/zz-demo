package com.example.kafkademo;

import com.example.kafkademo.config.KafkaProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class KafkaDemoApplication {

    @Autowired
    private KafkaProduct  kafkaProduct;
    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }

    @PostConstruct
    public void init() throws JsonProcessingException {
        for (int i = 0; i < 10; i++) {
            kafkaProduct.sendMsg();
        }
    }

}
