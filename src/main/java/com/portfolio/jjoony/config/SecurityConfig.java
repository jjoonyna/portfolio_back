package com.portfolio.jjoony.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
		
		// CORS 설정
			.cors(c -> {
			CorsConfigurationSource source = request -> {
				CorsConfiguration config = new CorsConfiguration();
					config.setAllowedOrigins(
						List.of("*")
					);
					config.setAllowedMethods(
						List.of("*")
					);
					return config;
				};
				c.configurationSource(source);
			}
			)
		//RESTFULL API 방식이라 csrf 비활성화
		.csrf().disable()
		//역할에 따라 요청 허용/제한 설정
		.authorizeRequests()
		.requestMatchers("/**").permitAll()
		.requestMatchers("/admin/**").hasRole("ADMIN")//전체 허용
		.requestMatchers("/test/**").hasRole("TEST");//프로젝트 등록 가능,프로젝트 수정, 삭제 및 내 정보 수정 불가
		
		
		
		http.formLogin((auth) -> auth
				.loginPage("/login")
				.loginProcessingUrl("/")
				.defaultSuccessUrl("/",true)
				.permitAll()
		);
		http.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
		);
		
		return http.build();
		
		
	}
}
