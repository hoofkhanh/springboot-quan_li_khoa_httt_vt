package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.ThongBao;

public interface ThongBaoRepository extends JpaRepository<ThongBao, Long>{

	@Query(value = "SELECT * FROM thong_bao ORDER BY ngay_dang DESC", nativeQuery = true)
	List<ThongBao> findAllSortNgayDang();
}
