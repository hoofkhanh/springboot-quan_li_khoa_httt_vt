package com.hokhanh.admin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hokhanh.libary.model.DeTaiNghienCuu;
import com.hokhanh.libary.model.ThongBao;
import com.hokhanh.libary.service.DeTaiNghienCuuService;
import com.hokhanh.libary.service.GiangVienService;
import com.hokhanh.libary.service.ThongBaoService;

@Controller
public class DeTaiNghienCuuController {

	@Autowired
	private DeTaiNghienCuuService deTaiNghienCuuService;
	
	@Autowired
	private GiangVienService giangVienService;
	
	@GetMapping("/deTaiNghienCuu")
	public String deTaiNghienCuu(Model model) {
		model.addAttribute("title", "Trang đề tài nghiên cứu");
		model.addAttribute("deTaiNghienCuuList", this.deTaiNghienCuuService.findAllSortNam());
		model.addAttribute("giangVienList", this.giangVienService.findAllGiangVien());
		return "deTaiNghienCuu";
	}
	
	@PostMapping("/deTaiNghienCuu/themDeTaiNghienCuu")
	public String themDeTaiNghienCuu(DeTaiNghienCuu deTaiNghienCuu, Model model, String allTenCacGiangVien)  {
		if(this.deTaiNghienCuuService.themDeTaiNghienCuu(deTaiNghienCuu, allTenCacGiangVien)== null) {
			model.addAttribute("title", "Trang đề tài nghiên cứu");
			model.addAttribute("deTaiNghienCuuList", this.deTaiNghienCuuService.findAllSortNam());
			model.addAttribute("giangVienList", this.giangVienService.findAllGiangVien());
			model.addAttribute("error", "Trùng tên đề tài nghiên cứu");
			return "deTaiNghienCuu";
		}
		
		return "redirect:/deTaiNghienCuu";
	}
	
	@PostMapping("/deTaiNghienCuu/chinhSuaDeTaiNghienCuu")
	public String chinhSuaThongBao(DeTaiNghienCuu deTaiNghienCuu, Model model, String allTenCacGiangVien) throws IOException {
		if(this.deTaiNghienCuuService.chinhSuaDeTaiNghienCuu(deTaiNghienCuu, allTenCacGiangVien)== null) {
			model.addAttribute("title", "Trang đề tài nghiên cứu");
			model.addAttribute("deTaiNghienCuuList", this.deTaiNghienCuuService.findAllSortNam());
			model.addAttribute("giangVienList", this.giangVienService.findAllGiangVien());
			model.addAttribute("error", "Trùng tên đề tài nghiên cứu");
			return "deTaiNghienCuu";
		}
		
		return "redirect:/deTaiNghienCuu";
	}
	
	@GetMapping("/deTaiNghienCuu/deleteById")
	public String deleteById(Long id) {
		this.deTaiNghienCuuService.deleteById(id);
		return "redirect:/deTaiNghienCuu";
	}
	
	@GetMapping("/deTaiNghienCuu/findById")
	@ResponseBody
	public DeTaiNghienCuu findById(Long id) {
		return this.deTaiNghienCuuService.findById(id);
	}
}
