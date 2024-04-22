package com.hokhanh.libary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.ChuyenNganh;
import com.hokhanh.libary.model.NganhHoc;
import com.hokhanh.libary.repository.ChuyenNganhRepository;

@Service
public class ChuyenNganhService {

	@Autowired
	private ChuyenNganhRepository chuyenNganhRepository;
	
	public List<ChuyenNganh> findAllChuyenNganh() {
		return this.chuyenNganhRepository.findAll();
	}

	public List<ChuyenNganh> findChuyenNganhByNganhHoc(Long id) {
		return this.chuyenNganhRepository.findChuyenNganhByNganhHoc(id);
	}
	
	public List<ChuyenNganh> findAllChuyenNganhSortNganhHoc() {
		return this.chuyenNganhRepository.findAllChuyenNganhSortNganhHoc();
	}

}
