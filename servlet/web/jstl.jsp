<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2023/11/27
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>jstl测试</title>
</head>
<body>
<c:forEach begin="1" end="10" var="i" step="2">
    <h1>
        ${i}
    </h1>
</c:forEach>
</body>
</html>
