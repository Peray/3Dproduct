'use strict';
/**
 * 
 * @authors peray (you@example.org)
 * @date    2017-06-27 10:55:33
 * @version $Id$
 */
$(function(){
	searchWeek();
})
var myChart = echarts.init(document.getElementById('main1'));
myChart.title = '坐标轴刻度与标签对齐';

var option = {
		color: ['#3398DB'],
		tooltip : {
			trigger: 'axis',
			axisPointer : {
				type : 'shadow'
			}
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis : [{
			type : 'category',
			name: '名称',
			data : [],
			axisTick: {
				alignWithLabel: true
			}
		}],
		yAxis : [{
			type : 'value',
			name:'定制次数',
			minInterval : 1
		}],
		series : [{
			name:'定制次数',
			type:'bar',
			barWidth: '40%',
			data:[]
		}]
	};

function searchWeek(){
	$.ajax({
	    type: "post",
	    url: "myServiceAdmin!searchWeek.action", //url, //
//	    data: {},
	    dataType: "json",
	    beforeSend:function(){
	    	myChart.showLoading({
	    		text: "图表数据正在努力加载..."
	    	});
	    },
	    success: function (ret) {
	    		ret = eval(ret);
	    		if(ret.list.length>0){
	    			var line = [],count=[];
	    			for(var i=0;i<ret.list.length;i++){
	    				line.push(ret.list[i].service_Name);
	    				count.push(ret.list[i].counts);
	    			}
	    			option.xAxis[0].data= line;
	    			option.series[0].data= count;
	    			myChart.hideLoading();
	    			myChart.setOption(option);
	    			window.onresize = myChart.resize;
	    		}else{
	    			myChart.hideLoading();
	    			$("#main1").html('<p class="p_data">暂无数据！</p>')
	    		}
		},
	    error: function (ret, ret1, ret2) {
	        debugger;
	    }
	});
}

function searchMonth(){
	$.ajax({
	    type: "post",
	    url: "myServiceAdmin!searchMonth.action", //url, //
//	    data: {},
	    dataType: "json",
	     beforeSend:function(){
	    	myChart.showLoading({
	    		text: "图表数据正在努力加载..."
	    	});
	    },
	    success: function (ret) {
			ret = eval(ret);
			var line = [],count=[];
			for(var i=0;i<ret.list.length;i++){
				line.push(ret.list[i].service_Name);
				count.push(ret.list[i].counts);
			}
			option.xAxis[0].data= line;
			option.series[0].data= count;
			myChart.hideLoading();
			myChart.setOption(option);
		},
	    error: function (ret, ret1, ret2) {
	        debugger;
	    }
	});
}
function searchYear(){
	$.ajax({
	    type: "post",
	    url: "myServiceAdmin!searchYear.action", //url, //
//	    data: {},
	    dataType: "json",
	     beforeSend:function(){
	    	myChart.showLoading({
	    		text: "图表数据正在努力加载..."
	    	});
	    },
	    success: function (ret) {
			ret = eval(ret);
			var line = [],count=[];
			for(var i=0;i<ret.list.length;i++){
				line.push(ret.list[i].service_Name);
				count.push(ret.list[i].counts);
			}
			option.xAxis[0].data= line;
			option.series[0].data= count;
			myChart.hideLoading();
			myChart.setOption(option);
		},
	    error: function (ret, ret1, ret2) {
	        debugger;
	    }
	});
}

