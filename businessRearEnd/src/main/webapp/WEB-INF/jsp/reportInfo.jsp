<%--
  Created by IntelliJ IDEA.
  User: Super_Family
  Date: 2020/5/8
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
    <title></title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/all.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/pop.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/main.css"/>
    <script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${basePath}/js/common/common.js"></script>
    <script type="text/javascript" src="${basePath}/js/common/echarts.js"></script>

    <script type="text/javascript">
        $(function() {
            var myChart = echarts.init(document.getElementById('report'));
            common.ajax({
                url : "${basePath}/report/showReport/${sessionScope.business.id}",
                success : function(data) {
                    var option = {
                        title: {
                            text: '营业额报表'
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        toolbox: {
                            feature: {
                                saveAsImage: {}
                            }
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false
                        },
                        yAxis: {
                            type: 'value'
                        }
                    };
                    $.extend(true,option,data);
                    myChart.setOption(option);
                },
                type : 'GET'
            });
        });
    </script>
</head>
<body style="background: #e1e9eb;">
<input type="hidden" id="basePath" value="${basePath}"/>
<div id="report" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
</body>
</html>