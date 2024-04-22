package com.hokhanh.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.Admin;
import com.hokhanh.libary.service.AdminService;

@Service
public class AdminDetailsService implements UserDetailsService{
	
	@Autowired
	private AdminService adminService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = this.adminService.findAdminByEmail(username);
		if(admin == null) {
			throw new UsernameNotFoundException("Could not find "+username);
		}
		
		return new AdminDetails(admin);
	}

}
