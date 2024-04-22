package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.HocPhi;

public interface HocPhiRepository extends JpaRepository<HocPhi, Long>{

	HocPhi findByNamHocAndHocKiAndTenNganhHocHoacChuyenNganhAndNamHocCuaSinhVien(int namHoc, int hocKi, String tenNganhHocHoacChuyenNganh, int namHocCuaSinhVien);

	HocPhi findByNamHocAndHocKiAndNamHocCuaSinhVien(int namHoc, int hocKi, int namHocCuaSinhVien);
	
	@Query(value = "SELECT * FROM hoc_phi ORDER BY ten_nganh_hoc_hoac_chuyen_nganh, nam_hoc, nam_hoc_cua_sinh_vien, hoc_ki", nativeQuery = true)
	List<HocPhi> findAllSorted();
	
	@Query(value = "SELECT hp.nam_Hoc, hp.nam_hoc_cua_sinh_vien, hp.hoc_ki, SUM(hp.tien_hoc_phi)  FROM sinh_vien_hoc_phi svhp INNER JOIN hoc_phi hp ON svhp.id_hoc_phi = hp.id_hoc_phi\r\n"
			+ "WHERE hp.nam_hoc = EXTRACT(YEAR FROM CURRENT_DATE)\r\n"
			+ "GROUP BY hp.tien_hoc_phi, hp.nam_Hoc, hp.nam_hoc_cua_sinh_vien, hp.hoc_ki\r\n"
			+ "ORDER BY hp.nam_hoc_cua_sinh_vien, hp.nam_hoc_cua_sinh_vien, hp.hoc_ki", nativeQuery = true)
	List<Object> tinhDoanhThu();
}
