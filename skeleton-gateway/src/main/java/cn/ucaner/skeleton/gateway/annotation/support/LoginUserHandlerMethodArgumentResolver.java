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
package cn.ucaner.skeleton.gateway.annotation.support;

import cn.ucaner.skeleton.gateway.annotation.LoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.gateway.annotation.support
 * @Description： <p>  SpringMVC3.1引入了HandlerMethodArgumentResolver接口,Spring调用该接口实现Controller的参数装配 </p>
 * <code> 对参数进行判断,达到鉴权的效果 or 考虑采用 filter的方式 判断 是否放行  SpringMvc </code>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 9:31
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    /**
     * 登录的cookie标志位
     */
    public static final  String LOGIN_COKIEE_FLAG = "jasonandy@hotmail.com";

    /**
     * 对传入的参数进行处理
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        /**
         * 这里其实就是对代码进行判断 看是不是有  loginUse这么一个注解  如果有的话 就需要做处理
         */
        /**
         * isAssignableFrom : 类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口
         *
         */
        return parameter.getParameterType().isAssignableFrom(String.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        return LOGIN_COKIEE_FLAG;
    }
}
