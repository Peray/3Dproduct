'use strict'
/**
 * 
 * @authors peray (you@example.org)
 * @date    2017-06-29 11:28:33
 * @version $Id$
 */
$(function() {
	//文件上传
	$('#afileToUpload').on('change', function() {
		var fileNames = '';
		$.each(this.files, function() {
			fileNames += '<span class="am-badge">' + this.name + '</span>';
		});
		$('#file-list').html(fileNames);
	});
	//表单验证
	var parrent = /[~!@#$%\^\+\*&\\\/\?\|:\{}()';="]/;
	var $form = $('#serviceForm');
	var $tooltip = $('<div id="vld-tooltip"></div>');
	$tooltip.appendTo(document.body);
	$form.validator();
	$form.on('keyup','.am-form-group input[type="text"],textarea', function() {
		var $this = $(this);
		var offset = $this.offset();
		if(!$this.is('#afileToUpload')){
			if(parrent.test($this.val())){
				$tooltip.text("不允许输入特殊字符").show().css({
					left: offset.left + 10,
					top: offset.top + $(this).outerHeight()-5
				});
				setTimeout($this.val(""),2000);
			}else {
				$tooltip.hide();
			}
		}
	});

	$("#afileToUpload").change(function(){
		var $this = $(this);
		var offset = $this.offset();
		var filepath=$(this).val();
    		//获得上传文件名
    		var fileArr=filepath.split("\\");
    		var fileTArr=fileArr[fileArr.length-1].toLowerCase().split(".");
    		var filetype=fileTArr[fileTArr.length-1];
   		 //切割出后缀文件名
   		 if(filetype != "geojson"){
        		$(this).val("");
        		$tooltip.text("只允许上传geojson格式的文件").show().css({
				left: offset.left + 10,
				top: offset.top + $(this).outerHeight()-5
			});
    		}else{
        		$tooltip.hide();
	    		 //创建和初始化地图
    		}
	})
});

function showFwlx(){
	var fwlx = $('#fwlx').val();
	if(fwlx == 'Geojson'){
		document.getElementById("Geojsondiv").style.display="";//显示 
		$('#WMSdiv').hide();
		$('#doc-ipt-num-1').val("");
		$('.am-map').hide();
	}
	if(fwlx == 'ArcGisServer'){
		document.getElementById("WMSdiv").style.display="";//显示 
		$('#Geojsondiv').hide();
		$('#doc-ipt-num-1').val("");
		$('.am-map').hide();
		 $.ajax({
			type:'post',
			url: 'glAdmin!searchNumber.action',
			dataType: 'json',
			success:function(ret){
				ret = eval(ret);
				var d = new Date();
				var month = d.getMonth()+1;
				var day = d.getDate();
				var str = d.getFullYear() + (month<10 ? '0' : '') + month +   (day<10 ? '0' : '') + day;
				var number = parseInt(ret.num)+1;
				var num = "";
				for(var i=0; i< 6; i++){
					num += Math.floor(Math.random()*10);
				}
				$('#doc-ipt-num-1').val("wms"+str+number);
				$('#imgName').val("wms"+str+number+"_"+num);
			}
		})
	}
}