package com.hokhanh.libary.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.DeTaiNghienCuu;
import com.hokhanh.libary.model.GiangVien;

import com.hokhanh.libary.repository.DeTaiNghienCuuRepository;
import com.hokhanh.libary.repository.GiangVienRepository;

@Service
public class DeTaiNghienCuuService {

	@Autowired
	private DeTaiNghienCuuRepository deTaiNghienCuuRepository;
	
	@Autowired
	private GiangVienRepository giangVienRepository;
		
	public List<DeTaiNghienCuu> findAllSortNam(){
		return this.deTaiNghienCuuRepository.findAllSortNam();
	}
	
	public DeTaiNghienCuu themDeTaiNghienCuu(DeTaiNghienCuu deTaiNghienCuu, String allTenCacGiangVien) {
		if(this.deTaiNghienCuuRepository.findByTenDeTai(deTaiNghienCuu.getTenDeTai()) != null) {
			return null;
		}
		
		List<String> tenCacGiangVien = new ArrayList<>();
		
		if(allTenCacGiangVien != null && allTenCacGiangVien.equals("")== false) {
			for (String emailTruong: allTenCacGiangVien.split(",")) {
				if(emailTruong != null  && emailTruong.equals("") == false) {
					GiangVien giangVien = this.giangVienRepository.findByEmailTruong(emailTruong);
					tenCacGiangVien.add(giangVien.getTenGiangVien());
				}
			}
		}
		
		deTaiNghienCuu.setTenCacGiangVien(tenCacGiangVien);
		
		return this.deTaiNghienCuuRepository.save(deTaiNghienCuu);
	}
	
	public DeTaiNghienCuu chinhSuaDeTaiNghienCuu(DeTaiNghienCuu deTaiNghienCuu, String allTenCacGiangVien)  {
		DeTaiNghienCuu deTaiNghienCuuCheck = this.deTaiNghienCuuRepository.findByTenDeTai(deTaiNghienCuu.getTenDeTai());
		if(deTaiNghienCuuCheck!= null && deTaiNghienCuuCheck.getId() != deTaiNghienCuu.getId() ) {
			return null;
		}
		
		List<String> tenCacGiangVien = new ArrayList<>();
			
		if(allTenCacGiangVien != null && allTenCacGiangVien.equals("")== false) {
			for (String emailTruong: allTenCacGiangVien.split(",")) {
				if(emailTruong != null  && emailTruong.equals("") == false) {
					GiangVien giangVien = this.giangVienRepository.findByEmailTruong(emailTruong);
					tenCacGiangVien.add(giangVien.getTenGiangVien());
				}
			}
		}
		
		deTaiNghienCuu.setTenCacGiangVien(tenCacGiangVien);
		
		return this.deTaiNghienCuuRepository.save(deTaiNghienCuu);
	}

	public void deleteById(Long id) {
		this.deTaiNghienCuuRepository.deleteById(id);
	}
	
	public DeTaiNghienCuu findById(Long id) {
		return this.deTaiNghienCuuRepository.findById(id).get();
	}
}
