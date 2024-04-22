document.addEventListener('DOMContentLoaded', function(){
	let imageFileOfTrack = document.getElementById('imageFile');
	imageFileOfTrack.addEventListener('change', function(){	
		if (imageFileOfTrack.files && imageFileOfTrack.files[0]) {
		    let reader = new FileReader();
		    reader.onload = function (e) {
		      $('#hinhAnhCuaThongBao')
		        .attr('src', e.target.result)
		        .width(76)
		        .height(76)
		    };
		    reader.readAsDataURL(imageFileOfTrack.files[0]);
		  }
	});
	
	/*Edit*/
	let imageFileOfTrackEdit = document.getElementById('imageFileEdit');
	imageFileOfTrackEdit.addEventListener('change', function(){	
		if (imageFileOfTrackEdit.files && imageFileOfTrackEdit.files[0]) {
		    let reader = new FileReader();
		    reader.onload = function (e) {
		      $('#hinhAnhCuaThongBaoEdit')
		        .attr('src', e.target.result)
		        .width(76)
		        .height(76)
		    };
		    reader.readAsDataURL(imageFileOfTrackEdit.files[0]);
		  }
	});
	
	Array.from(document.getElementsByClassName('editButton')).forEach(button => {
		button.addEventListener('click', function(event){
			let href = event.target.getAttribute('href');
			
			fetch(href)
				.then(response => response.json())
				.then(thongBao => {
					document.getElementById('idEdit').value = thongBao.id;
					document.getElementById('hinhAnhCuaThongBaoEdit').src = 'data:image/jpeg;base64,' + thongBao.image;
					document.getElementById('noiDungEdit').value = thongBao.noiDung;
					document.getElementById('tieuDeEdit').value = thongBao.tieuDeThongBao;
					document.getElementById('moTaEdit').value = thongBao.moTa;
				});
		});
	});
	
	
});