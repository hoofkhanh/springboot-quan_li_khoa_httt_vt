document.addEventListener('DOMContentLoaded', function(){
	let imageFileOfTrack = document.getElementById('imageFile');
	imageFileOfTrack.addEventListener('change', function(){	
		if (imageFileOfTrack.files && imageFileOfTrack.files[0]) {
		    let reader = new FileReader();
		    reader.onload = function (e) {
		      $('#imageCuaGiaoVien')
		        .attr('src', e.target.result)
		        .width(76)
		        .height(76)
		    };
		    reader.readAsDataURL(imageFileOfTrack.files[0]);
		  }
	});
	
	let imageFileOfTrackEdit = document.getElementById('imageFileEdit');
	imageFileOfTrackEdit.addEventListener('change', function(){	
		if (imageFileOfTrackEdit.files && imageFileOfTrackEdit.files[0]) {
		    let reader = new FileReader();
		    reader.onload = function (e) {
		      $('#imageCuaGiaoVienEdit')
		        .attr('src', e.target.result)
		        .width(76)
		        .height(76)
		    };
		    reader.readAsDataURL(imageFileOfTrackEdit.files[0]);
		  }
	});
	
	Array.from(document.getElementsByClassName('buttonEdit')).forEach(button => {
		button.addEventListener('click', function(event){
			event.preventDefault();
			
			let href = event.target.getAttribute('href');
			fetch(href)
				.then(response => response.json())
				.then(giangVien => {
					document.getElementById('imageCuaGiaoVienEdit').src = 'data:image/jpeg;base64,' + giangVien.hinhAnh;
					document.getElementById('tenGiangVienEdit').value = giangVien.tenGiangVien;
					document.getElementById('chucVuEdit').value = giangVien.chucVu;
					document.getElementById('emailCaNhanEdit').value = giangVien.email;
					document.getElementById('soDienThoaiEdit').value = giangVien.sdt;
					document.getElementById('ngaySinhEdit').value = giangVien.ngaySinh;
					
					if(giangVien.gioiTinh == 'Nam'){
						document.getElementById('gioiTinhNamEdit').checked = true;
					}else{
						document.getElementById('gioiTinhNuEdit').checked = true;
					}
					
					document.getElementById('noiSinhEdit').value = giangVien.noiSinh;
					document.getElementById('noiDaoTaoEdit').value = giangVien.noiDaoTao;
					
					document.getElementById('idEdit').value = giangVien.id;
					document.getElementById('emailTruongEdit').value = giangVien.emailTruong;
					document.getElementById('passwordEdit').value = giangVien.password;
					document.getElementById('vaiTro').value = giangVien.vaiTro.id;
					
					let monHocEdit = document.getElementById('monHocEdit').options;
					Array.from(monHocEdit).forEach(monHocTrongOption => {
						monHocTrongOption.selected = false;
					});
					
					if(giangVien.danhSachMonHoc != null && giangVien.danhSachMonHoc.length >0){
						Array.from(monHocEdit).forEach(monHocTrongOption => {
							Array.from(giangVien.danhSachMonHoc).forEach(monHoc => {
								if(monHocTrongOption.value == monHoc.id){
									monHocTrongOption.selected = true;
								}
							})
						});
					}
				});
		});
	});
});