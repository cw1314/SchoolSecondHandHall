<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" href="/assets/plugins/bootstrap-fileinput/css/fileinput.min.css">
<script src="/assets/plugins/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="/assets/plugins/bootstrap-fileinput/js/locales/zh.js"></script>
<section class="p-0">
    <div class="container-fluid p-0">
        <div class="row">
            <%@include file="../common/admin-left.jsp"%>
            <div class="col-lg-9 col-md-8 col-sm-12">
                <section style="padding-top: 10px">
                    <div class="container">
                        <div class="row">
                            <form id="productForm" class="form-horizontal">
                                <input type="hidden" id="id"name="id"value="${product.id}">
                                <input type="hidden" id="key"name="key">
                                <div class="col-lg-12 col-md-12">
                                    <div class="submit-page form-simple">
                                        <div class="frm_submit_block">
                                            <h3>基本信息</h3>
                                            <div class="form-row">
                                                <div class="form-group col-md-4">
                                                    <label>商品类型</label>
                                                    <select name="productType" class="form-control">
                                                        <option value="dorm"<c:if test="${product.productType == 'dorm'}">selected</c:if>>宿舍用品</option>
                                                        <option value="study"<c:if test="${product.productType == 'share'}">selected</c:if>>学习用品</option>
                                                        <option value="idle"<c:if test="${product.productType == 'share'}">selected</c:if>>闲置物品</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label>单价<a href="#" class="tip-topdata" ><i class="ti-help"></i></a></label>
                                                   <input type="number" name="price" class="form-control" value="${product.price}">
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label>所属学校</label>
                                                    <select name="city" class="form-control">
                                                        <option value="清华大学"<c:if test="${productSearchVo.city == '清华大学'}">selected</c:if>>清华大学</option>
                                                        <option value="北京大学"<c:if test="${productSearchVo.city == '北京大学'}">selected</c:if>>北京大学</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label>商品标题信息<a href="#" class="tip-topdata" data-tip="如计算机网络"><i class="ti-help"></i></a></label>
                                                    <input type="text" name="title" class="form-control"value="${product.title}">
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label>商品详细地址信息<a href="#" class="tip-topdata" data-tip="如北京大学一号宿舍楼"><i class="ti-help"></i></a></label>
                                                    <input type="text" name="address" class="form-control"value="${product.address}">
                                                </div>


                                                <div class="form-group col-md-6">
                                                    <label>联系人信息</label>
                                                    <input type="text" name="contactName" class="form-control"value="${product.contactName}">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>联系人电话</label>
                                                    <input type="text" name="contactPhone" class="form-control"value="${product.contactPhone}">
                                                </div>

                                                <div class="form-group col-md-12">
                                                    <label>详细描述</label>
                                                    <textarea class="form-control" name="content" rows="3">${product.content}</textarea>
                                                </div>





                                                <div class="form-group col-md-12">
                                                    <label>轮播图上传</label>
                                                    <input type="file" name="file" id="file" multiple class="file-loading"/>
                                                </div>
                                            <div class="form-group col-md-12">
                                                <button class="btn btn-theme bg-2" type="button" onclick="submitProduct()">发布</button>
                                            </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</section>
<%@include file="../common/footer.jsp"%>
<script>
    $(function () {
       var timestamp = Date.parse(new Date());
       $("#key").val(timestamp);
       $("#file").fileinput({
           language:'zh', //设置语言
           uploadUrl:"/file/upload?key="+timestamp,//上传地址
           allowedFileExtensions:['png','jpg','jpeg','gif'],//允许上传的文件后缀
           showUpload:true,//显示批量上传的按钮
           showCaption:false,//不显示标题
       });
    });
</script>
