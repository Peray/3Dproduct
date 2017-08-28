$(window).load(function() {
	getCategory();
});
function getCategory(){
	$.ajax({
		type:'post',
		url: 'categoryAdmin!execute.action',
		dataType: 'json',
//		data: {categoryId:1},
		success:function(ret){
			ret = eval(ret);
			var div = "";
			for (var i = 0; i < ret.list.length; i++) {
				if(ret.list[i].categoryName != "时相" &&　ret.list[i].categoryName != "服务类型"){
					div += '<div class="am-form-group">'+
					'<label class="am-u-sm-3 am-form-label">'+ret.list[i].categoryName+'</label>'+
					'<div class="am-u-sm-8">'+
						'<select name="'+ret.list[i].path+'" required>'+
							'<option value="">请选择</option>';
							for(var j = 0; j < ret.list[i].categoryList.length; j++){
								div += '<option value="'+ret.list[i].categoryList[j].categoryName+'">'+ret.list[i].categoryList[j].categoryName+'</option>';
							}
						div += '</select>'+
						'<span class="am-form-caret"></span>'+
					'</div>'+
					'<span class="am-u-sm-1 am-text-left am-padding-left-0 top-red">*</span>'+
					'</div>'
				}
			}
			$("#categorySelect").html(div);
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	})
};