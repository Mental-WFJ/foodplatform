<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <title>大众点评商户管理平台</title>
	    <link rel="stylesheet" type="text/css" href="${basePath}/css/login.css" />
	    <link rel="stylesheet" type="text/css" href="${basePath}/css/jquery.validate.css" />
	    <script src="${basePath}/js/common/jquery-1.8.3.js" type="text/javascript"></script>
	    <script src="${basePath}/js/common/jQuery.md5.js" type="text/javascript"></script>
	    <script src="${basePath}/js/common/validation/jquery.validate.min.js" type="text/javascript"></script>
	    <script src="${basePath}/js/common/validation/messages_zh.js" type="text/javascript"></script>
	    <script src="${basePath}/js/common/common.js" type="text/javascript"></script>
	    <script src="${basePath}/js/login.js" type="text/javascript"></script>
	</head>
	<body>
		<input type="hidden" id="basePath" value="${basePath}"/>
		<input type="hidden" id="message" value="${pageCode.msg}"/>
		<div class="main">
		    <div class="header hide"></div>
		    <div class="content">
		        <div class="title hide"></div>
		        <form id="mainForm" method="post" action="${basePath}/applicant/login">
		            <fieldset>
		                <div class="input">
		                    <input class="input_all name" name="phone" id="phone" placeholder="手机号" type="text" onFocus="this.className='input_all name_now';" onBlur="this.className='input_all name'"/>
		                </div>
		                <div class="input">
		                    <input class="input_all password" id="password" name="password" type="password" placeholder="密码" onFocus="this.className='input_all password_now';" onBlur="this.className='input_all password'"/>
		                </div>
		                <div class="checkbox">
		                    <input type="checkbox" name="remember" id="remember" /><label for="remember"><span>记住密码</span></label>
		                </div>
		                <div class="enter">
		                    <input class="button hide" type="button" id="submit_login" value="登录" />
		                </div>
		            </fieldset>
		        </form>
		    </div>
		</div>
	</body>
</html>