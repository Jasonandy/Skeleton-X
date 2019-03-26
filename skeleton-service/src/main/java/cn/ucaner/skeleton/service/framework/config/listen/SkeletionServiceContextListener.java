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
package cn.ucaner.skeleton.service.framework.config.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.framework.config.listen
 * @Description： <p> SkeletionServiceContextListener - 监听容器 可以用来做一些数据的初始化等 :ApplicationContext ContextRefreshedEvent被初始化或刷新时，该事件被发布。 </p>
 * @Author： - Jason
 * @CreatTime：2019/3/26 - 9:43
 * @Modify By：
 * @ModifyTime： 2019/3/26
 * @Modify marker：
 */
@Configuration
public class SkeletionServiceContextListener implements ApplicationListener<ContextRefreshedEvent> {


    private static final Logger logger = LoggerFactory.getLogger(SkeletionServiceContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // root application context 没有parent 说明它就是老大
        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
            logger.info(">>>> 喜大普奔 恭喜您 您的容器初始化成功.<<<");
            init();//初始化方法  可以用来初始化redis缓存等 ..
        }
    }


    /**
     * @Description: init() 初始化方法
     * @Autor: Jason
     */
    public void init() {
        logger.info("SkeletionServiceContextListener - {}","Init方法初始化完成...");
    }
}
