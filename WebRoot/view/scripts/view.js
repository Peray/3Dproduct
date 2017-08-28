/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-07-26 12:04:06
 * @version $Id$
 */

 

	 function iconshow(e){
	 	$(".cwidth").eq(e).children(".cesium-show").toggle()
	 }

	 var $tabli = $(".tab li");
	 var $tabC = $(".tabcontent div");
	 $tabli.click(function(event){
	 	var index = $(this).index();
	 	
	 	if(event.target.className == 'e'){
	 		$(this).removeClass('e');
	 		$tabC.eq(index).animate({width: '0'},800);
	 		$tabC.delay(800).hide();
	 	}else{
	 		$(this).addClass('e').siblings().removeClass('e');
	 		$tabC.eq(index).show().animate({width: '300px'},800).siblings().animate({width: '0'},800);
	 	}

	 })

	 var tabr = true;
	 $(".tabr li").click(function(){
	 	if(tabr){
	 		$(this).addClass('e')
	 		$(".tabcontentr").addClass('ert');
	 	}else{
	 		$(this).removeClass('e');
	 		$(".tabcontentr").removeClass('ert');
	 	}
	 	tabr = !tabr;
	 })
	  $(".tabl li").click(function(){
	 	if(tabr){
	 		$(this).addClass('e')
	 		$(".tabcontentl").addClass('ert');
	 	}else{
	 		$(this).removeClass('e');
	 		$(".tabcontentl").removeClass('ert');
	 	}
	 	tabr = !tabr;
	 })

	 var $list = $("#list>ul>li");
	 $list.click(function(){
	 	var index = $(this).index();
	 	$("#HelpContainerBG").show();
	 	showdetial(index);
	 })

	 function showdetial(index){
	 	$("#listContainer").addClass('in')
	 }

	 $(document).click(function(e){
	 	if(e.target.id == "HelpContainerBG"){
	 		$("#HelpContainerBG").hide();
	 		$("#listContainer").removeClass('in');
	 		$("#Import").removeClass('in');
	 	}
	 })
	function importGeojson(){
		$("#HelpContainerBG").hide();
	 	$("#listContainer").removeClass('in');
		$("#Import").removeClass('in');
	}
	function importGeojson1(){
		$("#HelpContainerBG").hide();
	 	$("#listContainer").removeClass('in');
		$("#Import1").removeClass('in');
	}
	
	function exportGeojson(){
		$("#HelpContainerBG").hide();
	 	$("#listContainer").removeClass('in');
		$("#export").removeClass('in');
	}


	var input = document.querySelector('#inputFile');		
	var input2 = $('#inputFile2');	
	input.addEventListener('change', function(e){
		loadGeojsonFile(e.target.files[0]);
		// var file =$("#inputFile");
		// file.after(file.clone().val(""));
		// file.remove();
	});
	input2.on('change', function(e){
		loadGeojsonFile(e.target.files[0]);
		// var file =$("#inputFile");
		// file.after(file.clone().val(""));
		// file.remove();
	});

	//导入
	function improt(i){
		$("#HelpContainerBG").show();
		$("#Import").addClass('in');

		if(i == true){
			$(".Input2d").hide();
			$(".Input3d").show();
		}else{
			$(".Input3d").hide();
			$(".Input2d").show();
		}
		exportT=i;
		debugger;
	}

	function exports(){
		$("#HelpContainerBG").show();
		$("#export").addClass('in');
	}


	function styleimport(i){
		$("#HelpContainerBG").show();
		$("#Import1").addClass('in');
	}



	if($list.length>10){
		$("#list>span.down").show();
	}else{
		$("#list>span.down").hide();
	}

	
	
	function detailsshow(){
		$("#collapse").click();
	}

	














		//滚轮事件 
		window.onload = function(){ 
			//使div可以跟随鼠标滚轮滚动来改变高度 
			//滚轮向下滚，div变高 滚轮向上滚 div变短 
			//获取box1 
			var box1 = document.getElementById("list_ul"); 
			var list = document.getElementById("list"); 
			/* 
			 * onmousewheel 
			 * - 鼠标滚轮滚动的事件，但是该事件火狐浏览器并不支持 
			 * - 在火狐中需要使用DOMMouseScroll，这个事件只能通过addEventListener()来绑定 
			 */ 
			//为box1绑定一个鼠标滚轮滚动的事件 
			box1.onmousewheel = function(event){ 
				event = event || window.event; 
				
		  	//判断滚轮滚动的方向 
			/* 
			 * wheelDelta 
			 * - 事件对象中的属性，可以用来判断鼠标滚轮滚动的方向 
			 * - 向下滚 -120 向上滚 +120 
			 * - 该属性的值并不重要，重要的是值的符号，需要通过符号来判断滚动的方向 
			 * - 但是该属性火狐浏览器并不支持 
			 */ 
		  	//alert(event.wheelDelta); 

			/* 
			 * 火狐中通过detail来判断方向 
			 * - 向上滚 -3  向下滚 +3 
			 */ 
			//alert(event.detail); 
			var box1h = box1.offsetHeight;
			var listh = list.offsetHeight;
			var n = box1h-listh;
			var absbox1 = Math.abs(box1.offsetTop);

			if(event.wheelDelta < 0 || event.detail > 0){ 
				//向下滚 
				//减少box1.offsetTop
				if(absbox1 <= n){
					box1.style.marginTop = box1.offsetTop - 20 + "px"; 
					$("#list>span.down").hide();
					$("#list>span.up").show();
				}
			}else{ 
				//向上滚 
				//增加box1.offsetTop 
				if(box1.offsetTop < 0){
					box1.style.marginTop = box1.offsetTop + 20 + "px"; 
				}else{
					$("#list>span.up").hide();
					$("#list>span.down").show();
				}
			} 
			
			/* 
			 * 使用addEventListener()绑定的事件，不能通过return false来取消默认行为 
			 * 需要调用事件对象 preventDefault()方法来取消默认行为 
			 * 但是在IE8中没有该方法 
			 */ 
			 event.preventDefault &&　event.preventDefault(); 

			//当页面中有滚动条时，由于滚轮滚动的默认行为会导致页面整体下移 
			//取消默认行为 
			return false; 
		}; 

		bind(box1 , "DOMMouseScroll" , box1.onmousewheel); 

		//点击事件
		$(".down").click(function(){
			var box1h = box1.offsetHeight;
			var listh = list.offsetHeight;
			var n = box1h-listh;
			var absbox1 = Math.abs(box1.offsetTop);
			if(absbox1 < n){
				box1.style.marginTop = box1.offsetTop - 20 + "px"; 
			}else if(absbox1-3 == n){
				$("#list>span.down").hide();
				$("#list>span.up").show();
			}
		})

		$(".up").click(function(){
			if(box1.offsetTop < 0){
				box1.style.marginTop = box1.offsetTop + 20 + "px"; 
			}else{
				$("#list>span.up").hide();
				$("#list>span.down").show();
			}
		})
	}; 

	function bind(obj , eventStr , callback){ 
		if(obj.addEventListener){ 
		  //如果是正常浏览器 
		  obj.addEventListener(eventStr , callback , false); 
		}else{ 
		  //IE8 
		  obj.attachEvent("on"+eventStr , function(){ 
		  	callback.call(obj); 
		  }); 
		} 
	} 

	var Rscreenw = $(window).width()/2;
	var Rscreenh = $(window).height();
	

	// $("#infobox").on("click","span",function(){
		// if($(this).attr("class") == "Spscreen"){
			// if($(this).children("img").hasClass('Ldown')){
				// $(this).parents("#infobox").removeClass("collapsed").addClass("uncollapsed").animate({'width': Rscreenw,'height': '100%','bottom':'0'},1000);
				// $("#cesiumContainer").animate({'width': Rscreenw},1000);
				// $(this).children("img").removeClass('Ldown').addClass('Lup');
				// $(".caf-toggle-flow").hide();
				// doubleCompareGlobe = !doubleCompareGlobe;
			// }else{
				// $(this).parents("#infobox").removeClass("uncollapsed").addClass("collapsed").animate({'width': '400px','height': '400px','bottom':'198px'},1000);
				// $("#cesiumContainer").animate({'width': '100%'},1000);
				// $(this).children("img").removeClass('Lup').addClass('Ldown');
				// $(".caf-toggle-flow").show();
				// doubleCompareGlobe = !doubleCompareGlobe;
			// }
			
		// }else if($(this).attr("class") == "threeD"){
			// // if($(this).text()=="2D"){
				// // $(this).parents("#infobox").removeClass("collapsed").addClass("uncollapsed").animate({'width': '100%','height': '100%','bottom':'0'},1000);
				// // $("#cesiumContainer").animate({'width': '400px','height':'400px','bottom':'0',"z-index":"100001"},1000);
				// // $(this).text("3D").attr("onclick","to3D()");
				// // $(".caf-toggle-flow").hide();
			// // }else{
				// // $(this).parents("#infobox").removeClass("uncollapsed").addClass("collapsed").animate({'width': '400px','height': '400px','bottom':'198px'},1000);
				// // $("#cesiumContainer").animate({'width': '100%','height':'100%',"z-index":"0"},1000);
				// // $(this).text("2D").attr("onclick","to2D()");
				// // $(".caf-toggle-flow").show();
			// // }
		// }
	// })


//三维视图加入标注
