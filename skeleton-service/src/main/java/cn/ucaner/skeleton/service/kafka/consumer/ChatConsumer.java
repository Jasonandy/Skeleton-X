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

    @KafkaListener(topics = KafkaConstant.KAFKA_CHAT_TOPIC)
    public void listen (ConsumerRecord<?, ?> record){
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


    @KafkaListener(topics = KafkaConstant.KAFKA_CALL_TOPIC)
    public void listenT1(ConsumerRecord<?, ?> record) {
        logger.info("== topic:{} ,key:{} , value:{} ==",record.topic(), record.offset(), record.value());
    }


    @KafkaListener(topics = {KafkaConstant.KAFKA_FACE_TOPIC,KafkaConstant.KAFKA_CALL_TOPIC})
    public void listenT2(ConsumerRecord<?, ?> record){
        logger.info("== topic:{} ,key:{} , value:{} ==",record.topic(), record.offset(), record.value());
    }
}
