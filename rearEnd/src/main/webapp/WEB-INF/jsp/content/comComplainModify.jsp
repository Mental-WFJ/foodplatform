<%--
  Created by IntelliJ IDEA.
  User: Super_Family
  Date: 2020/5/7
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/all.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/pop.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/jquery.validate.css"/>
    <script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${basePath}/js/common/validation/jquery.validate.js"></script>
    <script type="text/javascript" src="${basePath}/js/common/validation/messages_zh.js"></script>
    <script type="text/javascript" src="${basePath}/js/common/common.js"></script>
    <script type="text/javascript" src="${basePath}/js/content/adModify.js"></script>

    <script type="text/javascript">
        $(function() {
            common.showMessage($("#message").val());
        });

        function pass(id) {
            location.href = "${basePath}/complain/comModify/" + id + "/1";
        }

        function fail(id) {
            location.href = "${basePath}/complain/comModify/" + id + "/2";
        }
    </script>
</head>
<body style="background: #e1e9eb;">
<form id="mainForm" method="post" action="">
    <input type="hidden" name="id" value="${modifyObj.id}"/>
    <input type="hidden" id="message" value="${pageCode.msg}"/>
    <input type="hidden" id="basePath" value="${basePath}"/>
    <div class="right">
        <div class="current">当前位置：<a href="###">内容管理</a> &gt; 评论投诉管理</div>
        <div class="rightCont">
            <p class="g_title fix">查看内容</p>
            <table class="tab1" width="100%">
                <tbody>
                <tr>
                    <td align="right" width="10%">评论内容：</td>
                    <td width="30%">
                        <label width="100%">${modifyObj.comment.comment}</label>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="10%">投诉用户：</td>
                    <td width="30%">
                        <label width="100%">${modifyObj.member.name}</label>
                    </td>
                    <td align="right" width="10%">联系方式：</td>
                    <td width="30%">
                        <label width="100%">${modifyObj.member.phone}</label>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="10%">投诉原因：</td>
                    <td width="30%">
                        <label width="100%">${modifyObj.reason}</label>
                    </td>
                </tr>
                </tbody>
            </table>
            <div style="text-align: center; margin-top: 30px;">
                <input class="tabSub" value="通     过" type="button" onclick="pass('${modifyObj.id}');"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="tabSub" value="未 通 过" type="button" onclick="fail('${modifyObj.id}');"/>
            </div>
        </div>
    </div>
</form>
</body>
</html>
