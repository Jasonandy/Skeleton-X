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
package cn.ucaner.skeleton.gateway.jwt.filter;

import cn.ucaner.skeleton.gateway.jwt.token.JwtToken;
import cn.ucaner.skeleton.gateway.jwt.utils.JwtUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.gateway.jwt.filter
 * @Description： <p> JwtFilter  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 10:04
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(JwtFilter.class);


    /**
     * 1.对跨域提供支持 - preHandle - 对拦截器数据pre - 预处理.
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        /**
         * 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
         */
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }


    /**
     * 2.如果带有 token,则对 token 进行检查,否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        /**
         * 2.1 判断请求的请求头是否带上 "Token"
         */
        if (isLoginAttempt(request, response)) {
            /**
             * 2.2 如果存在 则进入 executeLogin 方法执行登入 检查 token 是否正确
             */
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                /**
                 * 校验异常
                 */
                responseError(response, e.getMessage());
            }
        }else {
        	 try {
                 executeLogin(request, response);
                 return true;
             } catch (Exception e) {
                 /**
                  * token 异常
                  */
                 responseError(response, e.getMessage());
             }
            //没有Authorization
            return true;
        }
        /**
         * 如果请求头不存在 Token
         *          则可能是执行登陆操作或者是游客状态访问
         *                  无需检查 token，直接返回 true
         */
        return true;
    }

    /**
     * 3.判断用户是否想要登入
     *      检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        /**
         * 判断请求头 是否带上token
         */
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(AUTHORIZATION_HEADER);
        return authorization != null;
    }


    /**
     * 4.relam 里面做的校验处理
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        /**
         * jwtToken 携带
         */
        String authorization = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        /**
         * 将head的jwtToken传入校验
         */
        JwtToken jwtToken = new JwtToken(authorization);
        String username = JwtUtil.getUsername(jwtToken.getToken());
        boolean jwtFlag = JwtUtil.verify(jwtToken.getToken(), username, "andy");

        logger.info("---jwtToken:{} = username:{} = jwtFlag:{} ---",jwtToken,username,jwtFlag);
        /**
         *
         * 这里做校验处理
         */
        logger.debug("Callback Api with jwtToken:{},Api被调用时传递的JWT参数.",authorization);

        return jwtFlag;
    }

    /**
     * 校验异常 将请求 重定向到指定的地方 /unauthorized/**
     * @param response
     * @param message
     */
    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            message = URLEncoder.encode(message, "UTF-8");
            logger.error("----- responseError：{} ----- ",message);
            httpServletResponse.sendRedirect("/unauthorized/**");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
