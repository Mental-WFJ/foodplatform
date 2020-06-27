<%--
  Created by IntelliJ IDEA.
  User: Super_Family
  Date: 2020/5/6
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
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
        $(function() {
            common.showMessage($("#message").val());
        });
    </script>

</head>
<body>
<c:set var="applicant" value="${sessionScope.applicant}"/>
<c:set var="business" value="${sessionScope.business}"/>
<form action="${basePath}/business/updateInfo/${applicant.id}/${business.id}" method="post" enctype="multipart/form-data">
    <input type="hidden" id="message" value="${message}"/>
    <table height="560px" width="100%" style="text-align: center; color: black; font-size: 20px; margin-top: 30px; background-color: white" id="mainPage">
        <tr height="100px">
            <th colspan="4" style="font-size: 30px">商户信息</th>
        </tr>
        <tr height="70px">
            <td width="120px" style="text-align: right">店铺标题：</td>
            <td width="230px"><input type="text" id="title" name="title" style="width: 300px; height: 40px"
                                     value="${business.title}"></td>
            <td width="140px" style="text-align: right">店铺副标题：</td>
            <td width="230px"><input type="text" id="subtitle" name="subtitle" style="width: 400px; height: 40px"
                                     value="${business.subtitle}"></td>
        </tr>
        <tr height="70px">
            <td style="text-align: right">城市：</td>
            <td><select id="city" name="city" style="width: 200px; height: 40px">
                <c:forEach var="item" items="${cityList}">
                    <c:if test="${item.city == business.city}">
                        <option value="${item.id}" selected="selected">${item.city}</option>
                    </c:if>
                    <c:if test="${item.city != business.city}">
                        <option value="${item.id}">${item.city}</option>
                    </c:if>
                </c:forEach>
            </select></td>
            <td style="text-align: right">店铺类别：</td>
            <td><select id="category" name="category" style="width: 200px; height: 40px">
                <c:forEach var="item" items="${categoryList}">
                    <c:if test="${item.type == business.category}">
                        <option value="${item.id}" selected="selected">${item.type}</option>
                    </c:if>
                    <c:if test="${item.type != business.category}">
                        <option value="${item.id}">${item.type}</option>
                    </c:if>
                </c:forEach>
            </select></td>
        </tr>
        <tr style="height: 70px;">
            <td style="text-align: right">店铺地址:</td>
            <td colspan="3"><label id="address" name="address"
                                   style="width: 900px;height: 40px">${business.address}</label></td>
        </tr>
        <tr style="height: 70px;">
            <c:if test="${business.imgFileName != null}">
                <td colspan="1"><a href="http://${business.imgFileName}">店铺图标：</a></td>
            </c:if>
            <c:if test="${business.imgFileName == null}">
                <td colspan="1">店铺图标：</td>
            </c:if>
            <td colspan="3"><input type="file" id="imgFile" name="imgFile"></td>
        </tr>
        <tr style="height: 70px;">
            <td colspan="1" style="text-align: right">店铺简介：</td>
            <td colspan="3"><input type="text" id="desc" name="desc" style="width: 900px; height: 40px"
                                   value="${business.desc}"></td>
        </tr>
        <tr style="height: 70px;">
            <td colspan="4"><input type="submit" style="height: 40px; width: 200px;" value="提交修改" id="busConfirm"
                                   name="busConfirm"></td>
        </tr>
    </table>
</form>
</body>
</html>
