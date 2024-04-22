document.addEventListener('DOMContentLoaded', function(){
	
	fetch('/admin/tinhDoanhThu')
		.then(response => response.json())
		.then(doanhThuList => {
			var data = {
			labels: ['N1 - HK1', 'N1 - HK2', 'N2 - HK1', 'N2 - HK2', 'N3 - HK1', 'N3 - HK2', 'N4 - HK1', 'N4 - HK2'],
				datasets: [{
					label: 'Doanh thu năm hiện tại',
					data: [],
					backgroundColor: 'rgba(255, 165, 0, 0.5)', // Màu nền
					borderColor: 'rgba(75, 192, 192, 1)', // Màu đường viền
					borderWidth: 1 // Độ rộng đường viền
				}]
			};
			
			
	        
	        Array.from(doanhThuList).forEach(doanhThu => {
				if(doanhThu.namHocCuaSinhVien == 1 && doanhThu.hocKi ==1){
					data.datasets[0].data[0] = doanhThu.tien;
				}else if(doanhThu.namHocCuaSinhVien == 1 && doanhThu.hocKi ==2){
					data.datasets[0].data[1] = doanhThu.tien;
				}else if(doanhThu.namHocCuaSinhVien == 2 && doanhThu.hocKi ==1){
					data.datasets[0].data[2] = doanhThu.tien;
				}else if(doanhThu.namHocCuaSinhVien == 2 && doanhThu.hocKi ==2){
					data.datasets[0].data[3] = doanhThu.tien;
				}else if(doanhThu.namHocCuaSinhVien == 3 && doanhThu.hocKi ==1){
					data.datasets[0].data[4] = doanhThu.tien;
				}else if(doanhThu.namHocCuaSinhVien == 3 && doanhThu.hocKi ==2){
					data.datasets[0].data[5] = doanhThu.tien;
				}else if(doanhThu.namHocCuaSinhVien == 4 && doanhThu.hocKi ==1){
					data.datasets[0].data[6] = doanhThu.tien;
				}else if(doanhThu.namHocCuaSinhVien == 4 && doanhThu.hocKi ==2){
					data.datasets[0].data[7] = doanhThu.tien;
				}
			});
			
			var ctx = document.getElementById('myChart').getContext('2d');
			
			var myChart = new Chart(ctx, {
				type: 'bar', // Loại biểu đồ (có thể thay đổi thành 'line', 'pie',...)
				data: data, // Dữ liệu đã chuẩn bị
				options: {
					scales: {
						y: {
							beginAtZero: true
						}
					}
				}
			});
			
		});
});