package com.portfolio.jjoony.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
	    // 쿠키에서 USER_ROLES 읽기
	    Cookie[] cookies = request.getCookies();
	    String roles = null;

	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("USER_ROLES".equals(cookie.getName())) {
	                roles = cookie.getValue();
	            }
	        }
	    }

	    if (roles != null) {
	        // 쿠키에서 읽은 권한 정보로 SecurityContext에 인증 정보 설정
	        List<SimpleGrantedAuthority> authorities = Arrays.stream(roles.split(","))
	                .map(SimpleGrantedAuthority::new)
	                .collect(Collectors.toList());
	        
	        UsernamePasswordAuthenticationToken authentication = 
	                new UsernamePasswordAuthenticationToken(null, null, authorities);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        System.out.println(authentication);
	    }
	    // 다음 필터로 요청 전달
	    filterChain.doFilter(request, response);
	}
}

