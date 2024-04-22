package com.hokhanh.libary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.Admin;
import com.hokhanh.libary.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	public Admin findAdminByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

}
