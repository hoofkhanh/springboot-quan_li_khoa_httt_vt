package com.hokhanh.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hokhanh.libary.model.HocPhi;
import com.hokhanh.libary.model.SinhVien;
import com.hokhanh.libary.service.ChuyenNganhService;
import com.hokhanh.libary.service.HocPhiService;
import com.hokhanh.libary.service.NganhHocService;
import com.hokhanh.libary.service.SinhVienService;

@Controller
public class HocPhiController {
	
	@Autowired
	private HocPhiService hocPhiService;
	
	@Autowired 
	private ChuyenNganhService chuyenNganhService;
	
	@Autowired
	private NganhHocService nganhHocService;
	
	@Autowired
	private SinhVienService sinhVienService;
	
	@GetMapping("/hocPhi")
	public String hocPhi(Model model) {
		model.addAttribute("title", "Trang học phí");
		model.addAttribute("hocPhiList", this.hocPhiService.findAllSorted());
		model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
		model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganh());
		model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVien());

		return "hocPhi";
	}

	@PostMapping("/hocPhi/themHocPhi")
	public String themHocPhi(HocPhi hocPhi, Model model, double soTienMotTinChi) {
		if(this.hocPhiService.themHocPhi(hocPhi, soTienMotTinChi) == null) {
			model.addAttribute("title", "Trang học phí");
			model.addAttribute("hocPhiList", this.hocPhiService.findAllSorted());
			model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
			model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganh());
			model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVien());
			model.addAttribute("error", "Học phí này đã được thêm vào");
			return "hocPhi";
		}
		
		return "redirect:/hocPhi";
	}
	
	@PostMapping("/hocPhi/chinhSuaHocPhi")
	public String chinhSuaHocPhi(HocPhi hocPhi, Model model,  double soTienMotTinChi) {
		if(this.hocPhiService.chinhSuaHocPhi(hocPhi, soTienMotTinChi) == null) {
			model.addAttribute("title", "Trang học phí");
			model.addAttribute("hocPhiList", this.hocPhiService.findAllSorted());
			model.addAttribute("nganhHocList", this.nganhHocService.findAllNganhHoc());
			model.addAttribute("chuyenNganhList", this.chuyenNganhService.findAllChuyenNganh());
			model.addAttribute("sinhVienList", this.sinhVienService.findAllSinhVien());
			model.addAttribute("error", "Học phí này đã được thêm vào");
			return "hocPhi";
		}
		
		return "redirect:/hocPhi";
	}
	
	@GetMapping("/hocPhi/findById")
	@ResponseBody
	public HocPhi findById(Long id) {
		return this.hocPhiService.findById(id);
	}
}
