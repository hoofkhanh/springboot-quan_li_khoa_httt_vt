package com.hokhanh.libary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.NganhHoc;
import com.hokhanh.libary.repository.NganhHocRepository;

@Service
public class NganhHocService {

	@Autowired
	private NganhHocRepository nganhHocRepository;
	
	public List<NganhHoc> findAllNganhHoc() {
		return this.nganhHocRepository.findAll();
	}
	
	public NganhHoc findById(Long id) {
		return this.nganhHocRepository.findById(id).get();
	}

	public NganhHoc findByTenNganh(String tenNganh) {
		return nganhHocRepository.findByTenNganh(tenNganh);
	}
	

}
