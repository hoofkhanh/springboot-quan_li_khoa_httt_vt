package com.hokhanh.libary.model;

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
@Table(name = "ChuyenNganh")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChuyenNganh {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_chuyenNganh")
	private Long id;
	
	private String tenChuyenNganh;
	
	@ManyToOne
	@JoinColumn(name = "id_nganhHoc", referencedColumnName = "id_nganhHoc")
	private NganhHoc nganhHoc;
}
