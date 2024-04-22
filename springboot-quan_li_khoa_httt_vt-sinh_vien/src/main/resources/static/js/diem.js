document.addEventListener('DOMContentLoaded', function(){
	Array.from(document.getElementsByClassName('diem-ren-luyen')).forEach(tr => {
		if(tr.children.length == 3){
			let drl = parseInt(tr.querySelectorAll('td')[1].textContent.split('/')[0]);
			if(drl >=0 && drl <= 34){
				tr.querySelectorAll('td')[2].textContent = 'Kém';
			}else if(drl >= 35 && drl <= 49){
				tr.querySelectorAll('td')[2].textContent = 'Yếu';
			}else if(drl >= 50 && drl <=64){
				tr.querySelectorAll('td')[2].textContent = 'Trung bình';
			}else if(drl >=65 && drl <=79){
				tr.querySelectorAll('td')[2].textContent = 'Khá';
			}else if(drl >=80 && drl <=89){
				tr.querySelectorAll('td')[2].textContent = 'Tốt';
			}else {
				tr.querySelectorAll('td')[2].textContent = 'Xuất xắc';
			}
		}else {
			tr.querySelectorAll('td')[1].textContent = '';
		}
	});
	
	Array.from(document.getElementsByClassName('diem-he-chu')).forEach(td => {
		let diem = parseFloat(td.parentNode.querySelectorAll('td')[4].textContent);
		if(diem <4){
			td.textContent = 'F';
		}else if(diem >=4 && diem <= 5.4){
			td.textContent = 'D';
		}else if(diem >= 5.5 && diem <= 6.9){
			td.textContent = 'C';
		}else if(diem >= 7 && diem <= 8.4){
			td.textContent = 'B';
		}else {
			td.textContent = 'A';
		}
	});
	
	function xepLoaiTrungBinhHocKy(tr, diem){
		diem = parseFloat(diem);
		if(diem <4){
			tr.textContent = 'Kém';
		}else if(diem >=4 && diem <= 4.9){
			tr.textContent = 'Yếu';
		}else if(diem >=5 && diem <= 6.9){
			tr.textContent = 'Trung bình';
		}else if(diem >=7 && diem <= 7.9){
			tr.textContent = 'Khá';
		}else if(diem >=8 && diem <= 8.9){
			tr.textContent = 'Giỏi';
		}else if(diem >=9 && diem <= 10){
			tr.textContent = 'Xuất sắc';
		}else{
			tr.textContent = 'Chưa xác định';
		}
	}
	
	/*diểm tb hk1 n1*/
	function tinhDTB_HK1_N1(){
		let diemTB = 0;
		let tongTinChi =0;
		Array.from(document.getElementsByClassName('tr-diem-hk1-n1')).forEach(tr => {
			let diem = parseFloat(tr.querySelectorAll('td')[4].textContent);
			let tinChi = parseInt(tr.querySelector('input').value);
			diemTB += diem * tinChi;
			tongTinChi += tinChi;
			
			if(diem <4 ){
				tr.style.backgroundColor = '#ff7575';
				tr.style.color = 'white';
			}
		});
		document.getElementById('diemTB-hk1-n1').textContent = Math.round((diemTB / tongTinChi) * 100) / 100 ;
		xepLoaiTrungBinhHocKy(document.getElementById('loai-hk1-n1'), document.getElementById('diemTB-hk1-n1').textContent)
	}
	tinhDTB_HK1_N1();
	
	
	/*diểm tb hk2 n1*/
	function tinhDTB_HK2_N1(){
		let diemTB = 0;
		let tongTinChi =0;
		Array.from(document.getElementsByClassName('tr-diem-hk2-n1')).forEach(tr => {
			let diem = parseFloat(tr.querySelectorAll('td')[4].textContent);
			let tinChi = parseInt(tr.querySelector('input').value);
			diemTB += diem * tinChi;
			tongTinChi += tinChi;
			
			if(diem <4 ){
				tr.style.backgroundColor = '#ff7575';
				tr.style.color = 'white';
			}
		});
		document.getElementById('diemTB-hk2-n1').textContent = Math.round((diemTB / tongTinChi) * 100) / 100 ;
		xepLoaiTrungBinhHocKy(document.getElementById('loai-hk2-n1'), document.getElementById('diemTB-hk2-n1').textContent)
	}
	tinhDTB_HK2_N1();
	
	
	/*diểm tb hk1 n2*/
	function tinhDTB_HK1_N2(){
		let diemTB = 0;
		let tongTinChi =0;
		Array.from(document.getElementsByClassName('tr-diem-hk1-n2')).forEach(tr => {
			let diem = parseFloat(tr.querySelectorAll('td')[4].textContent);
			let tinChi = parseInt(tr.querySelector('input').value);
			diemTB += diem * tinChi;
			tongTinChi += tinChi;
			
			if(diem <4 ){
				tr.style.backgroundColor = '#ff7575';
				tr.style.color = 'white';
			}
		});
		document.getElementById('diemTB-hk1-n2').textContent = Math.round((diemTB / tongTinChi) * 100) / 100 ;
		xepLoaiTrungBinhHocKy(document.getElementById('loai-hk1-n2'), document.getElementById('diemTB-hk1-n2').textContent)
	}
	tinhDTB_HK1_N2();
	
	/*diểm tb hk2 n2*/
	function tinhDTB_HK2_N2(){
		let diemTB = 0;
		let tongTinChi =0;
		Array.from(document.getElementsByClassName('tr-diem-hk2-n2')).forEach(tr => {
			let diem = parseFloat(tr.querySelectorAll('td')[4].textContent);
			let tinChi = parseInt(tr.querySelector('input').value);
			diemTB += diem * tinChi;
			tongTinChi += tinChi;
			
			if(diem <4 ){
				tr.style.backgroundColor = '#ff7575';
				tr.style.color = 'white';
			}
		});
		document.getElementById('diemTB-hk2-n2').textContent = Math.round((diemTB / tongTinChi) * 100) / 100 ;
		xepLoaiTrungBinhHocKy(document.getElementById('loai-hk2-n2'), document.getElementById('diemTB-hk2-n2').textContent)
	}
	tinhDTB_HK2_N2();
	
	/*diểm tb hk1 n3*/
	function tinhDTB_HK1_N3(){
		let diemTB = 0;
		let tongTinChi =0;
		Array.from(document.getElementsByClassName('tr-diem-hk1-n3')).forEach(tr => {
			let diem = parseFloat(tr.querySelectorAll('td')[4].textContent);
			let tinChi = parseInt(tr.querySelector('input').value);
			diemTB += diem * tinChi;
			tongTinChi += tinChi;
			
			if(diem <4 ){
				tr.style.backgroundColor = '#ff7575';
				tr.style.color = 'white';
			}
		});
		document.getElementById('diemTB-hk1-n3').textContent = Math.round((diemTB / tongTinChi) * 100) / 100 ;
		xepLoaiTrungBinhHocKy(document.getElementById('loai-hk1-n3'), document.getElementById('diemTB-hk1-n3').textContent)
	}
	tinhDTB_HK1_N3();
	
	/*diểm tb hk2 n3*/
	function tinhDTB_HK2_N3(){
		let diemTB = 0;
		let tongTinChi =0;
		Array.from(document.getElementsByClassName('tr-diem-hk2-n3')).forEach(tr => {
			let diem = parseFloat(tr.querySelectorAll('td')[4].textContent);
			let tinChi = parseInt(tr.querySelector('input').value);
			diemTB += diem * tinChi;
			tongTinChi += tinChi;
			
			if(diem <4 ){
				tr.style.backgroundColor = '#ff7575';
				tr.style.color = 'white';
			}
		});
		document.getElementById('diemTB-hk2-n3').textContent = Math.round((diemTB / tongTinChi) * 100) / 100 ;
		xepLoaiTrungBinhHocKy(document.getElementById('loai-hk2-n3'), document.getElementById('diemTB-hk2-n3').textContent)
	}
	tinhDTB_HK2_N3();
	
	
	/*diểm tb hk1 n4*/
	function tinhDTB_HK1_N4(){
		let diemTB = 0;
		let tongTinChi =0;
		Array.from(document.getElementsByClassName('tr-diem-hk1-n4')).forEach(tr => {
			let diem = parseFloat(tr.querySelectorAll('td')[4].textContent);
			let tinChi = parseInt(tr.querySelector('input').value);
			diemTB += diem * tinChi;
			tongTinChi += tinChi;
			
			if(diem <4 ){
				tr.style.backgroundColor = '#ff7575';
				tr.style.color = 'white';
			}
		});
		document.getElementById('diemTB-hk1-n4').textContent = Math.round((diemTB / tongTinChi) * 100) / 100 ;
		xepLoaiTrungBinhHocKy(document.getElementById('loai-hk1-n4'), document.getElementById('diemTB-hk1-n4').textContent)
	}
	tinhDTB_HK1_N4();
	
	
	/*diểm tb hk2 n4*/
	function tinhDTB_HK2_N4(){
		let diemTB = 0;
		let tongTinChi =0;
		Array.from(document.getElementsByClassName('tr-diem-hk2-n4')).forEach(tr => {
			let diem = parseFloat(tr.querySelectorAll('td')[4].textContent);
			let tinChi = parseInt(tr.querySelector('input').value);
			diemTB += diem * tinChi;
			tongTinChi += tinChi;
			
			if(diem <4 ){
				tr.style.backgroundColor = '#ff7575';
				tr.style.color = 'white';
			}
		});
		document.getElementById('diemTB-hk2-n4').textContent = Math.round((diemTB / tongTinChi) * 100) / 100 ;
		xepLoaiTrungBinhHocKy(document.getElementById('loai-hk2-n4'), document.getElementById('diemTB-hk2-n4').textContent)
	}
	tinhDTB_HK2_N4();
	
	
});