<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2023/11/29
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>servletContext对象共享数据</title>
</head>
<body>
访问次数：${applicationScope.get("viewNumber")}
</body>
</html>
