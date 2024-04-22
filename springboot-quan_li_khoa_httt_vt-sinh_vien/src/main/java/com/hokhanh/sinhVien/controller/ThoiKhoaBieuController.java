package com.hokhanh.sinhVien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hokhanh.libary.service.LopHocService;

@Controller
public class ThoiKhoaBieuController {
	
	@Autowired
	private LopHocService lopHocService;

	@GetMapping("/thoiKhoaBieu")
	public String thoiKhoaBieu(Model model) {
		model.addAttribute("tkbList", lopHocService.thoiKhoaBieu());
		return "thoiKhoaBieu";
	}
}
