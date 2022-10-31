
/*
 *	利用者情報編集画面のオプション表示
 */
 
 
 $(document).on('change', 'input[id="js-retirement"]', function(){
var selected = $('input[id="js-retirement"]:checked').val(); 
	if(selected=="1") {
		$('#js-option').removeClass("hidden");
	}else {
		$('#js-option').addClass("hidden");
	}
});

$(function(){
var selected = $('input[id="js-retirement"]:checked').val(); 
	if(selected=="1") {
		$('#js-option').removeClass("hidden");
	}else {
		$('#js-option').addClass("hidden");
	}
});