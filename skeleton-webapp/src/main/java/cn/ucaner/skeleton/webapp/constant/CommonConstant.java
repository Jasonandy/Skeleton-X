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
package cn.ucaner.skeleton.webapp.constant;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.constant
 * @Description： <p> CommonConstant   </p>
 * @Author： - Jason
 * @CreatTime：2019/7/2 - 15:38
 * @Modify By：
 * @ModifyTime： 2019/7/2
 * @Modify marker：
 */
public class CommonConstant {

    /**
     * 用户数据 Key前缀标识
     */
    public final static String USER_PREFIX = "USER_";

    /**
     * 群发消息Session Key前缀标识
     */
    public final static String CHAT_COMMON_PREFIX = "CHAT_COMMON_";

    /**
     * 推送至指定用户消息
     *      推送方Session Key前缀标识
     */
    public final static String CHAT_FROM_PREFIX = "CHAT_FROM_";

    /**
     * 推送至指定用户消息
     *      接收方Session Key前缀标识
     */
    public final static String CHAT_TO_PREFIX = "_TO_";

    /**
     * RedisTemplate 根据Key模糊匹配查询前缀
     */
    public final static String REDIS_MATCH_PREFIX = "*";

}
