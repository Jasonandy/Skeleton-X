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
package cn.ucaner.skeleton.webapp.service;

import cn.ucaner.skeleton.webapp.entity.Message;
import cn.ucaner.skeleton.webapp.entity.User;

import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.service
 * @Description： <p> ChatSessionService  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/2 - 15:43
 * @Modify By：
 * @ModifyTime： 2019/7/2
 * @Modify marker：
 */
public interface ChatSessionService {

    /**
     * 根据ID从Redis中查询数据
     *
     * @param id
     * @return User对象
     */
    User findById(String id);

    /**
     * 推送消息，储存到Redis数据库中
     *
     * @param fromId  推送方ID
     * @param toId    接收方ID
     * @param message 消息
     */
    void pushMessage(String fromId, String toId, String message);

    /**
     * 获取在线用户列表
     *
     * @return
     */
    List<User> onlineList();

    /**
     * 获取公共消息内容 -- 群组
     *
     * @return
     */
    List<Message> commonList();

    /**
     * 获取该用户与指定窗口的推送消息
     *
     * @param fromId 推送方ID
     * @param toId   接收方ID
     * @return
     */
    List<Message> selfList(String fromId, String toId);

    /**
     * 删除指定ID在Redis中储存的数据
     *
     * @param id
     */
    void delete(String id);
}
