package com.hokhanh.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hokhanh.libary.model.DoanhThu;
import com.hokhanh.libary.service.HocPhiService;

@Controller
public class IndexController {
	
	@Autowired
	private HocPhiService hocPhiService;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("title", "Trang chủ");
		
		return "index";
	}
	
	@GetMapping("/tinhDoanhThu")
	@ResponseBody
	public List<DoanhThu> tinhDoanhThu(Model model) {
		
		return this.hocPhiService.tinhDoanhThuTheoNam();
	}
}
