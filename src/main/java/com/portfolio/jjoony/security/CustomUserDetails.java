package com.portfolio.jjoony.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.portfolio.jjoony.model.User;

import lombok.Data;


@Data
public class CustomUserDetails implements UserDetails{
	
	private final User user;
	
	public CustomUserDetails(User user){
		this.user=user;
	}
	public final User getUser(){//사용자 정보 가져오기
		return user;
	}
	
	@Override
	public String getUsername(){//사용자 id 혹은 이름 가져오기
		return user.getId();
	}
	@Override
	public String getPassword(){//사용자 비밀번호 가져오기
		return user.getPwd();
	}
	//사용자가 수행할 작업을 GrantedAuthority인스턴스의 컬렉션으로 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(user.getRole()) );
	}
	@Override
	public boolean isAccountNonExpired(){//계정 만료
		return true;
	}
	@Override
	public boolean isAccountNonLocked(){//계정 잠금
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired(){//자격증명 만료
		return true;
	}
	@Override
	public boolean isEnabled(){//계정 비활성화
		return true;
	}
}
