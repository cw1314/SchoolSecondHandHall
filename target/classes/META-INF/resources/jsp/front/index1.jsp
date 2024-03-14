<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp"%>

<section class="gray-simple">
    <div class="container">
            <div class="sec-heading center">
                <h3>最新上架商品</h3>


            </div>
            <div class="row">

                <c:forEach items="${productList}" var="c">
                    <div class="col-lg-4 col-md-6 col-sm-12">
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
                                        <a href="/product/detail/${c.id}"class="cl-blue">￥${c.price}/个</a>
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
                <a href="/product" class="btn btn-theme arrow-btn bg-3">查看更多
                    <span><i class="ti-arrow-right"></i></span>
                </a>
            </div>

    </div>
</section>




<%@include file="../common/footer.jsp"%>