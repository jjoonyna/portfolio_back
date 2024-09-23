package com.portfolio.jjoony.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception{
		// CustomAuthenticationFilter 등록
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager);
        customAuthenticationFilter.setFilterProcessesUrl("/login_user"); // 로그인 경로 설정
        
		http
			
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
		// CORS 설정
			.cors(c -> {
			CorsConfigurationSource source = request -> {
				CorsConfiguration config = new CorsConfiguration();
					config.setAllowedOrigins(
						List.of("http://localhost:5173","http://localhost:80")
					);
					config.setAllowedMethods(
						List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
					);
					config.setAllowedHeaders(
						List.of("Authorization", "Content-Type", "X-Requested-With", "Accept"));
			        config.setAllowCredentials(true);
					return config;
				};
				c.configurationSource(source);
			}
			)
		//RESTFULL API 방식이라 csrf 비활성화
		.csrf().disable()
		//역할에 따라 요청 허용/제한 설정
		.authorizeRequests()
		.requestMatchers("/login_user","/find_about/**").permitAll()
		.requestMatchers("/update_about","/insert_project").hasAuthority("ROLE_TEST")//프로젝트 등록 가능,프로젝트 수정, 삭제 및 내 정보 수정 불가
		.requestMatchers("/**").hasRole("ADMIN")//전체 허용
		
	    .and()
        // CustomAuthenticationFilter를 UsernamePasswordAuthenticationFilter 앞에 등록
        .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new CustomCookieFilter(), UsernamePasswordAuthenticationFilter.class)  // Custom 필터 추가
        .securityContext()  // SecurityContext 유지 설정
	    .securityContextRepository(new HttpSessionSecurityContextRepository())  // 세션에 SecurityContext 저장
	    .and()
	    
		.logout()
		.logoutUrl("/logout")
		.addLogoutHandler(new CustomLogoutHandler())
		.logoutSuccessHandler(new CustomLogoutSuccessHandler())
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.permitAll();
	    return http.build();
		
		
	}
}
