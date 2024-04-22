package com.hokhanh.libary.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hokhanh.libary.model.ThongBao;
import com.hokhanh.libary.repository.ThongBaoRepository;

@Service
public class ThongBaoService {

	@Autowired
	private ThongBaoRepository thongBaoRepository;
	
	public List<ThongBao> findAllSortNgayDang(){
		return this.thongBaoRepository.findAllSortNgayDang();
	}
	
	public void themThongBao(ThongBao thongBao,  MultipartFile hinhAnh) throws IOException {
		thongBao.setNgayDang(LocalDate.now());
		
		if(hinhAnh.isEmpty() == false) {
			thongBao.setImage(Base64.getEncoder().encodeToString(hinhAnh.getBytes()));
		}else {
			thongBao.setImage(null);
		}
		
		this.thongBaoRepository.save(thongBao);
	}
	
	public void chinSuaThongBao(ThongBao thongBao,  MultipartFile hinhAnh) throws IOException {
		ThongBao thongBaoFromDatabase = this.thongBaoRepository.findById(thongBao.getId()).get();
		
		thongBao.setNgayDang(thongBaoFromDatabase.getNgayDang());
		
		if(hinhAnh.isEmpty() == false) {
			thongBao.setImage(Base64.getEncoder().encodeToString(hinhAnh.getBytes()));
		}else {
			thongBao.setImage(thongBaoFromDatabase.getImage());
		}
		
		this.thongBaoRepository.save(thongBao);
	}

	public void deleteById(Long id) {
		this.thongBaoRepository.deleteById(id);
	}
	
	public ThongBao findById(Long id) {
		return this.thongBaoRepository.findById(id).get();
	}
}
