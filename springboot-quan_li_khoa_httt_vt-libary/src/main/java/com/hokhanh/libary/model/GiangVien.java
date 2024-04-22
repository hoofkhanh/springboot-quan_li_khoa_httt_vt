package com.hokhanh.libary.model;

import java.time.LocalDate;
import java.util.List;

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
@Table(name = "GiangVien")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GiangVien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_giangVien")
	private Long id;
	
	private String tenGiangVien;
	private String chucVu;
	private String email;
	private String emailTruong;
	private String password;
	private String SDT;
	private LocalDate ngaySinh;
	private String gioiTinh;
	private String noiSinh;
	private String noiDaoTao;
	
	
	@Column(columnDefinition = "text")
	private String hinhAnh;	
	
	@ManyToMany
	@JoinTable(name = "GiangVien_MonHoc", joinColumns = @JoinColumn(name = "id_giangVien", referencedColumnName = "id_giangVien"),
			inverseJoinColumns = @JoinColumn(name= "id_monHoc", referencedColumnName = "id_monHoc"))
	private List<MonHoc>  danhSachMonHoc;
	
	@ManyToOne
	@JoinColumn(name = "id_vaiTro", referencedColumnName = "id_vaiTro")
	private VaiTro vaiTro;
}
