getSession();
var userId;
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
			if(ret.YN == 1){
				userId = ret.user.userId;
				div += '<img src="../img/tx_03.png"><a href="#" onmouseover="display()" onmouseout="disappear()" class="text">欢迎'+ret.user.logName+'</a><span class="space">|</span><a href="javascript:logout('+ret.user.logName+');" class="text">退出</a>';
				div +='<div id="box" onmouseover="display()" onmouseout="disappear()" style="display:none; width:100px; height:50px;line-height: 175%;padding:12px; text-align:left; position:absolute; z-index:100;margin-top:-17px; margin-left:-20px;"><button class="btn btn-primary btn-xs" onclick="passwordPage();">密码修改</button></div>';
			}else{
				div += '<img src="img/zc_13.png"><a href="login.html" class="text">登录</a>';
			}
			$(".sign-in").html(div);
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	})
};

function logout(logname){
	layer.confirm('确定退出？', {
		btn: ['确认', '离开'],
		btn2: function(index, layero){
			layer.close(index);
		}
	}, function(index){
		$.ajax({
			type:'post',
			url: 'userAdmin!logout.action',
			dataType: 'json',
			data: {"logName":logname},
			success:function(ret){
				ret = eval(ret);
				if(ret == 1){
					parent.location.href='../index.html';
				}
			},
			error: function(ret, ret1, ret2) {
				debugger;
			}
		})
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
	parent.location.href="../password.html?id="+userId;
}

var index;
var oldSrc = ['../img/6_03.png','../img/1_03.png','../img/2_05.png','../img/3_07.png','../img/4_09.png','../img/5_11.png'];
var newSrc = ['../img/06_03.png','../img/01_03.png','../img/02_05.png','../img/03_07.png','../img/04_09.png','../img/05_11.png'];
var locationB = ['../index.html','../view/view.html','../service.html','../view/view2.html','../own.html','index.html']
$("#headmenu>li").mouseenter(function(){
	index = $(this).index();
	$(this).children("img").attr("src",newSrc[index])
})
$("#headmenu>li").mouseleave(function(){
	if(parent.location.href.indexOf($(this).children("a").attr("href")) >0){
		return false
	}else{
		$(this).children("img").attr("src",oldSrc[index]);
	}
})
$("#headmenu>li").click(function(){
	parent.location.href = locationB[index];
})