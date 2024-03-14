<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp"%>

<div class="container">
    <h1 class="big-header-capt mb-0">用户反馈</h1>
    <p class="text-center mb-5">快速反馈，联系我们15238103381</p>
</div>
<section class="gray-simple">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="property_block_wrap style-2">
                    <div class="property_block_wrap_header no-collapse">
                        <h4>填写你的反馈信息</h4>
                    </div>
                    <div class="block-body">
                        <form class="form-simple" id="feedbackForm">
                            <div class="row">
                                <div class="col-lg-6 col-md-12">
                                    <div class="form-group">
                                        <label>姓名</label>
                                        <input type="text" name="contactName" class="form-control simple">
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-12">
                                    <div class="form-group">
                                        <label>电子邮箱</label>
                                        <input type="email" name="contactEmail" class="form-control simple">
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12">
                                    <div class="form-group">
                                        <label>标题</label>
                                        <input type="text" name="title" class="form-control simple">
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12">
                                    <div class="form-group">
                                        <label>内容</label>
                                        <textarea class="form-control simple" name="content">
                                        </textarea>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12">
                                    <div class="form-group">
                                      <button class="btn btn-theme bg-2" type="button" onclick="submitFeedBack()">提交</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
<%@include file="../common/footer.jsp"%>