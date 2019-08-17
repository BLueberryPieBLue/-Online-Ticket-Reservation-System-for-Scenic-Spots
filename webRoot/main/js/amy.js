function getKey(){
	$.ajax({
		url:"/ssm/getKey",
		type:"POST",
		dataType:"json",
		success:function(data){
			console.log(data);
			$("#myhead").html(
			   '<ul>'+
			   		'<li style="display:none"><p id="UIDD" style="color:orange;">'+data.u_id+'</p></li>'+
					'<li><p style="color:orange;">'+data.u_name+'</p></li>'+
					'<li><a onclick="my();" href="#"><p>我的账户</p></a></li>'+
					'<li><a href="zhuce.html"><p>注册</p></a></li>'+
					'<li><a onclick="logout();" href="#"><p>退出</p></a></li>'+
				'</ul>'
			
			);
			$("#liuyan").html(
					'<div class="content">'+
					'<div class="form-box">'+
						'<form id="datafrom" action="" method="post" enctype="multipart/form-data">'+
								'<input style="float: left;width:100%;" type="text" id="m_title" placeholder="请输入标题..."/>'+
								'<textarea id="sendtxt" name="sendtxt" placeholder="请输入留言内容..."></textarea>'+
								'<div class="filebox" style="width: 135px;">'+
									'<input type="file" name="file" id="imgfile"  />'+
									'<span>选择图片</span>'+
								'</div>'+
								'<input style="float: right" type="button" value="发布" onclick="sendData()" />'+
						'</form>'+
					'</div>'+
				'</div>'
			);
		}
	})
}
function sendData(){
	var formData = new FormData();
	formData.append('u_id',$("#UIDD").html());
	formData.append('m_title',$("#m_title").val());
	formData.append('file',$("#imgfile").get(0).files[0]);
	formData.append('m_info',$("#sendtxt").val());
	$.ajax({
		url:"/ssm/addMessage",
		type:"POST",
		dataType:"json",
		data:formData,
		processData: false,
        contentType: false,
		success : function(data) {
			if (data=="1"){
				showmessages();
			}
		},
		error : function(err) {
			console.log(err);
		}
	});
}
function logout(){
	$.ajax({
		url:"/ssm/logout",
		type:"POST",
		dataType:"json",
		success:function(data){
			window.location.href = '/ssm/main/';
		}
	});
}
function my(){
	$.ajax({
		url:"/ssm/getKey",
		type:"POST",
		dataType:"json",
		success:function(data){
			if(data.isroot=="1"){
				window.location.href = '/ssm/admin/';
			}
			if(data.isroot=="0"){
				window.location.href = '/ssm/user/';
			}
		}
	})
}
	