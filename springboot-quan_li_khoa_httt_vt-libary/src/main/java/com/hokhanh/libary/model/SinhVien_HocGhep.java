package com.hokhanh.libary.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SinhVien_HocGhep")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SinhVien_HocGhep {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_sinhVien_hocGhep")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_sinhVien", referencedColumnName = "id_sinhVien")
	private SinhVien sinhVien;
	
	@ManyToOne
	@JoinColumn(name = "id_lopHoc", referencedColumnName = "id_lopHoc")
	private LopHoc lopHoc;
	
	@ManyToOne
	@JoinColumn(name = "id_monHoc", referencedColumnName = "id_monHoc")
	private MonHoc monHoc;
	
	private LocalDate ngayDangKi;
	
	private String soKhoa;
	
	private double soTienPhaiDong;
}
