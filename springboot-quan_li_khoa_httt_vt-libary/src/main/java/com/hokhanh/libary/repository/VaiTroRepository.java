package com.hokhanh.libary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hokhanh.libary.model.VaiTro;

public interface VaiTroRepository extends JpaRepository<VaiTro, Long>{

	VaiTro findByTen(String ten);
}
