package com.ani.utils.core;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yeh on 15-9-26.
 */
public class AniHttpUtils {

    public static HttpServletRequest getHttpRequest(ServletRequest servletRequest) {
        return (HttpServletRequest) servletRequest;
    }

    public static HttpServletResponse getHttpResponse(ServletResponse servletResponse) {
        return (HttpServletResponse) servletResponse;
    }

    public static void clearCookie(Cookie cookie, HttpServletResponse res) {
        cookie.setMaxAge(0);
        cookie.setPath(cookie.getPath());
        res.addCookie(cookie);
    }

    public static void clearCookie(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        if(cookies == null) return;
        for(Cookie oneCookie: cookies) {
            clearCookie(oneCookie, res);
        }
    }
}
