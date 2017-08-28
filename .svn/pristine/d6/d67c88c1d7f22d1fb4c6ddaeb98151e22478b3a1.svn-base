'use strict';



$(window).load(function() {
	getCategory();
});

function getCategory(){
	$.ajax({
		type:'post',
		url: 'category!execute.action',
		dataType: 'json',
//		data: {categoryId:1},
		success:function(ret){
			ret = eval(ret);
			var div = "";
			for (var i = 0; i < ret.list.length; i++) {
				if(ret.list[i].categoryList.length>0){
					div += '<div class="list-item">'+
							'<span>'+ret.list[i].categoryName+'</span>'+
							'<ul>';
								for(var j = 0; j < ret.list[i].categoryList.length; j++){
									div += '<li onclick="categoryCx(\''+ret.list[i].path+'\',\''+ret.list[i].categoryList[j].categoryName+'\')">'+ret.list[i].categoryList[j].categoryName+'</li>';
								}
							div+='</ul>';
							var s = '<span class="fh'+i+'" onclick="xila('+i+')">∨</span>';
							ret.list[i].categoryList.length>2?div+=s:div+="";
						div+='</div>';
				}else{
					div+=""
				}
			}

			$("aside").html(div);

			$(".list-item li:first-child").addClass('show');

			$(".list-item li:nth-child(2)").addClass('show');
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	})
};
