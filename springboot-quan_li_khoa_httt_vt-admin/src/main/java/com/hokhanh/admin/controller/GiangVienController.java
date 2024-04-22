package com.hokhanh.admin.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.model.GiangVien_LopHoc_MonHoc;
import com.hokhanh.libary.model.LopHoc;
import com.hokhanh.libary.model.MonHoc;
import com.hokhanh.libary.service.GiangVienService;
import com.hokhanh.libary.service.LopHocService;
import com.hokhanh.libary.service.MonHocService;

@Controller
public class GiangVienController {
		
	@Autowired
	private GiangVienService giangVienService;
	
	@Autowired
	private MonHocService monHocService;
	
	@Autowired 
	private LopHocService lopHocService;
	

	@GetMapping("/giangVien")
	public String giangVien(Model model) {
		model.addAttribute("title", "Trang giảng viên");
		model.addAttribute("giangVienList", giangVienService.findAllGiangVien());
		model.addAttribute("monHocList", this.monHocService.findAllSortTenMonHoc());
		return "giangVien";
	}
	
	@PostMapping("/giangVien/themGiangVien")
	public String themGiangVien(GiangVien giangVien, Model model, MultipartFile hinhAnhCuaGiangVien) throws IOException {
		if(this.giangVienService.themGiangVien(giangVien, hinhAnhCuaGiangVien.getBytes()) == null) {
			model.addAttribute("title", "Trang giảng viên");
			model.addAttribute("giangVienList", giangVienService.findAllGiangVien());
			model.addAttribute("monHocList", this.monHocService.findAllSortTenMonHoc());
			model.addAttribute("error", "Trùng số điện thoại hoặc email");
			return "giangVien";
		}
		
		return "redirect:/giangVien";
	}
	
	@PostMapping("/giangVien/chinhSuaGiangVien")
	public String chinhSuaGiangVien(GiangVien giangVien, Model model, MultipartFile hinhAnhCuaGiangVien) throws IOException {
		byte[] hinhAnhCuaGiangVienByte = null;
		if(hinhAnhCuaGiangVien.isEmpty() == false) {
			hinhAnhCuaGiangVienByte = hinhAnhCuaGiangVien.getBytes();
		}
		
		if(this.giangVienService.chinhSuaGiangVien(giangVien, hinhAnhCuaGiangVienByte) == null) {
			model.addAttribute("title", "Trang giảng viên");
			model.addAttribute("giangVienList", giangVienService.findAllGiangVien());
			model.addAttribute("monHocList", this.monHocService.findAllSortTenMonHoc());
			model.addAttribute("error", "Trùng số điện thoại hoặc email");
			return "giangVien";
		}
		
		return "redirect:/giangVien";
	}
	
	@GetMapping("/giangVien/deleteById")
	public String deleteById(Long id) {
		this.giangVienService.deleteById(id);
		return "redirect:/giangVien";
	}
	
	@GetMapping("/giangVien/findById")
	@ResponseBody
	public GiangVien findById(Long id) {
		return this.giangVienService.findById(id);
	}
	
	@GetMapping("/giangVien/findByTenGiangVien")
	@ResponseBody
	public GiangVien findByTenGiangVien(String tenGiangVien) {
		return this.giangVienService.findByTenGiangVien(tenGiangVien);
	}
	
	@GetMapping("/giangVien/giangVienLopHocMonHoc")
	public String giangVienLopHocMonHoc(Model model) {
		model.addAttribute("title", "Trang lớp học và môn học của giảng viên");
		model.addAttribute("giangVienList", this.giangVienService.findAllGiangVien());
		model.addAttribute("lopHocList", this.lopHocService.lopHoc());
		model.addAttribute("gv_lh_mh_list", this.giangVienService.findAllSortGiangVienAndLopHocAndMonHoc());
		return "giangVienLopHocMonHoc";
	}
	
	@PostMapping("/giangVien/themGiangVienLopHocMonHoc")
	public String themGiangVienLopHocMonHoc(GiangVien_LopHoc_MonHoc giangVien_LopHoc_MonHoc, String emailTruong, Model model)  {
		if(this.giangVienService.themGiangVienLopHocMonHoc(giangVien_LopHoc_MonHoc, emailTruong) == null) {
			model.addAttribute("title", "Trang lớp học và môn học của giảng viên");
			model.addAttribute("giangVienList", this.giangVienService.findAllGiangVien());
			model.addAttribute("lopHocList", this.lopHocService.lopHoc());
			model.addAttribute("gv_lh_mh_list", this.giangVienService.findAllSortGiangVienAndLopHocAndMonHoc());
			model.addAttribute("error", "Giảng viên này đã được thêm dạy lớp này và môn này rồi");
			return "giangVienLopHocMonHoc";
		}
		
		return "redirect:/giangVien/giangVienLopHocMonHoc";
	}
	
	@PostMapping("/giangVien/chinhSuaGiangVienLopHocMonHoc")
	public String chinhSuaGiangVienLopHocMonHoc(GiangVien_LopHoc_MonHoc giangVien_LopHoc_MonHoc, String emailTruong, Model model)  {
		if(this.giangVienService.chinhSuaGiangVienLopHocMonHoc(giangVien_LopHoc_MonHoc, emailTruong) == null) {
			model.addAttribute("title", "Trang lớp học và môn học của giảng viên");
			model.addAttribute("giangVienList", this.giangVienService.findAllGiangVien());
			model.addAttribute("lopHocList", this.lopHocService.lopHoc());
			model.addAttribute("gv_lh_mh_list", this.giangVienService.findAllSortGiangVienAndLopHocAndMonHoc());
			model.addAttribute("error", "Giảng viên này đã được thêm dạy lớp này và môn này rồi");
			return "giangVienLopHocMonHoc";
		}
		
		return "redirect:/giangVien/giangVienLopHocMonHoc";
	}
	
	@GetMapping("/giangVien/deleteGiangVienLopHocMonHoc")
	public String deleteGiangVienLopHocMonHoc(Long id) {
		this.giangVienService.deleteGiangVienLopHocMonHoc(id);
		return "redirect:/giangVien/giangVienLopHocMonHoc";
	}
	
	@GetMapping("/giangVien/findByEmailTruong")
	@ResponseBody
	public GiangVien findByEmailTruong(String emailTruong) {
		return this.giangVienService.findByEmailTruong(emailTruong);
	}
	
	@GetMapping("/giangVien/findGiangVienLopHocMonHocById")
	@ResponseBody
	public GiangVien_LopHoc_MonHoc findGiangVienLopHocMonHocById(Long id) {
		return this.giangVienService.findGiangVienLopHocMonHocById(id);
	}
	
	@GetMapping("/giangVien/giangVienChuNhiem")
	public String giangVienChuNhiem(Model model) {
		model.addAttribute("title", "Trang giang viên chủ nhiệm");
		model.addAttribute("gvlhList", this.giangVienService.giangVienChuNhiem());
		return "giangVienChuNhiem";
	}
	
	@GetMapping("/giangVien/giangVienChuNhiem/deleteById")
	public String deleteGiangVienChuNhiemById(Long id) {
		this.giangVienService.deleteGiangVienChuNhiemById(id);
		return "redirect:/giangVien/giangVienChuNhiem";
	}
}
