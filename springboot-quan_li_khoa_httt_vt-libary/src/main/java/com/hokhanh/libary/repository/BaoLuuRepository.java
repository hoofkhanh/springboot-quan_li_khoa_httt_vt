package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.BaoLuu;
import com.hokhanh.libary.model.SinhVien;

public interface BaoLuuRepository extends JpaRepository<BaoLuu, Long>{

	BaoLuu findBySinhVien(SinhVien sinhVien);

//	nếu có ngày bảo lưu thì có nghĩa đã bảo lưu còn không có thì có nghĩa là sinh viẹn đã nghỉ học
	@Query(value = "SELECT * FROM bao_luu WHERE trang_thai = TRUE  ORDER BY ngay_bao_luu", nativeQuery = true)
	List<BaoLuu> findAllSinhVienBaoLuu();
}
