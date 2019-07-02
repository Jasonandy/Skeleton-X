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
package cn.ucaner.skeleton.webapp.config;

import cn.ucaner.skeleton.webapp.constant.CommonConstant;
import cn.ucaner.skeleton.webapp.entity.User;
import cn.ucaner.skeleton.webapp.service.ChatSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.config
 * @Description： <p> ScheduleTaskConfig  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/2 - 15:35
 * @Modify By：
 * @ModifyTime： 2019/7/2
 * @Modify marker：
 */
public class ScheduleTaskConfig {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTaskConfig.class);

    private static final Long MINUTE_30 = 1000 * 60 * 30L;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ChatSessionService chatSessionService;

    @Scheduled(cron = "0 */30 * * * ?")
    private void clearUser() {
        logger.info("定时任务 >>>>>>>>>> 清除注册时间超过30分钟的账户，以及其会话信息");
        List<User> userList = chatSessionService.onlineList();
        userList.forEach(user -> {
            if ((System.currentTimeMillis() - user.getId()) >= MINUTE_30) {
                chatSessionService.delete(user.getId().toString());
                if (redisTemplate.boundValueOps(CommonConstant.CHAT_COMMON_PREFIX + user.getId()).get() != null) {
                    redisTemplate.delete(CommonConstant.CHAT_COMMON_PREFIX + user.getId());
                }
                if (redisTemplate.boundValueOps(CommonConstant.CHAT_FROM_PREFIX + user.getId()).get() != null) {
                    redisTemplate.delete(CommonConstant.CHAT_FROM_PREFIX + user.getId());
                }
            }
        });
    }

}
