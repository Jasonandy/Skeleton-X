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
package cn.ucaner.skeleton.webapp.service.impl;

import cn.ucaner.skeleton.webapp.constant.CommonConstant;
import cn.ucaner.skeleton.webapp.entity.Message;
import cn.ucaner.skeleton.webapp.entity.User;
import cn.ucaner.skeleton.webapp.service.ChatSessionService;
import cn.ucaner.skeleton.webapp.utils.CommonUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.service.impl
 * @Description： <p> ChatSessionServiceImpl  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/2 - 15:44
 * @Modify By：
 * @ModifyTime： 2019/7/2
 * @Modify marker：
 */
@Service
public class ChatSessionServiceImpl implements ChatSessionService {

    private static Logger logger = LoggerFactory.getLogger(ChatSessionServiceImpl.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User findById(String id) {
        if (id != null) {
            String value = null;
            if (id.startsWith(CommonConstant.USER_PREFIX)) {
                value = redisTemplate.boundValueOps(id).get();
            } else {
                value = redisTemplate.boundValueOps(CommonConstant.USER_PREFIX + id).get();
            }
            JSONObject object = JSONObject.parseObject(value);
            if (object != null) {
                return object.toJavaObject(User.class);
            }
        }
        return null;
    }

    @Override
    public void pushMessage(String fromId, String toId, String message) {
        Message entity = new Message();
        entity.setMessage(message);
        entity.setFrom(this.findById(fromId));
        entity.setTime(CommonUtil.format(new Date()));
        if (toId != null) {
            //查询接收方信息
            entity.setTo(this.findById(toId));
            //单个用户推送
            push(entity, CommonConstant.CHAT_FROM_PREFIX + fromId + CommonConstant.CHAT_TO_PREFIX + toId);
        } else {
            //公共消息 -- 群组
            entity.setTo(null);
            push(entity, CommonConstant.CHAT_COMMON_PREFIX + fromId);
        }
    }

    /**
     * 推送消息
     *
     * @param entity Session value
     * @param key    Session key
     */
    private void push(Message entity, String key) {
        //这里按照 PREFIX_ID 格式，作为KEY储存消息记录
        //但一个用户可能推送很多消息，VALUE应该是数组
        List<Message> list = new ArrayList<>();
        String value = redisTemplate.boundValueOps(key).get();
        if (value == null) {
            //第一次推送消息
            list.add(entity);
        } else {
            //第n次推送消息
            list = Objects.requireNonNull(JSONObject.parseArray(value)).toJavaList(Message.class);
            list.add(entity);
        }
        redisTemplate.boundValueOps(key).set(JSONObject.toJSONString(list));
    }

    @Override
    public List<User> onlineList() {
        List<User> list = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(CommonConstant.USER_PREFIX + CommonConstant.REDIS_MATCH_PREFIX);
        if (keys != null && keys.size() > 0) {
            keys.forEach(key -> {
                list.add(this.findById(key));
            });
        }
        return list;
    }

    @Override
    public List<Message> commonList() {
        List<Message> list = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(CommonConstant.CHAT_COMMON_PREFIX + CommonConstant.REDIS_MATCH_PREFIX);
        if (keys != null && keys.size() > 0) {
            keys.forEach(key -> {
                String value = redisTemplate.boundValueOps(key).get();
                List<Message> messageList = Objects.requireNonNull(JSONObject.parseArray(value)).toJavaList(Message.class);
                list.addAll(messageList);
            });
        }
        CommonUtil.sort(list);
        return list;
    }

    @Override
    public List<Message> selfList(String fromId, String toId) {
        List<Message> list = new ArrayList<>();
        //A -> B
        String fromTo = redisTemplate.boundValueOps(CommonConstant.CHAT_FROM_PREFIX + fromId + CommonConstant.CHAT_TO_PREFIX + toId).get();
        //B -> A
        String toFrom = redisTemplate.boundValueOps(CommonConstant.CHAT_FROM_PREFIX + toId + CommonConstant.CHAT_TO_PREFIX + fromId).get();

        JSONArray fromToObject = JSONObject.parseArray(fromTo);
        JSONArray toFromObject = JSONObject.parseArray(toFrom);
        if (fromToObject != null) {
            list.addAll(fromToObject.toJavaList(Message.class));
        }
        if (toFromObject != null) {
            list.addAll(toFromObject.toJavaList(Message.class));
        }

        if (list.size() > 0) {
            CommonUtil.sort(list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void delete(String id) {
        if (id != null) {
            logger.info("从Redis中删除此Key: " + id);
            redisTemplate.delete(CommonConstant.USER_PREFIX + id);
        }
    }
}
