package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.model.LopHoc;

public interface GiangVienRepository extends JpaRepository<GiangVien, Long>{
	
	@Query(value =  "SELECT MAX(id_giang_vien) FROM giang_vien", nativeQuery = true)
	Long findMaxId();

	List<GiangVien> findBySDTOrEmail(String SDT, String email);
	
	@Query(value =  "SELECT * FROM giang_vien ORDER BY id_giang_vien", nativeQuery = true)
	List<GiangVien> findAllSortId();
	
	@Query(value = "SELECT gv.id_giang_vien FROM giang_vien gv LEFT JOIN giang_vien_lop_hoc lh ON gv.id_giang_vien = lh.id_giang_vien WHERE lh.id_giang_vien IS NULL", nativeQuery = true)
	List<Long> findAllGiangVienChuaChuNhiem();
	
	GiangVien findByEmailTruong(String emailTruong);

	GiangVien findByTenGiangVien(String tenGiangVien);

}
