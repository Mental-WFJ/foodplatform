<%--
  Created by IntelliJ IDEA.
  User: Super_Family
  Date: 2020/4/21
  Time: 16:23
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
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:400,700" rel="stylesheet">

    <!-- Css -->
    <link rel="stylesheet" href="${basePath}/css/nivo-slider.css">
    <link rel="stylesheet" href="${basePath}/css/owl.carousel.css">
    <link rel="stylesheet" href="${basePath}/css/owl.theme.css">
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/css/style.css">
    <link rel="stylesheet" href="${basePath}/css/responsive.css">

    <!-- jS -->
    <script src="${basePath}/js/jquery.min.js"></script>
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/jquery.nivo.slider.js"></script>
    <script src="${basePath}/js/owl.carousel.min.js"></script>
    <script src="${basePath}/js/jquery.nicescroll.js"></script>
    <script src="${basePath}/js/jquery.scrollUp.min.js"></script>
    <script src="${basePath}/js/main.js"></script>
    <script src="${basePath}/js/jquery.form.js"></script>

    <script type="text/javascript">
        var codeNum = "";

        function getCode() {
            codeNum = "";
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
                    } else if (resp.errcode == 3) {
                        alert("创建失败！");
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

        function checkLoginPhoneType() {
            var phone = $("#phone").val();
            var btn = document.getElementById("logCode");
            console.log(phone);
            if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))) {
                $("#myLoginLabel").html("请输入正确的手机号");
                $("#myLoginLabel").css("color", "red");
                btn.style.visibility = "hidden";
            } else {
                $("#myLoginLabel").html("");
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

        function refreshByCity() {
            var cityId = $("#city").val();
            location.href = "${basePath}/webAPI/" + cityId;
        }

        function showInfo() {
            document.getElementById("infoWin").style.display = "block";
        }

        function closeInfo() {
            document.getElementById("pwdTxt").value = "";
            document.getElementById("pwdTxt2").value = "";
            document.getElementById("infoWin").style.display = "none";
        }

        function updateInfo() {
            var pwd = $("#pwdTxt").val();
            var pwd2 = $("#pwdTxt2").val();
            var nameTxt = $("#nameTxt").val();
            var memberId = ${sessionScope.member.id} +"";
            var memberPhone = ${sessionScope.member.phone} +"";

            $.ajax({
                type: "POST",
                datatype: 'json',
                url: "${basePath}/member/updateInfo",
                data: {
                    memberId: memberId,
                    memberPhone: memberPhone,
                    name: nameTxt,
                    pwd: pwd,
                    pwd2: pwd2
                },
                success: function (data) {
                    if (data == 1) {
                        alert("修改成功！");
                        location.href = "${basePath}/webAPI/init";
                    } else if (data == 2)
                        alert("两次密码不一致！");
                    else if (data == 0)
                        alert("修改失败！");
                    document.getElementById("pwdTxt").value = "";
                    document.getElementById("pwdTxt2").value = "";
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
                    <li>
                        <div class="cart dropdown">
                            <a data-toggle="dropdown" href="#"><i class="fa fa-shopping-cart"></i>city：</a>
                            <select name="city" id="city" class="city" style="width: 80px;"
                                    onchange="refreshByCity()">
                                <c:set var="selectedCity" value="${sessionScope.selectedCity}"/>
                                <c:forEach items="${cityList}" var="item">
                                    <c:if test="${selectedCity.city == item.city}">
                                        <option value="${item.id}" selected="selected">${item.city}</option>
                                    </c:if>
                                    <c:if test="${selectedCity.city != item.city}">
                                        <option value="${item.id}">${item.city}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
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
                                    <input type="button" class="btn btn-primary" style="margin-left: 5px;"
                                           onclick="doRegister()" value="Create"/>
                                </p>
                            </div>
                        </fieldset>
                    </form>
                    <form action="${basePath}/member/memberLogin" method="post" id="login_form" class="std">
                        <fieldset>
                            <h3>Already registered?</h3>
                            <div class="form_content clearfix">
                                <h4>Enter your phone to login.</h4>
                                <label id="myLoginLabel"></label>
                                <p class="text">
                                    <label for="phone">Phone</label>
                                    <span><input style="margin-top: 4px;" placeholder="Phone" type="text" id="phone"
                                                 name="phone" value="" class="account_input" onblur="checkLoginPhoneType()"></span>
                                </p>
                                <p class="text">
                                    <label for="passwd">Password</label>
                                    <span><input style="margin-top: 5px;" placeholder="Password/Code" type="password"
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
            <ul class="nav navbar-nav nav-main">
                <li class="active"><a>HOME</a></li>
                <c:if test="${member == null}">
                    <li><a href="${basePath}/bussiness/search/null/${selectedCity.id}/null/null">Restaurant</a></li>
                </c:if>
                <c:if test="${member != null}">
                    <li><a href="${basePath}/bussiness/search/${member.id}/${selectedCity.id}/null/null">Restaurant</a>
                    </li>
                </c:if>
                <li><a>ARTICLE</a></li>
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
        <input type="button" value="取消" id="btnCancle" name="btnCancle" onclick="closeInfo()"/>
        <input type="button" value="确定" id="btnConfirm" name="btnConfirm" onclick="updateInfo()"/>
    </form>
</div>

<!-- SLIDER Start
================================================== -->
<section id="slider-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div id="slider" class="nivoSlider">
                    <c:forEach items="${adList}" var="ad">
                        <a href="http://${ad.link}"><img src="http://${ad.imgFileName}" alt="" width="1180px" ,
                                                  height="500px"/></a>
                    </c:forEach>
                </div>    <!-- End of /.nivoslider -->
            </div>    <!-- End of /.col-md-12 -->
        </div>    <!-- End of /.row -->
    </div>    <!-- End of /.container -->
</section> <!-- End of Section -->

<!-- CATAGORIE Start
================================================== -->

<section id="catagorie">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="block">
                    <div class="block-heading">
                        <h2>Recommended&nbsp;&nbsp;&nbsp;&nbsp;Restaurant</h2>
                    </div>
                    <div class="row" id="rRecommand" name="rRecommand">
                        <c:forEach items="${businessList}" var="business">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail">
                                    <c:if test="${member.phone == null}">
                                        <a class="catagotie-head"
                                           href="${basePath}/bussiness/article/${business.id}/null">
                                            <img src="http://${business.imgFileName}" alt=""
                                                 width="350px" height="250px">
                                            <h3>${business.title}</h3><h6>${business.subtitle}</h6>
                                        </a>
                                    </c:if>
                                    <c:if test="${member.phone != null}">
                                        <a class="catagotie-head"
                                           href="${basePath}/bussiness/article/${business.id}/${member.id}">
                                            <img src="http://${business.imgFileName}" alt=""
                                                 width="350px" height="250px">
                                            <h3>${business.title}</h3><h6>${business.subtitle}</h6>
                                        </a>
                                    </c:if>
                                    <div class="caption">
                                        <p>人均：${business.price}元/人&nbsp;&nbsp;&nbsp;销售数量：${business.number}&nbsp;&nbsp;&nbsp;
                                            星级：${business.starTotalNum}</p>
                                    </div>    <!-- End of /.caption -->
                                </div>    <!-- End of /.thumbnail -->
                            </div>
                            <!-- End of /.col-sm-6 col-md-4 -->
                        </c:forEach>
                    </div>    <!-- End of /.row -->
                </div>    <!-- End of /.block -->
            </div>    <!-- End of /.col-md-12 -->
        </div>    <!-- End of /.row -->
    </div>    <!-- End of /.container -->
</section>    <!-- End of Section -->

<!-- CALL TO ACTION Start
================================================== -->

<section id="call-to-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="block">
                    <div class="block-heading">
                        <h2>Our &nbsp;&nbsp;Partners</h2>
                    </div>
                </div>    <!-- End of /.block -->
                <div id="owl-example" class="owl-carousel">
                    <div><img src="${basePath}/images/company-1.png" alt=""></div>
                    <div><img src="${basePath}/images/company-2.png" alt=""></div>
                    <div><img src="${basePath}/images/company-3.png" alt=""></div>
                    <div><img src="${basePath}/images/company-4.png" alt=""></div>
                    <div><img src="${basePath}/images/company-5.png" alt=""></div>
                    <div><img src="${basePath}/images/company-6.png" alt=""></div>
                    <div><img src="${basePath}/images/company-8.png" alt=""></div>
                    <div><img src="${basePath}/images/company-9.png" alt=""></div>
                </div>    <!-- End of /.Owl-Slider -->
            </div>    <!-- End of /.col-md-12 -->
        </div> <!-- End Of /.Row -->
    </div> <!-- End Of /.Container -->
</section>    <!-- End of Section -->


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
