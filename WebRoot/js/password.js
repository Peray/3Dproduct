/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-07-31 16:07:59
 * @version $Id$
 */


//获取页面参数
 // function GetQueryString(name){
 // 	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
 // 	var r = window.location.search.substr(1).match(reg);
 // 	if(r!=null)return  unescape(r[2]); return null;
 // }

 // var userId=GetQueryString("id");
 // if(userId !=null && userId.toString().length>1){
 // 	alert(GetQueryString("id"));
 // }




var $maxg = $("#maxg");

$.validator.messages = {

	required: '输入不能为空.'

}

//登陆
$maxg.validate({
	errorPlacement: function(error, element) {
		$(element).closest("p").append(error);
	},
	errorElement: "span",
	rules: {
		oldpassword: {
			required: true
		},
		newpassword: {
			required: true
		},
		newpasswords:{
			required: true,
			equalTo: "#newpassword"
		},
		agree: "required"
	},
	messages: {
		newpasswords:{
			equalTo:"两次输入密码不一致"
		}
	},

	submitHandler: function(form) {
		var oldpassword = $('#oldpassword').val();
		var newpassword = $('#newpassword').val();
		$.ajax({
			type: "post",
			url: 'logon!passwordUpdate.action',
			data: {"userId":userId,"logPwd":oldpassword,"password":newpassword },
			dataType: "json",
			success:function(ret){
				ret = eval(ret);
				if(ret == 1){
					layer.open({
	 					icon: 6,
	 					content: '修改成功！',
	 					yes:function(index,layero){
	 						window.history.back();
	 						layer.close(index);
	 					}
	 				}); 
				}else if(ret == 2){
					layer.open({
	 					icon: 5,
	 					content: '原始密码错误！',
	 					yes:function(index,layero){
	 						layer.close(index);
	 					}
	 				}); 
				}else if(ret == 3){
					layer.open({
	 					icon: 5,
	 					content: '原始密码与新密码不能相同！',
	 					yes:function(index,layero){
	 						$("#newpassword").focus().val("");
	 						$("#newpasswords").val("");
	 						layer.close(index);
	 					}
	 				});
				}else{
					layer.open({
	 					icon: 5,
	 					content: '修改失败！',
	 					yes:function(index,layero){
	 						layer.close(index);
	 					}
	 				});
				}
			},
			error: function(ret, ret1, ret2) {
				debugger;
			}
		})
	}
});
