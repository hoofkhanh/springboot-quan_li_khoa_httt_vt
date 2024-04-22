package com.hokhanh.libary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hokhanh.libary.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	Admin findByEmail(String email);

}
