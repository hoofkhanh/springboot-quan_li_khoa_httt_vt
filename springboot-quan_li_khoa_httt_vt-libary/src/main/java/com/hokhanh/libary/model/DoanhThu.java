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
@Table(name = "DoanhThu")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoanhThu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_doanhThu")
	private Long id;
	
	private int nam;
	private int namHocCuaSinhVien;
	private int hocKi;
	private double tien;
}
