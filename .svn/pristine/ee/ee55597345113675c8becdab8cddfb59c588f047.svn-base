$(function(){
	//初始化
	$('.tu li').first().show();
	$('.shu li').first().addClass('active');
	
	//手动控制图片的淡入与淡出
	$('.shu li').mouseover(function(){
		$(this).addClass('active').siblings('li').removeClass('active');
		var index=$(this).index();
		i=index
		$('.tu li').eq(index).fadeIn().siblings().fadeOut();
	})
	
	//通过时间间隔函数控制图片的显示
	var i=0;
	var t=setInterval(move,2000);
	
	//动画运行的核心函数  向左
	function moveL(){
		i--
		if(i==-1){
		i=4;
		}
		$('.shu li').eq(i).addClass('active').siblings().removeClass();
		$('.tu li').eq(i).fadeIn().siblings().fadeOut();
	}
	
	//动画运行的核心函数  向右
	function move(){
		i++
		if(i==5){
		i=0;
		}
		$('.shu li').eq(i).addClass('active').siblings().removeClass();
		$('.tu li').eq(i).fadeIn().siblings().fadeOut();
	}
	
	//鼠标移入out元素时，停掉正在运行的动画
	$('.out').hover(function(){
		clearInterval(t);
	},function(){
		t=setInterval(move,2000);
	})
	
	//点击左边箭头执行动画
	$('.out .left').click(function(){
		moveL();
	})
	
	//点击右边箭头执行动画
	$('.out .right').click(function(){
		move();
	})
})