document.addEventListener('DOMContentLoaded', function(){
	/*trong chỗ insert*/
	document.getElementById('namHocCuaSinhVienNganhHoc').addEventListener('change', function(){
		if(this.value == 3){
			document.getElementById('hocKi1TrongNganhHoc').selected = true
			document.getElementById('hocKi2TrongNganhHoc').style.display  = 'none';
		}else {
			
			document.getElementById('hocKi2TrongNganhHoc').style.display  = 'block';
		}
	});
	
	document.getElementById('namHocCuaSinhVienChuyenNganh').addEventListener('change', function(){
		if(this.value == 3){
			document.getElementById('hocKi2TrongChuyenNganh').selected = true
			document.getElementById('hocKi1TrongChuyenNganh').style.display  = 'none';
		}else {
			document.getElementById('hocKi1TrongChuyenNganh').style.display  = 'block';
		}
	});
	
	/*trong chỗ edit*/
	document.getElementById('namHocCuaSinhVienTrongNganhHocChinhSua').addEventListener('change', function(){
		if(this.value == 3){
			document.getElementById('hocKi1TrongNganhHocTrongChinhSua').selected = true
			document.getElementById('hocKi2TrongNganhHocTrongChinhSua').style.display  = 'none';
		}else {
			
			document.getElementById('hocKi2TrongNganhHocTrongChinhSua').style.display  = 'block';
		}
	});
	
	Array.from(document.getElementsByClassName('editButtonNganhHoc')).forEach(button => {
		button.addEventListener('click', function(event){
			event.preventDefault();
			
			fetch(event.target.getAttribute('href'))	
				.then(response => response.json())
				.then(monHoc => {
					document.getElementById('idMonHocTrongNganhHocChinhSua').value = monHoc.id
					
					let monThuocNganh = document.getElementById('monThuocNganhTrongChinhSua').options;
					Array.from(monThuocNganh).forEach(nganhTrongOption => {
						nganhTrongOption.selected = false;
					});
					
					Array.from(monThuocNganh).forEach(nganhTrongOption => {
						Array.from(monHoc.danhSachNganhHoc).forEach(nganhHoc => {
							if(nganhTrongOption.value == nganhHoc.id){
								nganhTrongOption.selected = true;
							}
						})
					});
					
					document.getElementById('tenMonHocTrongNganhHocChinhSua').value = monHoc.tenMonHoc;
					document.getElementById('hocKiCuaSinhVienTrongNganhHocChinhSua').value = monHoc.hocKiCuaSinhVien;
					
					document.getElementById('namHocCuaSinhVienTrongNganhHocChinhSua').value = monHoc.namHocCuaSinhVien;
					if(monHoc.namHocCuaSinhVien == 3){
						document.getElementById('hocKi1TrongNganhHocTrongChinhSua').selected = true
						document.getElementById('hocKi2TrongNganhHocTrongChinhSua').style.display  = 'none';
					}else {
						document.getElementById('hocKi2TrongNganhHocTrongChinhSua').style.display  = 'block';
					}
					
					document.getElementById('soTinChiTrongNganhHocChinhSua').value = monHoc.soTinhChi;
				});
		});
	});
	
	document.getElementById('namHocCuaSinhVienTrongChuyenNganhChinhSua').addEventListener('change', function(){
		if(this.value == 3){
			document.getElementById('hocKi2TrongChuyenNganhTrongChinhSua').selected = true
			document.getElementById('hocKi1TrongChuyenNganhTrongChinhSua').style.display  = 'none';
		}else {
			document.getElementById('hocKi1TrongChuyenNganhTrongChinhSua').style.display  = 'block';
		}
	});
	
	Array.from(document.getElementsByClassName('editButtonChuyenNganh')).forEach(button => {
		button.addEventListener('click', function(event){
			event.preventDefault();
			
			fetch(event.target.getAttribute('href'))	
				.then(response => response.json())
				.then(monHoc => {
					document.getElementById('idMonHocTrongChuyenNganhChinhSua').value = monHoc.id
					
					let monThuocNganh = document.getElementById('monThuocChuyenNganhTrongChinhSua').options;
					Array.from(monThuocNganh).forEach(nganhTrongOption => {
						nganhTrongOption.selected = false;
					});
					
					Array.from(monThuocNganh).forEach(nganhTrongOption => {
						Array.from(monHoc.danhSachChuyenNganh).forEach(chuyenNganh => {
							if(nganhTrongOption.value == chuyenNganh.id){
								nganhTrongOption.selected = true;
							}
						})
					});
					
					document.getElementById('tenMonHocTrongChuyenNganhChinhSua').value = monHoc.tenMonHoc;
					document.getElementById('hocKiCuaSinhVienTrongChuyenNganhChinhSua').value = monHoc.hocKiCuaSinhVien;
					
					document.getElementById('namHocCuaSinhVienTrongChuyenNganhChinhSua').value = monHoc.namHocCuaSinhVien;
					if(monHoc.namHocCuaSinhVien == 3){
						document.getElementById('hocKi2TrongChuyenNganhTrongChinhSua').selected = true
						document.getElementById('hocKi1TrongChuyenNganhTrongChinhSua').style.display  = 'none';
					}else {
						document.getElementById('hocKi1TrongChuyenNganhTrongChinhSua').style.display  = 'block';
					}
					
					document.getElementById('soTinChiTrongChuyenNganhChinhSua').value = monHoc.soTinhChi;
				});
		});
	});
});