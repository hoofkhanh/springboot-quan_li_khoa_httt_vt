document.addEventListener('DOMContentLoaded', function(){
	
	document.getElementById('submitId').addEventListener('click', function(event){
		event.preventDefault();
		
		let mssvList =[];
		let diemThuongXuyenList =[];
		let diemThiList =[];
		Array.from(document.getElementById('dataTable').querySelectorAll('tbody tr')).forEach(tr => {
			mssvList.push(tr.querySelector('td').textContent);
			let diemThuongXuyen = tr.querySelectorAll('td')[2].querySelector('input').value;
			if(diemThuongXuyen ==''){
				diemThuongXuyenList.push(-1);
			}else{
				diemThuongXuyenList.push(diemThuongXuyen);
			}
			
			
			let diemThi = tr.querySelectorAll('td')[3].querySelector('input').value;
			if(diemThi ==''){
				diemThiList.push(-1);
			}else{
				diemThiList.push(diemThi);
			}
		});
		
		document.getElementById('sinhVienList').value = mssvList;
		document.getElementById('diemThuongXuyenList').value = diemThuongXuyenList;
		document.getElementById('diemThiList').value = diemThiList;
		
		document.getElementById('formId').submit();
	});
});