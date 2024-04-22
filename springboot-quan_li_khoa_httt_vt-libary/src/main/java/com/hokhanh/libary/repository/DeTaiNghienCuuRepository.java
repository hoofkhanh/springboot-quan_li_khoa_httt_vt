package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.DeTaiNghienCuu;

public interface DeTaiNghienCuuRepository extends JpaRepository<DeTaiNghienCuu, Long>{

	DeTaiNghienCuu findByTenDeTai(String tenDeTai);
	
	@Query(value = "SELECT * FROM de_tai_nghien_cuu ORDER BY nam DESC", nativeQuery = true)
	List<DeTaiNghienCuu> findAllSortNam();
}
