package com.portfolio.jjoony.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 로그아웃 시 세션 무효화
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        
        // JSESSIONID 쿠키 삭제
        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // 쿠키 즉시 삭제
        response.addCookie(cookie);
    }
}