
/*
 *	編集画面のオプション表示
 */
$(function(){

	if(type.value == "1") {
		$('.js-pc-option').removeClass("hidden");
	}else{
		$('.js-pc-option').addClass("hidden");
	}
	
});

$(document).on('change', '.js-devicetype', function(){

	if(type.value == "1") {
		$('.js-pc-option').removeClass("hidden");
	}else {
		$('.js-pc-option').addClass("hidden");
	}
	
});
