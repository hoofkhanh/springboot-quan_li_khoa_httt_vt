package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.HocPhi;
import com.hokhanh.libary.model.SinhVien;
import com.hokhanh.libary.model.SinhVien_HocPhi;

public interface SinhVien_HocPhiRepository extends JpaRepository<SinhVien_HocPhi, Long>{

	List<SinhVien_HocPhi> findBySinhVien(SinhVien sinhVien);

	List<SinhVien_HocPhi> findByHocPhi(HocPhi hp);

	@Query(value = "SELECT svhp.id_sinh_vien_hoc_phi FROM sinh_vien_hoc_phi svhp INNER JOIN hoc_phi hp ON hp.id_hoc_phi = svhp.id_hoc_phi WHERE id_sinh_vien = ?1 ORDER BY hp.nam_hoc, hp.nam_hoc_cua_sinh_vien, hp.hoc_ki", nativeQuery = true)
	List<Long> findSinhVienHocPhiBySinhVien(Long idSinHVien);

}
