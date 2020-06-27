<%--
  Created by IntelliJ IDEA.
  User: Super_Family
  Date: 2020/5/6
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
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

        function search(currentPage) {
            $("#currentPage").val(currentPage);
            $("#mainForm").submit();
        }
    </script>
</head>
<body>
<form action="${basePath}/order/showOrder/${sessionScope.business.id}" id="mainForm" method="post" style="background-color: white">
    <input type="hidden" name="page.currentPage" id="currentPage" value="1"/>
    <table width="100%" height="570px" style="background-color: white; margin-left: 30px">
        <c:forEach var="comment" items="${commentList}">
            <tr>
                <td>
                    <label>
                        用户：
                        <c:if test="${comment.orders.member.name != null}">
                            ${comment.orders.member.name}
                        </c:if>
                        <c:if test="${comment.orders.member.name == null}">
                            ${comment.orders.member.phone}
                        </c:if>&nbsp;&nbsp;&nbsp;
                        时间：${comment.createTime}
                        &nbsp;&nbsp;&nbsp;

                        人数：${comment.orders.num}&nbsp;&nbsp;&nbsp;
                        金额：${comment.orders.price}&nbsp;&nbsp;&nbsp;
                        星级：<c:if test="${comment.star != null}">${comment.star}</c:if>⭐
                    </label><br/>
                    <label>
                        评论内容：${comment.comment}
                    </label>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="button" onclick="search('1');" value="查询"/></td>
        </tr>
    </table>

    <!-- 分页 -->
    <t:page jsMethodName="search" page="${searchParam.page}"></t:page>
</form>
</body>
</html>
