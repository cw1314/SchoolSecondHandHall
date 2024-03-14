<%@ page contentType="text/html;charset=UTF-8" language="java" %>

</body>
</html>
<%--注册--%>
<div class="modal" id="signup">
    <div class="modal-dialog">
        <header>
            <div class="hm_nav">
                <h3 class="hm_nav_title">
                    注册
                </h3>
                <span class="mod-close"data-dismiss="modal"><i class="ti-close"></i></span>
            </div>
        </header>
        <div class="modal-content" id="sign-up">
            <div class="modal-body">
                <form id="registerForm">
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>姓名</label>
                                <div class="input-with-icon">
                                    <input type="text" name="userDisplayName" class="form-control" placeholder="姓名">
                                    <i class="ti-user"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>电子邮箱</label>
                                <div class="input-with-icon">
                                    <input type="email" name="email" class="form-control" placeholder="电子邮箱用来重置密码">
                                    <i class="ti-email"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>手机号</label>
                                <div class="input-with-icon">
                                    <input type="text" name="phone" class="form-control" placeholder="手机号">
                                    <i class="ti-mobile"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>账号</label>
                                <div class="input-with-icon">
                                    <input type="text" name="userName" class="form-control" placeholder="账号">
                                    <i class="ti-user"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>密码</label>
                                <div class="input-with-icon">
                                    <input type="password" name="userPass" class="form-control" placeholder="密码">
                                    <i class="ti-unlock"></i>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>性别</label>
                                <div class="simple">
                                    <select name="sex"class="form-control">
                                        <option value='保密'>保密</option>
                                        <option value='男'>男</option>
                                        <option value='女'>女</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>个人描述</label>
                                <div class="input-with-icon">
                                    <textarea type="text" name="userDesc"class="form-control"style="height: 80px"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>业余爱好</label>
                                <div class="input-with-icon">
                                    <input type="text" name="hobby" class="form-control" placeholder="业余爱好">
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>工作</label>
                                <div class="simple">
                                    <select name="job"class="form-control">
                                        <option value="互联网/计算机">互联网/计算机</option>
                                        <option value="生产制造">生产制造</option>
                                        <option value="教育/培训">教育/培训</option>
                                        <option value="金融/银行">金融/银行</option>
                                        <option value="保险/投资">保险/投资</option>
                                        <option value="文化/传媒">文化/传媒</option>
                                        <option value="公务员">公务员</option>
                                        <option value="服务行业">服务行业</option>
                                        <option value="学生">学生</option>
                                        <option value="其他">其他</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <button type="button"onclick="submitRegister()"class="btn btn-md full-width pop-login bg-2">创建账号</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<%--登录--%>
<div class="modal" id="login">
    <div class="modal-dialog">
        <header>
            <div class="hm_nav">
                <h3 class="hm_nav_title">
                    登录
                </h3>
                <span class="mod-close"data-dismiss="modal"><i class="ti-close"></i></span>
            </div>
        </header>
        <div class="modal-content" >
            <div class="modal-body">
                <form id="loginForm">
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>账号</label>
                                <div class="input-with-icon">
                                    <input type="text" name="userName" class="form-control" placeholder="请输入账号">
                                    <i class="ti-user"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                                <label>密码</label>
                                <div class="input-with-icon">
                                    <input type="password" name="userPass" class="form-control" placeholder="请输入密码">
                                    <i class="ti-unlock"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="button"onclick="submitLogin()"class="btn btn-md full-width pop-login bg-2">登录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>























