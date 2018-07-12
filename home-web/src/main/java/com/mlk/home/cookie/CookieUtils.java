package com.mlk.home.cookie;

import com.mlk.home.common.utils.TokenUtils;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by malikai on 2018-7-12.
 */
public class CookieUtils {
    public static String getName(HttpServletRequest request){
        Cookie[] cookies =  request.getCookies();
        String name = "";
        for (Cookie x:
                cookies) {
            if(x.getName().equals("JWT")){
                Claims xx = TokenUtils.parseJWT(x.getValue());
                name =(String) xx.get("jti");
            }
        }
        return name;
    }
}
