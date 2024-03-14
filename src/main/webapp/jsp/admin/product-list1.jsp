<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp"%>

<section class="p-0">
    <div class="container-fluid p-0">
        <div class="row">
            <%@include file="../common/admin-left.jsp"%>
            <div class="col-lg-9 col-md-8 col-sm-12">
                <div class="dashboard-body">
                    <div class="frm_submit_block">
                        <h4>商品管理&nbsp<a href="/admin/product/publish" class="btn btn-theme bg-2 btn-sm">发布新商品</a></h4>
                    </div>
                    <%----%>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>图片</th>
                            <th>状态</th>
                            <th>name</th>
                            <th>商品价格</th>
                            <th>地点</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!--  循环展示goods-->
                        <c:forEach items="${pageInfo.records}" var="c">
                            <tr>
                                <td>
                                    <img  style="height: 120px" src="${c.thumbnailUrl}"class="img-fluid">

                                </td>
                                <td>
                                    <c:choose>
                                    <c:when test="${c.status == 0}">
                                                <span class="dashboard_pr_status">
                                                    未售出
                                                </span>
                                    </c:when>
                                    <c:when test="${c.status == 1}">
                                                <span class="dashboard_pr_status published">
                                                    已售出
                                                </span>
                                    </c:when>
                                    <c:when test="${c.status == -1}">
                                                <span class="dashboard_pr_status expire">
                                                    已下架
                                                </span>
                                    </c:when>
                                    <c:when test="${c.status == -2}">
                                                <span class="dashboard_pr_status expire">
                                                    待审核
                                                </span>
                                    </c:when>
                                    <c:when test="${c.status == -3}">
                                                <span class="dashboard_pr_status expire">
                                                    审核不通过
                                                </span>
                                    </c:when>
                                    <c:otherwise>
                                                <span class="dashboard_pr_status">
                                                    未知状态
                                                </span>
                                    </c:otherwise>
                                </c:choose>
                                </td>
                                <td>${c.title}</td>
                                <td>￥${c.price}元</td>
                                <td>${c.address}</td>
                                <td>
                                    <a href="/product/detail/${c.id}" title="查看">&nbsp;<i class="ti-eye"></i>&nbsp;</a>
                                    <c:if test="${c.status != 1}">
                                        <a href="/admin/product/publish?id=${c.id}"title="编辑">&nbsp;<i class="ti-pencil"></i>&nbsp;</a>
                                    </c:if>
                                    <c:if test="${c.status == -1}">
                                        <a href="javascript:void(0)" onclick="upProduct(${c.id})" title="上架">&nbsp;<i class="ti-control-play"></i>&nbsp;</a>
                                    </c:if>
                                    <c:if test="${c.status == 0}">
                                        <a href="javascript:void(0)" onclick="downProduct(${c.id})" title="下架">&nbsp;<i class="ti-control-pause"></i>&nbsp;</a>
                                    </c:if>
                                    <c:if test="${c.status != 1}">
                                        <a href="javascript:void(0)" onclick="deleteProduct(${c.id})"title="删除">&nbsp;<i class="ti-close"></i>&nbsp;</a>
                                    </c:if>
                                    <c:if test="${isAdmin && c.status == -2}">
                                        <a href="javascript:void(0)" onclick="checkPassProduct(${c.id})"title="审核通过">&nbsp;<i class="ti-arrow-up"></i>&nbsp;</a>
                                        <a href="javascript:void(0)" onclick="checkRejectProduct(${c.id})"title="审核驳回">&nbsp;<i class="ti-arrow-down"></i>&nbsp;</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        <!--  /循环展示goods-->
                        </tbody>
                    </table>


                    <%----%>







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
