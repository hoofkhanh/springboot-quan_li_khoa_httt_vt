package com.hokhanh.giangVien.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hokhanh.libary.model.Admin;
import com.hokhanh.libary.model.GiangVien;

public class GiangVienDetails implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private GiangVien giangVien;

	public GiangVienDetails(GiangVien giangVien) {
		this.giangVien = giangVien;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(this.giangVien.getVaiTro().getTen()));
	}

	@Override
	public String getPassword() {
		return giangVien.getPassword();
	}

	@Override
	public String getUsername() {
		return giangVien.getEmailTruong();
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
