
/* 詳細画面のボタン表示 */

$(function(){
	if(document.getElementById('js-status').textContent=="故障中"){
		$('#js-rental-button').css('display', 'none');
	}else if(document.getElementById('js-status').textContent=="貸出中"){
		$('#js-rental-button').css('display', 'none');
		$('#js-edit-button').css('display', 'none');
		$('.delete-link').css('display', 'none');
		$('#js-button-field').css('display', 'block');
	}
});