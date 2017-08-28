$(function() {
	// 读取body data-type 判断是哪个页面然后执行相应页面方法，方法在下面。
	var dataType = $('body').attr('data-type');
	console.log(dataType);

	autoLeftNav();
	$(window).resize(function() {
		autoLeftNav();
		console.log($(window).width())
	});
})




// 风格切换
$('.tpl-skiner-toggle').on('click', function() {
	$('.tpl-skiner').toggleClass('active');
})

$('.tpl-skiner-content-bar').find('span').on('click', function() {
	$('body').attr('class', $(this).attr('data-color'))
	saveSelectColor.Color = $(this).attr('data-color');
	// 保存选择项
	storageSave(saveSelectColor);
})

// 侧边菜单开关
function autoLeftNav() {
	$('.tpl-header-switch-button').on('click', function() {
		if ($('.left-sidebar').is('.active')) {
			if ($(window).width() > 1024) {
				$('.tpl-content-wrapper').removeClass('active');
			}
			$('.left-sidebar').removeClass('active');
		} else {

			$('.left-sidebar').addClass('active');
			if ($(window).width() > 1024) {
				$('.tpl-content-wrapper').addClass('active');
			}
		}
	})

	if ($(window).width() < 1024) {
		$('.left-sidebar').addClass('active');
	} else {
		$('.left-sidebar').removeClass('active');
	}
}




// 侧边菜单
$('.sidebar-nav-sub-title').on('click', function() {
	$(this).siblings('.sidebar-nav-sub').slideToggle(80).end().find('.sidebar-nav-sub-ico').toggleClass('sidebar-nav-sub-ico-rotate');
})

$(window).load(function() {
	getdata(1,true);
});

var rp = 10;
//加载企业信息
function getdata(page,b){
	$.ajax({
		type: "post",
		url: 'zdAdmin!execute.action',
		dataType: 'json',
		data: { statr: page,rp:rp},
		success:function(ret){
			ret = eval(ret);
			var div = "";
			totalitems = ret.num;
			if (totalitems == 0) {
				ul = '<td>没有查询到相关数据</td>';
			}
			for (var i = 0; i < ret.list.length; i++) {
				div += '<tr class="gradeX">'+
							'<td class="id">'+(i+1)+'</td>'+
							'<td class="qymc">'+ret.list[i].typeName+'</td>'+
							'<td class="qyfr">'+ret.list[i].itemOrder+'</td>'+
							'<td>'+
								'<div class="tpl-table-black-operation">'+
									'<a href="javascript:;" class="tpl-table-black-operation-del" onclick="detal('+ret.list[i].typeId+')">'+
										'<i class="am-icon-trash">'+'</i>查看'+
									'</a>'+
								'</div>'+
							'</td>'+
							'<td class="six_td">'+
								'<span class="tpl-table-black-operation">'+
									'<a onClick="addZl('+ret.list[i].typeId+');">'+
										'<i class="am-icon-plus">'+'</i>添加'+
									'</a>'+
								'</span>'+
							'</td>'+
						'</tr>'
			}
			$(".tr1").html(div);

			if(b){
				initPage(totalitems);
				//排序
				$('#example-f').DataTable({
					bInfo: false, //页脚信息
					dom: 'ti'
				});
			}
			
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	})
	
};

$('#example-t').DataTable({
	bInfo: false, //页脚信息
	dom: 'ti'
});

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
	div += 	'<table width="100%" class="am-table">'+
				'<tbody class="ta_info">'+
			'<tr>'+
				'<td>字典名称：</td>'+
				'<td><input type="text" name="typeName" id="typeName" style="width:100%;height:30px;" /></td>'+
			'</tr>'+
			'<tr>'+
				'<td>字典顺序：</td>'+
				'<td><input type="text" name="itemOrder" id="itemOrder" style="width:100%;height:30px;" /></td>'+
			'</tr>';
			div += '</tbody></table>';
				div += '<div class="am-modal-footer btn_am">'+
				'<span class="am-modal-btn" onclick="add()">添加</span>'+
				'<span class="am-modal-btn">取消</span>'+
				'</div>';
	$(".am_info").html(div);
}
function add(){
	var zdName = $('#typeName').val();
	var zdsx = $('#itemOrder').val();
	$.ajax({
		type: "post",
		url: "zdAdmin!add.action", //url, //
		data: { typeName:zdName,itemOrder:zdsx},
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
	div += 	'<table width="100%" class="am-table">'+
				'<tbody class="ta_info">'+
			'<tr>'+
				'<td>字典名称：</td>'+
				'<td><input type="text" id="typeNamezl" style="width:100%;height:30px;" /></td>'+
			'</tr>'+
			'<tr>'+
				'<td>字典顺序：</td>'+
				'<td><input type="text" id="itemOrderzl" style="width:100%;height:30px;" /></td>'+
			'</tr>'+
			'<tr>'+
				'<td>父ID：</td>'+
				'<td><input type="text" id="parentIdzl" value="'+zId+'" style="width:100%;height:30px;" /></td>'+
			'</tr>';
			div += '</tbody></table>';
				div += '<div class="am-modal-footer btn_am">'+
				'<span class="am-modal-btn" onclick="addChicld()">添加</span>'+
				'<span class="am-modal-btn">取消</span>'+
				'</div>';
	$(".am_info").html(div);
}
function addChicld(){
	var zname = $('#typeNamezl').val();
	var zsx = $('#itemOrderzl').val();
	var pid = $('#parentIdzl').val();
	$.ajax({
		type: "post",
		url: "zdAdmin!add.action", //url, //
		data: { typeName:zname,itemOrder:zsx,typeId:pid},
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
	$("#my-alert").modal('open');
	$.ajax({
		type: "post",
		url: 'zdAdmin!execute.action',
		dataType: 'json',
		data:{typeId:id},
		success:function(ret){
			ret = eval(ret);
			var div = "";
			if ( ret.list.length == 0) {
				ul = '<td style = "font-size:14px;">没有查询到相关数据</td>';
				$("#zd_detial").html(ul);
			}else{
				for (var i = 0; i < ret.list.length; i++) {
					div += 	'<tr class="gradeX">'+
								'<td id="qymc">'+ret.list[i].typeName+'</td>'+
								'<td>'+ret.list[i].itemOrder+'</td>'
							'</tr>';
						
					$("#zd_detial").html(div);
				}
			}
		}
	})
}