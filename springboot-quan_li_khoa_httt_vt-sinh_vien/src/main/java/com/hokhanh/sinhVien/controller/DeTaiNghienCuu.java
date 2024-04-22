package com.hokhanh.sinhVien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hokhanh.libary.service.DeTaiNghienCuuService;

@Controller
public class DeTaiNghienCuu {

	@Autowired
	private DeTaiNghienCuuService deTaiNghienCuuService;
	
	@GetMapping("/deTaiNghienCuu")
	public String deTaiNghienCuu(Model model) {
		model.addAttribute("deTaiNghienCuuList", this.deTaiNghienCuuService.findAllSortNam());
		return "deTaiNgienCuu";
	}
}
