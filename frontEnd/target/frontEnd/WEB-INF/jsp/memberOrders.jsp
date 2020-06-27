<%--
  Created by IntelliJ IDEA.
  User: Super_Family
  Date: 2020/5/2
  Time: 12:15
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

        function quit() {
            location.href = "${basePath}/webAPI/clear";
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

        function showComment() {
            $('#commentContain').style.visibility = "block";
        }

        function closeComment() {
            document.getElementById("commentTxt").value = "";
            var obj=document.getElementById('starSelect');
            obj.selectedIndex = 0;
        }

        function submitComment(id) {
            var comment = $('#commentTxt').val();
            var ordersId = id;
            var star = $('#starSelect option:selected').val();

            $.ajax({
                type: "POST",
                datatype: 'json',
                url: "${basePath}/orders/submitComment",
                data: {
                    comment: comment,
                    star: star,
                    ordersId: ordersId
                },
                success: function (data) {
                    if (data)
                        alert("评论成功！");
                    else
                        alert("评论失败！");
                    location.href = "${basePath}/orders/getOrderList/${sessionScope.member.id}";
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
                            <a href="${basePath}/favorite/search/${member.id}/${selectedCity.id}/null/null">FAVORITIES</a>
                        </li>
                        <li><a href="javascript:void(0)">My ORDERS</a></li>
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

<section id="blog">
    <div class="container">
        <div class="row">
            <div class="col-md-9 clearfix">
                <div class="comments-box">
                    <c:forEach var="comment" items="${commentList}">
                        <div class="media">
                            <div class="media-body">
                                <h6 class="media-heading">
                                    <a href="${basePath}/bussiness/article/${comment.orders.businessId}/${comment.orders.memberId}">${comment.orders.business.title}</a>
                                    <span>${comment.orders.createTime}</span>
                                    <a href="javascript:void(0)" class="pull-right">(已评论)</a>
                                </h6>
                                <p>星级：${comment.star}⭐ &nbsp;&nbsp;消费：${comment.orders.price}元
                                    &nbsp;&nbsp;人数：${comment.orders.num}</p>
                                <p>${comment.comment}</p>
                            </div>    <!-- End of /.meida-body -->
                        </div>
                        <!-- End of /.media -->
                    </c:forEach>
                    <c:forEach var="order" items="${orderList}">
                        <div class="media">
                            <div class="media-body">
                                <h6 class="media-heading">
                                    <a href="${basePath}/bussiness/article/${order.businessId}/${order.memberId}">${order.business.title}</a>
                                    <span>${order.createTime}</span>
                                    <a href="javascript:void(0)" class="pull-right">(评论)</a>
                                </h6>
                                <p>消费：${order.price}元&nbsp;&nbsp;人数：${order.num}</p>
                                <div class="commentContain" id="commentContain">
                                    评论：<input type="text" id="commentTxt" name="commentTxt"
                                              style="width: auto; margin-left: 5px"><br/>
                                    星级<i style="color: red">(1-5⭐)</i>：
                                    <select id="starSelect" name="starSelect">
                                        <option value="1" selected="selected">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select><br/>
                                    <input type="button" id="comCancel" name="comCancel" value="取消"
                                           onclick="closeComment()">
                                    <input style="margin-left: 5px" type="button" id="comConfirm" name="comConfirm"
                                           value="提交" onclick="submitComment('${order.id}')">
                                </div>
                            </div>    <!-- End of /.meida-body -->
                        </div>
                        <!-- End of /.media -->
                    </c:forEach>
                </div>    <!-- End of /.comments-box -->
            </div>    <!-- End of /.col-md-9 -->

            <div class="col-md-3">
                <div class="blog-sidebar">
                    <div class="block">

                    </div>    <!-- End of /.block -->

                </div>    <!-- End of /.Sidebar -->
            </div>    <!-- End of /.col-md-3 -->
        </div>    <!-- End of /.row -->
    </div>    <!-- End of /.container -->
</section> <!-- End of /.Section -->


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
