package com.hokhanh.sinhVien.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.Admin;
import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.model.SinhVien;
import com.hokhanh.libary.repository.SinhVienRepository;
import com.hokhanh.libary.service.AdminService;
import com.hokhanh.libary.service.GiangVienService;
import com.hokhanh.libary.service.SinhVienService;

@Service
public class SinhVienDetailsService implements UserDetailsService{
	
	@Autowired
	private SinhVienRepository sinhVienRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SinhVien sinhVien = this.sinhVienRepository.findByEmailTruong(username);
		if(sinhVien == null) {
			throw new UsernameNotFoundException("Could not find "+username);
		}
		
		return new SinhVienDetails(sinhVien);
	}

}
