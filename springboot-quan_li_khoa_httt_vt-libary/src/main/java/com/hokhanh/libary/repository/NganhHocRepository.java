package com.hokhanh.libary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hokhanh.libary.model.NganhHoc;

public interface NganhHocRepository extends JpaRepository<NganhHoc, Long>{

	NganhHoc findByTenNganh(String tenNganh);
}
