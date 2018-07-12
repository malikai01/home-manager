package com.mlk.home.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlk.home.annotation.CurrentUser;
import com.mlk.home.common.utils.TokenUtils;
import com.mlk.home.constant.CurrentUserConstants;
import com.mlk.home.entity.ManagerLogin;
import com.mlk.home.service.ManagerBaseService;
import io.jsonwebtoken.Claims;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.Cookie;

/**
 * @description:自定义解析器实现参数绑定
 * 增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 * Created by malikai on 2018-7-12.
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Reference
    private ManagerBaseService managerBaseService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(ManagerLogin.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String name = webRequest.getHeader("JWT");
        ManagerLogin user = managerBaseService.queryByLoginName(name);
        if (user != null) {
            return user;
        }
        throw new MissingServletRequestPartException(CurrentUserConstants.CURRENT_USER);
    }
}
