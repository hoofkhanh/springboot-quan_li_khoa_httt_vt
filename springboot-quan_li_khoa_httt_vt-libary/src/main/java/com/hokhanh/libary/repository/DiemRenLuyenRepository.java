package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.Diem;
import com.hokhanh.libary.model.DiemRenLuyen;
import com.hokhanh.libary.model.SinhVien;

public interface DiemRenLuyenRepository extends JpaRepository<DiemRenLuyen, Long>{

	List<DiemRenLuyen> findBySinhVien(SinhVien sinhVien) ;

	DiemRenLuyen findBySinhVienAndHocKiCuaSinhVienAndNamHocCuaSinhVien(SinhVien sinhVien, int hocKiCuaSinhVien,
			int namHocCuaSinhVien);
		
	@Query(value =  "SELECT * FROM diem_ren_luyen where id_sinh_vien = ?1 ORDER BY nam_hoc_cua_sinh_vien, hoc_ki_cua_sinh_vien", nativeQuery = true)
	List<DiemRenLuyen> findBySinhVienSortNamHocAndHocKi(Long idSinhVien) ;

}
