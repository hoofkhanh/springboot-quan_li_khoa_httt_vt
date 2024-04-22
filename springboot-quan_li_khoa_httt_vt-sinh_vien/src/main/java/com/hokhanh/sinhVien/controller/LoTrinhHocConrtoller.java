package com.hokhanh.sinhVien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hokhanh.libary.service.ChuyenNganhService;
import com.hokhanh.libary.service.MonHocService;
import com.hokhanh.libary.service.NganhHocService;
import com.hokhanh.libary.service.ThongBaoService;

@Controller
public class LoTrinhHocConrtoller {

	@Autowired
	private MonHocService monHocService;
	
	@Autowired
	private NganhHocService nganhHocService;
	
	@Autowired
	private ChuyenNganhService chuyenNganhService;

	@GetMapping("/loTrinhHoc")
	public String loTrinhHoc(Model model) {
		model.addAttribute("monHocList", this.monHocService.findAllMonHoc());
		model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
		model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganhSortNganhHoc());
		return "loTrinhHoc";
	}
}
