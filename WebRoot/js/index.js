'use strict';
/**
 * index.js
 * @authors Your Name (you@example.org)
 * @date    2017-06-20 11:27:49
 * @version $Id$
 */

 //hover

var $detial_li = $(".detial li");

var imgSrc,imgSrc1,str,index;

$("#typeMenu").on("mouseover mouseout","li",function(event){

	if(event.type == "mouseover"){

		index = $(this).index();

		imgSrc = $(this).find("img")[0].src;
	
	//	str = imgSrc.substring(imgSrc.length-10, imgSrc.length-8);

		imgSrc1 = imgSrc.replace('img/1','img/2');

		$(this).find("img").attr("src",imgSrc1);

		// if(index == 2){
		// 	$(this).find(".detial").css("left",(index-1)*192+25).show();

		// 	$(this).find(".detial").children('span').css("left",(index-1)*192+50);

		// }else if(index == 3){
		// 	$(this).find(".detial").css("left",((index-2)*192)+25).show();

		// 	$(this).find(".detial").children('span').css("left",(index-1)*192+50);

		// }else if(index == 4){
		// 	$(this).find(".detial").css("left",((index-2)*192)+25).show();

		// 	$(this).find(".detial").children('span').css("left",(index-2)*192+50);

		// }else{

		// 	$(this).find(".detial").css("left",(index*192)+25).show();
		// }
	}else if(event.type == "mouseout"){

		$(this).find("img").attr("src",imgSrc);

		// $(this).find(".detial").hide();
	}
})




$("#typeMenu").on("click","li figure",function(){
	if($(".sign-in>a").text() == "登录"){
		layer.open({
			icon: 5,
			// closeBtn:2,
			content: '请先登录！',
			yes:function(index,layero){
				window.location.href="login.html";
				layer.close(index);
			}
		});  
	}else{
		var index = $(this).parent("li").index();
		switch (index) {
			case 0:window.location.href="view/view.html"
			break;

			case 1:window.location.href="service.html"
			break;

			case 2:window.location.href="view/view2.html"
			break;

			case 3:window.location.href="own.html"
			break;

			case 4:window.location.href="admin/index.html"
			break;

			default:window.location.href="index.html"
			break;
		}
	}
})

$(".tips>span").click(function(){
	$(".tips").fadeOut();
})