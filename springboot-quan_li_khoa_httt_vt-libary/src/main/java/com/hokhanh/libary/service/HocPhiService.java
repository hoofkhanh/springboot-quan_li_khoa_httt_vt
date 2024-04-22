package com.hokhanh.libary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hokhanh.libary.model.ChuyenNganh;
import com.hokhanh.libary.model.DoanhThu;
import com.hokhanh.libary.model.HocPhi;
import com.hokhanh.libary.model.MonHoc;
import com.hokhanh.libary.model.NganhHoc;
import com.hokhanh.libary.model.SinhVien;
import com.hokhanh.libary.repository.ChuyenNganhRepository;
import com.hokhanh.libary.repository.DoanhThuRepository;
import com.hokhanh.libary.repository.HocPhiRepository;
import com.hokhanh.libary.repository.MonHocRepository;
import com.hokhanh.libary.repository.NganhHocRepository;
import com.hokhanh.libary.repository.SinhVienRepository;

@Service
public class HocPhiService {

	@Autowired
	private HocPhiRepository hocPhiRepository;
	
	@Autowired
	private NganhHocRepository nganhHocRepository;
	
	@Autowired
	private ChuyenNganhRepository chuyenNganhRepository;
	
	@Autowired 
	private MonHocRepository monHocRepository;
	
	@Autowired
	private SinhVienRepository sinhVienRepository;
	
	@Autowired
	private DoanhThuRepository doanhThuRepository;
	
	public List<HocPhi> findAllSorted(){
		return this.hocPhiRepository.findAllSorted();
	}
	
	public HocPhi themHocPhi(HocPhi hocPhi, double soTienMotTinChi) {
		String tenNganhHoacChuyenNganh = "";
		if(hocPhi.getTenNganhHocHoacChuyenNganh().indexOf("Chuyên ngành: ") > -1) {
			tenNganhHoacChuyenNganh = hocPhi.getTenNganhHocHoacChuyenNganh().replace("Chuyên ngành: ", "");
		}else {
			tenNganhHoacChuyenNganh = hocPhi.getTenNganhHocHoacChuyenNganh();
		}
		
		if(this.hocPhiRepository.findByNamHocAndHocKiAndTenNganhHocHoacChuyenNganhAndNamHocCuaSinhVien
				(hocPhi.getNamHoc(), hocPhi.getHocKi(), tenNganhHoacChuyenNganh ,hocPhi.getNamHocCuaSinhVien()) != null) {
			return null;
		}
		
		if(hocPhi.getTenNganhHocHoacChuyenNganh().indexOf("Chuyên ngành: ") > -1) {
			hocPhi.setTenNganhHocHoacChuyenNganh(tenNganhHoacChuyenNganh);
			ChuyenNganh chuyenNganh = this.chuyenNganhRepository.findByTenChuyenNganh(hocPhi.getTenNganhHocHoacChuyenNganh());
			List<Long> monHocIdList = this.monHocRepository.findByChuyenNganh(chuyenNganh.getId());
			List<MonHoc> monHocList = new ArrayList<>();
			for (Long id : monHocIdList) {
				monHocList.add(this.monHocRepository.findById(id).get());
			}
						
			HocPhi newHocPhi = new HocPhi();
			newHocPhi.setHocKi(hocPhi.getHocKi());
			newHocPhi.setNamHoc(hocPhi.getNamHoc());
			newHocPhi.setNamHocCuaSinhVien(hocPhi.getNamHocCuaSinhVien());
			newHocPhi.setTenNganhHocHoacChuyenNganh(chuyenNganh.getTenChuyenNganh());
			
			double tienHocPhi = 0;
			int tongTinChi =0;
			for (MonHoc monHoc : monHocList) {
				if(monHoc.getHocKiCuaSinhVien() == hocPhi.getHocKi() && monHoc.getNamHocCuaSinhVien() == hocPhi.getNamHocCuaSinhVien()) {
					tienHocPhi += monHoc.getSoTinhChi() * soTienMotTinChi;
					tongTinChi += monHoc.getSoTinhChi();
				}
				
			}
			
			tienHocPhi = Math.round(tienHocPhi * 100.0) / 100.0;
			
			newHocPhi.setTienHocPhi(tienHocPhi);
			newHocPhi.setTongTinChi(tongTinChi);
			return this.hocPhiRepository.save(newHocPhi);
		}else {
			NganhHoc nganhHoc = this.nganhHocRepository.findByTenNganh(hocPhi.getTenNganhHocHoacChuyenNganh());
			List<Long> monHocIdList = this.monHocRepository.findByNganhHoc(nganhHoc.getId());
			List<MonHoc> monHocList = new ArrayList<>();
			for (Long id : monHocIdList) {
				monHocList.add(this.monHocRepository.findById(id).get());
			}
			
			HocPhi newHocPhi = new HocPhi();
			newHocPhi.setHocKi(hocPhi.getHocKi());
			newHocPhi.setNamHoc(hocPhi.getNamHoc());
			newHocPhi.setNamHocCuaSinhVien(hocPhi.getNamHocCuaSinhVien());
			newHocPhi.setTenNganhHocHoacChuyenNganh(nganhHoc.getTenNganh());
			
			double tienHocPhi = 0;
			int tongTinChi =0;
			for (MonHoc monHoc : monHocList) {
				if(monHoc.getHocKiCuaSinhVien() == hocPhi.getHocKi() && monHoc.getNamHocCuaSinhVien() == hocPhi.getNamHocCuaSinhVien()) {
					tienHocPhi += monHoc.getSoTinhChi() * soTienMotTinChi;
					tongTinChi += monHoc.getSoTinhChi();
				}
			}
			
			tienHocPhi = Math.round(tienHocPhi * 100.0) / 100.0;
			
			newHocPhi.setTienHocPhi(tienHocPhi);
			newHocPhi.setTongTinChi(tongTinChi);
			return this.hocPhiRepository.save(newHocPhi);
		}
		
	
	}

	public HocPhi chinhSuaHocPhi(HocPhi hocPhi , double soTienMotTinChi) {
		String tenNganhHoacChuyenNganh = "";
		if(hocPhi.getTenNganhHocHoacChuyenNganh().indexOf("Chuyên ngành: ") > -1) {
			tenNganhHoacChuyenNganh = hocPhi.getTenNganhHocHoacChuyenNganh().replace("Chuyên ngành: ", "");
		}else {
			tenNganhHoacChuyenNganh = hocPhi.getTenNganhHocHoacChuyenNganh();
		}
		
		HocPhi hocPhiCheck = this.hocPhiRepository.findByNamHocAndHocKiAndTenNganhHocHoacChuyenNganhAndNamHocCuaSinhVien
				(hocPhi.getNamHoc(), hocPhi.getHocKi(), tenNganhHoacChuyenNganh, hocPhi.getNamHocCuaSinhVien());
		
		
		if(hocPhiCheck != null && hocPhiCheck.getId() != hocPhi.getId()) {
			return null;
		}
		
		if(hocPhi.getTenNganhHocHoacChuyenNganh().indexOf("Chuyên ngành: ") > -1) {
			hocPhi.setTenNganhHocHoacChuyenNganh(tenNganhHoacChuyenNganh);
			ChuyenNganh chuyenNganh = this.chuyenNganhRepository.findByTenChuyenNganh(hocPhi.getTenNganhHocHoacChuyenNganh());
			List<Long> monHocIdList = this.monHocRepository.findByChuyenNganh(chuyenNganh.getId());
			List<MonHoc> monHocList = new ArrayList<>();
			for (Long id : monHocIdList) {
				monHocList.add(this.monHocRepository.findById(id).get());
			}
						
			HocPhi newHocPhi = new HocPhi();
			newHocPhi.setId(hocPhi.getId());
			newHocPhi.setHocKi(hocPhi.getHocKi());
			newHocPhi.setNamHoc(hocPhi.getNamHoc());
			newHocPhi.setNamHocCuaSinhVien(hocPhi.getNamHocCuaSinhVien());
			newHocPhi.setTenNganhHocHoacChuyenNganh(chuyenNganh.getTenChuyenNganh());
			newHocPhi.setHocPhiDuocGiam(hocPhi.getHocPhiDuocGiam());
			
			double tienHocPhi = 0;
			int tongTinChi =0;
			for (MonHoc monHoc : monHocList) {
				if(monHoc.getHocKiCuaSinhVien() == hocPhi.getHocKi() && monHoc.getNamHocCuaSinhVien() == hocPhi.getNamHocCuaSinhVien()) {
					tienHocPhi += monHoc.getSoTinhChi() * soTienMotTinChi;
					tongTinChi += monHoc.getSoTinhChi();
				}
				
			}
			
			tienHocPhi = Math.round(tienHocPhi * 100.0) / 100.0;
			
			newHocPhi.setTienHocPhi(tienHocPhi);
			newHocPhi.setTongTinChi(tongTinChi);
			return this.hocPhiRepository.save(newHocPhi);
		}else {
			NganhHoc nganhHoc = this.nganhHocRepository.findByTenNganh(hocPhi.getTenNganhHocHoacChuyenNganh());
			List<Long> monHocIdList = this.monHocRepository.findByNganhHoc(nganhHoc.getId());
			List<MonHoc> monHocList = new ArrayList<>();
			for (Long id : monHocIdList) {
				monHocList.add(this.monHocRepository.findById(id).get());
			}
			
			HocPhi newHocPhi = new HocPhi();
			newHocPhi.setId(hocPhi.getId());
			newHocPhi.setHocKi(hocPhi.getHocKi());
			newHocPhi.setNamHoc(hocPhi.getNamHoc());
			newHocPhi.setNamHocCuaSinhVien(hocPhi.getNamHocCuaSinhVien());
			newHocPhi.setTenNganhHocHoacChuyenNganh(nganhHoc.getTenNganh());
			newHocPhi.setHocPhiDuocGiam(hocPhi.getHocPhiDuocGiam());
			
			double tienHocPhi = 0;
			int tongTinChi =0;
			for (MonHoc monHoc : monHocList) {
				if(monHoc.getHocKiCuaSinhVien() == hocPhi.getHocKi() && monHoc.getNamHocCuaSinhVien() == hocPhi.getNamHocCuaSinhVien()) {
					tienHocPhi += monHoc.getSoTinhChi() * soTienMotTinChi;
					tongTinChi += monHoc.getSoTinhChi();
				}
			}
			
			tienHocPhi = Math.round(tienHocPhi * 100.0) / 100.0;
			
			newHocPhi.setTienHocPhi(tienHocPhi);
			newHocPhi.setTongTinChi(tongTinChi);
			return this.hocPhiRepository.save(newHocPhi);
		}
	}

	public HocPhi findById(Long id) {
		return this.hocPhiRepository.findById(id).get();
	}
	
	public List<DoanhThu> tinhDoanhThuTheoNam() {
	    List<DoanhThu> doanhThuList = new ArrayList<>();
	    for (Object obj : this.hocPhiRepository.tinhDoanhThu()) {
	        DoanhThu doanhThu = new DoanhThu();
	        Object[] row = (Object[]) obj;
	        for (int i = 0; i < row.length; i++) {
	            if (i == 0) {
	                doanhThu.setNam((Integer) row[i]);
	            } else if (i == 1) {
	                doanhThu.setNamHocCuaSinhVien((Integer) row[i]);
	            } else if (i == 2) {
	                doanhThu.setHocKi((Integer) row[i]);
	            } else {
	                doanhThu.setTien((Double) row[i]);
	            }
	        }
	        doanhThuList.add(doanhThu);
	    }
	    
	    if(doanhThuList != null && doanhThuList.isEmpty() == false) {
	    	for (DoanhThu doanhThu : doanhThuList) {
				DoanhThu dt = this.doanhThuRepository.findByNamAndNamHocCuaSinhVienAndHocKi(doanhThu.getNam(), doanhThu.getNamHocCuaSinhVien(), doanhThu.getHocKi());
				if(dt != null) {
					doanhThu.setId(dt.getId());
					this.doanhThuRepository.save(doanhThu);
				}else {
					this.doanhThuRepository.save(doanhThu);
				}
			}
	    }
	    
	    return doanhThuList;
	}

}
