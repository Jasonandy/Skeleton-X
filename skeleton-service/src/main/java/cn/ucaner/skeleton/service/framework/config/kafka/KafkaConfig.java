/*******************************************************************************
 * ~ Copyright (c) 2018 [jasonandy@hotmail.com | https://github.com/Jasonandy] *
 * ~                                                                           *
 * ~ Licensed under the Apache License, Version 2.0 (the "License”);           *
 * ~ you may not use this file except in compliance with the License.          *
 * ~ You may obtain a copy of the License at                                   *
 * ~                                                                           *
 * ~    http://www.apache.org/licenses/LICENSE-2.0                             *
 * ~                                                                           *
 * ~ Unless required by applicable law or agreed to in writing, software       *
 * ~ distributed under the License is distributed on an "AS IS" BASIS,         *
 * ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 * ~ See the License for the specific language governing permissions and       *
 * ~ limitations under the License.                                            *
 ******************************************************************************/
package cn.ucaner.skeleton.service.framework.config.kafka;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.framework.config.kafka
 * @Description： <p> KafkaConfig  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/30 - 16:00
 * @Modify By：
 * @ModifyTime： 2019/4/30
 * @Modify marker：
 */
//@Configuration
//@ConditionalOnProperty(name = "enabled", havingValue = "true",prefix="kafka")
//@PropertySources(value = {@PropertySource("classpath:/config/kafka/kafka.properties")})
public class KafkaConfig {

    private Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    /**
     * kafka servers
     */
    @Value("${kafka.bootStrapServers:localhost:9092}")
    private String bootStrapServers;

    /**
     * consumerGroupId
     */
    @Value("${kafka.consumer.consumerGroupId:jason-kafka}")
    private String consumerGroupId;

    /**
     *是否自动提交
     */
    @Value("${kafka.consumer.consumerEnableAutocommit:true}")
    private Boolean consumerEnableAutocommit;

    /**
     * 自动提交的频率
     */
    @Value("${kafka.consumer.consumerAutoCommitIntervalMs:100}")
    private String consumerAutoCommitIntervalMs;

    /**
     * Session超时设置
     */
    @Value("${kafka.consumer.consumerSessionTimeoutMs:15000}")
    private String consumerSessionTimeoutMs;

    /**
     * kafka.consumer.auto.offset.reset=latest
     */
    @Value("${kafka.consumer.consumerAutoOffsetReset:latest}")
    private String consumerAutoOffsetReset;


    /**************************************************************
     * 重试,0为不启用重试机制
     */
    @Value("${kafka.producer.producerRetries:1}")
    private Integer producerRetries;


    /**
     * 控制批处理大小,单位为字节
     */
    @Value("${kafka.producer.producerBatchSize:16384}")
    private Integer producerBatchSize;

    /**
     * 批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
     */
    @Value("${kafka.producer.producerLingerMs:1}")
    private Integer producerLingerMs;

    /**
     * 生产者可以使用的总内存字节来缓冲等待发送到服务器的记录
     ***********************************************************************/
    @Value("${kafka.producer.producerBufferMemory:1024000}")
    private Integer producerBufferMemory;



    /********************************************************************************************
     * ConcurrentKafkaListenerContainerFactory 为创建Kafka监听器的工程类
     * 这里只配置了消费者 ConcurrentKafkaListenerContainerFactory
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    /**
     * 根据consumerProps 填写的参数创建消费者工厂
     * @return
     */
    @Bean
    public ConsumerFactory consumerFactory() {
        logger.info(">>> ConsumerFactory init:{} ...", JSON.toJSONString(getConsumerProps()));
        return new DefaultKafkaConsumerFactory<>(getConsumerProps());
    }

    /**
     * 根据senderProps 填写的参数创建生产者工厂
     * @return
     */
    @Bean
    public ProducerFactory producerFactory() {
        logger.info(">>> ProducerFactory init:{} ...", JSON.toJSONString(getProducerProps()));
        return new DefaultKafkaProducerFactory<>(getProducerProps());
    }

    /**
     * kafkaTemplate实现了Kafka发送接收等功能
     * @return
     */
    @Bean
    public KafkaTemplate kafkaTemplate() {
        KafkaTemplate template = new KafkaTemplate(producerFactory());
        logger.info("<<<<< KafkaTemplate[kafka初始化开始] init success ...", JSON.toJSONString(getProducerProps()));
        return template;
    }

    /**
     * 消费者配置参数
     * @return
     */
    private Map<String, Object> getConsumerProps() {
        Map<String, Object> props = new HashMap<>(7);
        //连接地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
        //GroupID
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        //是否自动提交
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerEnableAutocommit);
        //自动提交的频率
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerAutoCommitIntervalMs);
        //Session超时设置
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, consumerSessionTimeoutMs);
        //auto.offset.reset 从什么地方开始消费
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerAutoOffsetReset);
        //键的反序列化方式 [IntegerSerializer , StringSerializer] [k-v] <String,String>
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //值的反序列化方式
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    /**
     * 生产者配置
     * @return
     */
    private Map<String, Object> getProducerProps (){
        Map<String, Object> props = new HashMap<>(7);
        //连接地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        //重试，0为不启用重试机制
        props.put(ProducerConfig.RETRIES_CONFIG, producerRetries);
        //控制批处理大小，单位为字节
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, producerBatchSize);
        //批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
        props.put(ProducerConfig.LINGER_MS_CONFIG,producerLingerMs);
        //生产者可以使用的总内存字节来缓冲等待发送到服务器的记录
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, producerBufferMemory);
        //键的序列化方式 [IntegerSerializer , StringSerializer]   [k-v]
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //值的序列化方式
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
}
