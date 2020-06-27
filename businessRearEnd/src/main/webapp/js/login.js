$(function() {

	common.showMessage($("#message").val());
    // 验证信息
	$("#mainForm").validate({
		rules:{
			"phone" : {
				required : true
			},
			"password" : {
				required : true
			}
		}
	});
	
	// 单击登录
	$("#submit_login").click(function () {
		$("#mainForm").submit();
    })
});