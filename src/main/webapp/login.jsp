<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>登录界面</title>

<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
</head>

<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="all-admin-index.html"><b>码农招聘平台</b>超强3合1登录界面</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">登录系统</p>

			<form action="${pageContext.request.contextPath}/login.do" method="post">
				<div class="form-group has-feedback">
					<input id="userName" type="text" name="username" class="form-control"
						placeholder="用户名"> <span
						class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input id="password" type="password" name="password" class="form-control"
						placeholder="密码"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div  class="form-group has-feedback">
					<select id="userRole" name="role">
						<option value="user" selected>user</option>
						<option value="boss">boss</option>
						<option value="admin">admin</option>

					</select>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<a href="signUp.jsp">没有账号?注册一个</a>
					</div>
					<!-- /.col class="btn btn-primary btn-block btn-flat"-->
					<div class="col-xs-4">
						<div id="submitBtn" class="btn btn-lg btn-default">登录</div>

					</div>
					<!-- /.col -->
				</div>
			</form>

			<a href="#" id="upBtn">人脸登录</a><br>


		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 2.2.3 -->
	<!-- Bootstrap 3.3.6 -->
	<!-- iCheck -->
	<script
		src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
	<script src="ui/layui.js"></script>
	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
			$('#submitBtn').click(function () {
				var userInfo=new Object();
				userInfo.username=$("#userName").val();
				userInfo.role=$("#userRole").val();
				var jsonString=JSON.stringify(userInfo);
				$("#userName").val(jsonString);
				console.log(jsonString);
				$("form").submit();
			});
		});
		//上传
		layui.use(["upload"],function(){
			var upload=layui.upload;
			var upper=upload.render({
				elem:"#upBtn",
				data:{username: function(){
						return $('#userName').val();
					}},
				url:"faceLogin.do",
				done:function(res){
					if(res=="0"){
						alert("登录失败");
					}else {
						$("#password").val(res);
						alert("登录成功");
					}
				},
				error:function(){
					alert("登录失败");
				}
			});
		});
	</script>
</body>

</html>