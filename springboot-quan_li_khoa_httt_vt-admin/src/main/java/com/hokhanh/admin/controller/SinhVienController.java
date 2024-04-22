package com.hokhanh.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hokhanh.libary.model.LopHoc;
import com.hokhanh.libary.model.SinhVien;
import com.hokhanh.libary.model.SinhVien_HocGhep;
import com.hokhanh.libary.model.SinhVien_HocPhi;
import com.hokhanh.libary.service.ChuyenNganhService;
import com.hokhanh.libary.service.LopHocService;
import com.hokhanh.libary.service.MonHocService;
import com.hokhanh.libary.service.NganhHocService;
import com.hokhanh.libary.service.SinhVienService;

@Controller
public class SinhVienController {

	@Autowired
	private SinhVienService sinhVienService;

	@Autowired
	private LopHocService lopHocService;

	@Autowired
	private NganhHocService nganhHocService;

	@Autowired
	private ChuyenNganhService chuyenNganhService;
	
	@Autowired
	private MonHocService monHocService;

	@GetMapping("/sinhVien")
	public String sinhVien(Model model) {
		model.addAttribute("title", "Trang sinh viên");
		model.addAttribute("lopHocList", this.lopHocService.lopHoc());
		model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
		model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganh());
		model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVien());
		return "sinhVien";
	}

	@PostMapping("/sinhVien/themSinhVien")
	public String themSinhVien(SinhVien sinhVien, Model model) {
		if (this.sinhVienService.themSinhVien(sinhVien) == null) {
			model.addAttribute("title", "Trang sinh viên");
			model.addAttribute("lopHocList", this.lopHocService.lopHoc());
			model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
			model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganh());
			model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVien());
			model.addAttribute("error", "Trùng email hoặc SĐT");
			return "sinhVien";
		}

		return "redirect:/sinhVien";
	}

	@PostMapping("/sinhVien/chinhSuaSinhVien")
	public String chinhSuaSinhVien(SinhVien sinhVien, Model model) {
		if (this.sinhVienService.chinhSuaSinhVien(sinhVien) == null) {
			model.addAttribute("title", "Trang sinh viên");
			model.addAttribute("lopHocList", this.lopHocService.lopHoc());
			model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
			model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganh());
			model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVien());
			model.addAttribute("error", "Trùng email hoặc SĐT");
			return "sinhVien";
		}

		return "redirect:/sinhVien";
	}

	@GetMapping("/sinhVien/deleteById")
	public String deleteById(Long id) {
		this.sinhVienService.deleteById(id);
		return "redirect:/sinhVien";
	}

	@GetMapping("/sinhVien/findById")
	@ResponseBody
	public SinhVien findById(Long id) {
		return this.sinhVienService.findById(id);
	}

	@GetMapping("/sinhVien/sinhVienBaoLuu")
	public String sinhVienBaoLuu(Model model) {
		model.addAttribute("title", "Trang bảo lưu sinh viên");
		model.addAttribute("lopHocList", this.lopHocService.lopHoc());
		model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
		model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganh());
		model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVienBaoLuu());
		return "sinhVienBaoLuu";
	}

	@GetMapping("/sinhVien/sinhVienBaoLuu/baoLuuSinhVien")
	public String baoLuuSinhVien(Long id, Model model) {
		if (this.sinhVienService.baoLuuSinhVien(id).equals("Thất bại")) {
			model.addAttribute("title", "Trang sinh viên");
			model.addAttribute("lopHocList", this.lopHocService.lopHoc());
			model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
			model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganh());
			model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVien());
			model.addAttribute("error", "Sinh viên đã bảo lưu 1 lần");
			return "sinhVien";
		}

		return "redirect:/sinhVien/sinhVienBaoLuu";
	}

	@GetMapping("/sinhVien/sinhVienBaoLuu/huyBaoLuuSinhVien")
	public String huyBaoLuuSinhVien(Long id, Model model) {
		this.sinhVienService.huyBaoLuuSinhVien(id);
		return "redirect:/sinhVien/sinhVienBaoLuu";
	}

	@GetMapping("/sinhVien/findSinhVienByHocPhi")
	@ResponseBody
	public List<SinhVien> findSinhVienByHocPhi(Model model, Long id) {
		return this.sinhVienService.findSinhVienByHocPhi(id);
	}

	@GetMapping("/sinhVien/sinhVienDongHocPhi")
	public String sinhVienDongHocPhi(Model model) {
		model.addAttribute("title", "Trang sinh viên đóng học phí");
		model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVienHocPhi());
		return "sinhVienDongHocPhi";
	}

	@PostMapping("/sinhVien/sinhVienDongHocPhi/dongHocPhi")
	public String dongHocPhiChoSinhVien(SinhVien_HocPhi sinhVien_HocPhi, String allSinhVien) {		
		this.sinhVienService.dongHocPhi(sinhVien_HocPhi, allSinhVien);
		return "redirect:/hocPhi";
	}

	@GetMapping("/sinhVien/sinhVienDongHocPhi/xacNhanDongHocPhi")
	public String xacNhanDongHocPhi(Long id) {		
		this.sinhVienService.xacNhanDongHocPhi(id);
		return "redirect:/sinhVien/sinhVienDongHocPhi";
	}
	
	@GetMapping("/sinhVien/sinhVienDongHocPhi/tamHoanHocPhi")
	public String tamHoanHocPhi(Long id) {		
		this.sinhVienService.tamHoanHocPhi(id);
		return "redirect:/sinhVien/sinhVienDongHocPhi";
	}
	
	@GetMapping("/sinhVien/sinhVienDongHocPhi/deleteSinhVienHocPhiById")
	public String deleteSinhVienHocPhiById(Long id) {		
		this.sinhVienService.deleteSinhVienHocPhiById(id);
		return "redirect:/sinhVien/sinhVienDongHocPhi";
	}
	
	@GetMapping("/sinhVien/sinhVienHocGhep")
	public String sinhVienHocGhep(Model model) {		
		model.addAttribute("title", "Trang sinh viên học ghép");
		model.addAttribute("sinhVienHocGhepList", this.sinhVienService.findAllSinhVienHocGhep());
		model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVien());
		model.addAttribute("monHocList", this.monHocService.findAllMonHocSortTen());
		return "sinhVienHocGhep";
	}
	
	@GetMapping("/sinhVien/sinhVienHocGhep/findLopHocByHocKiAndNamHocOfMon")
	@ResponseBody
	public List<LopHoc> findLopHocByHocKiAndNamHocOfMon(Long idMonHoc) {		
		return this.lopHocService.findLopHocByHocKiAndNamHocOfMon(idMonHoc);
	}
	
	@GetMapping("/sinhVien/sinhVienHocGhep/findById")
	@ResponseBody
	public SinhVien_HocGhep findSinhVienHocGhepById(Long id) {		
		return this.sinhVienService.findSinhVienHocGhepById(id);
	}
	
	@PostMapping("/sinhVien/themSinhVienHocGhep")
	public String themSinhVienHocGhep(SinhVien_HocGhep sinhVien, String emailTruong, Model model) {
		if (this.sinhVienService.themSinhVienHocGhep(sinhVien, emailTruong) == null) {
			model.addAttribute("title", "Trang sinh viên học ghép");
			model.addAttribute("sinhVienHocGhepList", this.sinhVienService.findAllSinhVienHocGhep());
			model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVien());
			model.addAttribute("monHocList", this.monHocService.findAllMonHocSortTen());
			model.addAttribute("error", "Trùng môn học của shinh viên");
			return "sinhVienHocGhep";
		}

		return "redirect:/sinhVien/sinhVienHocGhep";
	}

	@PostMapping("/sinhVien/chinhSuaSinhVienHocGhep")
	public String chinhSuaSinhVienHocGhep(SinhVien_HocGhep sinhVien, String emailTruong, Model model) {
		if (this.sinhVienService.chinhSuaSinhVienHocGhep(sinhVien, emailTruong) == null) {
			model.addAttribute("title", "Trang sinh viên học ghép");
			model.addAttribute("sinhVienHocGhepList", this.sinhVienService.findAllSinhVienHocGhep());
			model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVien());
			model.addAttribute("monHocList", this.monHocService.findAllMonHocSortTen());
			model.addAttribute("error", "Trùng môn học của shinh viên");
			return "sinhVienHocGhep";
		}

		return "redirect:/sinhVien/sinhVienHocGhep";
	}
	
	@GetMapping("/sinhVien/sinhVienHocGhep/deleteById")
	public String deleteSinhVienHocGhepById(Long id) {		
		this.sinhVienService.deleteSinhVienHocGhepById(id);
		return "redirect:/sinhVien/sinhVienHocGhep";
	}
	
	
	@GetMapping("/sinhVien/sinhVienMonHocNo")
	public String sinhVienMonHocNo(Long id, Model model) {		
		model.addAttribute("title", "Trang sinh viên nợ môn");
		model.addAttribute("svnmList", this.sinhVienService.sinhVienNoMon());
		return "sinhVienNoMon";
	}
	
	@GetMapping("/sinhVien/sinhVienMonHocNo/deleteById")
	public String deleteSinhVienMonHocNoById(Long id) {		
		this.sinhVienService.deleteSinhVienMonHocNoById(id);
		return "redirect:/sinhVien/sinhVienMonHocNo";
	}
	

}
