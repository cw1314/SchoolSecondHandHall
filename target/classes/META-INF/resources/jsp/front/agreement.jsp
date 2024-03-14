<%@page contentType="text/html;charset=UTF-8"  language="java" %>
<%@include file="../common/head.jsp"%>

<section class="gray-simple">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="property_block_wrap style-2">
                    <div id="content" style="margin-top: 50px;">
                        <h2 style="text-align: center;margin-top: 50px;">交易订单</h2>
                        <%----%>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>图片</th>

                                <th>name</th>
                                <th>商品价格</th>
                                <th>地点</th>
                                <th>卖家</th>

                            </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td>
                                        <img  style="height: 120px" src="${order.product.thumbnailUrl}"class="img-fluid">

                                    </td>

                                    <td>${order.product.title}</td>
                                    <td>￥${order.product.price}元</td>
                                    <td>${order.product.address}</td>
                                    <td>${order.sellerUser.userName}</td>
                                </tr>
                            </tbody>
                        </table>
                        <%----%>





                    </div>
                    <div style="text-align: center;margin-top: 50px;">
                        <c:choose>
                            <c:when test="${order.status == -2}">订单已取消</c:when>
                            <c:when test="${order.status == -1}"><a href="/order/pay?orderId=${order.id}">去付款</a></c:when>
                            <c:when test="${order.status == 0}">订单生效中</c:when>
                            <c:when test="${order.status == 1}">待评价</c:when>
                            <c:when test="${order.status == 2}">已完成</c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="../common/footer.jsp"%>
</body>
</html>
