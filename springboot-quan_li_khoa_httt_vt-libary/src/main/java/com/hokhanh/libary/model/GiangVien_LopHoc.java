package com.hokhanh.libary.model;

import java.time.LocalDate;
import java.util.List;

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
@Table(name = "GiangVien_LopHoc")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GiangVien_LopHoc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_giangVien_lopHoc")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_giangVien", referencedColumnName = "id_giangVien")
	private GiangVien giangVien;
	
	@ManyToOne
	@JoinColumn(name = "id_lopHoc", referencedColumnName = "id_lopHoc")
	private LopHoc lopHoc;
	
	private String soKhoaLopChuNhiem;
}
