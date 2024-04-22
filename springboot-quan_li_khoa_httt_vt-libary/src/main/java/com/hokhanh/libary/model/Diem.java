package com.hokhanh.libary.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
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
@Table(name = "Diem")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Diem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_diem")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_sinhVien", referencedColumnName = "id_sinhVien")
	private SinhVien sinhVien;
	
	@ManyToOne
	@JoinColumn(name = "id_monHoc", referencedColumnName = "id_monHoc")
	private MonHoc monHoc;
	
	private double diemThuongXuyen;
	private double diemThi;
	private double diemTrungBinh;
	private int hocKi;
	private int namCuaSinhVien;
}
