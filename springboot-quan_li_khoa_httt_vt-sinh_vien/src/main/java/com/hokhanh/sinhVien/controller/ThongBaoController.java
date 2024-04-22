package com.hokhanh.sinhVien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hokhanh.libary.service.ThongBaoService;

@Controller
public class ThongBaoController {

	@Autowired
	private ThongBaoService thongBaoServiceh;

	@GetMapping("/thongBao")
	public String thongBao(Model model) {
		model.addAttribute("thongBaoList", this.thongBaoServiceh.findAllSortNgayDang());
		return "ThongBao";
	}
}
