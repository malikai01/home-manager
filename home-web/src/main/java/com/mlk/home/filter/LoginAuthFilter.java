package com.mlk.home.filter;

/**
 * Created by malikai on 2018-7-12.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.mlk.home.common.utils.EmptyUtils;

public class LoginAuthFilter implements Filter {
    private static final ThreadLocal<Map<String, String>> USER_LOCAL_CONTEXT = new ThreadLocal<Map<String, String>>() {

        @Override
        protected Map<String, String> initialValue() {
            return Maps.newConcurrentMap();
        }

    };

    public static final String Header_AccessToken = "Access-Token";

    public static final String Header_AccessToken1 = "access_token";

    private static final String QueryString_AccessToken = "token";

    public static final String Local_Date = "Local-Date";

    public static final String User_Agent = "User-Agent";

    private static volatile String[] filterHeaders = null;

    /**
     * 获取需要临时存储的Http Header
     * 在配置文件中设置：filter.request.headers=headername1,headername2 可通过LoginAuthFilter获取客户端传的Header值
     * @return
     */
    /*private static String[] getFilterHeaders() {
        if (filterHeaders == null) {
            String[] headerArray = null;
            String filterHeaderConfig = BaseProperties.getString(PropConsts.User.FILTER_REQUEST_HEADERS);
            if (EmptyUtils.isEmpty(filterHeaderConfig) == false) {
                headerArray = Arrays.stream(filterHeaderConfig.split(SpecharsUtil.SYMBOL_COMMA)).filter(x -> EmptyUtils.isEmpty(x) == false)
                        .collect(Collectors.toList()).toArray(new String[0]);
            }
            if (headerArray == null || headerArray.length == 0)
                headerArray = new String[0];
            filterHeaders = headerArray;
        }
        return filterHeaders;
    }*/

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            Map<String, String> localContext = USER_LOCAL_CONTEXT.get();
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String accessToken =httpRequest.getHeader(Header_AccessToken);
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    localContext.put(cookie.getName(), cookie.getValue());
                    if(cookie.getName().equals("JWT")){
                        accessToken = cookie.getValue();
                    }
                }
            }

            if (EmptyUtils.isEmpty(accessToken)) {
                accessToken = httpRequest.getHeader(Header_AccessToken1);
            }
            if (EmptyUtils.isEmpty(accessToken)) {
                accessToken = httpRequest.getHeader(Header_AccessToken1);
            }

            if (EmptyUtils.isNotEmpty(accessToken)) {
                localContext.put(Header_AccessToken1, accessToken);
            }else {
                String contextPath=httpRequest.getContextPath();
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect(contextPath +"/manager/login");
            }

            String loacalDate = httpRequest.getHeader(Local_Date);
            if (EmptyUtils.isNotEmpty(loacalDate)) {
                localContext.put(Local_Date, loacalDate);
            }

            String userAgent = httpRequest.getHeader(User_Agent);
            if (EmptyUtils.isNotEmpty(userAgent)) {
                localContext.put(User_Agent, userAgent);
            }
        /*    for (String headerName : getFilterHeaders()) {
                String headerVal = httpRequest.getHeader(headerName);
                if (EmptyUtils.isNotEmpty(headerVal)) {
                    localContext.put(headerName, headerVal);
                }
            }*/

            if (EmptyUtils.isNotEmpty(localContext)) {
                USER_LOCAL_CONTEXT.set(localContext);
            }

            chain.doFilter(request, response);
        } finally {
            USER_LOCAL_CONTEXT.get().clear();
        }
    }

    @Override
    public void destroy() {}

    public static String getContext(String contextName) {
        return USER_LOCAL_CONTEXT.get().get(contextName);
    }

    public static String setContext(String key, String value) {
        return USER_LOCAL_CONTEXT.get().put(key, value);
    }
}
