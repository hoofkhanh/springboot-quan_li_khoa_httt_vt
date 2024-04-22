document.addEventListener('DOMContentLoaded', function(){
	/*Trong insert*/
	function nganhHocInInsert(){
		let firstOption =  document.getElementById('nganhHocInsert').options[0].value;
		fetch("/admin/lopHoc/findByIdNganhHoc?idNganhHoc="+firstOption)
				.then(response => response.json())
				.then(lopHocList => {
					let selectInLopHoc =  document.getElementById('lopHocInsert');
					selectInLopHoc.innerHTML = '';
					
					Array.from(lopHocList).forEach(lopHoc => {
						let option = document.createElement('option');
			            option.value = lopHoc.id;
			            option.text = lopHoc.tenLopHoc; 
			            selectInLopHoc.appendChild(option);
					});
				});
		
		document.getElementById('nganhHocInsert').addEventListener('change', function(){
			fetch("/admin/lopHoc/findByIdNganhHoc?idNganhHoc="+this.value)
				.then(response => response.json())
				.then(lopHocList => {
					let selectInLopHoc =  document.getElementById('lopHocInsert');
					selectInLopHoc.innerHTML = '';
					
					Array.from(lopHocList).forEach(lopHoc => {
						let option = document.createElement('option');
			            option.value = lopHoc.id;
			            option.text = lopHoc.tenLopHoc; 
			            selectInLopHoc.appendChild(option);
					});
				});
		});
	}
	
	nganhHocInInsert();
	
	/*Trong edit*/
	function nganhHocEdit(){
		document.getElementById('nganhHocEdit').addEventListener('change', async function(){
			await fetch("/admin/chuyenNganh/findChuyenNganhByNganhHoc?id="+this.value)
				.then(response => response.json())
				.then(chuyenNganhList => {
					
					let selectInLopHoc =  document.getElementById('chuyenNganhEdit');
					selectInLopHoc.innerHTML = '';
					
					let option = document.createElement('option');
			            option.value = 0;
			            option.text = 'Chưa có'; 
			            selectInLopHoc.appendChild(option);
					
					Array.from(chuyenNganhList).forEach(chuyenNganh => {
						let option = document.createElement('option');
			            option.value = chuyenNganh.id;
			            option.text = chuyenNganh.tenChuyenNganh; 
			            selectInLopHoc.appendChild(option);
					});
				});
				
			await fetch("/admin/lopHoc/findByIdNganhHoc?idNganhHoc="+this.value)
				.then(response => response.json())
				.then(lopHocList => {
					let selectInLopHoc =  document.getElementById('lopHocEdit');
					selectInLopHoc.innerHTML = '';
					
					Array.from(lopHocList).forEach(lopHoc => {
						let option = document.createElement('option');
			            option.value = lopHoc.id;
			            option.text = lopHoc.tenLopHoc; 
			            selectInLopHoc.appendChild(option);
					});
				});
		});
		
		document.getElementById('chuyenNganhEdit').addEventListener('change',async function(){
			await fetch("/admin/lopHoc/findByIdChuyenNganh?idChuyenNganh="+this.value)
				.then(response => response.json())
				.then(async lopHocList => {
					let selectInLopHoc =  document.getElementById('lopHocEdit');
					selectInLopHoc.innerHTML = '';
					
					if(lopHocList.length ==0){
						await fetch("/admin/lopHoc/findByIdNganhHoc?idNganhHoc="+document.getElementById('nganhHocEdit').value)
							.then(response => response.json())
							.then(lopHocList => {
								let selectInLopHoc =  document.getElementById('lopHocEdit');
								selectInLopHoc.innerHTML = '';
								
								Array.from(lopHocList).forEach(lopHoc => {
									let option = document.createElement('option');
						            option.value = lopHoc.id;
						            option.text = lopHoc.tenLopHoc; 
						            selectInLopHoc.appendChild(option);
								});
							});
					}else{
						Array.from(lopHocList).forEach(lopHoc => {
							let option = document.createElement('option');
				            option.value = lopHoc.id;
				            option.text = lopHoc.tenLopHoc; 
				            selectInLopHoc.appendChild(option);
						});
					}
					
					
				});
		});
		
	}
	
	nganhHocEdit();
	
	
	
	Array.from(document.getElementsByClassName('editButton')).forEach(button => {
		button.addEventListener('click', function(event){
			event.preventDefault();
			let href = event.target.getAttribute('href');
			
			fetch(href)
				.then(response => response.json())
				.then(async sinhVien => {
					document.getElementById('idEdit').value = sinhVien.id;
					document.getElementById('emailTruongEdit').value = sinhVien.emailTruong;
					document.getElementById('nienKhoaEdit').value = sinhVien.nienKhoa;
					document.getElementById('passwordEdit').value = sinhVien.password;
					document.getElementById('soKhoaCuaNganhEdit').value = sinhVien.soKhoaCuaNganh;
					document.getElementById('vaiTroEdit').value = sinhVien.vaiTro.id;
					
					document.getElementById('tenSinhVienEdit').value = sinhVien.hoVaTen;
					document.getElementById('ngaySinhEdit').value = sinhVien.ngaySinh;
					document.getElementById('soDienThoaiEdit').value = sinhVien.sdt;
					document.getElementById('emailEdit').value = sinhVien.email;
					
					if(sinhVien.gioiTinh == 'Nam'){
						document.getElementById('gioiTinhNamEdit').checked = true;
						document.getElementById('gioiTinhNuEdit').checked = false;
					}else{
						document.getElementById('gioiTinhNamEdit').checked = false;
						document.getElementById('gioiTinhNuEdit').checked = true;
					}
					
					document.getElementById('noiSinhEdit').value = sinhVien.noiSinh

					document.getElementById('nganhHocEdit').value = sinhVien.nganhHoc.id;
					
					await fetch("/admin/chuyenNganh/findChuyenNganhByNganhHoc?id="+sinhVien.nganhHoc.id)
						.then(response => response.json())
						.then(chuyenNganhList => {
							let selectInLopHoc =  document.getElementById('chuyenNganhEdit');
							selectInLopHoc.innerHTML = '';
							
							let option = document.createElement('option');
					            option.value = 0;
					            option.text = 'Chưa có'; 
					            selectInLopHoc.appendChild(option);
					            
							Array.from(chuyenNganhList).forEach(chuyenNganh => {
								let option = document.createElement('option');
					            option.value = chuyenNganh.id;
					            option.text = chuyenNganh.tenChuyenNganh; 
					            selectInLopHoc.appendChild(option);
							});
						});
					
					if(sinhVien.chuyenNganh != null){
						document.getElementById('chuyenNganhEdit').value = sinhVien.chuyenNganh.id;
					}
					
					let idChuyenNganh;
					if(sinhVien.chuyenNganh == null){
						idChuyenNganh =0;
					}else{
						idChuyenNganh = sinhVien.chuyenNganh.id;
					}
					
					await fetch("/admin/lopHoc/findByIdChuyenNganh?idChuyenNganh="+idChuyenNganh)
						.then(response => response.json())
						.then( async lopHocList => {
							
							let selectInLopHoc =  document.getElementById('lopHocEdit');
							selectInLopHoc.innerHTML = '';
							
							if(lopHocList.length ==0){
								await fetch("/admin/lopHoc/findByIdNganhHoc?idNganhHoc="+document.getElementById('nganhHocEdit').value)
									.then(response => response.json())
									.then(lopHocList => {
										let selectInLopHoc =  document.getElementById('lopHocEdit');
										selectInLopHoc.innerHTML = '';
										
										Array.from(lopHocList).forEach(lopHoc => {
											let option = document.createElement('option');
								            option.value = lopHoc.id;
								            option.text = lopHoc.tenLopHoc; 
								            selectInLopHoc.appendChild(option);
										});
									});
							}else{
								Array.from(lopHocList).forEach(lopHoc => {
									let option = document.createElement('option');
						            option.value = lopHoc.id;
						            option.text = lopHoc.tenLopHoc; 
						            selectInLopHoc.appendChild(option);
								});
								
							}
							
							
						});
						
						document.getElementById('lopHocEdit').value = sinhVien.lopHoc.id;
					
				});
		})
	});
});