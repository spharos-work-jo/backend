package com.workjo.pointapp.auth.domain;


import com.workjo.pointapp.common.domain.BaseDateTime;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@Getter
@AllArgsConstructor
@ToString
public class Member extends BaseDateTime implements UserDetails {

	private static final String ROLE_PREFIX = "ROLE_";

	@Id
	private Long id;
	private String loginId;
	private String UUID;
	private String password;
	private String name;

	private Role role;


	public Member() {
		this.role = Role.USER;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_PREFIX + role.getValue());
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		return authorities;
	}


	@Override
	public String getPassword() {
		return password;
	}


	@Override
	public String getUsername() {
		return loginId;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}

}