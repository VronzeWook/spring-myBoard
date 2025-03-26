package com.example.myBoard.global.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {

    // 특정 이름의 쿠기가 있는지 확인
    public static boolean hasCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return false;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // 쿠키를 응답에 추가
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge); // 쿠키 유지 시간
        cookie.setPath("/"); // 모든 경로에 대해서 유효
        cookie.setHttpOnly(false); // JS 접근 가능 여부
        response.addCookie(cookie);
    }


}
