	$(function(){
		$.showLoading("数据加载中...");
		$.ajax({
			url:'getCusInfo',
			type:'post',
			dataType:'json',
			success:function(data){
				$.hideLoading();
				if(data == 'null'){
					$.tooltip("用户不存在");
				}else if(data == 'exception'){
					$.tooltip("出现异常");
				}else{
					$("#firstName").html(data['customer']['firstName']);
					$("#lastName").html(data['customer']['lastName']);
					$("#email").html(data['customer']['email']);
					var date = new Date();
					date.setTime(parseInt(data['customer']['createDate']));
					$("#createDate").html(date.toLocaleDateString()+" "+date.toLocaleTimeString());
					date.setTime(parseInt(data['customer']['lastUpdate']));
					$("#lastUpdate").html(date.toLocaleDateString()+" "+date.toLocaleTimeString());
					
					$("#addressId").val(data['address']['addressId']);
					$("#country").html(data['country']['country']);
					$("#city").html(data['city']['city']);
					$("#address").html(data['address']['address']);
					$("#address2").html(data['address']['address2']);
					$("#district").html(data['address']['district']);
					$("#phone").html(data['address']['phone']);
					$("#postalCode").html(data['address']['postalCode']);
					date.setTime(parseInt(data['address']['lastUpdate']));
					$("#lastUpdateAddr").html(date.toLocaleDateString()+" "+date.toLocaleTimeString());
					
					
					$.tooltip('数据获取成功！', 2500, true);
				}
			},
			error:function(){
				
			}
		});
	
	$("#selectCountry").change(function(){
		var countryId = $(this).children('option:selected').val();
		$.ajax({
			url:'getCity',
			type:'post',
			data:'countryId='+countryId,
			dataType:'json',
			success:function(data){
				$("#selectCity").html("");
				$.each(data,function(n,value){
	                $("#selectCity").append("<option value='"+value['cityId']+"'>"+value['city']+"</option>");
	            });
			},
			error:function(){
				
			}
		});
	});
	$("#inputFirstName").blur(function(){
		if($("#inputFirstName").val().trim() == "")
			$("#errorFirstName").show();
	});
	$("#inputFirstName").focus(function(){
		$("#errorFirstName").hide();
	});
	$("#inputLastName").blur(function(){
		if($("#inputLastName").val().trim() == "")
			$("#errorLastName").show();
	});
	$("#inputLastName").focus(function(){
		$("#errorLastName").hide();
	});
	
	$("#editBasicInfoModal").on('hidden.bs.modal',function(e){
		$("#errorFirstName").hide();
		$("#errorLastName").hide();
	});
	$("#editAddressModal").on('hidden.bs.modal',function(e){
		$("#errorAddress").hide();
		$("#errorDistrict").hide();
		$("#errorPhone").hide();
	});
});
function editBasicInfo(){
	$("#inputFirstName").val($("#firstName").text());
	$("#inputLastName").val($("#lastName").text());
	$("#inputEmail").val($("#email").text());
	$("#editBasicInfoModal").modal("show");
}
function editAddress(){
	$.ajax({
		url:'getCountry',
		type:'post',
		dataType:'json',
		success:function(data){
			var countryId;
			$.each(data,function(n,value){
				if(value['country'] == $("#country").text()){
					countryId = value['countryId'];
					$("#selectCountry").append("<option value='"+value['countryId']+"' selected='selected'>"+value['country']+"</option>");
				}
				else
					$("#selectCountry").append("<option value='"+value['countryId']+"'>"+value['country']+"</option>");
			});
			$.ajax({
	            url:'getCity',
	            type:'post',
	            data:'countryId='+countryId,
	            dataType:'json',
	            success:function(data){
	                $("#selectCity").html("");
	                $.each(data,function(n,value){
	                	if(value['city'] == $("#city").text())
	                		$("#selectCity").append("<option value='"+value['cityId']+"' selected='selected'>"+value['city']+"</option>");
	                	else
	                		$("#selectCity").append("<option value='"+value['cityId']+"'>"+value['city']+"</option>");
	                });
	            },
	            error:function(){
	                
	            }
	        });
		},
		error:function(){
			
		}
	});
	$("#inputAddress").val($("#address").text());
	$("#inputAddress2").val($("#address2").text());
	$("#inputDistrict").val($("#district").text());
	$("#inputPhone").val($("#phone").text());
	$("#inputPostalCode").val($("#postalCode").text());
	$("#editAddressModal").modal("show");
}
function saveBasicChange(){
	if($("#inputFirstName").val().trim() == $("#firstName").text()
	&& $("#inputLastName").val().trim() == $("#lastName").text()
	&& $("#inputEmail").val().trim() == $("#email").text()){
		$("#editBasicInfoModal").modal("hide");
		$.tooltip('信息未作修改！', 2500, true);
	}else{
		if($("#inputFirstName").val().trim() != ""
			&& $("#inputLastName").val().trim() != ""){
			$.ajax({
				url:'changeBasicInfo',
				type:'post',
				data:'firstName='+$("#inputFirstName").val()
					+'&lastName='+$("#inputLastName").val()
					+'&email='+$("#inputEmail").val(),
				success:function(data){
					if(data == "success1"){
						$.tooltip('操作成功！请使用新用户名重新登陆', 2500, true,function(){
							window.location.href="index.jsp";
						});
					}else if(data == "success"){
						$.tooltip('操作成功！', 2500, true,function(){
							window.location.reload();
						});
					}else if(data == "firstNameExist"){
						$.tooltip("新用户名一存在，请更换First Name");
					}else if(data == "Exception"){
						$.tooltip("出错了！！！");
					}
				},
				error:function(data){
					$.tooltip("出错了！！！");
				}
			});
		}
	}
}
$("#inputAddress").blur(function(){
	if($("#inputAddress").val().trim() == ""){
		$("#errorAddress").show();
	}
});
$("#inputAddress").focus(function(){
	$("#errorAddress").hide();
});
$("#inputDistrict").blur(function(){
	if($("#inputDistrict").val().trim() == ""){
		$("#errorDistrict").show();
	}
});
$("#inputDistrict").focus(function(){
	$("#errorDistrict").hide();
});
$("#inputPhone").blur(function(){
	if($("#inputPhone").val().trim() == ""){
		$("#errorPhone").show();
	}
});
$("#inputPhone").focus(function(){
	$("#errorPhone").hide();
});
function saveAddressChange(){
	if($("#inputAddress").val().trim() == $("#address").text()
			&& $("#inputAddress2").val().trim() == $("#address2").text()
			&& $("#inputDistrict").val().trim() == $("#district").text()
			&& $("#inputPostalCode").val().trim() == $("#postalCode").text()
			&& $("#selectCity").find("option:selected").text().trim() == $("#city").text()
			&& $("#inputPhone").val().trim() == $("#phone").text()){
				$("#editAddressModal").modal("hide");
				$.tooltip('信息未作修改！', 2500, true);
	}else if($("#inputAddress").val().trim() != ""
		&& $("#inputDistrict").val().trim() != ""
		&& $("#inputPhone").val().trim() != ""){
		var formParam = $("#formEditAddress").serialize();
		$.ajax({
			url:'changeAddress',
			type:'post',
			data:formParam,
			success:function(data){
				if(data == "success"){
					$.tooltip('操作成功！', 2500, true,function(){
						window.location.reload();
					});
				}else if(data == "Exception"){
					$.tooltip("出错了！！！");
				}else if(data == "faild"){
					$.tooltip("更新失败了！！！");
				}
			},
			error:function(){
				$.tooltip("出错了！！！");
			}
		});
	}
}