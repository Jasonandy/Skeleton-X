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
package cn.ucaner.skeleton.webapp.controller;

import cn.ucaner.skeleton.webapp.entity.Message;
import cn.ucaner.skeleton.webapp.entity.User;
import cn.ucaner.skeleton.webapp.exception.GlobalException;
import cn.ucaner.skeleton.webapp.service.ChatSessionService;
import cn.ucaner.skeleton.webapp.utils.CommonUtil;
import cn.ucaner.skeleton.webapp.utils.R;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.controller
 * @Description： <p> WebsocketServerEndpoint  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/2 - 15:53
 * @Modify By：
 * @ModifyTime： 2019/7/2
 * @Modify marker：
 */
@Component
@ServerEndpoint(value = "/chat/{id}")
public class WebsocketServerEndpoint {

    private static Logger logger = LoggerFactory.getLogger(WebsocketServerEndpoint.class);

    private static ChatSessionService chatSessionService;

    @Autowired
    public void setChatSessionService(ChatSessionService chatSessionService) {
        WebsocketServerEndpoint.chatSessionService = chatSessionService;
    }

    /**
     * 在线连接数
     */
    private static long online = 0;

    /**
     * 用于存放当前Websocket对象的Set集合
     */
    private static CopyOnWriteArraySet<WebsocketServerEndpoint> websocketServerEndpoints = new CopyOnWriteArraySet<>();

    /**
     * 与客户端的会话Session
     */
    private Session session;

    /**
     * 当前会话窗口ID
     */
    private String fromId = "";


    /**
     * 链接成功调用的方法
     * @param session
     * @param id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        logger.info("onOpen >> 链接成功");
        this.session = session;

        //将当前websocket对象存入到Set集合中
        websocketServerEndpoints.add(this);

        //在线人数+1
        addOnlineCount();

        logger.info("有新窗口开始监听：" + id + ", 当前在线人数为：" + getOnlineCount());

        this.fromId = id;
        try {
            User user = chatSessionService.findById(fromId);
            //群发消息
            Map<String, Object> map = new HashMap<>();
            map.put("msg", "用户 " + user.getName() + " 已上线");
            sendMore(JSONObject.toJSONString(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 链接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        logger.info("onClose >> 链接关闭");

        //移除当前Websocket对象
        websocketServerEndpoints.remove(this);

        //在内线人数-1
        subOnLineCount();

        logger.info("链接关闭，当前在线人数：" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        logger.info("接收到窗口：" + fromId + " 的信息：" + message);

        chatSessionService.pushMessage(fromId, null, message);

        //群发消息
        sendMore(getData(null, message));
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    /**
     * 推送消息
     *
     * @param message
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 封装返回消息
     *
     * @param toId    指定窗口ID
     * @param message 消息内容
     * @return
     * @throws IOException
     */
    private String getData(String toId, String message) throws IOException {
        Message entity = new Message();
        entity.setMessage(message);
        entity.setTime(CommonUtil.format(new Date()));
        entity.setFrom(chatSessionService.findById(fromId));
        entity.setTo(chatSessionService.findById(toId));
        return JSONObject.toJSONString(new R(entity));
    }

    /**
     * 群发消息
     *
     * @param data
     */
    private void sendMore(String data) {
        for (WebsocketServerEndpoint websocketServerEndpoint : websocketServerEndpoints) {
            try {
                websocketServerEndpoint.sendMessage(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定窗口推送消息
     *
     * @param entity 推送消息
     * @param toId   接收方ID
     */
    public void sendTo(String toId, Message entity) {
        fromId = entity.getFrom().getId().toString();
        if (websocketServerEndpoints.size() <= 1) {
            throw new GlobalException("用户未上线");
        }
        boolean flag = false;
        for (WebsocketServerEndpoint endpoint : websocketServerEndpoints) {
            try {
                if (endpoint.fromId.equals(toId)) {
                    flag = true;
                    logger.info(entity.getFrom().getId() + " 推送消息到窗口：" + toId + " ，推送内容：" + entity.getMessage());

                    endpoint.sendMessage(getData(toId, entity.getMessage()));
                    chatSessionService.pushMessage(fromId, toId, entity.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        if (!flag) {
            throw new GlobalException("推送失败，找不到该窗口");
        }
    }

    private void subOnLineCount() {
        WebsocketServerEndpoint.online--;
    }

    private synchronized long getOnlineCount() {
        return online;
    }

    private void addOnlineCount() {
        WebsocketServerEndpoint.online++;
    }
}
