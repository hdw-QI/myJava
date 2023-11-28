<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2023/11/28
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人中心</title>
</head>
<body>
<c:if test="${sessionScope.get('account')!=null}">
    登陆成功！用户名为：${sessionScope.get('account')}
    <a href="logout">注销</a>
</c:if>
<c:if test="${sessionScope.get('account')==null}">
    <a href="login.jsp">请先登录</a>
</c:if>
<%--用cookie的方式--%>
<%--<c:if test="${requestScope.get('account')!=null}">--%>
<%--    登陆成功！用户名为：${requestScope.get('account').value}--%>
<%--    <a href="logout">注销</a>--%>
<%--</c:if>--%>
<%--<c:if test="${requestScope.get('account')==null}">--%>
<%--    <a href="login.jsp">请先登录</a>--%>
<%--</c:if>--%>
</body>
</html>
