
$(function(){
  $('#js-header').load('/common header');
  $('#js-footer').load('/common footer');
	
});

$(function(){
	document.title += ' - 電子機器管理システム';
});

/* header */
function dropDownMenu(){
	$(".has-child>a").off('click');//has-childクラスがついたaタグのonイベントをoff(無効)にし
	$(".has-child").removeClass('active');//activeクラスを削除
	$('.has-child').children('ul').css("display","");//スライドトグルで動作したdisplayも無効化にする
}

$(window).on('load',function(){
	dropDownMenu();
});

/* ページの上部に戻るボタン */

//スクロールした際の動きを関数でまとめる
function PageTopAnime() {
	var scroll = $(window).scrollTop();
	if (scroll >= 200){//上から200pxスクロールしたら
	$('#page-top a').css('display', 'flex');
		$('#page-top').removeClass('HideAnime');
		$('#page-top').addClass('DisplayAnime');
	}else{
		if($('#page-top').hasClass('DisplayAnime')){
			$('#page-top').removeClass('DisplayAnime');
			$('#page-top').addClass('HideAnime');
		}
	}
}

$(window).scroll(function () {
	PageTopAnime();
});

$(window).on('load', function () {
	$('#page-top a').css('display', 'none');
	PageTopAnime();
});

// #page-topをクリックした際の設定
$('#page-top a').click(function () {
    $('body,html').animate({
        scrollTop: 0
    }, 500);
    return false;//リンク自体の無効化
});
