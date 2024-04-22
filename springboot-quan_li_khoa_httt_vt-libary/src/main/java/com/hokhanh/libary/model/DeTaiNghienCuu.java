package com.hokhanh.libary.model;

import java.time.LocalDate;
import java.util.List;

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
@Table(name = "DeTaiNghienCuu")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeTaiNghienCuu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_deTaiNghienCuu")
	private Long id;
	
	private String tenDeTai;
	
	private LocalDate nam;
	
	private List<String> tenCacGiangVien;
}
