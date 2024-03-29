<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
	    <title>大众点评后台管理</title>
	    <link href="${basePath}/css/all.css" rel="stylesheet" type="text/css"/>
	    <link href="${basePath}/css/pop.css" rel="stylesheet" type="text/css"/>
	    <link href="${basePath}/css/index.css" rel="stylesheet" type="text/css"/>
	    <script src="${basePath}/js/common/jquery-1.8.3.js" type="text/javascript"></script>
	    <script src="${basePath}/js/common/common.js" type="text/javascript"></script>
	    <script src="${basePath}/js/common/json.js" type="text/javascript"></script>
	    <script src="${basePath}/js/system/index.js" type="text/javascript"></script>
	<script type="text/javascript">

		/***
		 * 提交修改密码操作
		 */
		// function checkPwd() {
		// 	$("#redisAddOrEditForm").submit();
		// }
	</script>
	</head>
	<body>
		<c:set var="user" value="${sessionScope.user}"/>
		<!-- 蒙版DIV -->
		<div id="mengban" style="display:none"></div>
		<input type="hidden" id="basePath" value="${basePath}"/>
		<div class="wishlistBox" style="display: none;left:550px;top:200px;">
		    <div class="personRigTop persongBgimg" style="height:200px;width:480px;">
		        <div class="persongRightTit" style="width:480px;">&nbsp;&nbsp;修改密码</div>
		        <div class="persongRigCon">
		            <form name="redisAddOrEditForm" action="${basePath}/users/pwd/${user.id}" method="post">
		                <table class="x-form-table">
		                    <tbody>
		                    <tr class="line">
		                        <td class="left" width="10%"><em class="required">*</em><label>原始密码：</label></td>
		                        <td width="90%">
		                            <input class="normal-input" name="oldPassword" id="oldPassword" style="width: 240px;" type="password"/>
		                        </td>
		                    </tr>
		                    <tr class="line">
		                        <td class="left"><label><em class="required">*</em>新密码：</label></td>
		                        <td>
		                            <input class="normal-input" name="newPassword" id="newPassword" style="width: 240px;" type="password"/>
		                        </td>
		                    </tr>
		                    <tr class="line">
		                        <td class="left"><em class="required">*</em><label>确认新密码：</label></td>
		                        <td>
		                            <input class="normal-input" name="newPasswordAgain" id="newPasswordAgain" style="width: 240px;" type="password"/>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="left"></td>
		                        <td class="submit">
		                            <input id="submitVal" class="tabSub" value="提交" type="submit"/>
		                            <input class="tabSub" value="关闭" onclick="closeDiv();" type="reset"/>
		                        </td>
		                    </tr>
		                    </tbody>
		                </table>
		            </form>
		        </div>
		    </div>
		</div>
		
		<form method="post" action="${basePath}/session" id="mainForm">
			<input type="hidden" name="_method" value="DELETE"/>
		    <div id="header">
		        <div class="iheader">
		            <div class="logo"><a href="#"><img src="" alt="" height="88px" width="99px"/></a> </div>
		            <div style="height: 44px;">
		                <div class="wuxianlogo"><img src="" alt="" height="28px" width="275px"/></div>
		                <div class="h_info">
		                    <span class="line"></span>

							欢迎您！${user.chName} &nbsp; 当前时间：
							<%
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						%><%=sdf.format(date)%>&nbsp;&nbsp;&nbsp;&nbsp;
		                    <!--<a href="javascript:void(0);" onclick="openAddDiv();">[修改密码]</a>
		                    &nbsp;-->
							<a href="javascript:void(0);" onclick="openAddDiv();">[修改密码]</a>
							&nbsp;
		                    <a href="javascript:void(0);" onclick="if(confirm('您确认退出系统?')){$('#mainForm').submit();};">[退出系统]</a>
		                </div>
		            </div>
		            <ul class="nav" id="menuDiv">
		            </ul>
		        </div>
		    </div>
		    <div id="container">
		        <table style="vertical-align:top" cellspacing="0" cellpadding="0" bgcolor="#e1e9eb" border="0">
		            <tbody>
		            <tr>
		                <td class="leftTd" style="vertical-align:top" width="150">
		                    <div class="left">
		                        <div class="ileft" id="subMenuDiv">
		                        	
		                        </div>
		                    </div>
		                </td>
		                <td width="7">
		                    <div class="pointer"></div>
		                </td>
		                <td style="vertical-align:top" height="600px" width="100%">
		                	<br/><iframe id="mainPage" src="" frameborder="0" height="580px" width="100%"></iframe><br/>
		                 </td>
		            </tr>
		            </tbody>
		        </table>
		    </div>
		    <div id="footer">
		    </div>
		</form>
	</body>
</html>