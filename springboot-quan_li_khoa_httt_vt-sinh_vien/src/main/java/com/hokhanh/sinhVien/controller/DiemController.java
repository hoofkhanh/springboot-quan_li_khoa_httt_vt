package com.hokhanh.sinhVien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hokhanh.libary.service.DiemRenLuyenService;
import com.hokhanh.libary.service.DiemService;
import com.hokhanh.libary.service.SinhVienService;

@Controller
public class DiemController {
	
	@Autowired
	private SinhVienService sinhVienService;
	
	@Autowired
	private DiemService diemService;
	
	@Autowired
	private DiemRenLuyenService diemRenLuyenService;

	@GetMapping("/diem")
	public String diem(Model model, Authentication authentication) {
		model.addAttribute("sinhVien", this.sinhVienService.findByEmailTruong(authentication.getName()));
		model.addAttribute("diemList", this.diemService.findBySinhVien(authentication));
		model.addAttribute("drlList", this.diemRenLuyenService.findBySinhVien(authentication));
		return "Diem";
	}
}
