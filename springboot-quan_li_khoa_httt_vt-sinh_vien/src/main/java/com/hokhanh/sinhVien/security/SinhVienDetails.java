package com.hokhanh.sinhVien.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hokhanh.libary.model.Admin;
import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.model.SinhVien;

public class SinhVienDetails implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private SinhVien sinhVien;

	public SinhVienDetails(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(this.sinhVien.getVaiTro().getTen()));
	}

	@Override
	public String getPassword() {
		return sinhVien.getPassword();
	}

	@Override
	public String getUsername() {
		return sinhVien.getEmailTruong();
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
