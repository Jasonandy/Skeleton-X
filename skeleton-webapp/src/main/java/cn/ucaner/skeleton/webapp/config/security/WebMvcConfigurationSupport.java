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
package cn.ucaner.skeleton.webapp.config.security;

import cn.ucaner.skeleton.webapp.security.auth.SkeletonPermissionEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import javax.annotation.Resource;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.config.security
 * @Description： <p> WebMvcConfigurationSupport </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 11:25
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 */
@Configurable
public class WebMvcConfigurationSupport extends GlobalMethodSecurityConfiguration {

    private static Logger logger = LoggerFactory.getLogger(WebMvcConfigurationSupport.class);

    @Resource
    private SkeletonPermissionEvaluator skeletonPermissionEvaluator;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return defaultMethodSecurityExpressionHandler();
    }

    @Bean
    public DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler(){
        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
        defaultMethodSecurityExpressionHandler.setPermissionEvaluator(skeletonPermissionEvaluator);
        logger.info("== 设置默认的鉴权器:{} ==","skeletonPermissionEvaluator");
        return defaultMethodSecurityExpressionHandler;
    }

}
