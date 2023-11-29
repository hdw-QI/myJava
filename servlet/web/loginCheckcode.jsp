<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2023/11/29
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>结合验证码进行登录</title>
  <link rel="stylesheet" href="./css/loginCheckcode.css" />
</head>
<body>
<div class="register">
  <form action="loginCheckcode" method="post">
    <div class="row">
      <div class="text">用户名：</div>
      <div class="registerForm">
        <input type="text" name="name" id="username" />
      </div>
    </div>
    <div class="row">
      <div class="text">密码：</div>
      <div class="registerForm">
        <input type="password" name="password" id="password" />
      </div>
    </div>
    <div class="row">
      <div class="text">验证码：</div>
      <div class="registerForm">
        <input type="password" name="vericode" />
      </div>
      <div class="imageCode">
        <img id="vericodeImg" src="imageCode" style="vertical-align: bottom;"><a id="kanbuq" href="javascript:changeImg();">看不清，换一张</a>
      </div>
    </div>
    <button type="submit" id="submit">登录</button>
  </form>
</div>
<script type="text/javascript" src="./js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
  function changeImg() {
    //需要让每次请求的url都发生变化。否则服务器会认为访问的时一张图片，就不会刷新请求了
    //每次url一样，服务器会认为访问的url是同一张图片，没变化啊
    $("#vericodeImg").attr("src","imageCode?"+Math.random())
  }
</script>
</body>
</html>
