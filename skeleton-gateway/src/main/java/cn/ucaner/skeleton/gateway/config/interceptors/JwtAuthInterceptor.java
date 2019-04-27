package cn.ucaner.skeleton.gateway.config.interceptors;

import cn.ucaner.skeleton.gateway.jwt.token.JwtToken;
import cn.ucaner.skeleton.gateway.jwt.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.gateway.config.interceptors
 * @Description： <p> JwtAuthInterceptor  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 10:58
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(JwtAuthInterceptor.class);

    /**
     * AUTHORIZATION_HEADER
     */
    protected static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * 预处理回调方法 ,实现处理器的预处理 第三个参数为响应的处理器 自定义Controller 返回值为true表示继续流程
     *      如调用下一个拦截器或处理器）或者接着执行
     *          postHandle()和afterCompletion() false表示流程中断 不会继续调用其他的拦截器或处理器 中断执行
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isLoginAttempt(request, response)) {
            try {
                return executeLogin(request, response);
            } catch (Exception e) {
                logger.error("======= 鉴权出现异常:{} =======",e.getMessage());
            }
        }else {
           logger.info("======= 无鉴权头信息 =======");
        }
        return false;
    }


    /**
     * 后处理回调方法, 实现处理器的后处理[DispatcherServlet进行视图返回渲染之前进行调用],
     *          此时我们可以通过modelAndView[模型和视图对象]对模型数据进行处理或对视图进行处理 - modelAndView也可能为null
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理
     * @param modelAndView 视图 - 可以用来给视图 自定义参数等
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 整个请求处理完毕回调方法 = 该方法也是需要当前对应的Interceptor的preHandle() 的返回值为true时才会执行
     *         也就是在DispatcherServlet渲染了对应的视图之后执行
     *                  适合用于进行资源清理 - 如性能监控中我们可以在此记录结束时间并输出消耗时间 还可以进行一些资源清理
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @param ex 异常
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 1.判断用户是否想要登入
     *      检测header里面是否包含Authorization字段即可
     */
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
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        JwtToken jwtToken = new JwtToken(authorization);
        boolean jwtFlag = JwtUtil.verify(authorization, JwtUtil.getUsername(authorization), "123456");
        logger.info("---jwt Token:{} = username:{} = jwtFlag:{} ---",jwtToken,JwtUtil.getUsername(authorization),jwtFlag);
        logger.debug("Callback Api with jwtToken:{},Api被调用时传递的JWT参数.",authorization);
        return jwtFlag;
    }
}
