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
@Table(name = "VaiTro")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VaiTro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_vaiTro")
	private Long id;
	
	private String ten;
}
