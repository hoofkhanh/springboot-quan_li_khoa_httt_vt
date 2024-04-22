package com.hokhanh.libary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.ChuyenNganh;
import com.hokhanh.libary.model.GiangVien_LopHoc;
import com.hokhanh.libary.model.LopHoc;
import com.hokhanh.libary.model.LopHoc_ThoiKhoaBieu;
import com.hokhanh.libary.model.MonHoc;
import com.hokhanh.libary.model.NganhHoc;
import com.hokhanh.libary.model.ThoiKhoaBieu;
import com.hokhanh.libary.repository.ChuyenNganhRepository;
import com.hokhanh.libary.repository.GiangVienRepository;
import com.hokhanh.libary.repository.GiangVien_LopHocRepository;
import com.hokhanh.libary.repository.LopHocRepository;
import com.hokhanh.libary.repository.LopHoc_ThoiKhoaBieuRepository;
import com.hokhanh.libary.repository.MonHocRepository;
import com.hokhanh.libary.repository.NganhHocRepository;
import com.hokhanh.libary.repository.ThoiKhoaBieuRepository;

@Service
public class LopHocService {

	@Autowired
	private LopHocRepository lopHocRepository;
	
	@Autowired
	private GiangVienRepository giangVienRepository ;
	
	@Autowired
	private ChuyenNganhRepository chuyenNganhRepository ;
	
	@Autowired
	private ThoiKhoaBieuRepository thoiKhoaBieuRepository;
	
	@Autowired
	private MonHocRepository monHocRepository;
	
	@Autowired
	private NganhHocRepository nganhHocRepository;
	
	@Autowired
	private GiangVien_LopHocRepository giangVien_LopHocRepository;
	
	@Autowired
	private LopHoc_ThoiKhoaBieuRepository hoc_ThoiKhoaBieuRepository;
	
	
	
	public List<LopHoc> lopHoc() {
		return this.lopHocRepository.findAllSortNganhHocAndChuyenNganh();
	}
	
	public LopHoc themLopHoc(LopHoc lopHoc, String chuyenNganhInsert) {
		if(this.lopHocRepository.findByTenLopHoc(lopHoc.getTenLopHoc()) != null) {
			return null;
		}
		
		if(chuyenNganhInsert != null && !chuyenNganhInsert.equals("null")) {
			lopHoc.setChuyenNganh(this.chuyenNganhRepository.findById(Long.parseLong(chuyenNganhInsert)).get());
		}
		
		return this.lopHocRepository.save(lopHoc);
	}
	
	public LopHoc chinhSuaLopHoc(LopHoc lopHoc, String giangVienEdit, String chuyenNganhEdit, String thoiKhoaBieuEdit, String soKhoaLopChuNhiem, String soKhoaThoiKhoaBieu) {
		LopHoc lopHocCheck = this.lopHocRepository.findByTenLopHoc(lopHoc.getTenLopHoc());
		if(lopHocCheck != null && lopHoc.getId() != lopHocCheck.getId()) {
			return null;
		}
		
		if(giangVienEdit!= null && giangVienEdit.equals("null") == false) {
			if(this.giangVien_LopHocRepository.findByLopHocAndSoKhoaLopChuNhiem( lopHoc, soKhoaLopChuNhiem) != null) {
				return null;
			}
						
			GiangVien_LopHoc giangVien_LopHoc = this.giangVien_LopHocRepository.findByGiangVien(this.giangVienRepository.findById(Long.parseLong(giangVienEdit)).get());
			if(giangVien_LopHoc != null) {
				giangVien_LopHoc.setLopHoc(lopHoc);
				giangVien_LopHoc.setSoKhoaLopChuNhiem(soKhoaLopChuNhiem);
				this.giangVien_LopHocRepository.save(giangVien_LopHoc);
			}else {
				GiangVien_LopHoc gvlh = new GiangVien_LopHoc();
				gvlh.setGiangVien(this.giangVienRepository.findById(Long.parseLong(giangVienEdit)).get());
				gvlh.setLopHoc(lopHoc);
				gvlh.setSoKhoaLopChuNhiem(soKhoaLopChuNhiem);
				this.giangVien_LopHocRepository.save(gvlh);
			}
		}
		
		if(chuyenNganhEdit != null && chuyenNganhEdit.equals("null") == false) {
			lopHoc.setChuyenNganh(this.chuyenNganhRepository.findById(Long.parseLong(chuyenNganhEdit)).get());
		}
		
		if(thoiKhoaBieuEdit != null && thoiKhoaBieuEdit.equals("") == false) {
			LopHoc_ThoiKhoaBieu hoc_ThoiKhoaBieu = this.hoc_ThoiKhoaBieuRepository.findByLopHocAndSoKhoa(lopHoc, soKhoaThoiKhoaBieu);
			if(hoc_ThoiKhoaBieu != null) {
				hoc_ThoiKhoaBieu.getThoiKhoaBieu().setLinkTKB(thoiKhoaBieuEdit);
				
				this.hoc_ThoiKhoaBieuRepository.save(hoc_ThoiKhoaBieu);
			}else {
				ThoiKhoaBieu tkb = new ThoiKhoaBieu();
				tkb.setLinkTKB(thoiKhoaBieuEdit);
				this.thoiKhoaBieuRepository.save(tkb);
				
				LopHoc_ThoiKhoaBieu mhtkb = new LopHoc_ThoiKhoaBieu();
				mhtkb.setLopHoc(lopHoc);
				mhtkb.setSoKhoa(soKhoaThoiKhoaBieu);
				mhtkb.setThoiKhoaBieu(tkb);
				this.hoc_ThoiKhoaBieuRepository.save(mhtkb);
			}
		}
		
		return this.lopHocRepository.save(lopHoc);
	}

	public LopHoc findById(Long id) {
		return this.lopHocRepository.findById(id).get();
	}

	public List<LopHoc> findByIdNganhHoc(Long idNganhHoc) {
		return this.lopHocRepository.findByIdNganhHoc(idNganhHoc);
	}

	public List<LopHoc> findByIdChuyenNganh(Long idChuyenNganh) {
		return this.lopHocRepository.findByIdChuyenNganh(idChuyenNganh);
	}

	public List<LopHoc> findAll() {
		return this.lopHocRepository.findAllSortNganhHocAndChuyenNganh();
	}

	public List<LopHoc> findLopHocByHocKiAndNamHocOfMon(Long idMonHoc) {
		MonHoc monHoc = this.monHocRepository.findById(idMonHoc).get();
		List<LopHoc> lopHocList = new ArrayList<>();
		if(monHoc.getDanhSachChuyenNganh() == null || monHoc.getDanhSachChuyenNganh().isEmpty()) {
			if(monHoc.getDanhSachNganhHoc() != null && monHoc.getDanhSachNganhHoc().isEmpty() == false) {
				for (NganhHoc nganhHoc : monHoc.getDanhSachNganhHoc()) {
					for (LopHoc lopHoc : this.lopHocRepository.findByIdNganhHoc(nganhHoc.getId())) {
						lopHocList.add(lopHoc);
					}
				}
			}
		}else {
			if(monHoc.getDanhSachChuyenNganh() != null && monHoc.getDanhSachChuyenNganh().isEmpty() == false) {
				for (ChuyenNganh chuyenNganh : monHoc.getDanhSachChuyenNganh()) {
					for (LopHoc lopHoc : this.lopHocRepository.findByIdChuyenNganh(chuyenNganh.getId())) {
						lopHocList.add(lopHoc);
					}
				}
			}
		}
		
		return lopHocList;
	}

	public List<LopHoc_ThoiKhoaBieu> thoiKhoaBieu() {
		return this.hoc_ThoiKhoaBieuRepository.findAllSortLopHocAndSoKhoa();
	}

	public void deleteTKBById(Long id) {
		LopHoc_ThoiKhoaBieu lhtkb = this.hoc_ThoiKhoaBieuRepository.findById(id).get();
		this.hoc_ThoiKhoaBieuRepository.deleteById(id);
		this.thoiKhoaBieuRepository.deleteById(lhtkb.getThoiKhoaBieu().getId());
	}


	
	
}
