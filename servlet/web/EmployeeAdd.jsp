<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2023/11/30
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>员工添加</title>
  <link rel="stylesheet" href="./css/register.css" />
</head>
<body>
<div class="uf/operationEmployee/addition">
  <form action="/uf/operationEmployee/addition" method="post">
    <div class="row">
      <div class="text">姓名：</div>
      <div class="registerForm">
        <input type="text" name="name"  />
      </div>
    </div>
    <div class="row">
      <div class="text">住址：</div>
      <div class="registerForm">
        <input type="text" name="location"  />
      </div>
    </div>
    <div class="row">
      <div class="text">性别：</div>
      <div class="registerForm" style="width: 300px">
        <input type="radio" name="sex" value="1"/>男
        <input type="radio" name="sex" value="0"/>女
      </div>
    </div>
    <div class="row">
      <div class="text">入职时间：</div>
      <div class="registerForm">
        <input type="date" name="join" />
      </div>
    </div>
    <div class="row">
      <div class="text">薪资：</div>
      <div class="registerForm">
        <input type="number" name="salary" />
      </div>
    </div>
    <div class="row">
      <div class="text">部门id：</div>
      <div class="registerForm">
        <input type="number" name="dept" />
      </div>
    </div>
    <div class="row">
      <div class="text">头像：</div>
      <div class="registerForm">
        <input type="text" name="photo" />
      </div>
    </div>
    <div class="row">
      <div class="text"></div>
      <div class="registerForm">
        <button type="submit" id="submit">添加</button>
        <button type="reset">重置</button>
      </div>
    </div>
  </form>
</div>
</body>
</html>
