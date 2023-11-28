<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2023/11/28
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<form action="uploadFile" method="post" enctype="multipart/form-data">
    上传文件: <input type="file" name="uploadFile"/><br>
    <button>上传</button>
</form>
</body>
</html>
