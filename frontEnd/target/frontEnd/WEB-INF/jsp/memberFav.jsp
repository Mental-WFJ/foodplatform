<%--
  Created by IntelliJ IDEA.
  User: Super_Family
  Date: 2020/4/23
  Time: 9:35
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

        function quit() {
            location.href = "${basePath}/webAPI/clear";
        }

        function refreshByCity() {
            var cityId = $("#city").val();
            location.href = "${basePath}/favorite/search/${sessionScope.member.id}/" + cityId + "/null/null";
        }

        function search() {
            var cityId = $("#city").val();
            var searchKey = $("#searchKey").val();
            if (searchKey == "")
                searchKey = null;
            if (${sessionScope.selectedCategory == null})
                location.href = "${basePath}/favorite/search/${member.id}/" + cityId + "/null/" + searchKey;
            else {
                alert(${sessionScope.selectedCategory.id});
                location.href = "${basePath}/favorite/search/${member.id}/" + cityId + "/" + ${sessionScope.selectedCategory.id} +"/" + searchKey;
            }
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

            $.ajax({
                type: "POST",
                datatype: 'json',
                url: "${basePath}/member/updateInfo",
                data: {
                    memberId: ${sessionScope.member.id},
                    memberPhone: ${sessionScope.member.phone},
                    name: nameTxt,
                    pwd: pwd,
                    pwd2: pwd2
                },
                success: function (data) {
                    alert(data);
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
                                <c:if test="${selectedCity == null}">
                                    <c:forEach items="${cityList}" var="item">
                                        <option value="${item.id}">${item.city}</option>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${selectedCity != null}">
                                    <c:forEach items="${cityList}" var="item">
                                        <c:if test="${selectedCity.city == item.city}">
                                            <option value="${item.id}" selected="selected">${item.city}</option>
                                        </c:if>
                                        <c:if test="${selectedCity.city != item.city}">
                                            <option value="${item.id}">${item.city}</option>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="col-md-2">
                <div class="search-box">
                    <div class="input-group">
                        <input placeholder="Search Here" type="text" class="form-control" id="searchKey"
                               name="searchKey">
                        <span class="input-group-btn">
					        	<button class="btn btn-default" type="button" onclick="search()"></button>
					      	</span>
                    </div><!-- /.input-group -->
                </div><!-- /.search-box -->
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
                                    <button class="btn btn-success" style="margin-top: 12px;" id="loginBtn">Log in
                                    </button>
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
                <li><a href="${basePath}/webAPI/${selectedCity.id}">HOME</a></li>
                <li><a href="${basePath}/bussiness/search/${member.id}/${selectedCity.id}/null/null">Restaurant</a>
                </li>
                <li><a>ARTICLE</a></li>
                <li class="dropdown" class="active">
                    <a href="javascript:void(0)">
                        My CENTER
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${basePath}/favorite/search/${member.id}/${selectedCity.id}/null/null/">FAVORITIES</a>
                        <li><a href="${basePath}/orders/getOrderList/${member.id}">My ORDERS</a></li>
                        <li><a href="javascript:void(0)" onclick="showInfo()">INFORMATION</a></li>
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


<section id="topic-header">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <h1>Favorities</h1>
                <p>Member's Favorities</p>
            </div>    <!-- End of /.col-md-4 -->
            <div class="col-md-8 hidden-xs">
                <ol class="breadcrumb pull-right">
                    <li><a href="home.jsp">Home</a></li>
                    <li class="active">Restaurant</li>
                </ol>
            </div>    <!-- End of /.col-md-8 -->
        </div>    <!-- End of /.row -->
    </div>    <!-- End of /.container -->
</section>    <!-- End of /#Topic-header -->


<!-- PRODUCTS Start
================================================== -->

<section id="shop">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="products-heading">
                    <h2>FAVORITIES</h2>
                </div>    <!-- End of /.Products-heading -->
                <div class="product-grid" align="left">
                    <ul>
                        <c:forEach var="item" items="${fList}">
                            <li>
                                <div class="products">
                                    <a href="${basePath}/bussiness/article/${item.businessId}/${member.id}">
                                        <img src="http://${item.business.imgFileName}" alt=""
                                             width="350px"
                                             height="250px">
                                    </a>
                                    <a href="#">
                                        <h4>${item.business.title}</h4>
                                    </a>
                                    <div class="caption">
                                        <p>人均：${item.business.price}元/人&nbsp;&nbsp;&nbsp;销售数量：${item.business.number}&nbsp;&nbsp;&nbsp;
                                            星级：${item.business.starTotalNum}</p>
                                    </div>    <!-- End of /.caption -->
                                </div>    <!-- End of /.products -->
                            </li>
                        </c:forEach>
                    </ul>
                </div>    <!-- End of /.products-grid -->
            </div>    <!-- End of /.col-md-9 -->

            <div class="col-md-3">
                <div class="blog-sidebar">
                    <div class="block">
                        <h4>Catagories</h4>
                        <div class="list-group">
                            <a href="${basePath}/favorite/search/${sessionScope.member.id}/${selectedCity.id}/null/null"
                               class="list-group-item">
                                <i class="fa  fa-dot-circle-o"></i>
                                全部
                            </a>
                            <c:forEach items="${categoryList}" var="cList">
                                <a href="${basePath}/favorite/search/${sessionScope.member.id}/${selectedCity.id}/${cList.id}/null"
                                   class="list-group-item">
                                    <i class="fa  fa-dot-circle-o"></i>
                                        ${cList.type}
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>    <!-- End of /.col-md-3 -->
        </div>    <!-- End of /.row -->
    </div>    <!-- End of /.container -->
</section><!-- End of Section -->


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

<%--<link rel="stylesheet" href="${basePath}/css/layui.css" media="all">--%>

<%--<script src="${basePath}/js/layui.js" charset="utf-8"></script>--%>
<%--<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->--%>
<%--<script>--%>
<%--    layui.use(['laypage', 'layer'], function () {--%>
<%--        var laypage = layui.laypage--%>
<%--            , layer = layui.layer;--%>

<%--//完整功能--%>
<%--        laypage.render({--%>
<%--            elem: 'pageDiv'--%>
<%--            , count: ${pagd.total},--%>
<%--            curr:${pagd.page}--%>
<%--            , limit:${pagd.pageSize}--%>
<%--            , layout: ['count', 'prev', 'page', 'limit', 'next', 'skip']--%>
<%--            , jump: function (obj, first) {--%>
<%--                // console.log(obj);--%>
<%--                // console.log(first);--%>
<%--                //首次不执行--%>
<%--                if (!first) {--%>
<%--                    //跳页的实现--%>
<%--                    //uc?op=queryByPage&page=1&pageSize=10--%>
<%--                    location.href = "TitleLikeSearch?page=" + obj.curr + "&pageSize=" + obj.limit;--%>
<%--                }--%>
<%--            }--%>
<%--        });--%>

<%--    });--%>
<%--</script>--%>

</body>
</html>
