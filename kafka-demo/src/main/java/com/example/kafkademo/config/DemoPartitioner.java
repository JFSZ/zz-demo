package com.example.kafkademo.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  * @Description: 自定义分区器
 *  * @Author: chenxue
 *  * @Date: 2020-11-02 11:14
 *  * @since
 *  
 */
@Component
public class DemoPartitioner implements Partitioner {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    /**
     * @Description: 如果在key 为null时，默认使用 轮询策略推送消息到，可用的分区。
     * 如果想在key为null时分配这些不可用分区的话。需要自定义分区器
     * @param topic 主题
     * @param key 健
     * @param keyBytes 序列化之后的健
     * @param value 值
     * @param valueBytes 序列化之后的值
     * @param cluster 集群的元数据
     * @Author: chenxue
     * @Date: 2020-11-02 11:15
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //获取分区列表
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int length = partitionInfos.size();
        if(null == keyBytes){
            return atomicInteger.getAndIncrement() % length;
        }else {
            return Utils.toPositive(Utils.murmur2(keyBytes)) %  length;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
