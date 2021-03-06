package com.samplex.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SampleUserDetails implements UserDetails {

	private String userId;
	private String password;
	private Collection<GrantedAuthority> authorities;
	private String seedToken;

	public SampleUserDetails(String userId, String password, String seedToken,
			Collection<GrantedAuthority> authorities) {
		this.userId = userId;
		this.password = password;
		this.authorities = authorities;
		this.seedToken = seedToken;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
