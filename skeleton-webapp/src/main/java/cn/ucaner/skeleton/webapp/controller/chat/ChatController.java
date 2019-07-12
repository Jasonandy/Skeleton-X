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
package cn.ucaner.skeleton.webapp.controller.chat;

import cn.ucaner.skeleton.webapp.entity.Message;
import cn.ucaner.skeleton.webapp.exception.GlobalException;
import cn.ucaner.skeleton.webapp.service.ChatSessionService;
import cn.ucaner.skeleton.webapp.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.controller
 * @Description： <p> ChatController  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/2 - 15:58
 * @Modify By：
 * @ModifyTime： 2019/7/2
 * @Modify marker：
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    private static Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatSessionService chatSessionService;

    /**
     * 获取当前窗口用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R info(@PathVariable("id") String id) {
        return new R(chatSessionService.findById(id));
    }

    /**
     * 向指定窗口推送消息
     *
     * @param toId    接收方ID
     * @param message 消息
     * @return
     */
    @PostMapping("/push/{toId}")
    public R push(@PathVariable("toId") String toId, @RequestBody Message message) {
        try {
            WebsocketServerEndpoint endpoint = new WebsocketServerEndpoint();
            logger.info("=== push msg : \n {}",message);
            endpoint.sendTo(toId, message);
            return new R();
        } catch (GlobalException e) {
            logger.error("=== error:{} ===",e.getMsg());
            return new R(500, e.getMsg());
        }
    }

    /**
     * 获取在线用户列表
     *
     * @return
     */
    @GetMapping("/online/list")
    public R onlineList() {
        return new R(chatSessionService.onlineList());
    }

    /**
     * 获取公共聊天消息内容
     *
     * @return
     */
    @GetMapping("/common")
    public R commonList() {
        return new R(chatSessionService.commonList());
    }

    /**
     * 获取指定用户的聊天消息内容
     *
     * @param fromId 该用户ID
     * @param toId   哪个窗口
     * @return
     */
    @GetMapping("/self/{fromId}/{toId}")
    public R selfList(@PathVariable("fromId") String fromId, @PathVariable("toId") String toId) {
        List<Message> list = chatSessionService.selfList(fromId, toId);
        return new R(list);
    }

    /**
     * 退出登录
     *
     * @param id 用户ID
     * @return
     */
    @DeleteMapping("/{id}")
    public R logout(@PathVariable("id") String id) {
        chatSessionService.delete(id);
        return new R();
    }
}
