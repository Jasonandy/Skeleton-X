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
package cn.ucaner.skeleton.gateway.config.filters;

import cn.ucaner.skeleton.gateway.jwt.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.gateway.config.filters
 * @Description： <p> CustomFilterRegistration  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 10:30
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
@Configuration
public class CustomFilterRegistration {

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new JwtFilter());
        registration.addUrlPatterns("/*");
        registration.setName("jwtFilter");
        return registration;
    }

}
