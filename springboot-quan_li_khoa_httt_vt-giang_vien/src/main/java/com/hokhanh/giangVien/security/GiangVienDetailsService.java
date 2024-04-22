package com.hokhanh.giangVien.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.Admin;
import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.service.AdminService;
import com.hokhanh.libary.service.GiangVienService;

@Service
public class GiangVienDetailsService implements UserDetailsService{
	
	@Autowired
	private GiangVienService giangVienService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		GiangVien giangVien = this.giangVienService.findByEmailTruong(username);
		if(giangVien == null) {
			throw new UsernameNotFoundException("Could not find "+username);
		}
		
		return new GiangVienDetails(giangVien);
	}

}
