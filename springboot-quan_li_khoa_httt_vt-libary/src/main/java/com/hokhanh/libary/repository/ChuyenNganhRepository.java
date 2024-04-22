package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.ChuyenNganh;

public interface ChuyenNganhRepository extends JpaRepository<ChuyenNganh, Long>{

	@Query(value = "SELECT * FROM chuyen_nganh WHERE id_nganh_hoc = ?1", nativeQuery = true)
	List<ChuyenNganh> findChuyenNganhByNganhHoc(Long id);
	
	ChuyenNganh findByTenChuyenNganh(String tenChuyenNganh);

	@Query(value = "SELECT * FROM chuyen_nganh ORDER BY id_nganh_hoc", nativeQuery = true)
	List<ChuyenNganh> findAllChuyenNganhSortNganhHoc();

}
