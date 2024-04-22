package com.hokhanh.sinhVien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hokhanh.libary.service.GiangVienService;

@Controller
public class GiangVienController {
	
	@Autowired
	private GiangVienService giangVienService;

	@GetMapping("/giangVienVaCanBo")
	public String giangVienVaCanBo(Model model) {
		model.addAttribute("giangVienList", this.giangVienService.findAllGiangVien());
		return "GiangVien";
	}
}
