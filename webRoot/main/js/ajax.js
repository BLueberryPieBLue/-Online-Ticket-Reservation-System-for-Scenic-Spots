//formatTime(1545903266795, 'Y-M-D h:m:s')
function formatTime (number, format) {
    let time = new Date(number)
    let newArr = []
    let formatArr = ['Y', 'M', 'D', 'h', 'm', 's']
    newArr.push(time.getFullYear())
    newArr.push(formatNumber(time.getMonth() + 1))
    newArr.push(formatNumber(time.getDate()))

    newArr.push(formatNumber(time.getHours()))
    newArr.push(formatNumber(time.getMinutes()))
    newArr.push(formatNumber(time.getSeconds()))

    for (let i in newArr) {
        format = format.replace(formatArr[i], newArr[i])
    }
    return format;
}
function formatNumber (n) {
    n = n.toString()
    return n[1] ? n : '0' + n;
}
//显示景区信息

function jingquxinxi(){
	$.ajax({
		url:"/ssm/GetAllSightList",
		type :"POST",
		dataType:"json",
		success: function(data){
			showSight(data);
//			showgundong(data);
		},
	    error : function(err) {
		console.log(err);
	}
	});
}
//景点信息
function showSight(data){
	var len=data.length;
	var str = '';
	
	for(i=0;i<len;i++)
		{
		  str +='<div class="col-md-6 col-sm-12 col-xs-6 " id="'+
		  data[i].s_id+
		  '"><div class="choose-us-box"><div class="col-md-12 col-sm-12 col-xs-12 no-padding choose-us-block"><div class="col-md-5 col-sm-4 col-xs-12 choose-us-cover"><i><img src="Images/'+
		  data[i].s_imgurl+
		  '" alt="choose-us" /></i></div><div class="col-md-7 col-sm-8 col-xs-12 choose-us-content"><h3>'+
		  data[i].s_name+
		  '</h3><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
		  data[i].s_info+
		  '</p></div><button class="button yellow" id="btn1" onclick="m1.show();Show(this);"><div class="title">加入购物车</div></button></div></div></div>'		
          
		}
	$("#sightshow").html(str);
	
}
//滚动
//function showgundong(data){
//	var len=data.length;
//	var str1 = '';
//	
//	for(i=0;i<len;i++)
//		{		
//       
//		  str1+='<div class="col-md-12 item"><a href="#" title="client"><img src="Images/'+
//          data[i].s_imgurl+
//          '" alt="clients-1"></a><p>langyashan</p></div>'
//		}
//	$("#gundong").html(str1);
//	console.log(str1);
//}
$(function(){
	jingquxinxi();
	remenxinxi();
})
// 推送近期热门景点，六条
function remenxinxi(){
	$.ajax({
		url:"/ssm/getRecentSight",
		type :"POST",
		dataType:"json",
		success: function(data){
			
			var str = '';
			for(i=0;i<6;i++){
				str+='<li class="col-md-4 col-sm-4 col-xs-6 design" id="'+
				data[i][0].s_id+
				'"><div class="content-image-block"><img src="Images/'+
				data[i][0].s_imgurl+
				'" alt="gallery"><div class="content-block-hover"><h5>'+
				'No.'+
				(i+1)+
				'&nbsp;'+
				data[i][0].s_name+
				'<br/>已售票：'+
				data[i][2]+
				'</h5><a class="zoom" href="Images/'+
				data[i][0].s_imgurl+
				'" title="Expand"><i class="fa fa-search"></i></a>'
			}
			$("#remenjingdian").html(str);
			
		},
	    error : function(err) {
		console.log(err);
	}
	});
}

function ticketxianshi(str){
	var str1='';
	$.ajax({
		url:"/ssm/SelectTicket",
		type :"POST",
		dataType:"json",
		data:{'str':str},
		success: function(data){
			var len=data.length;
			for(i=0;i<len;i++){
//				if(data[i].t_status=='1'){
//					t_status = '开售';
//				}else{
//					t_status = '停售';
//				}
//				
			str1+='<div class="row" id="'+
			data[i].t_id+
			'"><div class="col-xs-1">'+
			str+
			'</div><div class="col-xs-1" >'+
			data[i].t_category+
			'</div><div class="col-xs-1">'+
			data[i].t_price+
//			'</div><div class="col-xs-1">'+
//			t_status+
			'</div><div class="col-xs-1" style="width: 15%;margin-top: 10px;">'+
			'<img src="Images/'+
			data[i].t_imgurl+
			'"></div><div class="col-xs-1" >'+
			data[i].t_num+
			'</div><div class="col-xs-2">'+
			formatTime(data[i].t_time_start, 'Y-M-D')+
			'</div><div class="col-xs-2">'+
			formatTime(data[i].t_time_end, 'Y-M-D')+
			'</div><div class="col-xs-2"  id="btn-numbox"><li><ul class="count"><li><span id="num-jian" class="num-jian">-</span></li><li><input type="text" class="input-num" id="input-num'+
			data[i].t_id+
			'" value="0" /></li><li><span id="num-jia" class="num-jia">+</span></li></ul></li></div>'+
			'<div class="col-xs-2"><input style="width:120px;" id="t_time_start'+data[i].t_id+'" type="date"class="form-control input-sm duiqi" name="t_time_start">'+
			'</div><div class="col-xs-1" style=";margin-top: -10px; float: right;"><div class="m-bottom" ><button class="m-btn-sure" onclick="getuiddivid(this);">预订</button></div></div></div>'
			}
			console.log(str1);
			$("#tablebody").html(str1);
			ja(data.length);
		},
	    error : function(err) {
		console.log(err);
	}
	});
}
var m1 = new MyModal.modal(function() {
	alert("预订完成");
});
function Show(div) {
	$.ajax({
		url:"/ssm/getKey",
		type:"POST",
		dataType:"json",
		async:false,
		success:function(dd){
		console.log(dd.u_id);
		
		obj = div.parentNode.parentNode.parentNode;
		var id = obj.id;
		var str = $("#sightshow  > div > div > div > div > h3")[id - 1].innerHTML
		ticketxianshi(str);
		
		},
		error:function(err){
		console.log(err);
		alert("请先登录！");
		window.location.href = 'denglu.html';
		}
		
	});

}
//数字加减
function ja(length) {
	
	$(".num-jia")[0].onclick = function() {
		$(".input-num")[0].value = parseInt($(".input-num")[0].value) + 1;
	}

	$(".num-jian")[0].onclick = function() {
		if ($(".input-num")[0].value <= 0) {
			$(".input-num")[0].value = 0;
		} else {
			$(".input-num")[0].value = parseInt($(".input-num")[0].value) - 1;
		}
	}
	
	$(".num-jia")[1].onclick = function() {
		$(".input-num")[1].value = parseInt($(".input-num")[1].value) + 1;
	}

	$(".num-jian")[1].onclick = function() {
		if ($(".input-num")[1].value <= 0) {
			$(".input-num")[1].value = 0;
		} else {
			$(".input-num")[1].value = parseInt($(".input-num")[1].value) - 1;
		}
	}
}


function getuiddivid(div){
	var id='';
	var obj=div.parentNode.parentNode.parentNode;
	var divid=obj.id;
	$.ajax({
		url:"/ssm/getKey",
		type:"POST",
		dataType:"json",
        success: function(data){
			id=data.u_id;
			adddingdan(id,divid);
		},
	    error : function(err) {
		console.log(err);
	    }
	});
}
function adddingdan(id,divid){
	
	$.ajax({
		url:"/ssm/UserAddOrder",
		type :"POST",
		dataType:"json",
		data:{'u_id':id,'t_id':divid,'t_num':$("#input-num"+divid+"").val(),'d':$("#t_time_start"+divid+"").val()},
		success: function(data){
			console.log(id);
			console.log(divid);
			console.log($("#input-num"+divid+"").val());
			console.log($("#t_time_start"+divid+"").val());
			alert("预订完成");
		},
	    error : function(err) {
		console.log(err);
	}
	});
}	
	

