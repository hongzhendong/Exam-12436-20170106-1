function checkUserName(){
	userName = $("#username").val();
	$.ajax({
		url:'checkUserName',
		type:'post',
		data:'userName='+$("#username").val(),
		success:function(data){
			if(data == "true"){
				$("#result").removeClass("glyphicon glyphicon-remove");
				$("#result").addClass("glyphicon glyphicon-ok");
				$("#resultDiv").show();
			}else{
				$("#result").removeClass("glyphicon glyphicon-ok");
				$("#result").addClass("glyphicon glyphicon-remove");
				$("#resultDiv").show();
			}
		}
	});
}
function login(){
	$("#process").show();
	$("#errorTip").hide();
	var param = $("#loginForm").serialize();
	$.ajax({
		url:'login',
		type:'post',
		data:param,
		success:function(data){
			$("#process").hide();
			if(data == "success"){
				$("#resultDiv").hide();
				$("#pwdResultDiv").hide();
				$("#errorTip").html("登录成功");
				$("#errorTip").show();
				$("#errorTip").removeClass("HTooltip shake animated alert alert-danger");
				$("#errorTip").addClass("HTooltip bounceInDown animated alert alert-success");
				var iv = setInterval(function(){
					$("#errorTip").hide();
					clearInterval(iv);
					//登录成功，跳转页面
					window.location.href="index.html";
				},2000);
			}else if(data == "fail"){
				$("#errorTip").html("用户名或密码错误");
				$("#errorTip").show();
				$("#errorTip").removeClass("HTooltip bounceInDown animated alert alert-success");
				$("#errorTip").addClass("HTooltip shake animated alert alert-danger");
			}else if(data == "exception"){
				$("#errorTip").html("登录异常");
				$("#errorTip").show();
				$("#errorTip").removeClass("HTooltip bounceInDown animated alert alert-success");
				$("#errorTip").addClass("HTooltip shake animated alert alert-danger");
			}
		},
		error:function(){
			alert("error");
		}
	});
}

