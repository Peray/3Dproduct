$(window).load(function() {
	getdata(1,true);
});
var rp = 10;
//加载管理信息
function getdata(page,b){
	var sta = $('#startTime').val();
	var end = $('#endTime').val();
	$.ajax({
		type:'post',
		url: 'logsAdmin!execute.action',
		dataType: 'json',
		data: { "statr": page,"startTime":sta,"endTime":end},
		success:function(ret){
			ret = eval(ret);
			var div = "";
			totalitems = ret.num;
			if (totalitems == 0) {
				div += '<tr><td colSpan="6">没有查询到相关数据！</td></tr>';
			}else{
				for (var i = 0; i < ret.list.length; i++) {
					var date = new Date(ret.list[i].time.time);
							Y = date.getFullYear() + '-';
							M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
							D = (date.getDate()<10 ? '0'+date.getDate() : date.getDate()) + '&nbsp;';
							H = (date.getHours()<10 ? '0'+date.getHours() : date.getHours())+ ':';
							I = (date.getMinutes() <10 ? '0'+date.getMinutes() : date.getMinutes());
					div += '<tr>'+
								'<td>'+(i+1)+'</td>'+
								'<td>'+Y+M+D+H+I+'</td>'+
								'<td>'+ret.list[i].userName+'</td>'+
								'<td>'+ret.list[i].ip+'</td>'+
								'<td>'+ret.list[i].event+'</td>'+
//								'<td>'+
//								'<button class="am-btn am-btn-success am-radius am-btn-xs"  onClick="dongjie('+ret.list[i].userId+');">冻结</button>'+
//								'<button class="am-btn am-btn-danger am-radius am-btn-xs"  onClick="zhuxiao('+ret.list[i].userId+');">注销</button>'+	
//								'</td>'+
							'</tr>'
				}
			}
			$(".neirong").html(div);
			$("#myTable6").trigger("update");

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

//删除方法
function deletesc(id){
	layer.confirm('确认删除吗？', {
		btn: ['确认', '离开'],
		btn2: function(index, layero){
			layer.close(index);
		}
	}, function(index){
		$.ajax({
			type:'post',
			url: 'glAdmin!delete.action',
			dataType: 'json',
			data: {"glId":id},
			success:function(ret){
				var data = JSON.stringify(ret);
				layer.open({
					icon: 1,
					content: '删除成功!',
					yes:function(index,layero){
						location.href=location.href;
						layer.close(index);
					}
				});  
			},
			error: function(ret, ret1, ret2) {
				debugger;
			}
		})
	});
}
