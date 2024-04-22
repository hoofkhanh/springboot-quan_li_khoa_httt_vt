package com.hokhanh.giangVien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hokhanh.libary.service.DiemRenLuyenService;
import com.hokhanh.libary.service.GiangVienService;
import com.hokhanh.libary.service.SinhVienService;

@Controller
public class DiemRenLuyenController {

	@Autowired
	private DiemRenLuyenService diemRenLuyenService;
	
	
	@Autowired
	private SinhVienService sinhVienService;
	
	@GetMapping("/diemRenLuyen")
	public String diem(Model model, Authentication authentication) {
		model.addAttribute("title", "Trang điểm rèn luyện");
		model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVienOfGiangVienChuNhiem(authentication.getName()));
		return "diemRenLuyen";
	}
	
	@PostMapping("/diemRenLuyen/saveDiem")
	public String saveDiemRenLuyen(String allIdSinhVien, String allDiemRenLuyen, String allHocKi, String allNamHocCuaSinhVien) {
		this.diemRenLuyenService.saveDiemRenLuyen(allIdSinhVien, allDiemRenLuyen, allHocKi, allNamHocCuaSinhVien);
		return "redirect:/diemRenLuyen";
	}
	
	@GetMapping("/xemDiemRenLuyen")
	public String xemDiemRenLuyen(Model model, Authentication authentication) {
		model.addAttribute("title", "Trang xem điểm rèn luyện");
		model.addAttribute("drlList", this.diemRenLuyenService.findAllSinhVienyLopHocOfGiangVienChuNhiem(authentication.getName()));
		return "xemDiemRenLuyen";
	}
	
	@GetMapping("/xoaDiemRenLuyen")
	public String xoaDiemRenLuyen(Long id) {
		this.diemRenLuyenService.xoaDiemRenLuyen(id);
		return "redirect:/xemDiemRenLuyen";
	}
	
}
