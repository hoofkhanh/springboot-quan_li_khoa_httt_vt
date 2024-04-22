package com.hokhanh.libary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hokhanh.libary.model.LopHoc;
import com.hokhanh.libary.model.SinhVien;

public interface SinhVienRepository extends JpaRepository<SinhVien, Long>{

	@Query(value = "SELECT * FROM sinh_vien ORDER BY id_lop_hoc, id_nganh_hoc, id_chuyen_nganh", nativeQuery = true)
	List<SinhVien> findAllSinhVienSortLopHocAndNganhHocAndChuyenNganh();

	List<SinhVien> findBySDTOrEmail(String SDT, String email);
	
	@Query(value =  "SELECT MAX(id_sinh_vien) FROM sinh_vien", nativeQuery = true)
	Long findMaxId();

	SinhVien findByEmailTruong(String emailTruong);
	

	List<SinhVien> findByLopHocAndSoKhoaCuaNganh(LopHoc lopHoc, String soKhoa);
}
