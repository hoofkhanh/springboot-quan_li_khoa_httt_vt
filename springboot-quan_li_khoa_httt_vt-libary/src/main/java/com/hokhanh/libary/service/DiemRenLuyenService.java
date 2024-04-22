package com.hokhanh.libary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.BaoLuu;
import com.hokhanh.libary.model.Diem;
import com.hokhanh.libary.model.DiemRenLuyen;
import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.model.GiangVien_LopHoc;
import com.hokhanh.libary.model.LopHoc;
import com.hokhanh.libary.model.MonHoc;
import com.hokhanh.libary.model.SinhVien;
import com.hokhanh.libary.model.SinhVien_MonHocNo;
import com.hokhanh.libary.repository.BaoLuuRepository;
import com.hokhanh.libary.repository.DiemRenLuyenRepository;
import com.hokhanh.libary.repository.DiemRepository;
import com.hokhanh.libary.repository.GiangVienRepository;
import com.hokhanh.libary.repository.GiangVien_LopHocRepository;
import com.hokhanh.libary.repository.SinhVienRepository;

@Service
public class DiemRenLuyenService {
	
	@Autowired
	private SinhVienRepository sinhVienRepository;
	

	@Autowired
	private DiemRenLuyenRepository diemRenLuyenRepository;
	
	@Autowired
	private GiangVienRepository giangVienRepository;
	
	@Autowired
	private GiangVien_LopHocRepository giangVien_LopHocRepository;
	
	@Autowired
	private BaoLuuRepository baoLuuRepository;

	public void saveDiemRenLuyen(String allIdSinhVien, String allDiemRenLuyen, String allHocKi,
			String allNamHocCuaSinhVien) {
		List<SinhVien> sinhVienList = new ArrayList<>();
		for (String idSinhVien : allIdSinhVien.split(",")) {
			SinhVien sinhVien = this.sinhVienRepository.findById(Long.parseLong(idSinhVien)).get();
			sinhVienList.add(sinhVien);
		}
		
		List<Double> diemList = new ArrayList<>();
		for (String diem : allDiemRenLuyen.split(",")) {
			diemList.add(Double.parseDouble(diem));
		}
		
		List<Integer> hocKiList = new ArrayList<>();
		for (String hocKi : allHocKi.split(",")) {
			hocKiList.add(Integer.parseInt(hocKi));
		}
		
		List<Integer> namHocCuaSinhVienList = new ArrayList<>();
		for (String namHoc : allNamHocCuaSinhVien.split(",")) {
			namHocCuaSinhVienList.add(Integer.parseInt(namHoc));
		}
		
		for(int i=0; i< sinhVienList.size(); i++) {
			DiemRenLuyen diemRenLuyenDatabase = this.diemRenLuyenRepository.findBySinhVienAndHocKiCuaSinhVienAndNamHocCuaSinhVien(sinhVienList.get(i), hocKiList.get(i), namHocCuaSinhVienList.get(i));
			if(diemRenLuyenDatabase!= null) {
				if(diemList.get(i) != -1 && hocKiList.get(i) != -1 && namHocCuaSinhVienList.get(i) != -1 ) {
					DiemRenLuyen diemRenLuyen = new DiemRenLuyen();
					diemRenLuyen.setId(diemRenLuyenDatabase.getId());
					diemRenLuyen.setDiem(diemList.get(i));
					diemRenLuyen.setHocKiCuaSinhVien(hocKiList.get(i));
					diemRenLuyen.setNamHocCuaSinhVien(namHocCuaSinhVienList.get(i));
					diemRenLuyen.setSinhVien(sinhVienList.get(i));
					
					this.diemRenLuyenRepository.save(diemRenLuyen);
				}
			}else {
				if(diemList.get(i) != -1 && hocKiList.get(i) != -1 && namHocCuaSinhVienList.get(i) != -1 ) {
					DiemRenLuyen diemRenLuyen = new DiemRenLuyen();
					diemRenLuyen.setDiem(diemList.get(i));
					diemRenLuyen.setHocKiCuaSinhVien(hocKiList.get(i));
					diemRenLuyen.setNamHocCuaSinhVien(namHocCuaSinhVienList.get(i));
					diemRenLuyen.setSinhVien(sinhVienList.get(i));
					
					this.diemRenLuyenRepository.save(diemRenLuyen);
				}
			}
			
			
		}
	}

	public List<DiemRenLuyen> findAllSinhVienyLopHocOfGiangVienChuNhiem(String emailTruong) {
		GiangVien giangVien = this.giangVienRepository.findByEmailTruong(emailTruong);
		GiangVien_LopHoc giangVien_LopHoc = this.giangVien_LopHocRepository.findByGiangVien(giangVien);
		
		List<DiemRenLuyen> diemRenLuyenList = new ArrayList<>();
		
		List<SinhVien> sinhVienList = new ArrayList<>();
		if(giangVien_LopHoc != null && giangVien_LopHoc.getLopHoc() != null && giangVien_LopHoc.getSoKhoaLopChuNhiem() != null && giangVien_LopHoc.getSoKhoaLopChuNhiem().equals("") == false) {
			sinhVienList = this.sinhVienRepository.findByLopHocAndSoKhoaCuaNganh(giangVien_LopHoc.getLopHoc(), giangVien_LopHoc.getSoKhoaLopChuNhiem());
			
			if(sinhVienList != null && sinhVienList.isEmpty() == false) {
				for (SinhVien sinhVien : sinhVienList) {
					List<DiemRenLuyen> diemRenLuyenListTemp = this.diemRenLuyenRepository.findBySinhVienSortNamHocAndHocKi(sinhVien.getId());
					if(diemRenLuyenListTemp != null && diemRenLuyenListTemp.isEmpty() == false) {
						for (DiemRenLuyen drl : diemRenLuyenListTemp) {
							BaoLuu baoLuu = this.baoLuuRepository.findBySinhVien(drl.getSinhVien());
							if(baoLuu == null || ( baoLuu != null && baoLuu.isTrangThai() == false)) {
								diemRenLuyenList.add(drl);
							}
						}
					}
				}
			}
		
		}
		return diemRenLuyenList;
	}

	public void xoaDiemRenLuyen(Long id) {
		this.diemRenLuyenRepository.deleteById(id);
		
	}

	public List<DiemRenLuyen> findBySinhVien(Authentication authentication) {
		return this.diemRenLuyenRepository.findBySinhVien(this.sinhVienRepository.findByEmailTruong(authentication.getName()));
	}
}
