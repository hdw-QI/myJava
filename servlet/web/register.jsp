<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2023/11/27
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>表单验证</title>
    <link rel="stylesheet" href="./css/register.css" />
</head>
<body>
<div class="register">
    <form action="register" method="post">
        <div class="row">
            <div class="text">用户名：</div>
            <div class="registerForm">
                <input type="text" name="username" id="username" /><span id="usernameTip">不能有空格，可以是中文，长度控制在 2-10 字节以内</span>
            </div>
        </div>
        <div class="row">
            <div class="text">密码：</div>
            <div class="registerForm">
                <input type="password" name="password" id="password" /><span id="passwordTip">最小长度:8 最大长度:16</span>
            </div>
        </div>
        <div class="row">
            <div class="text">确认密码：</div>
            <div class="registerForm">
                <input type="password" name="againPassword" id="againPassword" /><span id="againPasswordTip">请再输入一遍您上面填写的密码</span>
            </div>
        </div>
        <div class="row">
            <div class="text">邮箱：</div>
            <div class="registerForm">
                <input type="email" name="email" id="email" /><span id="emailTip">请填写真实并且最常用的邮箱</span>
            </div>
        </div>
        <div class="row">
            <div class="text"><input type="checkbox" id="policy"></div>
            <div class="registerForm">
                我已阅读并完全同意条款内容
            </div>
        </div>
        <button type="submit" id="submit">提交注册</button>
        <button type="reset">重置</button>
    </form>
</div>
</body>
<script src="./js/register.js"></script>
</html>
