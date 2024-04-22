document.addEventListener('DOMContentLoaded', function(){
	
	document.getElementById('submitId').addEventListener('click', function(event){
		event.preventDefault();
		
		let mssvList =[];
		let diemList =[];
		let hocKiList =[];
		let namHocList =[];
		Array.from(document.getElementById('dataTable').querySelectorAll('tbody tr')).forEach(tr => {
			mssvList.push(tr.querySelectorAll('td')[1].textContent);
			let diem = tr.querySelectorAll('td')[4].querySelector('input').value;
			if(diem ==''){
				diemList.push(-1);
			}else{
				diemList.push(diem);
			}
			
			let hocKi =  tr.querySelectorAll('td')[6].querySelector('input').value;
			if(hocKi ==''){
				hocKiList.push(-1);
			}else{
				hocKiList.push(hocKi);
			}
			
			let namHoc =  tr.querySelectorAll('td')[5].querySelector('input').value;
			if(namHoc ==''){
				namHocList.push(-1);
			}else{
				namHocList.push(namHoc);
			}
		});
		
		document.getElementById('sinhVienList').value = mssvList;
		document.getElementById('diemList').value = diemList;
		document.getElementById('hocKiList').value = hocKiList;
		document.getElementById('namHocList').value = namHocList;
		
		document.getElementById('formId').submit();
	});
});