package com.hokhanh.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hokhanh.libary.model.MonHoc;
import com.hokhanh.libary.service.ChuyenNganhService;
import com.hokhanh.libary.service.MonHocService;
import com.hokhanh.libary.service.NganhHocService;

@Controller
public class MonHocController {
	
	@Autowired
	private ChuyenNganhService chuyenNganhService;
	
	@Autowired
	private NganhHocService nganhHocService;
	
	@Autowired
	private MonHocService monHocService;

	@GetMapping("/monHoc")
	public String monHoc(Model model) {
		model.addAttribute("title", "Trang môn học");
		model.addAttribute("nganhHocList", nganhHocService.findAllNganhHoc());
		model.addAttribute("chuyenNganhList", chuyenNganhService.findAllChuyenNganh());
		model.addAttribute("monHocList", monHocService.findAllMonHoc());
		return "monHoc";
	}
	
	@GetMapping("/monHoc/findMonHocById")
	@ResponseBody
	public MonHoc findMonHocById(Long id) {
		return this.monHocService.findMonHocById(id);
	}
	
	@PostMapping("/monHoc/themMonHoc")
	public String themMonHoc(MonHoc monHoc, Model model) {
		if(this.monHocService.themMonHoc(monHoc) == null) {
			model.addAttribute("title", "Trang môn học");
			model.addAttribute("nganhHocList", nganhHocService.findAllNganhHoc());
			model.addAttribute("chuyenNganhList", chuyenNganhService.findAllChuyenNganh());
			model.addAttribute("monHocList", monHocService.findAllMonHoc());
			model.addAttribute("error", "Trùng tên môn học");
			return "monHoc";
		}
		
		return "redirect:/monHoc";
	}
	
	@PostMapping("/monHoc/chinhSuaMonHoc")
	public String chinhSuaMonHoc(MonHoc monHoc, Model model) {
		if(this.monHocService.chinhSuaMonHoc(monHoc) == null) {
			model.addAttribute("title", "Trang môn học");
			model.addAttribute("nganhHocList", nganhHocService.findAllNganhHoc());
			model.addAttribute("chuyenNganhList", chuyenNganhService.findAllChuyenNganh());
			model.addAttribute("monHocList", monHocService.findAllMonHoc());
			model.addAttribute("error", "Trùng tên môn học");
			return "monHoc";
		}
		
		return "redirect:/monHoc";
	}
}
