<%--
  Created by IntelliJ IDEA.
  User: Super_Family
  Date: 2020/4/23
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>大众美食点评网</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <!-- Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:400,700' rel='stylesheet' type='text/css'>

    <!-- Css -->
    <link rel="stylesheet" href="${basePath}/css/nivo-slider.css">
    <link rel="stylesheet" href="${basePath}/css/owl.carousel.css">
    <link rel="stylesheet" href="${basePath}/css/owl.theme.css">
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/css/style.css">
    <link rel="stylesheet" href="${basePath}/css/responsive.css">

    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.4&key=申请的key值&plugin=AMap.Driving"></script>


    <!-- jS -->
    <script src="${basePath}/js/jquery.min.js"></script>
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/jquery.nivo.slider.js"></script>
    <script src="${basePath}/js/owl.carousel.min.js"></script>
    <script src="${basePath}/js/jquery.nicescroll.js"></script>
    <script src="${basePath}/js/jquery.scrollUp.min.js"></script>
    <script src="${basePath}/js/main.js"></script>
    <script src="${basePath}/js/jquery-1.8.3.js"></script>
    <script src="${basePath}/js/jquery.form.js"></script>

    <script type="text/javascript">
        var codeNum = "";
        var memberId = ${sessionScope.member.id} +"";
        var businessId = ${business.id};
        var commentId = "";
        var commentTxt = "";

        function getCode() {
            for (var i = 0; i < 6; i++)
                codeNum += Math.floor(Math.random() * 10);
            console.log("code: " + codeNum);
            alert("code: " + codeNum);
        }


        function doRegister() {
            var phone = document.getElementById("phone_create").value;
            var code = document.getElementById("code_get").value;

            $('#create-account_form').ajaxSubmit({
                datatype: 'json',
                data: {phone: phone, code: code, codeNum: codeNum},
                success: function (respText) {
                    var resp = $.parseJSON(respText);
                    if (resp.errcode == 0) {
                        alert("注册成功！2s后跳转到主页！");
                        setTimeout(function () {
                            window.location.href = "${basePath}/webAPI/init";
                        }, 2000);
                    } else if (resp.errcode == 1) {
                        alert("用户名已注册，请直接登入！");
                        setTimeout(function () {
                            window.location.href = "${basePath}/webAPI/init";
                        }, 2000);
                    } else if (resp.errcode == 2) {
                        alert("验证码输入错误！");
                        setTimeout(function () {
                            window.location.href = "${basePath}/webAPI/init";
                        }, 2000);
                    }
                },
                error: function (xhr) {

                }
            });
        }

        function checkPhoneType() {
            var phone = $("#phone_create").val();
            var btn = document.getElementById("codeBtn");
            console.log(phone);
            if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))) {
                $("#mylabel").html("请输入正确的手机号");
                $("#mylabel").css("color", "red");
                btn.style.visibility = "hidden";
            } else {
                $("#mylabel").html("");
                btn.style.visibility = "visible";
            }
        }

        function doLogin() {

            $('#login_form').ajaxSubmit({
                datatype: 'json',
                data: { codeNum: codeNum},
                success: function (respText) {
                    var resp = $.parseJSON(respText);
                    if (resp.errcode == 0) {
                        alert("登录成功！2s后跳转到主页！");
                        setTimeout(function () {
                            window.location.href = "${basePath}/webAPI/init";
                        }, 2000);
                    } else if (resp.errcode == 1) {
                        alert("用户名未注册，请先注册！");
                        setTimeout(function () {
                            window.location.href = "${basePath}/webAPI/init";
                        }, 2000);
                    } else if (resp.errcode == 2) {
                        alert("密码错误！");
                        setTimeout(function () {
                            window.location.href = "${basePath}/webAPI/init";
                        }, 2000);
                    } else if (resp.errcode == 3) {
                        alert("验证码错误！");
                        setTimeout(function () {
                            window.location.href = "${basePath}/webAPI/init";
                        }, 2000);
                    }
                },
                error: function (xhr) {

                }
            });
        }

        function quit() {
            location.href = "${basePath}/webAPI/clear";
        }

        function tip() {
            alert("请登录！！！");
        }

        function addFav() {
            $.ajax({
                type: "POST",
                url: "${basePath}/favorite/add",
                data: {
                    memberId: memberId,
                    businessId: businessId
                },
                success: function (data) {
                    if (data)
                        alert("收藏成功！");
                    else
                        alert("收藏失败！");

                    location.href = "${basePath}/bussiness/article/" + businessId + "/" + memberId;
                },
                error: function () {
                }
            });
        }

        function delFav() {
            $.ajax({
                type: "POST",
                url: "${basePath}/favorite/delete",
                data: {
                    memberId: memberId,
                    businessId: businessId
                },
                success: function (data) {
                    if (data)
                        alert("取消收藏成功！");
                    else
                        alert("取消收藏失败！");

                    location.href = "${basePath}/bussiness/article/" + businessId + "/" + memberId;
                },
                error: function () {
                }
            });
        }

        function openCommentCompalin(id, txt) {
            commentId = id;
            commentTxt = txt;
            alert("CommentId: " + commentId + " CommentTxt: " + txt);
            var commentTxtlbl = document.getElementById("commentTxtLable");
            var commentIdlbl = document.getElementById("commentIdLabel");
            commentTxtlbl.innerText = "评论内容：" + txt;
            commentIdlbl.innerText = id;
            document.getElementById("ComComplain").style.display = "block";
        }

        function closeCommentCompalin() {
            commentId = "";
            $('#tipLabel').html('');
            document.getElementById("btnComConfirm").style.visibility = "visible";
            document.getElementById("comReason").value = "";
            document.getElementById("ComComplain").style.display = "none";
        }

        function openBusinessCompalin() {
            document.getElementById("busComplain").style.display = "block";
        }

        function closeBusinessCompalin() {
            document.getElementById("busReason").value = "";
            $('#tipLabel2').html('');
            document.getElementById("btnBusConfirm").style.visibility = "visible";
            document.getElementById("busComplain").style.display = "none";
        }

        function txtLength() {
            var txt = $('#comReason').val();
            if ($.trim(txt).length > 200 || $.trim(txt).length == 0) {
                $('#tipLabel').css('color', 'red').html('理由字数请 &gt; 0 , &lt; 200');
                document.getElementById("btnComConfirm").style.visibility = "hidden";
            } else {
                $('#tipLabel').html('');
                document.getElementById("btnComConfirm").style.visibility = "visible";
            }
        }

        function txtLength2() {
            var txt = $('#busReason').val();
            if ($.trim(txt).length > 200 || $.trim(txt).length == 0) {
                $('#tipLabel2').css('color', 'red').html('理由字数请 &gt; 0 , &lt; 200');
                document.getElementById("btnBusConfirm").style.visibility = "hidden";
            } else {
                $('#tipLabel2').html('');
                document.getElementById("btnBusConfirm").style.visibility = "visible";
            }
        }

        function addCom() {
            var reason = $('#comReason').val();

            $.ajax({
                type: "POST",
                url: "${basePath}/complain/addCom",
                data: {
                    commentId: commentId,
                    memberId: memberId,
                    reason: reason
                },
                success: function (data) {
                    if (data)
                        alert("投诉成功，管理员将进行核实！");
                    else
                        alert("投诉失败，请重新进行！");
                },
                error: function () {
                    alert("调用方法失败");
                }
            });
        }

        function addBus() {
            var reason = $('#busReason').val();

            $.ajax({
                type: "POST",
                url: "${basePath}/complain/addBus",
                data: {
                    businessId: businessId,
                    memberId: memberId,
                    reason: reason
                },
                success: function (data) {
                    if (data)
                        alert("投诉成功，管理员将进行核实！");
                    else
                        alert("投诉失败，请重新进行！");
                },
                error: function () {
                    alert("调用方法失败");
                }
            });
        }

        function openPayWin() {
            document.getElementById("payWin").style.display = "block";
        }

        function closePayWin() {
            document.getElementById("numTxt").value = "";
            document.getElementById("payTxt").value = "";
            document.getElementById("payWin").style.display = "none";
        }


        function toPay() {
            var money = document.getElementById("payTxt").value;
            var num = document.getElementById("numTxt").value;
            alert("number: " + num + " money: " + money);
            $.ajax({
                type: "POST",
                url: "${basePath}/bussiness/topay",
                datatype: 'json',
                data: {
                    businessId: businessId,
                    memberId: memberId,
                    num: num,
                    money: money
                },
                success: function (data) {
                    alert("正在跳转支付界面....");
                    document.getElementById("numTxt").value = "";
                    document.getElementById("payTxt").value = "";
                    document.getElementById("payWin").style.display = "none";
                },
                error: function () {
                    alert("调用方法失败");
                }
            });
        }


    </script>
</head>
<body>

<!-- TOP HEADER Start
    ================================================== -->

<section id="top">
    <div class="container">
        <div class="row">
            <div class="col-md-7">
                <p class="contact-action"><i class="fa fa-phone-square"></i>IN CASE OF ANY QUESTIONS, CALL THIS NUMBER:
                    <strong>+188 6666 1234</strong></p>
            </div>
            <div class="col-md-3 clearfix">
                <ul class="login-cart">
                    <li>
                        <c:set var="member" value="${sessionScope.member}"/>
                        <c:if test="${member.phone == null}">
                            <a data-toggle="modal" data-target="#myModal" href="#">
                                <i class="fa fa-user"></i>
                                login
                            </a>
                        </c:if>
                        <c:if test="${member.phone != null}">
                            <div class="cart dropdown">
                                <a data-toggle="dropdown" href="#"><i class="fa fa-shopping-cart"></i>
                                    <c:if test="${member.name == null}">
                                        ${member.phone}
                                    </c:if>
                                    <c:if test="${member.name != null}">
                                        ${member.name}
                                    </c:if>
                                </a>
                                <div class="dropdown-menu dropup">
                                    <span class="caret" style="text-align: center"></span>
                                    <button class="btn btn-primary btn-sm" onclick="quit()">Logout</button>
                                </div>
                            </div>
                        </c:if>
                    </li>
                </ul>
            </div>
        </div> <!-- End Of /.row -->
    </div>    <!-- End Of /.Container -->


    <!-- MODAL Start
        ================================================== -->

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Introduce Yourself</h4>
                </div>
                <div class="modal-body clearfix">
                    <form action="${basePath}/member/register" method="post" id="create-account_form" class="std">
                        <fieldset>
                            <h3>Create your account</h3>
                            <div class="form_content clearfix">
                                <h4>Enter your phone to create an account.</h4>
                                <label id="mylabel"></label>
                                <p class="text">
                                    <label for="phone_create">Phone</label>
                                    <span>
											<input placeholder="Phone" type="text" id="phone_create" name="phone_create"
                                                   value="" class="account_input" onblur="checkPhoneType()">
                                    </span>
                                </p>
                                <p class="text">
                                    <label for="code_get">Code</label>
                                    <span>
											<input placeholder="input code" type="text" id="code_get" name="code_get"
                                                   value="" class="account_input">
									    </span>
                                </p>
                                <p>
                                    <span class="btn btn-get" onclick="getCode()" id="codeBtn"
                                          name="codeBtn">Get Code</span>
                                    <button class="btn btn-primary" style="margin-left: 5px;" onclick="doRegister()">
                                        Create
                                    </button>
                                </p>
                            </div>
                        </fieldset>
                    </form>
                    <form action="${basePath}/member/memberLogin" method="post" id="login_form" class="std"
                          id="mainForm">
                        <fieldset>
                            <h3>Already registered?</h3>
                            <div class="form_content clearfix">
                                <h4>Enter your phone to login.</h4>
                                <p class="text">
                                    <label for="phone">Phone</label>
                                    <span><input style="margin-top: 4px;" placeholder="Phone" type="text" id="phone"
                                                 name="phone" value="" class="account_input"></span>
                                </p>
                                <p class="text">
                                    <label for="passwd">Password</label>
                                    <span><input style="margin-top: 5px;" placeholder="Password" type="password"
                                                 id="passwd" name="passwd" value="" class="account_input"></span>
                                </p>
                                <p class="submit">
                                    <span class="btn btn-get" onclick="getCode()" id="logCode"
                                          name="logCode">Get Code</span>
                                    <input type="button" class="btn btn-success" style="margin-top: 12px;" id="loginBtn" value="Log in" onclick="doLogin()"/>
                                </p>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</section>  <!-- End of /Section -->


<!-- LOGO Start
================================================== -->

<header>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <a href="#">
                    <img src="${basePath}/images/logo.png" alt="logo">
                </a>
            </div>    <!-- End of /.col-md-12 -->
        </div>    <!-- End of /.row -->
    </div>    <!-- End of /.container -->
</header> <!-- End of /Header -->


<!-- MENU Start
================================================== -->

<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div> <!-- End of /.navbar-header -->

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <c:set var="selectedCity" value="${sessionScope.selectedCity}"/>
            <ul class="nav navbar-nav nav-main">
                <c:if test="${selectedCity != null}">
                    <li><a href="${basePath}/webAPI/${selectedCity.id}">HOME</a></li>
                </c:if>
                <c:if test="${selectedCity == null}">
                    <li><a href="${basePath}/webAPI/init">HOME</a></li>
                </c:if>
                <c:if test="${member == null}">
                    <li><a href="${basePath}/bussiness/search/null/${selectedCity.id}/null/null">Restaurant</a></li>
                </c:if>
                <c:if test="${member != null}">
                    <li><a href="${basePath}/bussiness/search/${member.id}/${selectedCity.id}/null/null">Restaurant</a>
                    </li>
                </c:if>
                <li class="active"><a>ARTICLE</a></li>
                <li class="dropdown">
                    <a href="javascript:void(0)">
                        My CENTER
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <c:if test="${member.id == null}">
                            <li><a href="javascript:void(0)">FAVORITIES</a>
                            </li>
                            <li><a href="javascript:void(0)">My ORDERS</a></li>
                            <li><a href="javascript:void(0)">INFORMATION</a></li>
                        </c:if>
                        <c:if test="${member.id != null}">
                            <li><a href="${basePath}/favorite/search/${member.id}/${selectedCity.id}/null/null">FAVORITIES</a>
                            </li>
                            <li><a href="${basePath}/orders/getOrderList/${member.id}">My ORDERS</a></li>
                            <li><a href="javascript:void(0)" onclick="showInfo()">INFORMATION</a></li>
                        </c:if>
                    </ul>
                </li>
                <li>
                    <a href="${basePath}/webAPI/apply">JOIN US</a>
                </li><!-- End of /.dropdown -->
            </ul> <!-- End of /.nav-main -->
        </div>    <!-- /.navbar-collapse -->
    </div>    <!-- /.container-fluid -->
</nav>    <!-- End of /.nav -->

<div id="infoWin" class="infoWin" name="infoWin" style="display:none">
    <form action="" method="post">
        <span style="font-size: 20px;"> 用户个人信息 </span> <br/>
        <label class="label" style="font-size: 13px;color: black;margin-top: 3px"> 用户电话：${member.phone}</label><br/>
        <label class="label" style="font-size: 13px;color: black;margin-top: 3px"> &nbsp;&nbsp;用户：</label>
        <input type="text" id="nameTxt" value="${member.name}" style=";margin-top: 3px"/> <br/>
        <label class="label" style="font-size: 13px;color: black;margin-top: 3px"> 修改密码：</label>
        <input type="password" id="pwdTxt" style=";margin-top: 3px"/><br/>
        <label class="label" style="font-size: 13px;color: black;margin-top: 3px"> 再次确认：</label>
        <input type="password" id="pwdTxt2" style=";margin-top: 3px"/><br/>
        <br/>
        <input type="button" value="取消" id="btnInfoCancle" name="btnInfoCancle" onclick="closeInfo()"/>
        <input type="button" value="确定" id="btnInfoConfirm" name="btnInfoConfirm" onclick="updateInfo()"/>
    </form>
</div>

<!-- Breadcrumbs Start
    ================================================== -->

<section id="topic-header">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <h1>ARTICLE</h1>
                <p>The Information of Restaurant</p>
            </div>    <!-- End of /.col-md-4 -->
            <c:set var="selectedCity" value="${sessionScope.selectedCity}"/>
            <div class="col-md-8 hidden-xs">
                <ol class="breadcrumb pull-right">
                    <li><a>HOME</a></li>
                    <li><a>Restaurant</a></li>
                    <li class="active">Restaurant Article</li>
                </ol>
            </div>    <!-- End of /.col-md-8 -->
        </div>    <!-- End of /.row -->
    </div>    <!-- End of /.container -->
</section>    <!-- End of /#Topic-header -->


<section id="blog">
    <div class="container">
        <div class="row">
            <div class="col-md-9 clearfix">
                <ul class="blog-zone">
                    <li>
                        <div class="blog-icon">
                            <i class="fa  fa-pencil"></i>
                        </div>
                        <div class="blog-box">
                            <img src="http://${business.imgFileName}" alt="" height="460px"
                                 width="1600px">
                            <div class="blog-post-body clearfix">
                                <a href="">
                                    <h2>${business.title}</h2>
                                </a>
                                <div class="blog-post-tag">
                                    <div class="block">
                                        <i class="fa fa-clock-o"></i>
                                        <p>星级：${business.starTotalNum}⭐</p>
                                    </div>
                                    <div class="block">
                                        <i class="fa fa-user"></i>
                                        <p>人均：${business.price}元/人</p>
                                    </div>
                                    <div class="block">
                                        <i class="fa fa-tags"></i>
                                        <p>已售：${business.number}</p>
                                    </div>
                                    <div class="block">
                                        <i class="fa fa-tags"></i>
                                        <p>类别：${business.category}</p>
                                    </div>
                                </div>
                                <div class="blog-post-tag">
                                    <div class="block">
                                        <i class="fa fa-tags"></i>
                                        <a href="${basePath}/webAPI/navigation/${business.id}"><p>地址：${business.address}
                                        </a></p>
                                    </div>
                                </div>
                                <p>餐厅介绍：${business.desc}</p>
                                <p></p>

                                <c:if test="${member.phone == null}">
                                    <input type="button" style="margin-left: 30px;" value="收藏" onclick="tip();"/>
                                    <input type="button" style="margin-left: 30px;" value="购买" onclick="tip();"/>
                                    <input type="button" style="margin-left: 30px;" value="投诉" onclick="tip();"/>
                                </c:if>
                                <c:if test="${member.phone != null}">
                                    <c:if test="${flag}">
                                        <i style="margin-left: 30px;"><input type="button" value="取消收藏"
                                                                             onclick="delFav();"/></i>
                                    </c:if>
                                    <c:if test="${!flag}">
                                        <i style="margin-left: 30px;"><input type="button" value="收藏"
                                                                             onclick="addFav();"/></i>
                                    </c:if>
                                    <i style="margin-left: 30px;"><input type="button" value="购买"
                                                                         onclick="openPayWin()"/></i>
                                    <i style="margin-left: 30px;"><input type="button" value="投诉"
                                                                         onclick="openBusinessCompalin()"/></i>
                                </c:if>
                            </div>
                        </div>
                    </li>
                </ul>    <!-- End of /.blog-zone -->

                <div class="comments-box">
                    <c:forEach var="comment" items="${commentList}">
                        <div class="media">
                            <div class="media-body">
                                <h6 class="media-heading">
                                    <c:if test="${comment.orders.member.name != null}">
                                        ${comment.orders.member.name}
                                    </c:if>
                                    <c:if test="${comment.orders.member.name == null}">
                                        匿名用户
                                    </c:if>
                                    <span>${comment.createTime}</span>
                                    <c:if test="${member == null}"><a href="javascript:void(0)" class="pull-right"
                                                                      onclick="tip()">(投诉)</a></c:if>
                                    <c:if test="${member != null}"><a href="javascript:void(0)" class="pull-right"
                                                                      onclick="openCommentCompalin('${comment.id}', '${comment.comment}')">(投诉)</a></c:if>
                                </h6>
                                <p>星级：${comment.star}⭐ &nbsp;&nbsp;消费：${comment.orders.price}元
                                    &nbsp;&nbsp;人数：${comment.orders.num}</p>
                                <p>${comment.comment}</p>
                            </div>    <!-- End of /.meida-body -->
                        </div>
                        <!-- End of /.media -->
                    </c:forEach>
                </div>    <!-- End of /.comments-box -->
            </div>    <!-- End of /.col-md-9 -->

            <div class="col-md-3">
                <div class="blog-sidebar">
                    <div class="block">
                        <h4>Catagories</h4>
                        <div class="list-group">
                            <a href="javascript:void(0)" class="list-group-item">
                                <i class="fa  fa-dot-circle-o"></i>
                                全部
                            </a>
                            <c:forEach items="${categoryList}" var="cList">
                                <a href="javascript:void(0)" class="list-group-item">
                                    <i class="fa  fa-dot-circle-o"></i>
                                        ${cList.type}
                                </a>
                            </c:forEach>
                        </div>
                    </div>    <!-- End of /.block -->

                </div>    <!-- End of /.Sidebar -->
            </div>    <!-- End of /.col-md-3 -->
        </div>    <!-- End of /.row -->
    </div>    <!-- End of /.container -->
</section> <!-- End of /.Section -->


<div id="ComComplain" class="ComComplain" name="ComComplain" style="display:none">
    <form action="" method="post">
        <span style="font-size: 20px;"> 投诉 </span> <br/>
        <label class="label" id="commentIdLabel"></label>
        <label class="label" id="commentTxtLable" style="font-size: 13px;color: black"></label> <br/>
        <label class="label" style="font-size: 13px;color: black;"> 投诉人：${member.name}</label>&nbsp;&nbsp;&nbsp;
        <label class="label" style="font-size: 13px;color: black;"> 投诉人电话：${member.phone}</label><br/>
        <label class="label" style="font-size: 13px;color: black">投诉原因<i style="color: red;">(必填)</i>：</label>
        <input type="text" width="100px" id="comReason" name="comReason" onblur="txtLength()"><br/>
        <label class="label" id="tipLabel"></label><br/>
        <input type="button" value="取消" id="btnComCancle" name="btnComCancle" onclick="closeCommentCompalin();"/>
        <input type="button" value="确定" id="btnComConfirm" name="btnComConfirm" onclick="addCom();"/>
    </form>
</div>

<div id="busComplain" class="busComplain" name="busComplain" style="display:none">
    <form action="" method="post">
        <span style="font-size: 20px;"> 投诉 </span> <br/>
        <label class="label" style="font-size: 13px;color: black;"> 投诉商家： ${business.title}</label> <br/>
        <label class="label" style="font-size: 13px;color: black;"> 投诉人：${member.name}</label>&nbsp;&nbsp;&nbsp;
        <label class="label" style="font-size: 13px;color: black;"> 投诉人电话：${member.phone}</label><br/>
        <label class="label" style="font-size: 13px;color: black;">投诉原因<i style="color: red;">(必填)</i>：</label>
        <input type="text" id="busReason" name="busReason" onblur="txtLength2()"><br/>
        <label class="label" id="tipLabel2"></label><br/>
        <input type="button" value="取消" id="btnBusCancle" name="btnBusCancle" onclick="closeBusinessCompalin();"/>
        <input type="button" value="确定" id="btnBusConfirm" name="btnBusConfirm" onclick="addBus();"/>
    </form>
</div>

<div id="payWin" class="payWin" name="payWin" style="display:none">
    <form action="" method="post">
        <span style="font-size: 20px;"> 购买 </span> <br/>
        <label class="label" style="font-size: 13px;color: black;"> 店铺：${business.title}</label><br/>
        <label class="label" style="font-size: 13px;color: black;"> 用户：${member.name}</label>&nbsp;&nbsp;&nbsp;
        <label class="label" style="font-size: 13px;color: black;"> 用户电话：${member.phone}</label><br/>
        <label class="label" style="font-size: 13px;color: black;"> 消费人数：</label><input type="text" id="numTxt"
                                                                                        onkeyup="this.value=this.value.replace(/\D/g,'')"/>
        <br/>
        <label class="label" style="font-size: 13px;color: black;"> 消费金额：</label><input type="text" id="payTxt"
                                                                                        onkeyup="this.value=this.value.replace(/\D/g,'')"/><br/>
        <br/>
        <input type="button" value="取消" id="btnCancle" name="btnCancle" onclick="closePayWin()"/>
        <input type="button" value="确定" id="btnConfirm" name="btnConfirm" onclick="toPay()"/>
    </form>
</div>

<!-- FOOTER Start
================================================== -->

<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="block clearfix">
                    <a href="#">
                        <img src="${basePath}/images/footer-logo.png" alt="">
                    </a>
                    <p>
                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been
                        the industry's standard dummy text ever since the 1500s
                    </p>
                </div>    <!-- End Of /.block -->
            </div> <!-- End Of /.Col-md-4 -->
            <div class="col-md-4">
                <div class="block">

                </div>    <!-- End Of /.block -->
            </div> <!-- End Of Col-md-3 -->
            <div class="col-md-4">
                <div class="block">
                    <h4>GET IN TOUCH</h4>
                    <p><i class="fa  fa-map-marker"></i> <span>Food Code d.o.o.,</span>1000 Ljubljana Celovska cesta
                        135, Slovenia</p>

                    <p><i class="fa  fa-mobile"></i> <span>Mobile:</span> (+386) 40 654 123 651</p>

                    <p class="mail"><i class="fa  fa-envelope"></i>Eamil: <span>food@platform.com</span></p>
                    <p>&nbsp;</p>
                </div>    <!-- End Of /.block -->
            </div> <!-- End Of Col-md-3 -->
        </div> <!-- End Of /.row -->
    </div> <!-- End Of /.Container -->

</footer> <!-- End Of Footer -->

<a id="back-top" href="#"></a>
</body>
</html>
