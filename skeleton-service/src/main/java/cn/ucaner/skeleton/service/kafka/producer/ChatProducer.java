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
package cn.ucaner.skeleton.service.kafka.producer;

import cn.ucaner.skeleton.service.kafka.constants.KafkaConstant;
import cn.ucaner.skeleton.service.user.entity.User;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.kafka.producer
 * @Description： <p> ChatProducer  https://blog.csdn.net/liyiming2017/article/details/82805479 </p>
 * @Author： - Jason
 * @CreatTime：2019/4/28 - 15:00
 * @Modify By：
 * @ModifyTime： 2019/4/28
 * @Modify marker：
 */
@Component
public class ChatProducer {

    private static Logger logger = LoggerFactory.getLogger(ChatProducer.class);

    @Resource
    private KafkaTemplate kafkaTemplate;

    /**
     * 1.点对点、发布订阅
     * {
     *     点对点 一个发布 一个消费 一旦被消费 其它的就消费不了.
     *     单来说就是生产者（Producer）发送消息到队列,消费者（Consumer）从队列中取出消息.
     * }
     *2.发布-订阅消息系统
     * 发布订阅的模型也比较好理解，首先消费者需要订阅这个队列，生产者只要发送一条消息到队列中，
     *  所有已订阅该队列的的消费者都能接收到该消息，未订阅的用户则无法接收。
     *      就像我们的微信关注微信公众号一样，只有关注了的用户才会收到公众号推送的消息。
     */

    /**
     * 生产者 生产数据到kafka
     * @param user
     */
    public void sendChatUser(User user){
        try {
            kafkaTemplate.send(KafkaConstant.KAFKA_CHAT_TOPIC, JSON.toJSONString(user));
        } catch (Exception e) {
            logger.info("=== ChatProducer:sendChatUser:error:{} ===",e.getMessage());
        }
    }

    /**
     * 发送数据
     * @param user
     */
    public void sendSomethings(User user){
        try {
            kafkaTemplate.send(KafkaConstant.KAFKA_CALL_TOPIC, JSON.toJSONString(user));
        } catch (Exception e) {
            logger.info("=== ChatProducer:sendSomethings:error:{} ===",e.getMessage());
        }
    }


    /**
     * send2Topic
     * @param topic 主题
     * @param key   key
     * @param dataStr data 数据
     */
    public void send2Topic(String topic, String key, String dataStr){
        try {
            kafkaTemplate.send(topic,key,dataStr);
        } catch (Exception e) {
            logger.info("=== ChatProducer:send2Topic:error:{} ===",e.getMessage());
        }
    }

    /**
     * sendPartitionKey
     * @param topic  主题
     * @param partition 分区
     * @param key  key
     * @param dataStr 数据
     */
    public void sendPartitionKey(String topic, Integer partition ,String key, String dataStr){
        try {
            kafkaTemplate.send(topic,partition, key,dataStr);
        } catch (Exception e) {
            logger.info("=== ChatProducer:send2Topic:error:{} ===",e.getMessage());
        }
    }

}
