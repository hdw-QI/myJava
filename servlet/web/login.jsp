<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2023/11/28
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="./css/register.css" />
</head>
<body>
<div class="register">
    <form action="login" method="post">
        <div class="row">
            <div class="text">用户名：</div>
            <div class="registerForm">
                <input type="text" name="username" id="username" />
            </div>
        </div>
        <div class="row">
            <div class="text">密码：</div>
            <div class="registerForm">
                <input type="password" name="password" id="password" />
            </div>
        </div>
        <button type="submit" id="submit">登录</button>
    </form>
</div>
</body>
</html>
