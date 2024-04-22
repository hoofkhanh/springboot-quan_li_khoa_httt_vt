package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.ChuyenNganh;
import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.model.LopHoc;

public interface LopHocRepository extends JpaRepository<LopHoc, Long>{
	
	LopHoc findByTenLopHoc(String tenLopHoc);
	
	@Query(value =  "SELECT * FROM lop_hoc ORDER BY id_nganh_hoc, id_chuyen_nganh, ten_lop_hoc", nativeQuery = true)
	List<LopHoc> findAllSortNganhHocAndChuyenNganh();

	@Query(value =  "SELECT * FROM lop_hoc WHERE id_nganh_hoc = ?1 AND id_chuyen_nganh IS NULL ORDER BY id_chuyen_nganh, ten_lop_hoc", nativeQuery = true)
	List<LopHoc> findByIdNganhHoc(Long idNganhHoc);

	@Query(value =  "SELECT * FROM lop_hoc WHERE id_chuyen_nganh = ?1 ORDER BY id_chuyen_nganh, ten_lop_hoc", nativeQuery = true)
	List<LopHoc> findByIdChuyenNganh(Long idChuyenNganh);
	
}
