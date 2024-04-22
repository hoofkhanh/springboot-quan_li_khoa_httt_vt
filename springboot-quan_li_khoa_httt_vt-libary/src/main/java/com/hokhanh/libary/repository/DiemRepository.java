package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hokhanh.libary.model.Diem;
import com.hokhanh.libary.model.MonHoc;
import com.hokhanh.libary.model.SinhVien;

public interface DiemRepository extends JpaRepository<Diem, Long>{

	List<Diem> findBySinhVien(SinhVien sinhVien);

	Diem findBySinhVienAndMonHoc(SinhVien sinhVien, MonHoc monHoc);
}
