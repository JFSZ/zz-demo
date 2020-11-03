package com.example.kafkademo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *  * @Description: 生产者拦截器
 *  * @Author: chenxue
 *  * @Date: 2020-11-02 11:48
 *  * @since
 *  
 */
@Slf4j
@Component
public class ProducerInterceptorPrefix implements ProducerInterceptor {

    private volatile int successNum = 0;
    private volatile int failNum = 0;
    /**
     * @Description: 消息在序列化、计算分区之前会调用onSend，来对消息进行相应的定制化处理
     * @param record 返回ProducerRecord 类型
     * @Author: chenxue
     * @Date: 2020-11-02 12:50
     * @throws
     */
    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        String value = "prefix-" + record.value();
        return new ProducerRecord(record.topic(),record.partition(),record.timestamp(),record.key(),value,record.headers());
    }

    /**
     * @Description: 消息被应答或者消息发送失败时调用
     * @param metadata
     * @param exception
     * @Author: chenxue
     * @Date: 2020-11-02 13:08
     * @throws
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (null == exception) {
            successNum ++;
        }else {
            failNum ++;
        }
    }

    @Override
    public void close() {
        int successRatio = successNum / (failNum + successNum);
        log.info("发送成功率:{}", successRatio);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
