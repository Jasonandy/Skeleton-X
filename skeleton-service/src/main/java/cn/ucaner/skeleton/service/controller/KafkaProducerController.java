/******************************************************************************
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
package cn.ucaner.skeleton.service.controller;

import cn.ucaner.skeleton.common.utils.pk.PKGenerator;
import cn.ucaner.skeleton.common.vo.RespBody;
import cn.ucaner.skeleton.service.kafka.producer.ChatProducer;
import cn.ucaner.skeleton.service.user.entity.User;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.controller
 * @Description： <p> KafkaProducerController  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/28 - 14:43
 * @Modify By：
 * @ModifyTime： 2019/4/28
 * @Modify marker：
 */
@RestController
@RequestMapping("kafka")
public class KafkaProducerController {

    private static Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);

    @Autowired
    ChatProducer chatProducer;

    @ResponseBody
    @GetMapping("send")
    @RequestMapping(value="/send/{name}/{count}",method= RequestMethod.GET)
    public RespBody send(@PathVariable String name, @PathVariable Integer count) {
        RespBody respBody = new RespBody();
        long startTime ,endTime;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setName(name);
            user.setId(PKGenerator.uuid32());
            user.setAge(new Random().nextInt(24));
            user.setDesc("=== A Good Man ===");
            user.setErrorMsg(" none ");
            chatProducer.sendChatUser(user);
        }
        endTime = System.currentTimeMillis();
        logger.info("=== send: count:{} , cost time: {}ms ===",count,((endTime-startTime)/1000));
        respBody.addOK(count,"发送成功!");
        return respBody;
    }

    @ResponseBody
    @GetMapping("send2Topic")
    @RequestMapping(value="/send2Topic/{topic}/{key}",method= RequestMethod.GET)
    public RespBody send2Topic(@PathVariable String topic, @PathVariable String key) {
        RespBody respBody = new RespBody();
        long startTime ,endTime;
        startTime = System.currentTimeMillis();
        User user = new User();
        user.setName(PKGenerator.uuid32());
        user.setId(PKGenerator.uuid32());
        user.setAge(new Random().nextInt(24));
        user.setDesc("=== A Better Man ===");
        user.setErrorMsg(" none ");
        chatProducer.send2Topic(topic,key, JSON.toJSONString(user));
        endTime = System.currentTimeMillis();
        logger.info("=== send2Topic: topic:{} key:{} , cost time: {}ms ===",topic,key,((endTime-startTime)/1000));
        respBody.addOK(topic,"发送成功!");
        return respBody;
    }

}
