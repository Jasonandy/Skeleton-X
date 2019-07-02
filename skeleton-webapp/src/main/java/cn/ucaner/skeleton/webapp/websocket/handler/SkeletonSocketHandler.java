package cn.ucaner.skeleton.webapp.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.websocket.handler
 * @Description： <p> SkeletonSocketHandler - 相当于controller的处理器 </p>
 * @Author： - Jason
 * @CreatTime：2019/7/2 - 14:49
 * @Modify By：
 * @ModifyTime： 2019/7/2
 * @Modify marker：
 */
public class SkeletonSocketHandler extends TextWebSocketHandler {

    private static Logger logger = LoggerFactory.getLogger(SkeletonSocketHandler.class);

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        Map<String, String> map = JSONObject.parseObject(payload, HashMap.class);
        logger.info("=== 接受到的数据:{} ===",map);
        session.sendMessage(new TextMessage("服务器返回收到的信息:" + payload));
    }
}
