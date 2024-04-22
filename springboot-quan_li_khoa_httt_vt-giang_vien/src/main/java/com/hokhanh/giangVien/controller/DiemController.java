package com.hokhanh.giangVien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hokhanh.libary.model.Diem;
import com.hokhanh.libary.model.SinhVien;
import com.hokhanh.libary.service.DiemService;
import com.hokhanh.libary.service.GiangVienService;
import com.hokhanh.libary.service.LopHocService;
import com.hokhanh.libary.service.SinhVienService;

@Controller
public class DiemController {
	
	@Autowired
	private DiemService diemService;
	
	@Autowired
	private GiangVienService giangVienService;
	
	@Autowired
	private SinhVienService sinhVienService;
	
	@GetMapping("/diem")
	public String diem(Model model, Authentication authentication) {
		model.addAttribute("title", "Trang điểm");
		model.addAttribute("gvlhmhList", this.giangVienService.findGiangVien_LopHoc_MonHocByGiangVien(authentication.getName()));
		return "diem";
	}
	
	@GetMapping("/nhapDiem")
	public String diem(Long idLopHoc, Long idMonHoc, String tenLopHoc, String soKhoa, String tenMonHoc, Model model) {
		model.addAttribute("title", "Trang điểm");
		model.addAttribute("tenLopHoc", tenLopHoc);
		model.addAttribute("tenMonHoc", tenMonHoc);
		model.addAttribute("idMonHoc", idMonHoc);
		model.addAttribute("idLopHoc", idLopHoc);
		model.addAttribute("soKhoa", soKhoa);
		List<SinhVien> sinhVienList = this.sinhVienService.findByLopHocAndSoKhoaCuaNganh(idLopHoc, soKhoa, idMonHoc);
		model.addAttribute("sinhVienList", sinhVienList);
		model.addAttribute("diemList", this.diemService.findBySinhVienAndMonHoc(sinhVienList, idMonHoc));
		return "nhapDiem";
	}
	
	@PostMapping("/nhapDiem/saveDiem")
	public String saveDiem(String sinhVienList, String diemThuongXuyenList, String diemThiList, Long monHoc ,Model model, Long idLopHoc, Long idMonHoc, String tenLopHoc, String tenMonHoc, String soKhoa, RedirectAttributes redirectAttributes) {
		this.diemService.saveDiem(sinhVienList, diemThuongXuyenList, diemThiList, monHoc);
		redirectAttributes.addAttribute("tenLopHoc", tenLopHoc);
		redirectAttributes.addAttribute("tenMonHoc", tenMonHoc);
		redirectAttributes.addAttribute("idMonHoc", idMonHoc);
		redirectAttributes.addAttribute("idLopHoc", idLopHoc);
		redirectAttributes.addAttribute("soKhoa", soKhoa);
		return "redirect:/nhapDiem";
	}
}
