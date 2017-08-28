'use strict';
/**
 * 
 * @authors peray (you@example.org)
 * @date    2017-06-26 09:24:41
 * @version $Id$
 */


//表单验证

var $login = $("#login"),
	$register = $("#register");

$.validator.messages = {

	required: '输入不能为空.'

}

//登陆
$login.validate({
	errorPlacement: function(error, element) {
		$(element).closest("p").append(error);
	},
	errorElement: "span",
	rules: {
		logNames: {
			required: true
		},
		logPwds: {
			required: true
		},
		imageCode:{
			required: true,
			equalTo: "#checkCode"
		},
		agree: "required"
	},
	messages: {
		imageCode:{
			equalTo:"验证码输入不正确"
		}
	},

	submitHandler: function(form) {
		var logname = $('#logName').val();
		var logpwd = $('#logPwd').val();
		$.ajax({
	        type: "post",
	        url: "logon!logon.action", //url, //
	        data: {"logName":logname,"logPwd":logpwd},
	        dataType: "json",
	        success: function (ret) {
	 			ret = eval(ret);
	 			if(ret == "1"){
	 				layer.open({
	 					icon: 5,
	 					content: '登录账号不存在或已被注销！',
	 					yes:function(index,layero){
	 						location.href=location.href;
	 						layer.close(index);
	 					}
	 				});
	 			}if(ret == "2"){
	 				location.href="index.html"
	 			}
			},
	        error: function (ret, ret1, ret2) {
	            debugger;
	        }
		});
//		document.getElementById("login").submit();
	}
});

//注册
$register.validate({
	errorPlacement: function(error, element) {
		$(element).closest("p").append(error);
	},
	errorElement: "span",
	rules: {
		logNames: {
			required: true,
			rangelength: [4, 15]
		},
		logPwds: {
			required: true,
			rangelength: [6, 20]
		},
		checkpwd: {
			required: true,
			rangelength: [6, 20],
			equalTo: "#logPwds"
		},
		department: {
			required: true
		},
		cName:{
			required: true
		},
		email:{
			email:true
		},
		agree: "required"
	},

	messages: {
		logNames: {
			rangelength: "账号必须在4-15位之间！"
		},
		logPwds: {
			rangelength: "密码必须在6-20位之间！"
		},
		checkpwd: {
			rangelength: "密码必须在6-20位之间！",
			equalTo: "两次密码输入不一致！"
		},
		email:{
			email:"邮箱格式不正确"
		}
	},
	submitHandler: function(form) {
		var logname = $('#logNames').val();
		var logpwd = $('#logPwds').val();
		var department = $('#department').val();
		var cname = $('#cName').val();
		var email = $('#email').val();
		$.ajax({
	        type: "post",
	        url: "logon!add.action", //url, //
	        data: {"logName":logname,"logPwd":logpwd,"department":department,"cName":cname,"email":email,"signs":0},
	        dataType: "json",
	        success: function (ret) {
	 			ret = eval(ret);
	 			if(ret == "1"){
	 				layer.open({
	 					icon: 6,
	 					content: '注册成功请登录！',
	 					yes:function(index,layero){
	 						$('.logintz').click();
	 						layer.close(index);
	 					}
	 				});  
	 			}
			},
	        error: function (ret, ret1, ret2) {
	            debugger;
	        }
		});
	}
});

function yzname(){
	var dlname = $('#logNames').val();
	if(dlname != null){
		$.ajax({
	        type: "post",
	        url: "logon!searchName.action", //url, //
	        data: {"logName":dlname},
	        dataType: "json",
	        success: function (ret) {
	 			ret = eval(ret);
	 			if(ret == 1){
	 				layer.open({
	 					icon: 3,
	 					closeBtn:0,
	 					content: '该用户名存在！请重新输入！',
	 					yes:function(index,layero){
	 						$('#logNames').focus().val("");
	 						layer.close(index);
	 					}
	 				});
	 			}
			},
	        error: function (ret, ret1, ret2) {
	            debugger;
	        }
		});
	}
}

var $a = $(".turn-right a");

$a.click(function() {
	
	$(this).parents("form").addClass("none").siblings().removeClass('none');
	
});

//登录验证码
var code; //在全局 定义验证码   
function createCode() {
    code = "";
    var codeLength = 5;//验证码的长度   
    var checkCode = document.getElementById("checkCode");
    var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//所有候选组成验证码的字符，当然也可以用中文的   
   
    for (var i = 0; i < codeLength; i++) {
        var charIndex = Math.floor(Math.random() * 36);
        code += selectChar[charIndex];
    }
    //alert(code);
    if (checkCode) {
        checkCode.className = "code";
        checkCode.value = code;
    }
}
