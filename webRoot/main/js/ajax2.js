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
		  '</p></div><button class="button yellow" id="btn1" onclick="Show(this);"><div class="title">加入购物车</div></button></div></div></div>'		
          
		}
	$("#sightshow").html(str);
	
}

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

function showmessage(data){
	var len=data.length;
	var str = '';
	var isroot='';
	for(i=0;i<len;i++)
		{
			str+='<article class="type-post" id="m'+data[i].m_id+'">'+
				'<div class="entry-cover">'+
			'<a title="Blog" href="#">'+
				'<img alt="blog" src="Images/'+data[i].m_imgurl+'" />'+
			'</a>'+
		'</div>'+
		'<div class="blog-content">'+
			'<div class="entry-header">'+
				'<div class="entry-meta">'+
					'<div class="post-admin">By <a href="#" id="u'+data[i].u_id+'">'+SelectUseru_nameByUId(data[i].u_id)+data[i].u_id+'</a></div>'+
					'<div class="post-date"><a href="#">'+formatTime(data[i].m_time, 'Y-M-D')+'</a></div>'+
				'</div>'+
				'<h3 class="entry-title"><a href="#">'+data[i].m_title+'</a></h3>'+
			'</div>'+
			'<div class="entry-content">'+
				'<p>'+data[i].m_info+'</p>'+
			'</div>'+
		'</div>'+
	'</article>'
		}
	$("#articles").html(str);
}
//查询用户u_nameByUId
function SelectUseru_nameByUId(uid){
	$.ajax({
		url:"/ssm/SelectUserByUId",
		type :"POST",
		dataType:"json",
		data:{'UId':uid},
		success: function(data){
			//console.log(data);
			$("#u"+uid).html(data.u_name);
		},
	    error : function(err) {
		console.log(err);
	}
	});
	
}
function showmessages(){
	$.ajax({
		url:"/ssm/GetAllMessageList",
		type :"POST",
		dataType:"json",
		success : function(data) {
			showmessage(data);
		},
		error : function(err) {
			console.log(err);
		}
	});
}
showmessages();