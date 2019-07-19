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

import cn.ucaner.skeleton.webapp.security.auth.SkeletonAccessDecisionManagerService;
import cn.ucaner.skeleton.webapp.security.auth.SkeletonAuthenticationProvider;
import cn.ucaner.skeleton.webapp.security.detail.SkeletonUserDetailService;
import cn.ucaner.skeleton.webapp.security.handler.login.SkeletonLoginSuccessHandler;
import cn.ucaner.skeleton.webapp.security.handler.logout.SkeletonLogoutSuccessHandler;
import cn.ucaner.skeleton.webapp.security.session.SkeletonSessionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SkeletonSecurityConfig extends WebSecurityConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(SkeletonSecurityConfig.class);

    private static final String COOKIE_NAME_REMEMBER_ME = "remember-me";

    @Autowired
    private SkeletonLoginSuccessHandler skeletonLoginSuccessHandler;

    @Autowired
    private SkeletonLogoutSuccessHandler skeletonLogoutSuccessHandler;

    @Autowired
    private SkeletonUserDetailService skeletonUserDetailService;

    @Autowired
    private SkeletonSessionRegistry skeletonSessionRegistry;

    @Autowired
    private SkeletonAuthenticationProvider skeletonAuthenticationProvider;

    @Autowired
    private SkeletonAccessDecisionManagerService skeletonAccessDecisionManagerService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("=== 拦截需要处理的页面 ===");

        //拦截页面
        http.authorizeRequests()
            //全部页面都要验证
            .anyRequest().authenticated()
            //使用自定义拦截
            .accessDecisionManager(skeletonAccessDecisionManagerService);

        //禁用csrf [跨站请求伪造]- 使用自定义登录页面
        http.csrf()
                .disable();

        logger.info("== 登录:{},defaultSuccessUrl:{},successHandler:{} ==","formLogin","index","skeletonLoginSuccessHandler");

        //登录
        http.formLogin()
                //访问接口
                .loginPage("/account/login.do")
                .usernameParameter("username")
                .passwordParameter("password")
                //先defaultSuccessUrl后successHandler,不然successHandler不会执行
                .defaultSuccessUrl("/index.do")
                .successHandler(skeletonLoginSuccessHandler)

                //高级设置-拦截器
                .withObjectPostProcessor(new ObjectPostProcessor<UsernamePasswordAuthenticationFilter>() {
                    @Override
                    public <O extends UsernamePasswordAuthenticationFilter> O postProcess(O fsi) {
                        return fsi;
                    }
                })
                .permitAll();


        logger.info("== 登出:{},logoutSuccessUrl:{},logoutSuccessUrl:{},logoutSuccessHandler:skeletonLogoutSuccessHandler ==","logout","login","skeletonLoginSuccessHandler");
        //登出
        http.logout()
                .logoutUrl("/account/logout.do")
                .logoutSuccessUrl("/account/login.do")
                .logoutSuccessHandler(skeletonLogoutSuccessHandler)
                .deleteCookies(COOKIE_NAME_REMEMBER_ME)
                .permitAll();


        //记住我
        http.rememberMe()
                .rememberMeCookieName(COOKIE_NAME_REMEMBER_ME)
                .userDetailsService(skeletonUserDetailService);


        //Session管理器
        http.sessionManagement()
                .sessionFixation().changeSessionId()
                .sessionAuthenticationErrorUrl("/account/login.do")
                //Session失效
                .invalidSessionUrl("/account/login.do")
                //只能同时一个人在线
                .maximumSessions(1)
                //启用这个让maximumSessions生效
                .sessionRegistry(skeletonSessionRegistry)
                .expiredUrl("/account/login.do");


        //权限验证失败进入的页面（只对使用自定义拦截有效）
        http.exceptionHandling()
                .accessDeniedPage("/access_denied.do");


        //允许同源iframe访问
        http.headers()
                .frameOptions()
                .sameOrigin();
    }


    /**
     * Web层面的拦截 用来跳过的资源
     * @param web
     */
    @Override
    public void configure(WebSecurity web){
        web.ignoring()
                //需要开放的资源
                .antMatchers("/account/login.do")
                .antMatchers("/login")
                //静态资源
                .antMatchers("**/favicon.ico")
                .antMatchers("/static/**")
                .antMatchers("/assets/**")
                .antMatchers("/captcha.jpg");
    }



    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(skeletonAuthenticationProvider);
    }

}
