document.addEventListener('DOMContentLoaded', function(){
	document.getElementById('giangVienInsert').addEventListener('change', function(){
		let emailTruong = this.value;
		
		fetch('/admin/giangVien/findByEmailTruong?emailTruong='+emailTruong)
			.then(response => response.text())
			.then(giangVien => {
				document.getElementById('monDayInsert').innerHTML = '';
				if(giangVien != ''){
					giangVien = JSON.parse(giangVien);
					if(giangVien.danhSachMonHoc != null && giangVien.danhSachMonHoc.length >0){
						Array.from(giangVien.danhSachMonHoc).forEach(monHoc => {
							let option = document.createElement('option');
				            option.value = monHoc.id;
				            option.text = monHoc.tenMonHoc;
				            document.getElementById('monDayInsert').appendChild(option);
						});
					}
				}
			});
	});
	
	
	document.getElementById('giangVienEdit').addEventListener('change', function(){
		let emailTruong = this.value;
		
		fetch('/admin/giangVien/findByEmailTruong?emailTruong='+emailTruong)
			.then(response => response.text())
			.then(giangVien => {
				document.getElementById('monDayEdit').innerHTML = '';
				if(giangVien != ''){
					giangVien = JSON.parse(giangVien);
					if(giangVien.danhSachMonHoc != null && giangVien.danhSachMonHoc.length >0){
						Array.from(giangVien.danhSachMonHoc).forEach(monHoc => {
							let option = document.createElement('option');
				            option.value = monHoc.id;
				            option.text = monHoc.tenMonHoc;
				            document.getElementById('monDayEdit').appendChild(option);
						});
					}
				}
			});
	});
	
	Array.from(document.getElementsByClassName('editButton')).forEach(button => {
		button.addEventListener('click', function(event){
			event.preventDefault();	
			let href = event.target.getAttribute('href');
			
			fetch(href)
				.then(response => response.json())
				.then(model => {
					document.getElementById('idEdit').value = model.id;
					document.getElementById('giangVienEdit').value = model.giangVien.emailTruong;
					document.getElementById('lopDayEdit').value = model.lopHoc.id;
					document.getElementById('soKhoaEdit').value = model.soKhoa;
					
					document.getElementById('monDayEdit').innerHTML = '';
					if(model.giangVien.danhSachMonHoc != null && model.giangVien.danhSachMonHoc.length >0){
						Array.from(model.giangVien.danhSachMonHoc).forEach(monHoc => {
							let option = document.createElement('option');
				            option.value = monHoc.id;
				            option.text = monHoc.tenMonHoc;
				            document.getElementById('monDayEdit').appendChild(option);
						});
						document.getElementById('monDayEdit').value = model.monHoc.id;
					}
					
				});
		})
	});
});