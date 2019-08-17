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
		  '</p></div></div></div></div>'		
          
		}
	$("#sightshow").html(str);
	
}
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
})
//window.onload = function() {
//	
//}