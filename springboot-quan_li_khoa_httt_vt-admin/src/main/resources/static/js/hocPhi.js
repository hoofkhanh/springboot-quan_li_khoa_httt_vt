document.addEventListener('DOMContentLoaded', function(){
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
	
	/*Trong chỉnh sửa*/
	document.getElementById('namHocCuaSinhVienTrongNganhHocChinhSua').addEventListener('change', function(){
		if(this.value == 3){
			document.getElementById('hocKi1TrongNganhHocTrongChinhSua').selected = true
			document.getElementById('hocKi2TrongNganhHocTrongChinhSua').style.display  = 'none';
		}else {
			
			document.getElementById('hocKi2TrongNganhHocTrongChinhSua').style.display  = 'block';
		}
	});
	
	document.getElementById('namHocCuaSinhVienTrongChuyenNganhChinhSua').addEventListener('change', function(){
		if(this.value == 3){
			document.getElementById('hocKi2TrongChuyenNganhTrongChinhSua').selected = true
			document.getElementById('hocKi1TrongChuyenNganhTrongChinhSua').style.display  = 'none';
		}else {
			document.getElementById('hocKi1TrongChuyenNganhTrongChinhSua').style.display  = 'block';
		}
	});
	
	Array.from(document.getElementsByClassName('editButtonNganhHoc')).forEach(button => {
		button.addEventListener('click', async function(event){
			event.preventDefault();
			
			await fetch(event.target.getAttribute('href'))	
				.then(response => response.json())
				.then(async hocPhi => {
					await fetch('/admin/nganhHoc/findByTenNganh?tenNganh='+hocPhi.tenNganhHocHoacChuyenNganh)
						.then(response => response.text())
						.then(nganhHoc => {
							if(nganhHoc == ''){
								document.getElementById('idChuyenNganhChinhSua').value = hocPhi.id;
								document.getElementById('tenChuyenNganhChinhSua').value = 'Chuyên ngành: ' + hocPhi.tenNganhHocHoacChuyenNganh;
								document.getElementById('namChinhSua2').value = hocPhi.namHoc;
								document.getElementById('hocKiCuaSinhVienTrongChuyenNganhChinhSua').value = hocPhi.hocKi;
								document.getElementById('namHocCuaSinhVienTrongChuyenNganhChinhSua').value = hocPhi.namHocCuaSinhVien;
								document.getElementById('hocPhiDuocGiamEdit2').value = hocPhi.hocPhiDuocGiam;
								document.getElementById('soTienMotTinChiChinhSua2').value =Number((hocPhi.tienHocPhi/hocPhi.tongTinChi).toFixed(3));
								
								if(hocPhi.namHocCuaSinhVien == 3){
									document.getElementById('hocKi2TrongChuyenNganhTrongChinhSua').selected = true
									document.getElementById('hocKi1TrongChuyenNganhTrongChinhSua').style.display  = 'none';
								}else {
									document.getElementById('hocKi1TrongChuyenNganhTrongChinhSua').style.display  = 'block';
								}
					
								$('#editModalChuyenNganh').modal('show');
							}else{
								document.getElementById('idNganhHocChinhSua').value = hocPhi.id;;
								document.getElementById('tenNganhChinhSua').value = hocPhi.tenNganhHocHoacChuyenNganh;
								document.getElementById('namChinhSua').value = hocPhi.namHoc;
								document.getElementById('hocKiCuaSinhVienTrongNganhHocChinhSua').value = hocPhi.hocKi;
								document.getElementById('namHocCuaSinhVienTrongNganhHocChinhSua').value = hocPhi.namHocCuaSinhVien;
								document.getElementById('hocPhiDuocGiamEdit').value = hocPhi.hocPhiDuocGiam;
								document.getElementById('soTienMotTinChiChinhSua').value = Number((hocPhi.tienHocPhi/hocPhi.tongTinChi).toFixed(3));
								
								if(hocPhi.namHocCuaSinhVien == 3){
									document.getElementById('hocKi1TrongNganhHocTrongChinhSua').selected = true
									document.getElementById('hocKi2TrongNganhHocTrongChinhSua').style.display  = 'none';
								}else {
									document.getElementById('hocKi2TrongNganhHocTrongChinhSua').style.display  = 'block';
								}
								
								$('#editModalNganhHoc').modal('show');
							}
						})
				});
		});
	});
	
	let allOptionSinhVien = document.getElementById('sinhVienListEdit');
	let optionList = [];
	Array.from(allOptionSinhVien.options).forEach(option => {
		optionList.push(option);
	});
	
	Array.from(document.getElementsByClassName('dongHocPhiClass')).forEach(element => {
		element.addEventListener('click', function(event){
			let url = event.target.getAttribute('href');
			let id = url.substring(url.indexOf("id=") + 3).trim();
			document.getElementById('hocPhiTrongDongHocPhi').value= id;
						
			document.getElementById('sinhVienListEdit').innerHTML = '';
			Array.from(optionList).forEach(option => {
				document.getElementById('sinhVienListEdit').appendChild(option);
			});
						
			fetch(url)
				.then(response => response.json())
				.then(sinhVienList => {
					Array.from(sinhVienList).forEach(sinhVien => {
						Array.from(document.getElementById('sinhVienListEdit').options).forEach(option => {
							if(sinhVien.emailTruong == option.value){
								let options = document.getElementById('sinhVienListEdit').options;
								for (var i = 0; i < options.length; i++) {
								    if (options[i].value === sinhVien.emailTruong) {
								        options[i].parentNode.removeChild(options[i]);
								        break; 
								    }
								}
							}
						});
					})
				})
		});
	});
	
});