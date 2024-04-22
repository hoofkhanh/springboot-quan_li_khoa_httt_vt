package com.hokhanh.libary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HocPhi")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HocPhi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_hocPhi")
	private Long id;
	
	private double tienHocPhi;
	private double hocPhiDuocGiam;
	private int namHoc;
	private int hocKi;
	private String tenNganhHocHoacChuyenNganh;
	private int namHocCuaSinhVien;
	private int tongTinChi;
}


