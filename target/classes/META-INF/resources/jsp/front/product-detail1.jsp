<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp"%>
<div class="container">
<div class="row">
    <div class="col-md-5">
        <img width="400px" src="${product.thumbnailUrl}" alt="..." class="img-rounded">
    </div>
    <div class="col-md-7">
        <h2>商品名：${product.title}</h2>
        <h2>价格：￥${product.price}/个</h2>
        <h4 class="property_block_title">
            商品详细信息
        </h4>
        <ul class="deatil_features">
            <li>
                <strong>状态:</strong>
                <c:choose>
                    <c:when test="${product.status == 1}">已售出</c:when>
                    <c:when test="${product.status == 0}">未售出</c:when>
                    <c:when test="${product.status == -1}">已下架</c:when>
                    <c:when test="${product.status == -2}">待审核</c:when>
                    <c:when test="${product.status == -3}">审核驳回</c:when>
                    <c:otherwise>未知状态</c:otherwise>
                </c:choose>
            </li>
            <li>
                <strong>联系人姓名:</strong>
                ${product.contactName}
            </li>
            <li>
                <strong>联系人电话:</strong>
                ${product.contactPhone}
            </li>
        </ul>
        <div class="like_share_wrap">
            <ul class="like_share_list">
                <li>
                    <a href="javascript:void(0)"class="btn btn-likes" onclick="submitMark(${product.id})"><i class="fas fa-heart"></i>收藏</a>
                </li>
                <li>
                    <a href="javascript:void(0);" class="btn btn-md full-width btn-theme bg-2" onclick="createOrder(${product.id})">立即购买</a>
<%--                    <a href="/order/create?productId=${product.id}" class="btn btn-md full-width btn-theme bg-2">立即购买</a>--%>
                </li>
            </ul>
        </div>
        ${product.content}
    </div>
    <div class="property_block_wrap style-2">
        <div data-toggle="collapse" href="#clFive" class="property_block_wrap_header">
            <h4 class="property_block_title">商品照片</h4>
        </div>
        <div id="clFive" class="panel-collapse collapse show">
            <div class="block-body">
                <ul class="list-gallery-inline">
                    <c:forEach items="${product.slideImgList}" var="url">
                        <li>
                            <a href="${url}" class="mfp-gallery">
                                <img src="${url}" class="img-fluid mx-auto">
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>




</div>

</div>

<%@include file="../common/footer.jsp"%>
