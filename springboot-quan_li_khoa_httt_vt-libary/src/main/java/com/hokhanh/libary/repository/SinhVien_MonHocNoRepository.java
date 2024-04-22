package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hokhanh.libary.model.MonHoc;
import com.hokhanh.libary.model.SinhVien;
import com.hokhanh.libary.model.SinhVien_MonHocNo;

public interface SinhVien_MonHocNoRepository extends JpaRepository<SinhVien_MonHocNo, Long>{

	List<SinhVien_MonHocNo> findBySinhVien(SinhVien sinhVien);
	
	SinhVien_MonHocNo findByMonHocAndSinhVien(MonHoc monHoc, SinhVien sinhVien);

}
