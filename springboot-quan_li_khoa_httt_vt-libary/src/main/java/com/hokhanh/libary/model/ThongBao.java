package com.hokhanh.libary.model;

import java.time.LocalDate;

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
@Table(name = "ThongBao")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ThongBao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_thongBao")
	private Long id;
	
	private String tieuDeThongBao;
	private LocalDate ngayDang;
	
	@Column(columnDefinition = "text")
	private String noiDung;
	
	@Column(columnDefinition = "text")
	private String moTa;
	
	@Column(columnDefinition = "text")
	private String image;
}
