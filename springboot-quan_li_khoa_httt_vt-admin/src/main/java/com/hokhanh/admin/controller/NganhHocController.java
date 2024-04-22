package com.hokhanh.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hokhanh.libary.model.NganhHoc;
import com.hokhanh.libary.service.NganhHocService;

@Controller
public class NganhHocController {
	
	@Autowired
	private NganhHocService nganhHocService;

	@GetMapping("/nganhHoc/findById")
	@ResponseBody
	public NganhHoc findById(Long id) {
		return this.nganhHocService.findById(id);
	}
	
	@GetMapping("/nganhHoc/findByTenNganh")
	@ResponseBody
	public NganhHoc findByTenNganh(String tenNganh) {
		return this.nganhHocService.findByTenNganh(tenNganh);
	}
}
