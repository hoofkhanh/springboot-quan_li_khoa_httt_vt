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
@Table(name = "SinhVien_HocPhi")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SinhVien_HocPhi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_sinhVien_hocPhi")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_sinhVien", referencedColumnName = "id_sinhVien")
	private SinhVien sinhVien;
	
	@ManyToOne
	@JoinColumn(name = "id_hocPhi", referencedColumnName = "id_hocPhi")
	private HocPhi hocPhi;
	
	private boolean tamHoan;
}
