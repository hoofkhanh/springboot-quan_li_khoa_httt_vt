document.addEventListener('DOMContentLoaded', function(){
	
	fetch('/admin/sinhVien/sinhVienHocGhep/findLopHocByHocKiAndNamHocOfMon?idMonHoc='+document.getElementById('monHocInsert').options[0].value)
			.then(response => response.json())
			.then(lopHocList => {
				Array.from(lopHocList).forEach(lopHoc => {
					let option = document.createElement('option');
		            option.value = lopHoc.id;
		            option.text = lopHoc.tenLopHoc; 
		            document.getElementById('lopHocInsert').appendChild(option);
				});
			});
	
	document.getElementById('monHocInsert').addEventListener('change', function(){
		document.getElementById('lopHocInsert').innerHTML = '';
		fetch('/admin/sinhVien/sinhVienHocGhep/findLopHocByHocKiAndNamHocOfMon?idMonHoc='+this.value)
			.then(response => response.json())
			.then(lopHocList => {
				Array.from(lopHocList).forEach(lopHoc => {
					let option = document.createElement('option');
		            option.value = lopHoc.id;
		            option.text = lopHoc.tenLopHoc; 
		            document.getElementById('lopHocInsert').appendChild(option);
				});
			});
	});
	
	document.getElementById('monHocEdit').addEventListener('change', function(){
		document.getElementById('lopHocEdit').innerHTML = '';
		fetch('/admin/sinhVien/sinhVienHocGhep/findLopHocByHocKiAndNamHocOfMon?idMonHoc='+this.value)
			.then(response => response.json())
			.then(lopHocList => {
				Array.from(lopHocList).forEach(lopHoc => {
					let option = document.createElement('option');
		            option.value = lopHoc.id;
		            option.text = lopHoc.tenLopHoc; 
		            document.getElementById('lopHocEdit').appendChild(option);
				});
			});
	});
	
	Array.from(document.getElementsByClassName('editButton')).forEach(button => {
		button.addEventListener('click', function(event){
			event.preventDefault();
			let href = event.target.getAttribute('href');
			
			fetch(href)
				.then(response => response.json())
				.then(async svhg => {
					document.getElementById('idEdit').value = svhg.id;
					document.getElementById('emailTruongEdit').value = svhg.sinhVien.emailTruong;
					document.getElementById('monHocEdit').value = svhg.monHoc.id;
					document.getElementById('soKhoaEdit').value = svhg.soKhoa;
					document.getElementById('soTienPhaiDongEdit').value = svhg.soTienPhaiDong;

					document.getElementById('lopHocEdit').innerHTML = '';
					await fetch('/admin/sinhVien/sinhVienHocGhep/findLopHocByHocKiAndNamHocOfMon?idMonHoc='+svhg.monHoc.id)
								.then(response => response.json())
								.then(lopHocList => {
									Array.from(lopHocList).forEach(lopHoc => {
										let option = document.createElement('option');
							            option.value = lopHoc.id;
							            option.text = lopHoc.tenLopHoc; 
							            document.getElementById('lopHocEdit').appendChild(option);
									});
								});
								
					document.getElementById('lopHocEdit').value = svhg.lopHoc.id;
					document.getElementById('ngayDangKiEdit').value = svhg.ngayDangKi;
				});
		});
	});
});