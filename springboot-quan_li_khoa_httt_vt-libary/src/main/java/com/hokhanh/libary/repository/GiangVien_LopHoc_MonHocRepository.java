package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.model.GiangVien_LopHoc_MonHoc;
import com.hokhanh.libary.model.LopHoc;
import com.hokhanh.libary.model.MonHoc;

import jakarta.transaction.Transactional;

public interface GiangVien_LopHoc_MonHocRepository extends JpaRepository<GiangVien_LopHoc_MonHoc, Long> {
	
	GiangVien_LopHoc_MonHoc findByGiangVienAndLopHocAndMonHocAndSoKhoa(GiangVien giangVien, LopHoc lopHoc, MonHoc monHoc, String soKhoa);
	
	List<GiangVien_LopHoc_MonHoc> findByGiangVien(GiangVien giangVien);

	@Transactional
	@Modifying
	void deleteAllByGiangVien(GiangVien giangVien);
	
	@Query(value = "SELECT * FROM giang_vien_lop_hoc_mon_hoc ORDER BY id_giang_vien, id_lop_hoc, id_mon_hoc, so_khoa", nativeQuery = true)
	List<GiangVien_LopHoc_MonHoc> findAllSortGiangVienAndLopHocAndMonHoc();
}
