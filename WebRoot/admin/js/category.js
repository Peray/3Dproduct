$(window).load(function() {
	getdata(1,true);
});
var rp = 10;
//加载管理信息
function getdata(page,b){
	$.ajax({
		type:'post',
		url: 'categoryAdmin!execute.action',
		dataType: 'json',
		data: { statr: page,rp:rp},
		success:function(ret){
			ret = eval(ret);
			var div = "";
			totalitems = ret.num;
			if (totalitems == 0) {
				div += '<tr><td colSpan="7">没有查询到相关数据！</td></tr>';
			}else{
				for (var i = 0; i < ret.list.length; i++) {
					div += '<tr>'+
								'<td>'+(i+1)+'</td>'+
								'<td>'+ret.list[i].categoryName+'</td>'+
								'<td>'+ret.list[i].path+'</td>'+
								'<td class="am-text-center">'+
									'<button class="am-btn am-btn-default am-radius am-btn-xs" onClick="detal('+ret.list[i].categoryId+');">查看</button>　'+
									'<button class="am-btn am-btn-default am-radius am-btn-xs" onClick="edit('+ret.list[i].categoryId+');">编辑</button>　'+
									'<button class="am-btn am-btn-default am-radius am-btn-xs" onClick="addZl('+ret.list[i].categoryId+');">添加子类</button>　'+
									//'<button class="am-btn am-btn-default am-radius am-btn-xs" onClick="deletesc('+ret.list[i].categoryId+');">删除</button>'+
								'</td>'+
							'</tr>'
				}
			}
			$(".neirong").html(div);
			if(b){
				initPage(totalitems);
			}
			
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	})
};
//
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
				if(num!=1){
					getdata(num,false);
				}if(num == 1){
					getdata(1,false);
				}
			}
		});
	}
}
function addType(){
	$("#my-alert").modal('open');
	var div="";
	div+= 	'<div class="am-popup-hd">'+
				'<h4 class="am-popup-title">字典添加</h4>'+
				'<span data-am-modal-close class="am-close">&times;</span>'+
			'</div>'+
			'<div class="am-popup-bd am_info" style="min-height:542px;">'+
				'<form class="am-form am-form-horizontal">'+
					'<fieldset>'	+
						'<div class="am-form-group">'+
							'<label class="am-u-sm-3 am-form-label">分类名称</label>'+
							'<div class="am-u-sm-8">'+
								'<input type="text" name="categoryName" id="categoryName" />'+
							'</div>'+
							'<span class="am-u-sm-1"></span>'+
						'</div>'+

						'<div class="am-form-group">'+
							'<label class="am-u-sm-3 am-form-label" for="doc-ipt-num-1">服务编号</label>'+
							'<div class="am-u-sm-8">'+
								'<input type="text" name="path" id="path" />'+
							'</div>'+
							'<span class="am-u-sm-1"></span>'+
						'</div>'+
					'</fieldset>'+
				'</form>'+
			'</div>'+
			'<div class="am-modal-footer">'+
				'<span class="am-modal-btn" onclick="add()">添加</span>'+
				'<span class="am-modal-btn">取消</span>'+
			'</div>';
			
	$("#inner").html(div);
}
function edit(id){
	$("#my-alert1").modal('close');
	$("#my-alert").modal('open');
	$.ajax({
		type: "post",
		url: "categoryAdmin!edit.action", //url, //
		data: { categoryId:id},
		dataType: "json",
		success: function(ret) {
			ret = eval(ret);
			var div="";
			div+= 	'<div class="am-popup-hd">'+
						'<h4 class="am-popup-title">字典修改</h4>'+
						'<span data-am-modal-close class="am-close">&times;</span>'+
					'</div>'+
					'<div class="am-popup-bd am_info" style="min-height:542px;">'+
						'<form class="am-form am-form-horizontal">'+
							'<fieldset>'	+
								'<div class="am-form-group">'+
									'<label class="am-u-sm-3 am-form-label">分类名称</label>'+
									'<div class="am-u-sm-8">'+
										'<input type="text" id="categoryNameEdit" value="'+ret.list[0].categoryName+'" />'+
									'</div>'+
									'<span class="am-u-sm-1"></span>'+
								'</div>'+
		
								'<div class="am-form-group">'+
									'<label class="am-u-sm-3 am-form-label" for="doc-ipt-num-1">服务编号</label>'+
									'<div class="am-u-sm-8">'+
										'<input type="text" id="pathEdit" value="'+ret.list[0].path+'" />'+
									'</div>'+
									'<span class="am-u-sm-1"></span>'+
								'</div>'+
							'</fieldset>'+
						'</form>'+
					'</div>'+
					'<div class="am-modal-footer">'+
						'<span class="am-modal-btn" onclick="updata('+ret.list[0].categoryId+')">修改</span>'+
						'<span class="am-modal-btn">取消</span>'+
					'</div>';
					
			$("#inner").html(div);
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	});
}
function add(){
	var zdName = $('#categoryName').val();
	var zdsx = $('#path').val();
	$.ajax({
		type: "post",
		url: "categoryAdmin!add.action", //url, //
		data: { categoryName:zdName,path:zdsx},
		dataType: "text",
		success: function(ret) {
			var data = eval(ret);
			if (data == '1') {
				getdata(1,true);
			}
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	});
}

function updata(id){
	var zdName = $('#categoryNameEdit').val();
	var zdsx = $('#pathEdit').val();
	$.ajax({
		type: "post",
		url: "categoryAdmin!update.action", //url, //
		data: {categoryId:id,categoryName:zdName,path:zdsx},
		dataType: "text",
		success: function(ret) {
			var data = eval(ret);
			if (data == '1') {
				getdata(1,true);
			}
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	});
}
function addZl(zId){
	$("#my-alert").modal('open');
	var div="";
	div+= 	'<div class="am-popup-hd">'+
				'<h4 class="am-popup-title">字典添加</h4>'+
				'<span data-am-modal-close class="am-close">&times;</span>'+
			'</div>'+
			'<div class="am-popup-bd am_info" style="min-height:542px;">'+
				'<form class="am-form am-form-horizontal">'+
					'<fieldset>'	+
						'<div class="am-form-group">'+
							'<label class="am-u-sm-3 am-form-label">分类名称</label>'+
							'<div class="am-u-sm-8">'+
								'<input type="text" id="categoryNamezl" />'+
							'</div>'+
							'<span class="am-u-sm-1"></span>'+
						'</div>'+

						'<div class="am-form-group">'+
							'<label class="am-u-sm-3 am-form-label" for="doc-ipt-num-1">路径</label>'+
							'<div class="am-u-sm-8">'+
								'<input type="text" id="pathzl" />'+
							'</div>'+
							'<span class="am-u-sm-1"></span>'+
						'</div>'+

						'<div class="am-form-group">'+
							'<label class="am-u-sm-3 am-form-label" for="doc-ipt-num-1">父ID</label>'+
							'<div class="am-u-sm-8">'+
								'<input type="text" id="parentIdzl" value="'+zId+'" readonly />'+
							'</div>'+
							'<span class="am-u-sm-1"></span>'+
						'</div>'+
					'</fieldset>'+
				'</form>'+
			'</div>'+
			'<div class="am-modal-footer">'+
				'<span class="am-modal-btn" onclick="addChicld()">添加</span>'+
				'<span class="am-modal-btn">取消</span>'+
			'</div>';
	$("#inner").html(div);
}
function addChicld(){
	var zname = $('#categoryNamezl').val();
	var zsx = $('#pathzl').val();
	var pid = $('#parentIdzl').val();
	$.ajax({
		type: "post",
		url: "categoryAdmin!add.action", //url, //
		data: { categoryName:zname,path:zsx,categoryId:pid},
		dataType: "text",
		success: function(ret) {
			var data = eval(ret);
			if (data == '1') {
				getdata(1,true);
			}
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	});
}

function detal(id){
	$("#my-alert1").modal('open');
	$.ajax({
		type: "post",
		url: 'categoryAdmin!execute.action',
		dataType: 'json',
		data:{categoryId:id},
		success:function(ret){
			ret = eval(ret);
			var div = "";
			if ( ret.list.length == 0) {
				ul = '<tr ><td colspan="2" style = "font-size:14px;">没有查询到相关数据</td></tr>';
				$("#zd_detial1").html(ul);;
			}else{
				for (var i = 0; i < ret.list.length; i++) {
					div += 	'<tr>'+
								'<td id="qymc">'+ret.list[i].categoryName+'</td>'+
								'<td>'+ret.list[i].path+'</td>'+
								'<td class="am-text-center">'+
									'<button class="am-btn am-btn-default am-radius am-btn-xs" onClick="edit('+ret.list[i].categoryId+');">编辑</button>　'+
									'<button class="am-btn am-btn-default am-radius am-btn-xs" onClick="deletesc('+ret.list[i].categoryId+');">删除</button>'+
								'</td>'+
							'</tr>';
						
					$("#zd_detial1").html(div);
				}
			}
		}
	})
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
			url: "categoryAdmin!delete.action", //url, //
	        data: {"categoryId":id},
	        dataType: "json",
			success:function(ret){
				ret = eval(ret);
				if(ret == "2"){
	 				layer.open({
						icon: 1,
						content: '删除失败，请先删除子类！!',
						yes:function(index,layero){
							layer.close(index);
						}
					});  
	 			}else{
	 				location.href=location.href;
	 			}
			},
			error: function(ret, ret1, ret2) {
				debugger;
			}
		})
	});
}