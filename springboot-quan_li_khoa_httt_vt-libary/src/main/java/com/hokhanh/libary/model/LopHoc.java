package com.hokhanh.libary.model;

import java.time.LocalDate;

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
@Table(name = "LopHoc")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LopHoc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_lopHoc")
	private Long id;
	
	private String tenLopHoc;
	
	@ManyToOne
	@JoinColumn(name = "id_nganhHoc", referencedColumnName = "id_nganhHoc")
	private NganhHoc nganhHoc;
	
	@ManyToOne
	@JoinColumn(name = "id_chuyenNganh", referencedColumnName = "id_chuyenNganh")
	private ChuyenNganh chuyenNganh;
}
