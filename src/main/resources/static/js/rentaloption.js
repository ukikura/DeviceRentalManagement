'use strict';

/*
 *	編集画面のオプション表示
 */


$(function(){
	getDeviceNameList();
	if($('#js-rentaloption-deviceName').children('option').length==0){
	}
	
	let loc = location.search,
	param1 = loc.substring(1,loc.lastIndexOf("&"));
	/*param2 = '"'+loc.substring(loc.lastIndexOf("&")+1)+'"';*/
	if(param1!=""){
	$('select[id="js-type"]').val(param1);
		
	}
	
});

$(document).on('change', '.js-devicetype', function() {
	getDeviceNameList();
});



function getDeviceNameList() {
	const type = $('select[id="js-type"]').val();

	//typeに一致する機器一覧が欲しい
	$.ajax({
		url: "/getDeviceNameList?type=" + type,
		dataType: "text",
		type: "GET"
		// Ajaxが正常終了した場合
	}).done(function(data, textStatus, jqXHR) {
		// 該当するデータが無かった場合
		if (!data) {
			return;
		}
		// 該当するデータがあった場合は、取得したUserDataオブジェクトの内容を
		// 画面に表示する。その際、名前・性別・メモはデコードする
		const DeviceNameList = JSON.parse(data);
		setOption(DeviceNameList);
		// Ajaxが異常終了した場合
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("エラーが発生し、データが取得できませんでした");
	});

}

function setOption(value) {
	//option初期化
	$('#js-rentaloption-deviceName').children().remove();
	// selectタグを取得する
	let select = document.getElementById("js-rentaloption-deviceName");
	for (let i = 0; i < value.length; i++) {
		// optionタグを作成する
		let option = document.createElement("option");
		option.text = value[i];
		option.value = value[i];
		// selectタグの子要素にoptionタグを追加する
		select.appendChild(option);
	}
}