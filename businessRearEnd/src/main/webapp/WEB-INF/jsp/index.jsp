<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
    <title>大众点评商户管理平台</title>
    <link href="${basePath}/css/all.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}/css/pop.css" rel="stylesheet" type="text/css"/>
    <link href="${basePath}/css/index.css" rel="stylesheet" type="text/css"/>
    <script src="${basePath}/js/common/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="${basePath}/js/common/common.js" type="text/javascript"></script>
    <script src="${basePath}/js/common/json.js" type="text/javascript"></script>

    <script type="text/javascript">

        /**
         * 方法描述:单击子菜单（页面左部菜单），初始化主页面
         */
        function clickSubMenu(element, path) {
            // 将其他有[选中样式]的节点的样式清空
            $("#subMenuDiv").find(".on").attr("class", "");
            // 将当前单击的节点置为[选中样式]
            $(element).children().attr("class", "on");
            // 按指定地址加载主页面(iframe)
            $("#mainPage").attr("src", $("#basePath").val() + path);
        }

        /**
         * 方法描述:单击菜单（页面上部菜单），初始化子菜单（即页面左部菜单）
         */
        function clickMenu(element) {
            // 将同级节点的[选中样式]清空
            $("#menuDiv").children().attr("class", "");
            // 将当前单击的节点置为[选中样式]
            $(element).attr("class", "on");
        }

        /**
         * 打开密码修改弹出层
         */
        function openAddDiv() {
            $("#mengban").css("visibility", "visible");
            $(".wishlistBox").show();
            $(".wishlistBox").find(".persongRightTit").html("&nbsp;&nbsp;修改密码");
            $("#submitVal").show();
        }

        /**
         * 关闭密码修改弹出层
         */
        function closeDiv() {
            $("#mengban").css("visibility", "hidden");
            $("#oldPassword").val("");
            $("#newPassword").val("");
            $("#newPasswordAgain").val("");
            $(".wishlistBox").hide();
        }


        /***
         * 提交修改密码操作
         */
        function checkPwd() {

            $.ajax({
                type: "POST",
                datatype: "json",
                url: "${basePath}/applicant/changePwd/${sessionScope.applicant.id}",
                data: {
                    pwd: $("#oldPassword").val(),
                    pwd2: $("#newPassword").val(),
                    pwd3: $("#newPasswordAgain").val(),
                },
                success: function (data) {
                    if (data == 1)
                        alert("修改密码成功！");
                    else if (data == 2)
                        alert("两次密码输入不同!");
                    else if (data == 0)
                        alert("修改密码失败！");
                    $("#oldPassword").val("");
                    $("#newPassword").val("");
                    $("#newPasswordAgain").val("");
                },
                error: function () {
                    alert("调用方法失败");
                }
            });
        }
    </script>
</head>
<body>

<c:set var="applicant" value="${sessionScope.applicant}"/>
<c:set var="business" value="${sessionScope.business}"/>
<!-- 蒙版DIV -->
<div id="mengban" style="display:none"></div>
<input type="hidden" id="basePath" value="${basePath}"/>
<div class="wishlistBox" style="display: none;left:550px;top:200px;">
    <div class="personRigTop persongBgimg" style="height:200px;width:480px;">
        <div class="persongRightTit" style="width:480px;">&nbsp;&nbsp;修改密码</div>
        <div class="persongRigCon">
            <form name="redisAddOrEditForm" action="${basePath}/applicant/changePwd/${applicant.id}" method="post">
                <table class="x-form-table">
                    <tbody>
                    <tr class="line">
                        <td class="left" width="10%"><em class="required">*</em><label>原始密码：</label></td>
                        <td width="90%">
                            <input class="normal-input" name="oldPassword" id="oldPassword" style="width: 240px;"
                                   type="password"/>
                        </td>
                    </tr>
                    <tr class="line">
                        <td class="left"><label><em class="required">*</em>新密码：</label></td>
                        <td>
                            <input class="normal-input" name="newPassword" id="newPassword" style="width: 240px;"
                                   type="password"/>
                        </td>
                    </tr>
                    <tr class="line">
                        <td class="left"><em class="required">*</em><label>确认新密码：</label></td>
                        <td>
                            <input class="normal-input" name="newPasswordAgain" id="newPasswordAgain"
                                   style="width: 240px;" type="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="left"></td>
                        <td class="submit">
                            <input id="submitVal" class="tabSub" value="提交" onclick="checkPwd();" type="button"/>
                            <input class="tabSub" value="关闭" onclick="closeDiv();" type="reset"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

<form method="post" action="${basePath}/index/logout" id="mainForm">
    <div id="header">
        <div class="iheader">
            <div class="logo"><a href="#"><img src="" alt="" height="88px" width="99px"/></a></div>
            <div style="height: 44px;">
                <div class="wuxianlogo"><img src="" alt="" height="28px" width="275px"/></div>
                <div class="h_info">
                    <span class="line"></span>

                    欢迎您！${applicant.applicantName} &nbsp; 当前时间：
                    <%
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    %><%=sdf.format(date)%>&nbsp;&nbsp;&nbsp;&nbsp;
                    <!--<a href="javascript:void(0);" onclick="openAddDiv();">[修改密码]</a>
                    &nbsp;-->
                    <a href="javascript:void(0);"
                       onclick="if(confirm('您确认退出系统?')){$('#mainForm').submit();};">[退出系统]</a>
                </div>
            </div>
            <ul class="nav" id="menuDiv">
                <li onclick="clickMenu(this)"><a><span>商户信息</span></a></li>
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
                            <h3 onclick="clickSubMenu(this, '/business/showInfo')"><a>基本信息</a></h3>
                            <h3 onclick="clickSubMenu(this, '/order/showOrder/${business.id}')"><a>订单信息</a></h3>
                            <h3 onclick="clickSubMenu(this, '/report')"><a>报表信息</a></h3>
                        </div>
                    </div>
                </td>
                <td width="7">
                    <div class="pointer"></div>
                </td>
                <td style="vertical-align:top" height="600px" width="100%">
                    <br/>
                    <iframe id="mainPage" src="" frameborder="0" height="580px" width="100%"></iframe>
                    <br/>
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