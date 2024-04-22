package com.hokhanh.libary.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SinhVien")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SinhVien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_sinhVien")
	private Long id;
	
	private String hoVaTen;
	private LocalDate ngaySinh;
	private String SDT;
	private String gioiTinh;
	private String noiSinh;
	private String nienKhoa;
	private String email;
	private String emailTruong;
	private String password;
	private String soKhoaCuaNganh;
	

	
	
	@ManyToOne
	@JoinColumn(name = "id_lopHoc", referencedColumnName = "id_lopHoc")
	private LopHoc lopHoc;
	
	@ManyToOne
	@JoinColumn(name = "id_nganhHoc", referencedColumnName = "id_nganhHoc")
	private NganhHoc nganhHoc;
	
	@ManyToOne
	@JoinColumn(name = "id_chuyenNganh", referencedColumnName = "id_chuyenNganh")
	private ChuyenNganh chuyenNganh;
	
	@ManyToOne
	@JoinColumn(name = "id_vaiTro", referencedColumnName = "id_vaiTro")
	private VaiTro vaiTro;
	
}