document.addEventListener('DOMContentLoaded', function(){
	
	Array.from(document.getElementsByClassName('editButton')).forEach(button => {
		button.addEventListener('click', function(event){
			let href = event.target.getAttribute('href');
			
			fetch(href)
				.then(response => response.json())
				.then( deTaiNghienCuu => {
					console.log(deTaiNghienCuu)
					document.getElementById('idEdit').value = deTaiNghienCuu.id;
					document.getElementById('tenDeTaiEdit').value = deTaiNghienCuu.tenDeTai;
					document.getElementById('namEdit').value = deTaiNghienCuu.nam;
					document.getElementById('allTenCacGiangVienEdit').value = '';
					
					Array.from(deTaiNghienCuu.tenCacGiangVien).forEach( async(tenGiangVien, index) => {
						fetch('/admin/giangVien/findByTenGiangVien?tenGiangVien='+tenGiangVien)
							.then(response => response.json())
							.then(giangVien => {
								document.getElementById('allTenCacGiangVienEdit').value +=  giangVien.emailTruong;
								if(index != deTaiNghienCuu.tenCacGiangVien.length -1){
									document.getElementById('allTenCacGiangVienEdit').value += ', ';
								}
							});
					});
					
				});
		});
	});
	
	
});