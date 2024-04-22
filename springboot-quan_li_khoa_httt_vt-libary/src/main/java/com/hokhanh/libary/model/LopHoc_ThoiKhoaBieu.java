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
@Table(name = "LopHoc_ThoiKhoaBieu")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LopHoc_ThoiKhoaBieu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_lopHoc_thoiKhoaBieu")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_lopHoc", referencedColumnName = "id_lopHoc")
	private LopHoc lopHoc;
	
	@ManyToOne
	@JoinColumn(name = "id_thoiKhoaBieu", referencedColumnName = "id_thoiKhoaBieu")
	private ThoiKhoaBieu thoiKhoaBieu;
	
	private String soKhoa;
}
