package com.mlk.home.cookie;

import com.mlk.home.common.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by malikai on 2018-7-12.
 * 解析cookie里面的token
 */
public class CookieUtils {
    //{jti=admin, iat=1531365819, sub=malikai@hujiang.com, iss=www.mlkfamilymanager.com, exp=3062731635}
    public static Map<String, Object> getTokenResolved(HttpServletRequest request){
        Cookie[] cookies =  request.getCookies();
        String name = "";
        Map<String, Object> map = new HashMap<>();
        for (Cookie x:
                cookies) {
            if(x.getName().equals("JWT")){
                Claims xx = TokenUtils.parseJWT(x.getValue());
                map = (Map<String, Object>)xx;
            }
        }
        return map;
    }
}
