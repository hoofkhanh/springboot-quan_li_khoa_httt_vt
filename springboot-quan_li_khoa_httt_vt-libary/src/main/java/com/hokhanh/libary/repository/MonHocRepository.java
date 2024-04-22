package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.MonHoc;

public interface MonHocRepository extends JpaRepository<MonHoc, Long>{

	MonHoc findByTenMonHoc(String tenMonHoc);

	@Query(value = "SELECT * FROM mon_hoc ORDER BY nam_hoc_cua_sinh_vien ASC, hoc_ki_cua_sinh_vien ASC", nativeQuery = true)
	List<MonHoc> findAllSortNamHocVaHocKi();
	
	@Query(value = "SELECT id_mon_hoc FROM mon_hoc_chuyen_nganh WHERE id_chuyen_nganh = ?1", nativeQuery = true)
	List<Long> findByChuyenNganh(Long idChuyenNganh);
	
	@Query(value = "SELECT id_mon_hoc FROM mon_hoc_nganh_hoc WHERE id_nganh_hoc = ?1", nativeQuery = true)
	List<Long> findByNganhHoc(Long idChuyenNganh);
	
	@Query(value = "SELECT * FROM mon_hoc ORDER BY ten_mon_hoc", nativeQuery = true)
	List<MonHoc> findAllSortTenMonHoc();

	@Query(value = "SELECT * FROM mon_hoc ORDER BY ten_mon_hoc", nativeQuery = true)
	List<MonHoc> findAllMonHocSortTen();
}
