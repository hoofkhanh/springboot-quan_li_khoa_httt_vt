package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.model.GiangVien_LopHoc;
import com.hokhanh.libary.model.LopHoc;

public interface GiangVien_LopHocRepository extends JpaRepository<GiangVien_LopHoc, Long> {

	GiangVien_LopHoc findByGiangVien(GiangVien giangVien);
	
	GiangVien_LopHoc findByGiangVienAndLopHocAndSoKhoaLopChuNhiem(GiangVien giangVien, LopHoc lopHoc, String soKhoaLopChuNhiem);

	GiangVien_LopHoc findByLopHocAndSoKhoaLopChuNhiem( LopHoc lopHoc, String soKhoaLopChuNhiem);

	@Query(value = "SELECT * FROM giang_vien_lop_hoc ORDER BY id_lop_hoc, so_khoa_lop_chu_nhiem", nativeQuery = true)
	List<GiangVien_LopHoc> findSortGiangVienAndLopHocAndSoKhoaChuNhiem();
}
