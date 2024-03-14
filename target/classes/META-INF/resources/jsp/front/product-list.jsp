<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp"%>
<section class="gray-simple">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="page-sidebar">
                    <form action="/product" method="get" class="form-inline">
                        <div class="form-group col-lg-3">
                            <select name="city" id="city" class="form-control" style="margin-right: 20px">
<%--                                <option value="">学校</option>--%>
                                <option value=" ">学校</option>
                                <option value="清华大学"<c:if test="${productSearchVo.city == '清华大学'}">selected</c:if>>清华大学</option>
                                <option value="北京大学"<c:if test="${productSearchVo.city == '北京大学'}">selected</c:if>>北京大学</option>
                            </select>
                        </div>
                        <div class="form-group col-lg-2">
                            <select name="productType" id="productType" class="form-control">
                                <option value=" ">类别</option>

                                <option value="dorm"<c:if test="${productSearchVo.productType == 'dorm'}">selected</c:if>>宿舍用品</option>
                                <option value="study"<c:if test="${productSearchVo.productType == 'study'}">selected</c:if>>学习用品</option>
                                <option value="idle"<c:if test="${productSearchVo.productType == 'idle'}">selected</c:if>>闲置杂物</option>
                            </select>
                        </div>
                        <div class="form-group col-lg-2 col-lg-offset-1">
                            <div class="input-with-icon">
                                <input type="text" id="title" name="title" class="form-control" placeholder="商品名" value="${productSearchVo.title}">
                                <i class="ti-search"></i>
                            </div>
                        </div>
                        
                        
                        <div class="col-lg-2">
                            <button type="submit" class="btn btn-theme full-width bg-2">搜索</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="row">
                    <c:forEach items="${pageInfo.records}" var="c">
                        <div class="col-lg-3 col-md-3 col-sm-6">
                            <div class="single_property_style property_style_2 modern">
                                <div class="listing_thumb_wrapper">
                                    <a href="/product/detail/${c.id}"><img src="${c.thumbnailUrl}" class="img-fluid mx-auto"></a>
                                </div>
                                <div class="property_caption_wrappers pb-0">
                                    <div class="property_short_detail">
                                        <div class="pr_type_status">
                                            <h4 class="pr-property_title mb-1"><a href="/product/detail/${c.id}">${c.title}</a></h4>
                                            <div class="listing-location-name">
                                                <a href="/product/detail/${c.id}">${c.address}</a>
                                            </div>
                                        </div>
                                        <div class="property-real-price">
                                            <a href="/product/detail/${c.id}"class="cl-blue">￥${c.price}</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="modern_property_footer">
                                    <div class="fp_types">
                                        <a href="javascript:void(0)" class="markProduct" onclick="submitMark(${c.id})">收藏</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="row">
                    <%@include file="../common/page.jsp"%>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="../common/footer.jsp"%>
