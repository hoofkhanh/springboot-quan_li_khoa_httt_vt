package com.hokhanh.libary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.MonHoc;
import com.hokhanh.libary.repository.MonHocRepository;

@Service
public class MonHocService {

	@Autowired
	private MonHocRepository monHocRepository;
	
	public List<MonHoc> findAllMonHocSortTen() {
		return this.monHocRepository.findAllMonHocSortTen();
	}
	
	public List<MonHoc> findAllMonHoc() {
		return this.monHocRepository.findAllSortNamHocVaHocKi();
	}
	
	public MonHoc findMonHocById(Long id) {
		return this.monHocRepository.findById(id).get();
	}
	
	public MonHoc themMonHoc(MonHoc monHoc) {
		if(this.monHocRepository.findByTenMonHoc(monHoc.getTenMonHoc()) != null) {
			return null;
		}
		
		return this.monHocRepository.save(monHoc);
	}

	public MonHoc chinhSuaMonHoc(MonHoc monHoc) {
		MonHoc monHocCheckId = this.monHocRepository.findByTenMonHoc(monHoc.getTenMonHoc());
		if(monHocCheckId != null && monHocCheckId.getId() != monHoc.getId()) {
			return null;
		}
		
		return this.monHocRepository.save(monHoc);
	}

	public List<MonHoc> findAllSortTenMonHoc() {
		return this.monHocRepository.findAllSortTenMonHoc();
	}

	
}
