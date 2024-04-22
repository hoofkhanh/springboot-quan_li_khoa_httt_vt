package com.hokhanh.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hokhanh.libary.model.LopHoc;
import com.hokhanh.libary.model.LopHoc_ThoiKhoaBieu;
import com.hokhanh.libary.repository.GiangVienRepository;
import com.hokhanh.libary.repository.LopHoc_ThoiKhoaBieuRepository;
import com.hokhanh.libary.service.ChuyenNganhService;
import com.hokhanh.libary.service.GiangVienService;
import com.hokhanh.libary.service.LopHocService;
import com.hokhanh.libary.service.NganhHocService;

@Controller
public class LopHocController {

	@Autowired
	private LopHocService lopHocService;
	
	@Autowired 
	private NganhHocService nganhHocService;
	
	@Autowired 
	private ChuyenNganhService chuyenNganhService;
	
	@Autowired 
	private GiangVienService giangVienService;
	
	
	@GetMapping("/lopHoc")
	public String lopHoc(LopHoc lopHoc, Model model) {
		model.addAttribute("title", "Trang lớp học");
		model.addAttribute("lopHocList", this.lopHocService.lopHoc());
		model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
		model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganh());
		model.addAttribute("giangVienChuaChuNhiemList", giangVienService.findAllGiangVien());
		return "lopHoc";
	}
	
	@PostMapping("/lopHoc/themLopHoc")
	public String themLopHoc(LopHoc lopHoc, Model model, String chuyenNganhInsert) {
		if(this.lopHocService.themLopHoc(lopHoc, chuyenNganhInsert) == null) {
			model.addAttribute("title", "Trang lớp học");
			model.addAttribute("lopHocList", this.lopHocService.lopHoc());
			model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
			model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganh());
			model.addAttribute("error", "Trùng tên lớp học trùng giáo viên chủ nhiệm");
			return "lopHoc";
		}
		
		return "redirect:/lopHoc";
	}
	
	@PostMapping("/lopHoc/chinhSuaLopHoc")
	public String chinhSuaLopHoc(LopHoc lopHoc, Model model, String giangVienEdit, String chuyenNganhEdit,
				String thoiKhoaBieuEdit, String soKhoaLopChuNhiem, String soKhoaThoiKhoaBieu) {
		if(this.lopHocService.chinhSuaLopHoc(lopHoc, giangVienEdit, chuyenNganhEdit, thoiKhoaBieuEdit, soKhoaLopChuNhiem, soKhoaThoiKhoaBieu) == null) {
			model.addAttribute("title", "Trang lớp học");
			model.addAttribute("lopHocList", this.lopHocService.lopHoc());
			model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
			model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganh());
			model.addAttribute("giangVienChuaChuNhiemList", giangVienService.findAllGiangVien());
			model.addAttribute("error", "Lớp đó đã có giáo viên chủ nhiệm");
			return "lopHoc";
		}
		
		return "redirect:/lopHoc";
	}
	
	@GetMapping("/lopHoc/findById")
	@ResponseBody
	public LopHoc findById(Long id) {
		return this.lopHocService.findById(id);
	}
	
	@GetMapping("/lopHoc/findByIdNganhHoc")
	@ResponseBody
	public List<LopHoc> findByIdNganhHoc(Long idNganhHoc) {
		return this.lopHocService.findByIdNganhHoc(idNganhHoc);
	}
	
	@GetMapping("/lopHoc/findByIdChuyenNganh")
	@ResponseBody
	public List<LopHoc> findByIdChuyenNganh(Long idChuyenNganh) {
		return this.lopHocService.findByIdChuyenNganh(idChuyenNganh);
	}
	
	@GetMapping("/lopHoc/findAll")
	@ResponseBody
	public List<LopHoc> findAll() {
		return this.lopHocService.findAll();
	}
	
	@GetMapping("/lopHoc/thoiKhoaBieu")
	public String thoiKhoaBieu(LopHoc lopHoc, Model model) {
		model.addAttribute("title", "Trang thời khóa biểu");
		model.addAttribute("lhtkbList", this.lopHocService.thoiKhoaBieu());
		return "thoiKhoaBieu";
	}
	
	@GetMapping("/lopHoc/thoiKhoaBieu/deleteById")
	public String thoiKhoaBieu(Long id) {
		this.lopHocService.deleteTKBById(id);
		return "redirect:/lopHoc/thoiKhoaBieu";
	}
	
}
