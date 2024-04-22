package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.LopHoc;
import com.hokhanh.libary.model.LopHoc_ThoiKhoaBieu;
import com.hokhanh.libary.model.ThoiKhoaBieu;

public interface LopHoc_ThoiKhoaBieuRepository extends JpaRepository<LopHoc_ThoiKhoaBieu, Long> {

	LopHoc_ThoiKhoaBieu findByLopHocAndSoKhoa(LopHoc lopHoc, String soKhoa);

	@Query(value = "SELECT * FROM lop_hoc_thoi_khoa_bieu ORDER BY id_lop_hoc, so_khoa", nativeQuery = true)
	List<LopHoc_ThoiKhoaBieu> findAllSortLopHocAndSoKhoa();
}
