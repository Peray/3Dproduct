
var userId;
var userLevel;
function getSession(){
	$.ajax({
		type:'post',
		url: 'session!execute.action',
		dataType: 'json',
//		data: { },
		success:function(ret){
			ret = eval(ret);
			var div="";
			var head="";
			var menu="";
			var input = "";
			if(ret.YN == 1){
				userId = ret.user.userId;
				userLevel = ret.user.level;
				if(window.location.pathname == '/3DProduc/index.html'){
					div += '<img src="img/zc_13.png"><a href="#" onmouseover="display()" onmouseout="disappear()" class="text">欢迎'+ret.user.logName+'</a><span class="space">|</span><a href="javascript:logout('+ret.user.userId+');" class="text">退出</a>';
				}else{
					div += '<img src="img/tx_03.png"><a href="#" onmouseover="display()" onmouseout="disappear()" class="text">欢迎'+ret.user.logName+'</a><span class="space">|</span><a href="javascript:logout('+ret.user.userId+');" class="text">退出</a>';
				}
				div +='<div id="box" onmouseover="display()" onmouseout="disappear()" style="display:none; width:100px; height:50px;line-height: 175%;padding:12px; text-align:left; position:absolute; z-index:100;margin-top:-17px; margin-left:-20px;"><button class="btn btn-primary btn-xs" onclick="passwordPage();">密码修改</button></div>';
				if(window.location.pathname == '/3DProduc/service.html'){
					head += '<li><img src="img/6_03.png" alt="首页"><a href="index.html">首页</a></li>'+
							'<li><img src="img/1_03.png" alt="三维展示"><a href="view/view.html">三维展示</a></li>'+
							 '<li class="checka"><img src="img/02_05.png" alt="服务资源"><a class="blue" href="service.html">服务资源</a></li>';
							 if(ret.user.level == "2" || ret.user.level == "3"){
							 	 head += '<li><img src="img/3_07.png" alt="典型应用"><a href="view/view2.html">典型应用</a></li><li><img src="img/4_09.png" alt="我的资源"><a href="own.html">我的资源</a></li>';
							 }
				}else if(window.location.pathname == '/3DProduc/own.html'){
					head += '<li><img src="img/6_03.png" alt="首页"><a href="index.html">首页</a></li>'+
							'<li><img src="img/1_03.png" alt="三维展示"><a href="view/view.html">三维展示</a></li>'+
							 '<li><img src="img/2_05.png" alt="服务资源"><a href="service.html">服务资源</a></li>';
							 if(ret.user.level == "2" || ret.user.level == "3"){
							 	 head += '<li><img src="img/3_07.png" alt="典型应用"><a href="view/view2.html">典型应用</a></li><li class="checka"><img src="img/04_09.png" alt="我的资源"><a class="blue" href="own.html">我的资源</a></li>';
							 }
				}else if(window.location.pathname == '/3DProduc/password.html'){
					head += '<li><img src="img/6_03.png" alt="首页"><a href="index.html">首页</a></li>'+
							'<li><img src="img/1_03.png" alt="三维展示"><a href="view/view.html">三维展示</a></li>'+
							 '<li><img src="img/2_05.png" alt="服务资源"><a href="service.html">服务资源</a></li>';
							 if(ret.user.level == "2" || ret.user.level == "3"){
							 	 head += '<li><img src="img/3_07.png" alt="典型应用"><a href="view/view2.html">典型应用</a></li><li><img src="img/4_09.png" alt="我的资源"><a href="own.html">我的资源</a></li>';
							 }
				}
				if(ret.user.level == "3"){
				 	 head += '<li><img src="img/5_11.png" alt="运维管理"><a href="admin/index.html">运维管理</a></li>';
				 }
				 
				 if(ret.user.level == "1"){
				 	   menu += '<li><figure><img src="img/11_22.png" alt="三维展示" title="三维展示" /><figcaption>三维展示</figcaption></figure>	</li>'+
				 	 				'<li><figure><img src="img/12_25.png" alt="服务资源" title="服务资源" /><figcaption>服务资源</figcaption>	</figure></li>';
				  }else if(ret.user.level == "2"){
					   menu += '<li><figure><img src="img/11_22.png" alt="三维展示" title="三维展示" /><figcaption>三维展示</figcaption></figure>	</li>'+
				 	 				'<li><figure><img src="img/12_25.png" alt="服务资源" title="服务资源" /><figcaption>服务资源</figcaption>	</figure></li>'+
				 	 				'<li><figure><img src="img/13_25.png" alt="典型应用" title="典型应用" /><figcaption>典型应用</figcaption></figure></li>'+
				 	 				'<li><figure><img src="img/14_25.png" alt="我的资源" title="我的资源" /><figcaption>我的资源</figcaption></figure></li>';
				  }else if(ret.user.level == "3"){
					   menu += '<li><figure><img src="img/11_22.png" alt="三维展示" title="三维展示" /><figcaption>三维展示</figcaption></figure>	</li>'+
				 	 				'<li><figure><img src="img/12_25.png" alt="服务资源" title="服务资源" /><figcaption>服务资源</figcaption>	</figure></li>'+
				 	 				'<li><figure><img src="img/13_25.png" alt="典型应用" title="典型应用" /><figcaption>典型应用</figcaption></figure></li>'+
				 	 				'<li><figure><img src="img/14_25.png" alt="我的资源" title="我的资源" /><figcaption>我的资源</figcaption></figure></li>'+
				 	 				'<li><figure><img src="img/15_25.png" alt="运维管理" title="运维管理" /><figcaption>运维管理</figcaption></figure></li>';
				  }
			}else{
				div += '<img src="img/zc_13.png"><a href="login.html" class="text">登录</a>';
				menu += '<li><figure><img src="img/11_22.png" alt="三维展示" title="三维展示" /><figcaption>三维展示</figcaption></figure>	</li>'+
			 	 				'<li><figure><img src="img/12_25.png" alt="服务资源" title="服务资源" /><figcaption>服务资源</figcaption>	</figure></li>';
			}
			$(".sign-in").html(div);
			$("#typeMenu").html(menu);
			$("#headmenu").html(head);
			
			var index;
			var oldSrc = ['img/6_03.png','img/1_03.png','img/2_05.png','img/3_07.png','img/4_09.png','img/5_11.png'];
			var newSrc = ['img/06_03.png','img/01_03.png','img/02_05.png','img/03_07.png','img/04_09.png','img/05_11.png'];
			var locationB = ['index.html','view/view.html','service.html','view/view2.html','own.html','admin/index.html']
			$("#headmenu>li").mouseenter(function(){
				index = $(this).index();
				$(this).children("img").attr("src",newSrc[index])
			})
			$("#headmenu>li").mouseleave(function(){
				if(window.location.href.indexOf($(this).children("a").attr("href")) >0){
					return false
				}else{
					$(this).children("img").attr("src",oldSrc[index]);
				}
			})
			$("#headmenu>li").click(function(){
				parent.location.href = locationB[index];
			})
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	})
};

function logout(id){

	layer.confirm('确定退出？', {
		btn: ['确认', '离开'],
		btn2: function(index, layero){
			layer.close(index);
		}
	}, function(index){
		$.ajax({
			type:'post',
			url: 'logon!logout.action',
			dataType: 'json',
			data: {"userId":id },
			success:function(ret){
				ret = eval(ret);
				if(ret == 1){
					window.location.href='index.html';
				}
			},
			error: function(ret, ret1, ret2) {
				debugger;
			}
		})
	});
	
}

//关键字查询跳转方法
window.onload=function(){
	var otxt=document.getElementById("keys");
	otxt.oninput=function(){
		if(typeof(userId)  == "undefined"){
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
			if((/[\u4e00-\u9fa5]+/).test(otxt.value)){
				localStorage.setItem("key",otxt.value);
				location.href = "service.html";
			}
		}
	}
	var cpLock = false;
    $('#keys').on('compositionstart', function () {
        cpLock = true;
    });
    $('#keys').on('compositionend', function () {
        cpLock = false;
    });
    $('#keys').on('input', function () {
        if(typeof(userId)  == "undefined"){
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
			if (!cpLock) {
	            localStorage.setItem("key",otxt.value);
			    location.href = "service.html";
	        }
		}
    });
}
//密码修改div弹出层
function display(){
	$("#box").show();
}
function disappear(){
	$("#box").hide();
}
function passwordPage(){
	window.location.href="password.html";
}