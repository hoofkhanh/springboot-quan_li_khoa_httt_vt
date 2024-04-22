package com.hokhanh.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hokhanh.libary.model.ChuyenNganh;
import com.hokhanh.libary.service.ChuyenNganhService;

@Controller
public class ChuyenNganhController {

	@Autowired
	private ChuyenNganhService chuyenNganhService;

	@GetMapping("/chuyenNganh/findChuyenNganhByNganhHoc")
	@ResponseBody
	public List<ChuyenNganh> findById(Long id) {
		return this.chuyenNganhService.findChuyenNganhByNganhHoc(id);
	}
}
