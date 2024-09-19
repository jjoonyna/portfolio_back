package com.portfolio.jjoony.security;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomCookieFilter extends OncePerRequestFilter {

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // JSESSIONID 쿠키 설정
        Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());
        cookie.setPath("/");
        cookie.setHttpOnly(true);  // HttpOnly 설정
        cookie.setSecure(true);    // Secure 설정 (HTTPS에서만 사용)
        cookie.setDomain("localhost");  // 도메인 설정 (필요에 따라)
        cookie.setMaxAge(60 * 60);  // 1시간 동안 유지

        response.addCookie(cookie); // 응답에 쿠키 추가
        filterChain.doFilter(request, response);
    }

}
