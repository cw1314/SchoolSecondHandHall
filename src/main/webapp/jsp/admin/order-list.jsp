<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp"%>

<section class="p-0">
    <div class="container-fluid p-0">
        <div class="row">
            <%@include file="../common/admin-left.jsp"%>
            <div class="col-lg-9 col-md-8 col-sm-12">
                <div class="dashboard-body">
                    <div class="dashboard-wraper">
                        <table class="table table-striped">
<%--                        <table class="property-table-wrap responsive-table bkmark">--%>
                            <tbody>
                            <tr>
                                <th><i class="fa fa-file"></i>订单列表</th>
                                <th>商品名</th>
                                <th>金额</th>
                                <th>状态</th>
                                <th>卖家</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${pageInfo.records}" var="c">
                                <c:choose>
                                    <c:when test="${c.sellerUserId == sessionScope.user.id||c.buyerUserId == sessionScope.user.id||sessionScope.user.role=='admin'}">
                                        <tr>
                                            <td class="dashboard_propert_wrapper">
                                                <img src="${c.product.thumbnailUrl}" style="width: 70px">
                                            </td>
                                            <td>
                                                <a href="/product/detail/${c.productId}" target="_blank">${c.product.title}</a>
                                            </td>
                                            <td>
                                                ￥${c.totalAmount}元
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${c.status == -3}"><span class="text-warning">买家已取消</span></c:when>
                                                    <c:when test="${c.status == -2}"><span class="text-warning">待付款</span></c:when>
                                                    <c:when test="${c.status == -1}"><span class="text-warning">待付款</span></c:when>
                                                    <c:when test="${c.status == 0}"><span class="text-warning">待收货</span></c:when>
                                                    <c:when test="${c.status == 1}"><span class="text-warning">已到期</span></c:when>
                                                    <c:when test="${c.status == 2}"><span class="text-warning">交易完成</span></c:when>
                                                    <c:when test="${c.status == 3}"><span class="text-warning">退租申请不通过</span></c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                    ${c.sellerUser.userDisplayName}<br/>${c.sellerUser.phone}
                                            </td>
                                            <td>
                                                <a href="/order/agreement/view?orderId=${c.id}" target="_blank">查看订单</a>
                                                <br/>
                                                <c:if test="${c.status == -2 || c.status == -1}">
                                                    <a href="javascript:void(0)" onclick="cancelOrder(${c.id})">取消订单</a>
                                                </c:if>
                                                <c:if test="${c.status == 0 || c.status == 3}">
<%--                                                    <a href="javascript:void(0)" onclick="endOrder(${c.id})">申请退租</a>--%>
                                                    <a href="javascript:void(0)" onclick="endOrderPass(${c.id})">确认收货</a>
                                                </c:if>

                                            </td>
                                        </tr>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>

                    <div class="row">
                        <div class="col-lg-12">
                            <%@include file="../common/page.jsp"%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="../common/footer.jsp"%>
