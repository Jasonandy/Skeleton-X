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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.config.security
 * @Description： <p> SkeletonSecurityConfig </p>
 * @Author： - Jason
 * @CreatTime：2019/7/12 - 15:04
 * @Modify By：
 * @ModifyTime： 2019/7/12
 * @Modify marker：
 */
@Configuration
@EnableWebSecurity
public class SkeletonSecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(SkeletonSecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("=== 拦截需要处理的页面 ===");
        http.formLogin()
                .loginPage("/index")
                .loginProcessingUrl("/login")
                .successForwardUrl("/index")
                .and()
                .authorizeRequests()
                .antMatchers("/index", "/login", "/error", "/favicon.ico").permitAll()
                .anyRequest()
                .authenticated()
                // csrf 关闭 防止xss攻击的 但是我们不需要 所以我们禁掉
                .and()
                .csrf()
                .disable();
    }

    /**
     * 如果我们以这种方式定义密码的时候 要在密码前面加上{noop}这个前缀或者配置一个密码加密器的bean 否则验证会出错。
     * 另外还有一点就是一定要添加roles或者authorities,否则spring_security不予通过.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /**
         * 关系维护在内存里面
         */
        auth.userDetailsService(new InMemoryUserDetailsManager(
                User.builder().username("admin").password("{noop}123456").authorities("admin").build(),
                User.builder().username("root").password("{noop}123456").authorities("user").build()
        ));

        /**
         * 校验关系维护在 db 数据库里面
         */
        /*auth.userDetailsService(userDetailsService);*/
        super.configure(auth);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * just for test
     * @param args
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
}
