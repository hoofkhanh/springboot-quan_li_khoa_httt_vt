package com.hokhanh.admin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hokhanh.libary.model.ThongBao;
import com.hokhanh.libary.service.ThongBaoService;

@Controller
public class ThongBaoController {

	@Autowired
	private ThongBaoService thongBaoService;
	
	@GetMapping("/thongBao")
	public String thongBao(Model model) {
		model.addAttribute("title", "Trang thông báo");
		model.addAttribute("thongBaoList", this.thongBaoService.findAllSortNgayDang());
		return "thongBao";
	}
	
	@PostMapping("/thongBao/themThongBao")
	public String themThongBao(ThongBao thongBao, MultipartFile hinhAnh) throws IOException {
		this.thongBaoService.themThongBao(thongBao, hinhAnh);
		return "redirect:/thongBao";
	}
	
	@PostMapping("/thongBao/chinhSuaThongBao")
	public String chinhSuaThongBao(ThongBao thongBao, MultipartFile hinhAnh) throws IOException {
		this.thongBaoService.chinSuaThongBao(thongBao, hinhAnh);
		return "redirect:/thongBao";
	}
	
	@GetMapping("/thongBao/deleteById")
	public String deleteById(Long id) {
		this.thongBaoService.deleteById(id);
		return "redirect:/thongBao";
	}
	
	@GetMapping("/thongBao/findById")
	@ResponseBody
	public ThongBao findById(Long id) {
		return this.thongBaoService.findById(id);
	}
	
}
