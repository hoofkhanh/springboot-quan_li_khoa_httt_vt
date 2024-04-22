package com.hokhanh.libary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.Diem;
import com.hokhanh.libary.model.MonHoc;
import com.hokhanh.libary.model.SinhVien;
import com.hokhanh.libary.model.SinhVien_MonHocNo;
import com.hokhanh.libary.repository.DiemRepository;
import com.hokhanh.libary.repository.MonHocRepository;
import com.hokhanh.libary.repository.SinhVienRepository;
import com.hokhanh.libary.repository.SinhVien_MonHocNoRepository;

@Service
public class DiemService {

	@Autowired
	private DiemRepository diemRepository;
	
	@Autowired
	private MonHocRepository monHocRepository;
	
	@Autowired
	private SinhVienRepository sinhVienRepository;
	
	@Autowired
	private SinhVien_MonHocNoRepository sinhVien_MonHocNoRepository;
	
	public List<Diem> findBySinhVienAndMonHoc(List<SinhVien> sinhVienList, Long idMonHoc) {
		List<Diem> diemList = new ArrayList<>();
		for (SinhVien sv : sinhVienList) {
			Diem diem =  this.diemRepository.findBySinhVienAndMonHoc(sv, this.monHocRepository.findById(idMonHoc).get());
			if(diem == null) {
				diemList.add(new Diem());
			}else {
				diemList.add(diem);
			}
			
		}
		
		return diemList;
	}
	
	public void saveDiem(String sinhVienList, String diemThuongXuyenList, String diemThiList, Long monHoc) {
		List<SinhVien> sinhViens = new ArrayList<>();
		for (String idSinhVien : sinhVienList.split(",")) {
			SinhVien sinhVien = this.sinhVienRepository.findById(Long.parseLong(idSinhVien)).get();
			sinhViens.add(sinhVien);
		}
		
		List<Double> diemThuongXuyens = new ArrayList<>();
		for (String diemThuongXuyen : diemThuongXuyenList.split(",")) {
			diemThuongXuyens.add(Double.parseDouble(diemThuongXuyen));
		}
		
		List<Double> diemThis = new ArrayList<>();
		for (String diemThi : diemThiList.split(",")) {
			diemThis.add(Double.parseDouble(diemThi));
		}
		
		
		for(int i=0; i< sinhViens.size(); i++) {
			Diem diemDatabase = this.diemRepository.findBySinhVienAndMonHoc(sinhViens.get(i), this.monHocRepository.findById(monHoc).get());
			if(diemDatabase!= null) {
				Diem diem = new Diem();
				diem.setId(diemDatabase.getId());
				diem.setSinhVien(sinhViens.get(i));
				diem.setDiemThuongXuyen(diemThuongXuyens.get(i));
				diem.setDiemThi(diemThis.get(i));
					
				double dtb = diemThuongXuyens.get(i)*0.4 + diemThis.get(i)*0.6;
				double roundedDTB= Math.round(dtb * 100.0) / 100.0;
				diem.setDiemTrungBinh(roundedDTB);
				MonHoc monHocTemp = this.monHocRepository.findById(monHoc).get();
				diem.setMonHoc(monHocTemp);
				diem.setHocKi(monHocTemp.getHocKiCuaSinhVien());
				diem.setNamCuaSinhVien(monHocTemp.getNamHocCuaSinhVien());
				
				if(diem.getDiemThi() != -1 && diem.getDiemThuongXuyen() != -1) {
					if(diem.getDiemTrungBinh() < 4) {
						SinhVien_MonHocNo sinhVien_MonHocNoDatabase = this.sinhVien_MonHocNoRepository.findByMonHocAndSinhVien(monHocTemp, sinhViens.get(i));
						if(sinhVien_MonHocNoDatabase == null){
							SinhVien_MonHocNo sinhVien_MonHocNo = new SinhVien_MonHocNo();
							sinhVien_MonHocNo.setMonHoc(monHocTemp);
							sinhVien_MonHocNo.setSinhVien(sinhViens.get(i));
							this.sinhVien_MonHocNoRepository.save(sinhVien_MonHocNo);
						}
						
					}else {
						SinhVien_MonHocNo sinhVien_MonHocNo = this.sinhVien_MonHocNoRepository.findByMonHocAndSinhVien(monHocTemp, sinhViens.get(i));
						if(sinhVien_MonHocNo != null) {
							this.sinhVien_MonHocNoRepository.deleteById(sinhVien_MonHocNo.getId());
						}
					}
				}
				
				this.diemRepository.save(diem);
			}else {
				Diem diem = new Diem();
				diem.setSinhVien(sinhViens.get(i));
				diem.setDiemThuongXuyen(diemThuongXuyens.get(i));
				diem.setDiemThi(diemThis.get(i));
				
				double dtb = diemThuongXuyens.get(i)*0.4 + diemThis.get(i)*0.6;
				double roundedDTB= Math.round(dtb * 100.0) / 100.0;
				diem.setDiemTrungBinh(roundedDTB);
				MonHoc monHocTemp = this.monHocRepository.findById(monHoc).get();
				diem.setMonHoc(monHocTemp);
				diem.setHocKi(monHocTemp.getHocKiCuaSinhVien());
				diem.setNamCuaSinhVien(monHocTemp.getNamHocCuaSinhVien());
				
				if(diem.getDiemThi() != -1 && diem.getDiemThuongXuyen() != -1) {
					if(diem.getDiemTrungBinh() < 4) {
						SinhVien_MonHocNo sinhVien_MonHocNoDatabase = this.sinhVien_MonHocNoRepository.findByMonHocAndSinhVien(monHocTemp, sinhViens.get(i));
						if(sinhVien_MonHocNoDatabase == null){
							SinhVien_MonHocNo sinhVien_MonHocNo = new SinhVien_MonHocNo();
							sinhVien_MonHocNo.setMonHoc(monHocTemp);
							sinhVien_MonHocNo.setSinhVien(sinhViens.get(i));
							this.sinhVien_MonHocNoRepository.save(sinhVien_MonHocNo);
						}
					}else {
						SinhVien_MonHocNo sinhVien_MonHocNo = this.sinhVien_MonHocNoRepository.findByMonHocAndSinhVien(monHocTemp, sinhViens.get(i));
						if(sinhVien_MonHocNo != null) {
							this.sinhVien_MonHocNoRepository.deleteById(sinhVien_MonHocNo.getId());
						}
					}
				}
				
				this.diemRepository.save(diem);
			}
			
			
		}
	}

	public List<Diem> findBySinhVien(Authentication authentication) {
		return this.diemRepository.findBySinhVien(this.sinhVienRepository.findByEmailTruong(authentication.getName()));
	}
}
