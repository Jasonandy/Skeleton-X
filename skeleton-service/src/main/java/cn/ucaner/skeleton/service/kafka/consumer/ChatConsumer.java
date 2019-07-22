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
package cn.ucaner.skeleton.service.kafka.consumer;

import cn.ucaner.skeleton.service.kafka.constants.KafkaConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.kafka.consumer
 * @Description： <p> ChatConsumer  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/28 - 14:49
 * @Modify By：
 * @ModifyTime： 2019/4/28
 * @Modify marker：
 */
@Component
public class ChatConsumer {

    private static Logger logger = LoggerFactory.getLogger(ChatConsumer.class);

    /**
     * @KafkaListener 注解工作流程
     *  1.解析;解析@KafkaListener注解.
     *  2.注册;解析后的数据注册到spring-kafka.
     *  3.监听;开始监听topic变更.
     *  4.调用;调用注解标识的方法，将监听到的数据作为参数传入.
     *
     *
     *
     */



    /**
     * listen
     * @param record
     */
    @KafkaListener(topics = KafkaConstant.KAFKA_CHAT_TOPIC)
    public void listenChat (ConsumerRecord<?, ?> record){
        /**
         * 判断是否为Null
         */
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        logger.info("== topic:{} ,offset:{} , value:{} ==",record.topic(), record.offset(), record.value());
        if(kafkaMessage.isPresent()){
            Object message = kafkaMessage.get();
            logger.info("=== 消费信息:{} ===",message);
        }
    }


    /**
     * listenCall
     * @param record
     */
    @KafkaListener(topics = KafkaConstant.KAFKA_CALL_TOPIC)
    public void listenCall(ConsumerRecord<?, ?> record) {
        logger.info("== topic:{} ,key:{} , value:{} ==",record.topic(), record.offset(), record.value());
    }


    /**
     * listenFaceChat
     * @param record
     */
    @KafkaListener(topics = {KafkaConstant.KAFKA_FACE_TOPIC,KafkaConstant.KAFKA_CALL_TOPIC})
    public void listenFaceChat(ConsumerRecord<?, ?> record){
        logger.info("== topic:{} ,key:{} , value:{} ==",record.topic(), record.offset(), record.value());
    }

    /**
     * id是消费者监听容器
     *      配置topic和分区：监听两个topic，分别为topic1、topic2，topic1只接收分区0，3的消息，
     *                  KAFKA_FACE_TOPIC KAFKA_CALL_TOPIC 接收分区0和分区1的消息,但是分区1的消费者初始位置为5
     *
     *  选着指定的服务id  消费指定的topic 指定topic指定的partition  指定offset
     * @param record
     */
    @KafkaListener(id = "2",topicPartitions ={
            @TopicPartition(topic = KafkaConstant.KAFKA_FACE_TOPIC,partitions = { "0","3"}),
            @TopicPartition(topic = KafkaConstant.KAFKA_CALL_TOPIC,
                    partitions = "0",
                    partitionOffsets =
            @PartitionOffset(partition = "1", initialOffset = "5"))})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("=== topic:{} ,key:{} , value:{} ===",record.topic(),record.key(),record.value());
    }
}
