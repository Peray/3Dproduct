'use strict';
/**
 * 
 * @authors peray (you@example.org)
 * @date    2017-06-26 14:43:31
 * @version $Id$
 */
$(window).load(function() {
	getdata(1,true);
});
$('.input-r').click(function() {
	searchData(1,true);
});

var rp = 10;

//定制我的资源
function getdata(page,b){
	$.ajax({
		type:'post',
		url: 'myService!execute.action',
		dataType: 'json',
		data: { statr: page,userId:userId},
		success:function(ret){
			ret = eval(ret);
			var totalitems = ret.num;
			var div = "";
//			$("#searKey").html("共"+totalitems+"条记录");
			if (totalitems == 0) {
				div += '<tr><td colSpan="7">没有查询到相关数据！</td></tr>';
			}else{
				for (var i = 0; i < ret.list.length; i++) {
					var date = new Date(ret.list[i].time.time);
							var Y = date.getFullYear() + '-';
							var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
							var D = date.getDate();
					
						div += '<tr>'+
							'<td>'+(i+1)+'</td>'+
							'<td>'+ret.list[i].serviceName+'</td>'+
							'<td>'+Y+M+D+'</td>'+
							'<td>'+
								'<button class="btn btn-primary btn-xs" onclick="detail('+ret.list[i].parentId+')">查看</button>'+
								'<button class="btn btn-danger btn-xs" onclick="cancle('+ret.list[i].serviceId+')">取消定制</button>'+
							'</td>'+
						'</tr>';
				}
			}
			$("#tbody").html(div);
			$("#myTable").trigger("update");

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




//查看
function detail(id){
	$.ajax({
	        type: "post",
	        url: "gl!detail.action",
	        data: {"glId":id},
	        dataType: "json",
	        success: function (ret) {
	 			ret = eval(ret);
				var div = "";

				var date = new Date(ret.list[0].fbTime.time);
				var Y = date.getFullYear() + '-';
				var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
				var D = (date.getDate() < 10 ? '0'+( date.getDate()) :  date.getDate());

				div +=	`<form class="ckfw">
								<p>
									<label>服务编号</label>
									<input type="text" disabled value=${ret.list[0].glNumber} />
								</p>

								<p>
									<label>服务名称</label>
									<input type="text" disabled value=${ret.list[0].glName} />
								</p>

								<p>
									<label>服务发布时间</label>
									<input type="text" disabled value=${Y+M+D} />
								</p>

								<p>
									<label style="vertical-align:top;">描述</label>
									<textarea rows="3" cols="37" maxlength="37" disabled>${ret.list[0].content}</textarea>
									
								</p>

								<p>
									<label style="vertical-align:top;">方位描述</label>
									<textarea rows="3" cols="37" maxlength="37" disabled>${ret.metaList[0].fwContent}</textarea>
								</p>

								<p>
									<label style="font-weight:bold;">地理信息坐标</label>
								</p>

								<p>
									<label>最大X坐标</label>
									<input type="text" disabled value=${ret.metaList[0].zdx}>
								</p>

								<p>
									<label>最大Y坐标</label>
									<input type="text" disabled value=${ret.metaList[0].zdy} />
								</p>

								<p>
									<label>最小X坐标</label>
									<input type="text" disabled value=${ret.metaList[0].zxx} />
								</p>

								<p style="margin-bottom:10px;">
									<label>最小Y坐标</label>
									<input type="text" disabled value=${ret.metaList[0].zxy} />
								</p>

								<p>
									<label>服务类型</label>
									<input type="text" disabled value=${ret.metaList[0].fwlx} />
								</p>

								<p>
									<label>卫星类型</label>
									<input type="text" disabled value=${ret.metaList[0].wxlx} />
								</p>

								<p>
									<label>分辨率</label>
									<input type="text" disabled value=${ret.metaList[0].fbl} />
								</p>

								<p>
									<label>时相</label>
									<input type="text" disabled value=${ret.metaList[0].sx.substring(0, 10)} />
								</p>

								<p>
									<label>来源</label>
									<input type="text" disabled value=${ret.metaList[0].yxSource} />
								</p>

								<p>
									<label>原始影像采集单位</label>
									<input type="text" disabled value=${ret.metaList[0].yxcjdw} />
								</p>

								<p>
									<label>影像处理单位</label>
									<input type="text" disabled value=${ret.metaList[0].yxcldw} />
								</p>

								<p>
									<label>影像审定时间</label>
									<input type="text" disabled value=${ret.metaList[0].yxsdsj.substring(0, 10)} />
								</p>

								<p>
									<label>数据密集</label>
									<input type="text" disabled value=${ret.metaList[0].sjmj} />
								</p>

								<p>
									<label>知识产权归属</label>
									<input type="text" disabled value=${ret.metaList[0].cqgs} />
								</p>

								<p>
									<label>审核人</label>
									<input type="text" disabled value=${ret.metaList[0].shPop} />
								</p>

								<p>
									<label>审核时间</label>
									<input type="text" disabled value=${ret.metaList[0].shTime.substring(0, 10)} />
								</p>
							</form>`
	
				$(".peray-body").html(div);
			},
	        error: function (ret, ret1, ret2) {
	            debugger;
	        }
	   });
	$("body").addClass("peray-open").css("padding-right","17px");
	$("#myPeray").show();
	var timer = setTimeout(function(){
		$("#myPeray").addClass("in");
		clearTimeout(timer);
	},0);
}


$(document).on('click',function(e){
	if(e.target.className == 'e'|| e.target.id == 'myPeray'){
		$("body").removeClass("peray-open").css("padding-right","0px");
		$("#myPeray").removeClass("in");
		var timer = setTimeout(function(){
			$("#myPeray").hide();
			clearTimeout(timer);
		},0);
	}
})
$(".close").click(function(){
	$("body").removeClass("peray-open").css("padding-right","0px");
	$("#myPeray").removeClass("in");
	var timer = setTimeout(function(){
		$("#myPeray").hide();
		clearTimeout(timer);
	},0);
})

//取消订阅
function cancle(id){
	layer.confirm('您真的确定要取消吗?', {
		btn: ['确认', '离开'],
		btn2: function(index, layero){
			layer.close(index);
		}
	}, function(index){
		$.ajax({
			type:'post',
			url: 'myService!delete.action',
			dataType: 'json',
			data: {"serviceId":id},
			success:function(ret){
				layer.close(index);
				getdata(1,true);
			},
			error: function(ret, ret1, ret2) {
				debugger;
			}
		})
	});
}


//查询检索
function searchData(page,b){
	var keyword = $('#key').val();
	$.ajax({
		type: "post",
		url: "myService!searchKey.action", 
		data: {"statr": page,"userId":userId,"info":keyword},
		dataType: "json",
		success: function (ret) {
			ret = eval(ret);
			var totalitems = ret.num;
			var div = "";
//			$("#searKey").html("共"+totalitems+"条记录");
			if (totalitems == 0) {
				div += '<tr><td colSpan="7">没有查询到相关数据！</td></tr>';
			}else{
				for (var i = 0; i < ret.list.length; i++) {
					var date = new Date(ret.list[i].time.time);
							var Y = date.getFullYear() + '-';
							var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
							var D = date.getDate();
					
						div += '<tr>'+
							'<td>'+(i+1)+'</td>'+
							'<td>'+ret.list[i].serviceName+'</td>'+
							'<td>'+Y+M+D+'</td>'+
							'<td>'+
								'<button class="btn btn-primary btn-xs" onclick="detail('+ret.list[i].parentId+')">查看</button>'+
								'<button class="btn btn-danger btn-xs" onclick="cancle('+ret.list[i].serviceId+')">取消定制</button>'+
							'</td>'+
						'</tr>';
				}
			}
			$("#tbody").html(div);
			$("#myTable").trigger("update");

			if(b){
				initPages(totalitems);
			}
			
		},
		error: function(ret, ret1, ret2) {
			debugger;
		}
	})
};


function initPages(totalitems) {
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