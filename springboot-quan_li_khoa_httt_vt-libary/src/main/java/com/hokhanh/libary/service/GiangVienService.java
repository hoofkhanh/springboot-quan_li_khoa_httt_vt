package com.hokhanh.libary.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.model.GiangVien_LopHoc;
import com.hokhanh.libary.model.GiangVien_LopHoc_MonHoc;
import com.hokhanh.libary.model.LopHoc;
import com.hokhanh.libary.model.VaiTro;
import com.hokhanh.libary.repository.GiangVienRepository;
import com.hokhanh.libary.repository.GiangVien_LopHocRepository;
import com.hokhanh.libary.repository.GiangVien_LopHoc_MonHocRepository;
import com.hokhanh.libary.repository.LopHocRepository;
import com.hokhanh.libary.repository.VaiTroRepository;

@Service
public class GiangVienService {

	@Autowired
	private GiangVienRepository giangVienRepository;
	
	@Autowired
	private VaiTroRepository vaiTroRepository;
	
	@Autowired
	private LopHocRepository lopHocRepository;
	
	@Autowired
	private GiangVien_LopHoc_MonHocRepository giangVien_LopHoc_MonHocRepository;
	
	@Autowired
	private GiangVien_LopHocRepository giangVien_LopHocRepository;
	
	
	

	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public List<GiangVien> findAllGiangVien() {
		List<GiangVien> giangVienList =  this.giangVienRepository.findAllSortId();
		if(giangVienList!= null && giangVienList.isEmpty()==false) {
			for (GiangVien giangVien : giangVienList) {
				String password = giangVien.getEmailTruong().substring(0, giangVien.getEmailTruong().indexOf("@")) + "giangvien!@#123456";
				giangVien.setPassword(password);
			}
		}
		
		return giangVienList;
	}
	
	public GiangVien themGiangVien(GiangVien giangVien, byte[] hinhAnhCuaGiangVien) {
		List<GiangVien> giangVienCheck = this.giangVienRepository.findBySDTOrEmail(giangVien.getSDT(), giangVien.getEmail());
		if(giangVienCheck != null && !giangVienCheck.isEmpty()) {
			return null;
		}
		GiangVien gv = this.giangVienRepository.save(giangVien);
		
		Long maxIdInt = this.giangVienRepository.findMaxId();
		if(maxIdInt  == null) {
			maxIdInt = (long) 1;
		}
		
		String maxId = maxIdInt + "";
		
		gv.setEmailTruong(maxId + "@gv.hcmunre.edu.vn");
		
		gv.setHinhAnh(Base64.getEncoder().encodeToString(hinhAnhCuaGiangVien));
		
		gv.setVaiTro(vaiTroRepository.findByTen("Giảng Viên"));
		
		gv.setPassword(bCryptPasswordEncoder.encode(maxId + "giangvien!@#123456"));
		
		return this.giangVienRepository.save(gv);
	}
	
	public GiangVien chinhSuaGiangVien(GiangVien giangVien, byte[] hinhAnhCuaGiangVien) {
		List<GiangVien> giangVienCheck = this.giangVienRepository.findBySDTOrEmail(giangVien.getSDT(), giangVien.getEmail());
		if(giangVienCheck != null && !giangVienCheck.isEmpty()) {
			for (GiangVien gv : giangVienCheck) {
				if(gv.getId() != giangVien.getId()) {
					return null;
				}
			}
		}
		
		GiangVien giangVienDatabase = this.giangVienRepository.findById(giangVien.getId()).get();
		if(hinhAnhCuaGiangVien == null) {
			giangVien.setHinhAnh(giangVienDatabase.getHinhAnh());
		}else {
			giangVien.setHinhAnh(Base64.getEncoder().encodeToString(hinhAnhCuaGiangVien));
		}
		
		return this.giangVienRepository.save(giangVien);
	}

	public GiangVien findById(Long id) {
		return this.giangVienRepository.findById(id).get();
	}

	public void deleteById(Long id) {
		GiangVien giangVien = this.findById(id);
		giangVien.setDanhSachMonHoc(null);
		this.giangVienRepository.save(giangVien);
		
		GiangVien_LopHoc gvlh =  this.giangVien_LopHocRepository.findByGiangVien(giangVien);
		if(gvlh != null) {
			this.giangVien_LopHocRepository.deleteById(gvlh.getId());
		}
		
		this.giangVien_LopHoc_MonHocRepository.deleteAllByGiangVien(giangVien);
		
		this.giangVienRepository.deleteById(id);
	}
	
	public List<GiangVien> findAllGiangVienChuaChuNhiem() {
		List<Long> allId = this.giangVienRepository.findAllGiangVienChuaChuNhiem();
		List<GiangVien> giangVienList = new ArrayList<>();
		if(allId != null && allId.isEmpty() == false) {
			for (Long id : allId) {
				GiangVien giangVien = this.giangVienRepository.findById(id).get();
				giangVienList.add(giangVien);
			}
		}
		return giangVienList;
	}

	public GiangVien findByTenGiangVien(String tenGiangVien) {
		return this.giangVienRepository.findByTenGiangVien(tenGiangVien);
	}
	
	public List<GiangVien_LopHoc_MonHoc> findAllSortGiangVienAndLopHocAndMonHoc(){
		return this.giangVien_LopHoc_MonHocRepository.findAllSortGiangVienAndLopHocAndMonHoc();
	}

	public GiangVien_LopHoc_MonHoc themGiangVienLopHocMonHoc(GiangVien_LopHoc_MonHoc giangVien_LopHoc_MonHoc, String emailTruong) {
		GiangVien giangVien = this.giangVienRepository.findByEmailTruong(emailTruong);
		if(giangVien == null) {
			return null;
		}else {
			giangVien_LopHoc_MonHoc.setGiangVien(giangVien);
		}
		
		
		if(this.giangVien_LopHoc_MonHocRepository.findByGiangVienAndLopHocAndMonHocAndSoKhoa(giangVien_LopHoc_MonHoc.getGiangVien(),
				giangVien_LopHoc_MonHoc.getLopHoc(), giangVien_LopHoc_MonHoc.getMonHoc(), giangVien_LopHoc_MonHoc.getSoKhoa()) != null) {
			return null;
		}
		
		return this.giangVien_LopHoc_MonHocRepository.save(giangVien_LopHoc_MonHoc);
	}
	
	public GiangVien_LopHoc_MonHoc chinhSuaGiangVienLopHocMonHoc(GiangVien_LopHoc_MonHoc giangVien_LopHoc_MonHoc, String emailTruong) {
		GiangVien giangVien = this.giangVienRepository.findByEmailTruong(emailTruong);
		if(giangVien == null) {
			return null;
		}else {
			giangVien_LopHoc_MonHoc.setGiangVien(giangVien);
		}
		
		GiangVien_LopHoc_MonHoc gv_lh_mh = this.giangVien_LopHoc_MonHocRepository.findByGiangVienAndLopHocAndMonHocAndSoKhoa(giangVien_LopHoc_MonHoc.getGiangVien(),
				giangVien_LopHoc_MonHoc.getLopHoc(), giangVien_LopHoc_MonHoc.getMonHoc(), giangVien_LopHoc_MonHoc.getSoKhoa());
		
		if(gv_lh_mh != null && gv_lh_mh.getId() != giangVien_LopHoc_MonHoc.getId()) {
			return null;
		}
		
		return this.giangVien_LopHoc_MonHocRepository.save(giangVien_LopHoc_MonHoc);
	}
	
	public void deleteGiangVienLopHocMonHoc(Long id) {
		this.giangVien_LopHoc_MonHocRepository.deleteById(id);
	}

	public GiangVien findByEmailTruong(String emailTruong) {
		return this.giangVienRepository.findByEmailTruong(emailTruong);
	}

	public GiangVien_LopHoc_MonHoc findGiangVienLopHocMonHocById(Long id) {
		return this.giangVien_LopHoc_MonHocRepository.findById(id).get();
	}
	
	public List<GiangVien_LopHoc_MonHoc> findGiangVien_LopHoc_MonHocByGiangVien(String emailTruong){
		GiangVien giangVien = this.giangVienRepository.findByEmailTruong(emailTruong);
		return this.giangVien_LopHoc_MonHocRepository.findByGiangVien(giangVien);
	}

	public void deleteGiangVienChuNhiemById(Long id) {
		this.giangVien_LopHocRepository.deleteById(id);
	}

	public List<GiangVien_LopHoc> giangVienChuNhiem() {
		return this.giangVien_LopHocRepository.findSortGiangVienAndLopHocAndSoKhoaChuNhiem();
	}

	
}
