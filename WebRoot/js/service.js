var imgNamePath = system.pturl;
$(window).load(function() {
	getdata(1,true);
	if(localStorage.getItem("key") != null){
		$('#key').val(localStorage.getItem("key"));
		$('#key').focus();
		localStorage.removeItem("key")
	}
});

$('.input-r').click(function() {
	searchData(1,true);
});
var name,val;
function categoryCx(k,v) {
	name = k;
	val = v;
	searchCategory(1,true);
}

var rp = 10;

//加载管理信息
function getdata(page,b){
	$.ajax({
		type:'post',
		url: 'gl!execute.action',
		dataType: 'json',
		data: { statr: page,rp:rp},
		success:function(ret){
			ret = eval(ret);
			var div = "";
			totalitems = ret.num;
			$("#searKey").html("共<font color='#E82F37' style='font-weight:bold;'>"+totalitems+"</font>条记录");
			if (totalitems == 0) {
				$(".listService").css("border-top","1px solid #ddd")
				div += '<span class="kong">没有查询到相关数据！</span>';
			}else{
				for (var i = 0; i < ret.list.length; i++) {
					var date = new Date(ret.list[i].fbTime.time);
							Y = date.getFullYear() + '-';
							M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
							D = date.getDate();
					div += '<figure class="clearfix"><span><img src="'+imgNamePath+"/"+ret.list[i].imgPath+'" alt="标签" title="标签" /><a onclick="detail('+ret.list[i].glId+','+i+')">点击查看</a></span><figcaption>'+
								'<p><strong>服务名称：</strong><span>'+ret.list[i].glName+'</span><span class="skim1" id="biaoji'+(i)+'"><img src="img/sc/dz1_17.png" alt="未定制" title="未定制" /><span>未定制</span></span></p>'+
								'<p><strong>描述：</strong><span>'+ret.list[i].content+'</span></p>'+
								'<p><strong>发布人：</strong><span>'+ret.list[i].fbPop+'</span></p>'+
								'<p><strong>发布时间：</strong><span>'+Y+M+D+'</span><span class="skim">'+ret.list[i].browse+'浏览</span></p>'+
								'</figcaption></figure>'
					myService(ret.list[i].glId,i);
				}
			}
			$(".listService").html(div);
			if(b){
				initPage(totalitems);
			}
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	})
};

//分页
function initPage(totalitems) {
	if (totalitems > 0) {
		$.jqPaginator('#pagination2', {
			totalPages: Math.ceil(totalitems/rp),
			visiblePages: 10,
			currentPage: 1,
			activeClass:'am-active',
			disableClass:'am-disabled',
			prev: '<li class="prev"><a href="javascript:;">«</a></li>',
			next: '<li class="next"><a href="javascript:;">»</a></li>',
			page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
			onPageChange:function(num,type){
			getdata(num,false);
			}
		});
	}
}

//根据关键字查询
function searchData(page,b){
	var key = $('#key').val();
	$.ajax({
	        type: "post",
	        url: "gl!searchKey.action", //url, //
	        data: {"statr": page,"info":key},
	        dataType: "json",
	        success: function (ret) {
	 			ret = eval(ret);
				var div = "";
				pageNum = ret.num;
				$("#searKey").html("关键字<font color='#E82F37' style='font-weight:bold;'>"+key+"</font>的内容，共检出<font color='#E82F37' style='font-weight:bold;'>"+pageNum+"</font>条记录");
				if (pageNum == 0) {
					$(".listService").css("border-top","1px solid #ddd")
					div += '<span class="kong">没有查询到相关数据！</span>';
				}else{
					for (var i = 0; i < ret.list.length; i++) {
						var date = new Date(ret.list[i].fbTime.time);
							Y = date.getFullYear() + '-';
							M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
							D = date.getDate();
							var nameRed = "<span style='color:#e80707;'>" + key + "</span>";
							dataNAME = ret.list[i].glName.replace(key, nameRed);
							dataCONTENT = ret.list[i].content.replace(key, nameRed);

						div += '<figure class="clearfix"><span><img src="'+imgNamePath+"/"+ret.list[i].imgPath+'" alt="标签" title="标签" /><a onclick="detail('+ret.list[i].glId+','+i+')">点击查看</a></span><figcaption>'+
									'<p><strong>服务名称：</strong><span>'+dataNAME+'</span><span class="skim1" id="biaoji'+(i)+'"><img src="img/sc/dz1_17.png" alt="未定制" title="未定制" /><span>未定制</span></span></p>'+
									'<p><strong>描述：</strong><span>'+dataCONTENT+'</span></p>'+
									'<p><strong>发布人：</strong><span>'+ret.list[i].fbPop+'</span></p>'+
									'<p><strong>发布时间：</strong><span>'+Y+M+D+'</span><span class="skim">'+ret.list[i].browse+'浏览</span></p>'+
									'</figcaption></figure>'
						myService(ret.list[i].glId,i);
					}
				}
				$(".listService").html(div);
				
				if(b){
					initPages(pageNum);
				}
			},
	        error: function (ret, ret1, ret2) {
	            debugger;
	        }
	   });
}

//分页
function initPages(totalitems) {
	if (totalitems > 0) {
		$.jqPaginator('#pagination2', {
			totalPages: Math.ceil(totalitems/rp),
			visiblePages: 10,
			currentPage: 1,
			activeClass:'am-active',
			disableClass:'am-disabled',
			prev: '<li class="prev"><a href="javascript:;">«</a></li>',
			next: '<li class="next"><a href="javascript:;">»</a></li>',
			page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
			onPageChange:function(num,type){
			searchData(num,false);
			}
		});
	}
}


$('aside').on('click', ".list-item li",function(){
	$(this).addClass('active').siblings("li").removeClass('active');
})




function xila(id){

	if($(".fh"+id).text() == "∨"){

		$(".fh"+id).siblings("ul").children('li').not(".show").show();

		$(".fh"+id).text("∧");

	}else if($(".fh"+id).text() == "∧"){

		$(".fh"+id).siblings("ul").children('li').not(".show").hide();

		$(".fh"+id).text("∨");
		
	}
}


//l列表查询
function searchCategory(page,b){
	$.ajax({
	        type: "post",
	        url: "gl!categorySearch.action",
	        data: {"statr": page,"type":name,"val":val},
	        dataType: "json",
	        success: function (ret) {
	 			ret = eval(ret);
				var div = "";
				pageNum = ret.num;
				$("#searKey").html("根据<font color='#E82F37' style='font-weight:bold;'>"+val+"</font>分类，共检出<font color='#E82F37' style='font-weight:bold;'>"+pageNum+"</font>条记录");
				if (pageNum == 0) {
					$(".listService").css("border-top","1px solid #ddd")
					div += '<span class="kong">没有查询到相关数据！</span>';
				}else{
					for (var i = 0; i < ret.list.length; i++) {
						var date = new Date(ret.list[i].fbTime.time);
								Y = date.getFullYear() + '-';
								M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
								D = date.getDate();
						div += '<figure class="clearfix"><span><img src="'+imgNamePath+"/"+ret.list[i].imgPath+'" alt="标签" title="标签" /><a onclick="detail('+ret.list[i].glId+','+i+')">点击查看</a></span><figcaption>'+
									'<p><strong>服务名称：</strong><span>'+ret.list[i].glName+'</span><span class="skim1" id="biaoji'+(i)+'"><img src="img/sc/dz1_17.png" alt="未定制" title="未定制" /><span>未定制</span></span></p>'+
									'<p><strong>描述：</strong><span>'+ret.list[i].content+'</span></p>'+
									'<p><strong>发布人：</strong><span>'+ret.list[i].fbPop+'</span></p>'+
									'<p><strong>发布时间：</strong><span>'+Y+M+D+'</span><span class="skim">'+ret.list[i].browse+'浏览</span></p>'+
									'</figcaption></figure>'
						myService(ret.list[i].glId,i);
					}
				}
				$(".listService").html(div);
				if(b){
					initPagec(pageNum);
				}
			},
	        error: function (ret, ret1, ret2) {
	            debugger;
	        }
	   });
}
function initPagec(totalitems) {
	if (totalitems > 0) {
		$.jqPaginator('#pagination2', {
			totalPages: Math.ceil(totalitems/rp),
			visiblePages: 10,
			currentPage: 1,
			activeClass:'am-active',
			disableClass:'am-disabled',
			prev: '<li class="prev"><a href="javascript:;">«</a></li>',
			next: '<li class="next"><a href="javascript:;">»</a></li>',
			page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
			onPageChange:function(num,type){
			searchCategory(num,false);
			}
		});
	}
}



//详细信息
var glNumber,glName,address,glId;
function detail(id,n){
	$.ajax({
	        type: "post",
	        url: "gl!detail.action", //url, //
	        data: {"glId":id},
	        dataType: "json",
	        success: function (ret) {
	 			ret = eval(ret);
				var div = "";
				if(ret.list.length > 0 &&　ret.metaList.length > 0){
					glNumber = ret.list[0].glNumber;
					glName = ret.list[0].glName;
					address = ret.list[0].address;
					glId = ret.list[0].glId;
	
					var date = new Date(ret.list[0].fbTime.time);
					Y = date.getFullYear() + '-';
					M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
					D = (date.getDate() < 10 ? '0'+( date.getDate()) :  date.getDate());

					div +=	`<form class="ckfw">
								<p>
									<label>服务编号</label>
									<input type="text" disabled value=${ret.list[0].glNumber} />
								</p>

								<p>
									<label>服务名称</label>
									<input type="text" disabled value=${ret.list[0].glName} />
								</p>

								<p>
									<label>服务发布时间</label>
									<input type="text" disabled value=${Y+M+D} />
								</p>

								<p>
									<label style="vertical-align:top;">描述</label>
									<textarea rows="3" cols="37" maxlength="37" disabled>${ret.list[0].content}</textarea>
									
								</p>

								<p>
									<label style="vertical-align:top;">方位描述</label>
									<textarea rows="3" cols="37" maxlength="37" disabled>${ret.metaList[0].fwContent}</textarea>
								</p>

								<p>
									<label style="font-weight:bold;">地理信息坐标</label>
								</p>

								<p>
									<label>最大X坐标</label>
									<input type="text" disabled value=${ret.metaList[0].zdx}>
								</p>

								<p>
									<label>最大Y坐标</label>
									<input type="text" disabled value=${ret.metaList[0].zdy} />
								</p>

								<p>
									<label>最小X坐标</label>
									<input type="text" disabled value=${ret.metaList[0].zxx} />
								</p>

								<p style="margin-bottom:10px;">
									<label>最小Y坐标</label>
									<input type="text" disabled value=${ret.metaList[0].zxy} />
								</p>

								<p>
									<label>服务类型</label>
									<input type="text" disabled value=${ret.metaList[0].fwlx} />
								</p>

								<p>
									<label>卫星类型</label>
									<input type="text" disabled value=${ret.metaList[0].wxlx} />
								</p>

								<p>
									<label>分辨率</label>
									<input type="text" disabled value=${ret.metaList[0].fbl} />
								</p>

								<p>
									<label>时相</label>
									<input type="text" disabled value=${ret.metaList[0].sx.substring(0, 10)} />
								</p>

								<p>
									<label>来源</label>
									<input type="text" disabled value=${ret.metaList[0].yxSource} />
								</p>

								<p>
									<label>原始影像采集单位</label>
									<input type="text" disabled value=${ret.metaList[0].yxcjdw} />
								</p>

								<p>
									<label>影像处理单位</label>
									<input type="text" disabled value=${ret.metaList[0].yxcldw} />
								</p>

								<p>
									<label>影像审定时间</label>
									<input type="text" disabled value=${ret.metaList[0].yxsdsj.substring(0, 10)} />
								</p>

								<p>
									<label>数据密集</label>
									<input type="text" disabled value=${ret.metaList[0].sjmj} />
								</p>

								<p>
									<label>知识产权归属</label>
									<input type="text" disabled value=${ret.metaList[0].cqgs} />
								</p>

								<p>
									<label>审核人</label>
									<input type="text" disabled value=${ret.metaList[0].shPop} />
								</p>

								<p>
									<label>审核时间</label>
									<input type="text" disabled value=${ret.metaList[0].shTime.substring(0, 10)} />
								</p>
							</form>
					
							<div class="btn-foot">`
								if(userLevel == "2" || userLevel == "3"){
									if($("#biaoji"+n).text()=="已定制"){
										div+='<button type="button" id="Customized" class="btn btn-primary" disabled>定制我的资源</button>';
									}else{
										div+='<button type="button" id="Customized" class="btn btn-primary">定制我的资源</button>';
									}
								}
							`</div>`
				}else{
					div +="数据异常！";
				}		
				$(".peray-body").html(div);
			},
	        error: function (ret, ret1, ret2) {
	            debugger;
	        }
	   });
	$("body").addClass("peray-open").css("padding-right","17px");
	$("#myPeray").show();
	var timer = setTimeout(function(){
		$("#myPeray").addClass("in");
		clearTimeout(timer);
	},0);
}


$(document).on('click',function(e){
	if(e.target.className == 'e'|| e.target.id == 'myPeray'){
		$("body").removeClass("peray-open").css("padding-right","0px");
		$("#myPeray").removeClass("in");
		var timer = setTimeout(function(){
			$("#myPeray").hide();
			clearTimeout(timer);
		},0);
	}
})



$(".peray-body").on("click","#Customized",function(){
	$.ajax({
 		url: 'myService!add.action',
 		type: 'POST',
 		dataType: 'json',
 		data: {
 			'serviceNumber':glNumber,
 			'serviceName':glName,
 			'address':address,
 			'parentId':glId
 		},
 		success:function(ret){
 			$("body").removeClass("peray-open").css("padding-right","0px");
 			$("#myPeray").removeClass("in");
 			var timer = setTimeout(function(){
 				$("#myPeray").hide();
 				clearTimeout(timer);
 			},0);
			layer.open({
				icon: 1,
				content: '定制成功！',
				closeBtn: false,
				yes:function(index,layero){
					layer.close(index);
					window.location.reload();
				}
			}); 
			// getdata(1,true);
 		},
 		error: function (ret, ret1, ret2) {
            debugger;
        }
 	});
})
 //定制我的资源
 // function Customized(id){
 	
 // }
 
 function myService(id,pnum){
 	$.ajax({
		type:'post',
		url: 'myService!execute.action',
		dataType: 'json',
		data: { userId: userId,parentId:id},
		success:function(ret){
			ret = eval(ret);
			if(ret.num == 1){
				$("#biaoji"+pnum).html('<img src="img/sc/dz2_19.png" alt="已定制" title="已定制" /><span>已定制</span>');
			}
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	})
 }
 
$(".listService").on("hover","figure span",function(e){
  	if(e.type == "mouseenter"){
  		$(this).children("a").stop().slideDown();
  	}else if(e.type == "mouseleave"){
  		$(this).children("a").stop().slideUp();
  	}
 })