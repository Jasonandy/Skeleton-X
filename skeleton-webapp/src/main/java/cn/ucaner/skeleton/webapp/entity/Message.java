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
package cn.ucaner.skeleton.webapp.entity;

import java.io.Serializable;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.entity
 * @Description： <p> Message  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/2 - 15:40
 * @Modify By：
 * @ModifyTime： 2019/7/2
 * @Modify marker：
 */
public class Message implements Serializable {

    /**
     * 消息推送者
     */
    private User from;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 消息接收者：
     *      如果是私有（向指定窗口推送），to即为接受者User对象
     *      如果是公共消息（群组聊天），to设为null
     */
    private User to;

    /**
     * 创建时间
     */
    private String time;

    public void setMessage(String message) {
        this.message = message == null ? "" : message.replaceAll("\r\n|\r|\n", "");
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
