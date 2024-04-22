document.addEventListener('DOMContentLoaded', function(){
	Array.from(document.getElementsByClassName('detail-container-row')).forEach(element => {
		element.addEventListener('click', function(){
			element.querySelector('.noi-dung').click();
		})
	});
});