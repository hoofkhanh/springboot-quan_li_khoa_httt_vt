package com.hokhanh.libary.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.BaoLuu;
import com.hokhanh.libary.model.Diem;
import com.hokhanh.libary.model.DiemRenLuyen;
import com.hokhanh.libary.model.GiangVien;
import com.hokhanh.libary.model.GiangVien_LopHoc;
import com.hokhanh.libary.model.HocPhi;
import com.hokhanh.libary.model.LopHoc;
import com.hokhanh.libary.model.MonHoc;
import com.hokhanh.libary.model.SinhVien;
import com.hokhanh.libary.model.SinhVien_HocGhep;
import com.hokhanh.libary.model.SinhVien_HocPhi;
import com.hokhanh.libary.model.SinhVien_MonHocNo;
import com.hokhanh.libary.repository.BaoLuuRepository;
import com.hokhanh.libary.repository.DiemRenLuyenRepository;
import com.hokhanh.libary.repository.DiemRepository;
import com.hokhanh.libary.repository.GiangVienRepository;
import com.hokhanh.libary.repository.GiangVien_LopHocRepository;
import com.hokhanh.libary.repository.HocPhiRepository;
import com.hokhanh.libary.repository.LopHocRepository;
import com.hokhanh.libary.repository.MonHocRepository;
import com.hokhanh.libary.repository.SinhVienRepository;
import com.hokhanh.libary.repository.SinhVien_HocGhepRepository;
import com.hokhanh.libary.repository.SinhVien_HocPhiRepository;
import com.hokhanh.libary.repository.SinhVien_MonHocNoRepository;
import com.hokhanh.libary.repository.VaiTroRepository;

@Service
public class SinhVienService {

	@Autowired
	private SinhVienRepository sinhVienRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private VaiTroRepository vaiTroRepository;
	
	@Autowired
	private BaoLuuRepository baoLuuRepository;
	
	@Autowired
	private DiemRenLuyenRepository diemRenLuyenRepository;
	
	@Autowired
	private SinhVien_HocPhiRepository sinhVien_HocPhiRepository;
	
	@Autowired 
	private SinhVien_MonHocNoRepository sinhVien_MonHocNoRepository;
	
	@Autowired
	private DiemRepository diemRepository;
	
	@Autowired
	private HocPhiRepository hocPhiRepository;
	
	@Autowired
	private LopHocRepository lopHocRepository;
	
	@Autowired
	private SinhVien_HocGhepRepository hocGhepRepository;
	
	@Autowired
	private MonHocRepository monHocRepository;
	
	@Autowired
	private SinhVien_HocGhepRepository vien_HocGhepRepository;
	
	@Autowired
	private GiangVienRepository giangVienRepository;
	
	@Autowired
	private GiangVien_LopHocRepository giangVien_LopHocRepository;

	
	public List<SinhVien> findAllSinhVien(){
		List<SinhVien> sinhVienList =  this.sinhVienRepository.findAllSinhVienSortLopHocAndNganhHocAndChuyenNganh();
		
		List<SinhVien> newSinhVienList = new ArrayList<>();
		if(sinhVienList!= null && sinhVienList.isEmpty()==false) {
			for (SinhVien sinhVien : sinhVienList) {
				String password = sinhVien.getEmailTruong().substring(0, sinhVien.getEmailTruong().indexOf("@")) + "sinhvien!@#123456";
				sinhVien.setPassword(password);
				BaoLuu baoLuu = this.baoLuuRepository.findBySinhVien(sinhVien);
				if(baoLuu == null || (baoLuu != null && baoLuu.isTrangThai() == false)) {
					newSinhVienList.add(sinhVien);
				}
			}
		}
		
		
		return newSinhVienList;
	}
	
	public SinhVien themSinhVien(SinhVien sinhVien) {
		List<SinhVien> sinhVienCheck = this.sinhVienRepository.findBySDTOrEmail(sinhVien.getSDT(),  sinhVien.getEmail());
		if(sinhVienCheck!= null && !sinhVienCheck.isEmpty()) {
			return null;
		}
		
		SinhVien sv = this.sinhVienRepository.save(sinhVien);
		
		Long maxIdInt = this.sinhVienRepository.findMaxId();
		if(maxIdInt  == null) {
			maxIdInt = (long) 1;
		}
		
		String maxId = maxIdInt + "";
		
		sv.setEmailTruong(maxId + "@sv.hcmunre.edu.vn");
		
		int startedYear = LocalDate.now().getYear();
		int endedYear = startedYear + 4;
		sv.setNienKhoa(startedYear + " - " + endedYear);
		
		sv.setPassword(bCryptPasswordEncoder.encode(maxId + "sinhvien!@#123456"));
		
		
		
		sv.setVaiTro(vaiTroRepository.findByTen("Sinh Viên"));
		
		return this.sinhVienRepository.save(sv);
	}

	public SinhVien chinhSuaSinhVien(SinhVien sinhVien) {
		List<SinhVien> sinhVienCheck = this.sinhVienRepository.findBySDTOrEmail(sinhVien.getSDT(),  sinhVien.getEmail());
		if(sinhVienCheck!= null && !sinhVienCheck.isEmpty()) {
			for (SinhVien sv : sinhVienCheck) {
				if(sv.getId() != sinhVien.getId()) {
					return null;
				}
			}
		}
		
		return this.sinhVienRepository.save(sinhVien);
	}
	
	public SinhVien findById(Long id) {
		return this.sinhVienRepository.findById(id).get();
	}

	public String baoLuuSinhVien(Long id) {
		SinhVien sinhVien = this.sinhVienRepository.findById(id).get();
		if(baoLuuRepository.findBySinhVien(sinhVien) != null) {
			return "Thất bại";
		}
		
		BaoLuu baoLuu = new BaoLuu();
		baoLuu.setSinhVien(sinhVien);
		baoLuu.setNgayBaoLuu(LocalDate.now());
		baoLuu.setTrangThai(true);
		this.baoLuuRepository.save(baoLuu);
		return "Thành công";
	}

	public void huyBaoLuuSinhVien(Long id) {
		SinhVien sinhVien = this.sinhVienRepository.findById(id).get();
		BaoLuu baoLuu = baoLuuRepository.findBySinhVien(sinhVien);
		baoLuu.setTrangThai(false);
		this.baoLuuRepository.save(baoLuu);
	}

	public List<SinhVien> findAllSinhVienBaoLuu() {
		List<BaoLuu> baoLuuList = this.baoLuuRepository.findAllSinhVienBaoLuu();
		List<SinhVien> sinhVienList = new ArrayList<>();
		
		if(baoLuuList!= null && !baoLuuList.isEmpty()) {
			for (BaoLuu baoLuu : baoLuuList) {
				SinhVien sinhVien = baoLuu.getSinhVien();
				String password = sinhVien.getEmailTruong().substring(0, sinhVien.getEmailTruong().indexOf("@")) + "sinhvien!@#123456";
				sinhVien.setPassword(password);
				sinhVienList.add(sinhVien);
			}
		}
		
		return sinhVienList;
	}

	public void deleteById(Long id) {
		SinhVien sinhVien = this.sinhVienRepository.findById(id).get();
		List<DiemRenLuyen> diemRenLuyenList = diemRenLuyenRepository.findBySinhVien(sinhVien);
		if(diemRenLuyenList!= null && diemRenLuyenList.isEmpty() == false) {
			for (DiemRenLuyen diemRenLuyen : diemRenLuyenList) {
				this.diemRenLuyenRepository.deleteById(diemRenLuyen.getId());
			}
		}
		
		BaoLuu baoLuu = this.baoLuuRepository.findBySinhVien(sinhVien);
		if(baoLuu != null) {
			this.baoLuuRepository.deleteById(baoLuu.getId());
		}
		
		List<SinhVien_HocPhi> sinhVienHocPhi = this.sinhVien_HocPhiRepository.findBySinhVien(sinhVien);
		if(sinhVienHocPhi!= null && sinhVienHocPhi.isEmpty() == false) {
			for (SinhVien_HocPhi svhp : sinhVienHocPhi) {
				this.sinhVien_HocPhiRepository.deleteById(svhp.getId());
			}
		}
		
		List<SinhVien_MonHocNo> sinhVienMonHocNo = this.sinhVien_MonHocNoRepository.findBySinhVien(sinhVien);
		if(sinhVienMonHocNo!= null && sinhVienMonHocNo.isEmpty() == false) {
			for (SinhVien_MonHocNo svmhn : sinhVienMonHocNo) {
				this.sinhVien_MonHocNoRepository.deleteById(svmhn.getId());
			}
		}
		
		List<SinhVien_HocGhep> svhgList = this.vien_HocGhepRepository.findBySinhVien(sinhVien);
		if(svhgList!= null && svhgList.isEmpty() == false) {
			for (SinhVien_HocGhep svhg : svhgList) {
				this.vien_HocGhepRepository.deleteById(svhg.getId());
			}
		}
		
		List<Diem> diem = this.diemRepository.findBySinhVien(sinhVien);
		if(diem!= null && diem.isEmpty() == false) {
			for (Diem d : diem) {
				this.diemRepository.deleteById(d.getId());
			}
		}
		
		this.sinhVienRepository.deleteById(id);
	}
	
	public List<SinhVien_HocPhi> findAllSinhVienHocPhi(){
		List<SinhVien_HocPhi> sinhVien_HocPhiList = this.sinhVien_HocPhiRepository.findAll();
		
		if(sinhVien_HocPhiList != null && sinhVien_HocPhiList.isEmpty() == false) {
			for (SinhVien_HocPhi sinhVien_HocPhi : sinhVien_HocPhiList) {
				String password = sinhVien_HocPhi.getSinhVien().getEmailTruong().substring(0, sinhVien_HocPhi.getSinhVien().getEmailTruong().indexOf("@")) + "sinhvien!@#123456";
				sinhVien_HocPhi.getSinhVien().setPassword(password);
			}
		}
					
		return sinhVien_HocPhiList;
	}
	
	public List<SinhVien> findSinhVienByHocPhi(Long id){
		HocPhi hp = this.hocPhiRepository.findById(id).get();
		List<SinhVien> sinhVienList = new ArrayList<>();
		for (SinhVien_HocPhi svHP : this.sinhVien_HocPhiRepository.findByHocPhi(hp)) {
			sinhVienList.add(svHP.getSinhVien());
		}
		
		return sinhVienList;
	}
	
	public void dongHocPhi(SinhVien_HocPhi sinhVien_HocPhi, String allSinhVien){		
		if(allSinhVien != null && allSinhVien.equals("")== false) {
			for (String emailTruong: allSinhVien.split(",")) {
				if(emailTruong != null  && emailTruong.equals("") == false) {
					SinhVien_HocPhi svhp = new SinhVien_HocPhi();
					svhp.setTamHoan(sinhVien_HocPhi.isTamHoan());
					svhp.setHocPhi(sinhVien_HocPhi.getHocPhi());
					
					SinhVien sv =  this.sinhVienRepository.findByEmailTruong(emailTruong.trim());
					svhp.setSinhVien(sv);
					this.sinhVien_HocPhiRepository.save(svhp);
				}
			}
		}
	}

	public void xacNhanDongHocPhi(Long id) {
		SinhVien_HocPhi svhp =  this.sinhVien_HocPhiRepository.findById(id).get();
		svhp.setTamHoan(false);
		this.sinhVien_HocPhiRepository.save(svhp);
	}
	
	public void tamHoanHocPhi(Long id) {
		SinhVien_HocPhi svhp =  this.sinhVien_HocPhiRepository.findById(id).get();
		svhp.setTamHoan(true);
		this.sinhVien_HocPhiRepository.save(svhp);
	}

	public void deleteSinhVienHocPhiById(Long id) {
		this.sinhVien_HocPhiRepository.deleteById(id);
	}
	
	public List<SinhVien> findByLopHocAndSoKhoaCuaNganh(Long idLopHoc, String soKhoa, Long idMonHoc){
		LopHoc lopHoc = this.lopHocRepository.findById(idLopHoc).get();
		MonHoc monHoc = this.monHocRepository.findById(idMonHoc).get();
		
		List<SinhVien> sinhVienList =  this.sinhVienRepository.findByLopHocAndSoKhoaCuaNganh(lopHoc, soKhoa);
		
		List<SinhVien_HocGhep> sinhVienHG  =this.hocGhepRepository.findByMonHocAndLopHocAndSoKhoa(monHoc, lopHoc, soKhoa);
		if(sinhVienHG != null && sinhVienHG.isEmpty() == false) {
			for (SinhVien_HocGhep sinhVien_HocGhep : sinhVienHG) {
				sinhVienList.add(sinhVien_HocGhep.getSinhVien());
			}
		}
		
		Iterator<SinhVien> iterator = sinhVienList.iterator();
		while (iterator.hasNext()) {
		    SinhVien sv = iterator.next();
		    BaoLuu baoLuu = this.baoLuuRepository.findBySinhVien(sv);
		    if (baoLuu != null && baoLuu.isTrangThai() == true) {
		        iterator.remove(); 
		    }
		}
		
		return sinhVienList;
	}
	
	public SinhVien_HocGhep themSinhVienHocGhep(SinhVien_HocGhep sinhVien_HocGhep, String emailTruong) {
		SinhVien sinhVien = this.sinhVienRepository.findByEmailTruong(emailTruong);
		if(hocGhepRepository.findByMonHocAndSinhVien( sinhVien_HocGhep.getMonHoc(), sinhVien) != null) {
			return null;
		}
				
		sinhVien_HocGhep.setSinhVien(sinhVien);
		sinhVien_HocGhep.setNgayDangKi(LocalDate.now());
		return this.hocGhepRepository.save(sinhVien_HocGhep);
	}
	
	public SinhVien_HocGhep chinhSuaSinhVienHocGhep(SinhVien_HocGhep sinhVien_HocGhep, String emailTruong) {
		SinhVien sinhVien = this.sinhVienRepository.findByEmailTruong(emailTruong);
		SinhVien_HocGhep svhg = hocGhepRepository.findByMonHocAndSinhVien( sinhVien_HocGhep.getMonHoc(), sinhVien);
		if( svhg != null && svhg.getId() != sinhVien_HocGhep.getId()) {
			return null;
		}
		
		sinhVien_HocGhep.setSinhVien(sinhVien);
		return this.hocGhepRepository.save(sinhVien_HocGhep);
	}
	
	public void deleteSinhVienHocGhepById(Long id) {
		this.hocGhepRepository.deleteById(id);
	}
	
	public SinhVien_HocGhep findSinhVienHocGhepById(Long id) {
		return this.hocGhepRepository.findById(id).get();
	}
	
	public List<SinhVien_HocGhep> findAllSinhVienHocGhep() {
		return this.hocGhepRepository.findAll();
	}

	public List<SinhVien_MonHocNo> sinhVienNoMon() {
		return this.sinhVien_MonHocNoRepository.findAll();
	}

	public void deleteSinhVienMonHocNoById(Long id) {
		this.sinhVien_MonHocNoRepository.deleteById(id);
	}

	public List<SinhVien> findAllSinhVienOfGiangVienChuNhiem(String emailTruongCuaGiaoVien) {
		GiangVien giangVien = this.giangVienRepository.findByEmailTruong(emailTruongCuaGiaoVien);
		GiangVien_LopHoc gvlh = this.giangVien_LopHocRepository.findByGiangVien(giangVien);
		
		List<SinhVien> sinhVienList = new ArrayList<>();
		if(gvlh != null && gvlh.getLopHoc() != null && gvlh.getSoKhoaLopChuNhiem() != null && !gvlh.getSoKhoaLopChuNhiem().equals("")) {
			sinhVienList = this.sinhVienRepository.findByLopHocAndSoKhoaCuaNganh(gvlh.getLopHoc(), gvlh.getSoKhoaLopChuNhiem());
		}
		
		if(sinhVienList != null && sinhVienList.isEmpty() == false) {
			Iterator<SinhVien> iterator = sinhVienList.iterator();
			while(iterator.hasNext()) {
				SinhVien sinhVien = iterator.next();
				BaoLuu baoLuu = this.baoLuuRepository.findBySinhVien(sinhVien);
				if(baoLuu != null && baoLuu.isTrangThai() == true) {
					iterator.remove();
				}
			}
		}
		
		return sinhVienList;
	}
	
	public List<SinhVien_HocPhi> findSinhVienHocPhiBySinhVien(String emailTruong) {
		List<SinhVien_HocPhi> svhpList = new ArrayList<>();
		SinhVien sinhVien = this.sinhVienRepository.findByEmailTruong(emailTruong);
		List<Long> allId = this.sinhVien_HocPhiRepository.findSinhVienHocPhiBySinhVien(sinhVien.getId());
		for (Long long1 : allId) {
			svhpList.add(sinhVien_HocPhiRepository.findById(long1).get());
		}
		return svhpList;
	}
	
	public SinhVien findByEmailTruong(String emailTruong) {
		return this.sinhVienRepository.findByEmailTruong(emailTruong);
	}
	
}
