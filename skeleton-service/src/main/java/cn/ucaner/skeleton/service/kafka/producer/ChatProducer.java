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
 * @Description： <p> ChatProducer  </p>
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
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 生产者 生产数据到kafka
     * @param user
     */
    public void sendChatUser(User user){
        kafkaTemplate.send(KafkaConstant.KAFKA_CHAT_TOPIC, JSON.toJSONString(user));
        logger.info("== sendChatUser:{} ==",JSON.toJSONString(user));
    }


}
