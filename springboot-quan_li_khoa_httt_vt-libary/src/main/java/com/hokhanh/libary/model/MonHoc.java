package com.hokhanh.libary.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MonHoc")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonHoc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_monHoc")
	private Long id;
	
	private String tenMonHoc;
	private int soTinhChi;
	private int hocKiCuaSinhVien;
	private int namHocCuaSinhVien;
	
	@ManyToMany
	@JoinTable(name = "MonHoc_ChuyenNganh", joinColumns = @JoinColumn(name = "id_monHoc", referencedColumnName = "id_monHoc"),
			inverseJoinColumns = @JoinColumn(name= "id_chuyenNganh", referencedColumnName = "id_chuyenNganh"))
	private List<ChuyenNganh>  danhSachChuyenNganh;
	
	@ManyToMany
	@JoinTable(name = "MonHoc_NganhHoc", joinColumns = @JoinColumn(name = "id_monHoc", referencedColumnName = "id_monHoc"),
			inverseJoinColumns = @JoinColumn(name= "id_nganhHoc", referencedColumnName = "id_nganhHoc"))
	private List<NganhHoc>  danhSachNganhHoc;
}
