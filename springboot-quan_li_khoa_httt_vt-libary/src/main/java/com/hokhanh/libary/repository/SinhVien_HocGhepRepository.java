package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.model.GiangVien_LopHoc_MonHoc;
import com.hokhanh.libary.model.LopHoc;
import com.hokhanh.libary.model.MonHoc;
import com.hokhanh.libary.model.SinhVien;
import com.hokhanh.libary.model.SinhVien_HocGhep;

import jakarta.transaction.Transactional;

public interface SinhVien_HocGhepRepository extends JpaRepository<SinhVien_HocGhep, Long> {
	
	SinhVien_HocGhep findByMonHocAndSinhVien( MonHoc monHoc, SinhVien sinhVien);
	

	List<SinhVien_HocGhep> findByMonHocAndLopHocAndSoKhoa(MonHoc monHoc, LopHoc lopHoc, String soKhoa);
	
	List<SinhVien_HocGhep> findBySinhVien(SinhVien sinhVien);
}
