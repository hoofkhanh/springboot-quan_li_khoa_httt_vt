package com.hokhanh.libary.model;

import java.time.LocalDate;

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
@Table(name = "DiemRenLuyen")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiemRenLuyen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_diemRenLuyen")
	private Long id;
	
	private double diem;
	private int hocKiCuaSinhVien;
	private int namHocCuaSinhVien;
	
	@ManyToOne
	@JoinColumn(name = "id_sinhVien", referencedColumnName = "id_sinhVien")
	private SinhVien sinhVien;
}
