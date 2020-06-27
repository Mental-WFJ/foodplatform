<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/all.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/pop.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/main.css"/>
    <script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${basePath}/js/common/common.js"></script>

    <script type="text/javascript">

        $(function() {
            common.showMessage($("#message").val());
        });

        function search(currentPage) {
            $("#currentPage").val(currentPage);
            $("#mainForm").submit();
        }

    </script>
</head>
<body style="background: #e1e9eb;">
<form action="${basePath}/businesses/getList" id="mainForm" method="post">
    <input type="hidden" id="id" name="id"/>
    <input type="hidden" id="basePath" value="${basePath}"/>
    <input type="hidden" name="page.currentPage" id="currentPage" value="1"/>
    <div class="right">
        <div class="current">当前位置：<a href="#">内容管理</a> &gt; 商户管理</div>
        <div class="rightCont">
            <p class="g_title fix">商户列表</p>
            <table class="tab1">
                <tbody>
                <tr>
                    <td align="right" width="80">标题：</td>
                    <td>
                        <input name="title" id="title" value="${searchParam.title}" class="allInput" type="text"/>
                    </td>
                    <td style="text-align: right;" width="150">
                        <input class="tabSub" value="查询" onclick="search('1');" type="button"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="zixun fix">
                <table class="tab2" width="100%">
                    <tbody>
                    <tr>
                        <th>序号</th>
                        <th>标题</th>
                        <th>副标题</th>
                        <th>城市</th>
                        <th>类别</th>
                        <th>操作</th>
                    </tr>

                    <c:forEach items="${list}" var="item" varStatus="s">
                        <tr>
                            <td>${s.index + 1}</td>
                            <td>${item.title}</td>
                            <td>${item.subtitle}</td>
                            <td>${item.city}</td>
                            <td>${item.category}</td>
                            <td>
                                <c:if test="${item.useable == 0}">
                                    <a href="${basePath}/businesses/modify/${item.id}/1">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                </c:if>
                                <c:if test="${item.useable == 1}">
                                    <a href="${basePath}/businesses/modify/${item.id}/0">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <!-- 分页 -->
                <t:page jsMethodName="search" page="${searchParam.page}"></t:page>
            </div>
        </div>
    </div>
</form>
</body>
</html>