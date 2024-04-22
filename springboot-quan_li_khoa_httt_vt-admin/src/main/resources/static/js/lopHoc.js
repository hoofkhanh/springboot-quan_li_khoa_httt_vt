document.addEventListener('DOMContentLoaded' , function(){
	/*thêm*/
	id = document.getElementById('nganhHocInsert').options[0].value;
	document.getElementById('chuyenNganhInsert').innerHTML = '';
	let option = document.createElement('option');
    option.value = null;
    option.text = 'Không có'; 
    document.getElementById('chuyenNganhInsert').appendChild(option);
	fetch('/admin/chuyenNganh/findChuyenNganhByNganhHoc?id='+id)
		.then(response => response.json())
		.then(chuyenNganhs => {
			Array.from(chuyenNganhs).forEach(chuyenNganh => {
				let option = document.createElement('option');
	            option.value = chuyenNganh.id;
	            option.text = chuyenNganh.tenChuyenNganh; // Đặt nội dung của tùy chọn tùy thuộc vào yêu cầu của bạn
	            document.getElementById('chuyenNganhInsert').appendChild(option);
			});
			
		});
		
	document.getElementById('nganhHocInsert').addEventListener('change', function(){
		id = this.value;
		document.getElementById('chuyenNganhInsert').innerHTML = '';
		let option = document.createElement('option');
        option.value = null;
        option.text = 'Không có'; 
        document.getElementById('chuyenNganhInsert').appendChild(option);
		fetch('/admin/chuyenNganh/findChuyenNganhByNganhHoc?id='+id)
			.then(response => response.json())
			.then(chuyenNganhs => {
				Array.from(chuyenNganhs).forEach(chuyenNganh => {
					let option = document.createElement('option');
		            option.value = chuyenNganh.id;
		            option.text = chuyenNganh.tenChuyenNganh; // Đặt nội dung của tùy chọn tùy thuộc vào yêu cầu của bạn
		            document.getElementById('chuyenNganhInsert').appendChild(option);
				});
				
			});
	});
	
	
	/*chỉnh sửa*/
	
	id = document.getElementById('nganhHocEdit').options[0].value;
	document.getElementById('chuyenNganhEdit').innerHTML = '';
	let optionEdit = document.createElement('option');
    optionEdit.value = null;
    optionEdit.text = 'Không có'; 
    document.getElementById('chuyenNganhEdit').appendChild(optionEdit);
	fetch('/admin/chuyenNganh/findChuyenNganhByNganhHoc?id='+id)
		.then(response => response.json())
		.then(chuyenNganhs => {
			Array.from(chuyenNganhs).forEach(chuyenNganh => {
				let option = document.createElement('option');
	            option.value = chuyenNganh.id;
	            option.text = chuyenNganh.tenChuyenNganh; // Đặt nội dung của tùy chọn tùy thuộc vào yêu cầu của bạn
	            document.getElementById('chuyenNganhEdit').appendChild(option);
			});
			
		});
		
	document.getElementById('nganhHocEdit').addEventListener('change', function(){
		id = this.value;
		document.getElementById('chuyenNganhEdit').innerHTML = '';
		let option = document.createElement('option');
        option.value = null;
        option.text = 'Không có'; 
        document.getElementById('chuyenNganhEdit').appendChild(option);
		fetch('/admin/chuyenNganh/findChuyenNganhByNganhHoc?id='+id)
			.then(response => response.json())
			.then(chuyenNganhs => {
				Array.from(chuyenNganhs).forEach(chuyenNganh => {
					let option = document.createElement('option');
		            option.value = chuyenNganh.id;
		            option.text = chuyenNganh.tenChuyenNganh; // Đặt nội dung của tùy chọn tùy thuộc vào yêu cầu của bạn
		            document.getElementById('chuyenNganhEdit').appendChild(option);
				});
			});
	});
	
	Array.from(document.getElementsByClassName('editButton')).forEach(button => {
		button.addEventListener('click',  function(event){
			event.preventDefault();
			let href = event.target.getAttribute('href');
			
			fetch(href)
			.then(response => response.json())
			.then(async lopHoc => {
				document.getElementById('idEdit').value = lopHoc.id
				document.getElementById('tenLopHocEdit').value = lopHoc.tenLopHoc;
				document.getElementById('nganhHocEdit').value = lopHoc.nganhHoc.id;
				
				id = lopHoc.nganhHoc.id;
				document.getElementById('chuyenNganhEdit').innerHTML = '';
				let option = document.createElement('option');
			    option.value = null;
			    option.text = 'Không có'; 
			    document.getElementById('chuyenNganhEdit').appendChild(option);
				await fetch('/admin/chuyenNganh/findChuyenNganhByNganhHoc?id='+id)
					.then(response => response.json())
					.then(chuyenNganhs => {
						Array.from(chuyenNganhs).forEach(chuyenNganh => {
							let option = document.createElement('option');
				            option.value = chuyenNganh.id;
				            option.text = chuyenNganh.tenChuyenNganh; // Đặt nội dung của tùy chọn tùy thuộc vào yêu cầu của bạn
				            document.getElementById('chuyenNganhEdit').appendChild(option);
						});
					});
				
				if(lopHoc.chuyenNganh != null){
					document.getElementById('chuyenNganhEdit').value = lopHoc.chuyenNganh.id;
				}else{
					document.getElementById('chuyenNganhEdit').value = null;
				}

				
			});
		});
	});
});