'use strict'
/**
 * 
 * @authors peray (you@example.org)
 * @date    2017-06-29 11:28:33
 * @version $Id$
 */

 var $form = $("#addForm");
$form.validator({
	submit:function(){
		var formValidity = this.isFormValid();
		if(formValidity){
			var logName = $("#logName").val();
			var logPwd = $("#logPwd").val();
			var department = $("#department").val();
			var cName= $("#cName").val();
			var email = $("#email").val();
			var level= $("#level").val();
			$.ajax({
				type:'post',
				url: 'userAdmin!add.action',
				dataType: 'json',
				data: {
					"logName":logName,
					"logPwd":logPwd,
					"department":department,
					"cName":cName,
					"email":email,
					"level":level,
					"signs":1
				},
				success:function(ret){
					window.location = "item-user.html";
				},
				error: function(ret, ret1, ret2) {
					debugger;
				}
			})
		}else{
			return formValidity;
		}
	}
})
