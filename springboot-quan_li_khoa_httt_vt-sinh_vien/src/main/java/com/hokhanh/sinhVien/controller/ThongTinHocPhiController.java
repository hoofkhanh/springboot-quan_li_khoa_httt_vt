package com.hokhanh.sinhVien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hokhanh.libary.service.SinhVienService;

@Controller
public class ThongTinHocPhiController {
	
	@Autowired
	private SinhVienService sinhVienService;

	@GetMapping("/thongTinHocPhi")
	public String thongTinHocPhi(Model model, Authentication authentication) {
		model.addAttribute("hocPhiList", sinhVienService.findSinhVienHocPhiBySinhVien(authentication.getName()));
		model.addAttribute("sinhVien", this.sinhVienService.findByEmailTruong(authentication.getName()));
		return "thongTinHocPhi";
	}
}
