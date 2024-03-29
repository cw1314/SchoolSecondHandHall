<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>快乐商城</title>
    <link rel="stylesheet" href="/assets/css/styles.css">
    <link rel="stylesheet" href="/assets/css/colors.css">
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/custom.js"></script>
    <script src="/assets/js/moment.min.js"></script>
    <script src="/assets/js/daterangepicker.js"></script>
    <script src="/assets/js/dropzone.js"></script>
    <script src="/assets/js/imagesloaded.js"></script>
    <script src="/assets/js/ion.rangeSlider.min.js"></script>
    <script src="/assets/js/jquery.magnific-popup.min.js"></script>
    <script src="/assets/js/lightbox.js"></script>
    <script src="/assets/js/markerclusterer.js"></script>
    <script src="/assets/js/morris.min.js"></script>
    <script src="/assets/js/raphael.min.js"></script>
    <script src="/assets/js/select2.min.js"></script>
    <script src="/assets/js/slick.js"></script>
    <script src="/assets/js/slider-bg.js"></script>
    <script src="/assets/js/script.js"></script>


</head>
<meta name="viewport" content="width=device-width">
<body class="yellow-skin">
    <div class="head head-light dark-text">
        <div class="container">
            <nav class="navigation navigation-landscape">
                <div class="nav-header">
                   <a class="nav-brand" href="/"> 快乐商城</a>
                 </div>
                <div class="nav-menus-wrapper">
                    <ul class="nav-menu">
                        <li><a href="/" style="font-size: 15px;">首页</a></li>
                        <li><a href="/product?productType=" style="font-size: 15px;">商品清单</a></li>
                        <%--<li><a href="/product?productType=study" style="font-size: 15px;">学习用品</a></li>
                        <li><a href="/product?productType=idle" style="font-size: 15px;">闲置物品</a></li>--%>
                        <li><a href="/news" style="font-size: 15px;">用户公告</a></li>
                        <li><a href="/feedback" style="font-size: 15px;">联系我们</a></li>
                    </ul>
                </div>
                <c:choose>
                    <c:when test="${sessionScope.user == null}">
                        <ul class="nav-menu nav-menu-social align-to-right">
                            <li>
                                <a href="#" class="text-blue"data-toggle="modal"data-target="#login">
                                    <i class="fas fa-user-circle mr-1"></i>
                                    <span class="dn-lg"> 登录</span>
                                </a>
                            </li>
                            <li class="add-listing dark-bg">
                                <a href="#" class="theme-cl"data-toggle="modal"data-target="#signup">
                                    <i class="fas fa-arrow-alt-circle-right mr-1"></i>
                                    注册
                                </a>
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="nav-menu nav-menu-social align-to-right">
                            <li>
                                <div class="btn-group account-drop">
                                    <button type="button" class="btn btn-order-by-filt" data-toggle="dropdown">
                                        <img src="${sessionScope.user.userAvatar}" class="avater-img">
                                            ${sessionScope.user.userDisplayName}(
                                            <c:choose>
                                                <c:when test="${sessionScope.user.role == 'admin'}">管理员</c:when>

                                                <c:otherwise>用户</c:otherwise>
                                            </c:choose>
                                            )
                                    </button>
                                    <c:choose>
                                        <%--管理员--%>
                                        <c:when test="${sessionScope.user.role == 'admin'}">
                                            <div class="dropdown-menu pull-right animated flipInX">
                                                <a href="/admin/profile">
                                                    <i class="ti-user"></i>&nbsp;个人信息
                                                </a>
                                                <a href="/admin/product">
                                                    <i class="ti-layers"></i>商品管理
                                                </a>
                                                <a href="/admin/order/">
                                                    <i class="ti-gift"></i>订单管理
                                                </a>
                                                <a href="/admin/mark">
                                                    <i class="ti-bookmark"></i>&nbsp;我的收藏
                                                </a>
                                                <a href="/admin/news/">
                                                    <i class="ti-info"></i>新闻管理
                                                </a>
                                                <a href="/admin/feedback">
                                                    <i class="ti-alert"></i>&nbsp;反馈管理
                                                </a>
                                                <a href="/admin/user">
                                                    <i class="ti-user"></i>&nbsp;用户管理
                                                </a>
                                                <a href="/admin/password">
                                                    <i class="ti-unlock"></i>&nbsp;密码修改
                                                </a>
                                                <a href="/login/logout">
                                                    <i class="ti-power-off"></i>退出登录
                                                </a>
                                            </div>
                                        </c:when>
                                        <%--卖家--%>
                                        <c:when test="${sessionScope.user.role != 'admin'}">
                                            <div class="dropdown-menu pull-right animated flipInX">
                                                <a href="/admin/profile">
                                                    <i class="ti-user"></i>个人信息
                                                </a>
                                                <a href="/admin/product/">
                                                    <i class="ti-layers"></i>商品管理
                                                </a>
                                                <a href="/admin/order/">
                                                    <i class="ti-info"></i>订单管理
                                                </a>
                                                <a href="/admin/mark">
                                                    <i class="ti-bookmark"></i>&nbsp;我的收藏
                                                </a>
                                                <a href="/admin/feedback">
                                                    <i class="ti-alert"></i>&nbsp;我的反馈
                                                </a>
                                                <a href="/admin/password">
                                                    <i class="ti-unlock"></i>&nbsp;密码修改
                                                </a>
                                                <a href="/login/logout">
                                                    <i class="ti-power-off"></i>退出登录
                                                </a>
                                            </div>
                                        </c:when>

                                    </c:choose>
                                </div>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </nav>
        </div>
    </div>
