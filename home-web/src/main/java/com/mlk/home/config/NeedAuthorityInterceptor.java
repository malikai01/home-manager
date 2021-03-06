package com.mlk.home.config;

/**
 * Created by malikai on 2018-7-12.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlk.home.UserContext;
import com.mlk.home.annotation.NeedAuthority;
import com.mlk.home.common.utils.EmptyUtils;
import com.mlk.home.common.utils.TokenUtils;
import com.mlk.home.cookie.CookieUtils;
import com.mlk.home.entity.ManagerLogin;
import com.mlk.home.filter.LoginAuthFilter;
import com.mlk.home.service.ManagerBaseService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Map;


/**
 * NeedAuthority拦截器
 *
 */
@Component
public class NeedAuthorityInterceptor extends HandlerInterceptorAdapter{
    @Reference
    private ManagerBaseService managerBaseService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object _handler)
            throws Exception {
        if( !(_handler instanceof HandlerMethod)){
            return true;
        }
        if(request.getRequestURI().contains("index")){
            return true;
        }
        HandlerMethod handler = (HandlerMethod) _handler;
        //先看类上面是否有注解，如果有，则直接验证权限，如果没有则看方法上是否有注解。
        NeedAuthority authrity  = handler.getBeanType().getAnnotation(NeedAuthority.class);
        if(EmptyUtils.isEmpty(authrity)){
            authrity = handler.getMethodAnnotation(NeedAuthority.class);
        }
        if (EmptyUtils.isEmpty(authrity)) {
            return true;
        }
        if (!authrity.isCheck()) {
            return true;
        }

        // 如果token为空，验证cookie
        String accessToken = LoginAuthFilter.getContext(LoginAuthFilter.Header_AccessToken1);
        ManagerLogin user = null;
        if (EmptyUtils.isEmpty(accessToken)) {
            // 从cookie中获取 jti=admin, iat=1531365819, sub=malikai@hujiang.com, iss=www.mlkfamilymanager.com, exp=3062731635
            Map<String, Object> map = CookieUtils.getTokenResolved(request);
            if(map.containsKey("jti")) {
                String name = (String) map.get("jti");
                user = managerBaseService.queryByLoginName(name);
            }
        } else {
            // validate token
            Map<String, Object> map = CookieUtils.getTokenResolved(request);
            String name = (String) map.get("jti");
            boolean flag = TokenUtils.validateJWT(map);
            if(flag){
                user=managerBaseService.queryByLoginName(name);
            }
        }
        if (user == null) {
            throw new Exception("No Authority!");
        }
        UserContext.getInstance().setUser(user);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //这个方法可以往request中添加一些公共的工具类给前端页面进行调用

    }



    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //当请求处理完成调用
    }

}
