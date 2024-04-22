package com.hokhanh.libary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hokhanh.libary.model.DoanhThu;

public interface DoanhThuRepository extends JpaRepository<DoanhThu, Long>{

	DoanhThu findByNamAndNamHocCuaSinhVienAndHocKi(int nam, int namHocCuaSinhVien, int hocKi);
}
