<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2023/11/27
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>jstl测试</title>
</head>
<body>

<%--1、核心标签库  http://java.sun.com/jsp/jstl/core--%>
<%--声明变量 变量在pagecontext作用域下面--%>
<c:set var="name" value="qq">

</c:set>
${name}

<%--判断--%>
<c:if test="${name!=null}">
    ${name}
</c:if>

<%--多分支 只会执行一个分支，当有多个分支的test都为true，只会执行第一个--%>
<c:choose>
    <c:when test="${name.equals('qq')}">
        ${'hu'}
    </c:when>
    <c:when test="${name!=null}">
        ${name}
    </c:when>
</c:choose>

<c:forEach begin="1" end="10" var="i" step="2">
    <h1>
        ${i}
    </h1>
</c:forEach>

<%--2、格式化标签库  http://java.sun.com/jsp/jstl/fmt--%>

<%--格式化日期--%>
<%--<c:set var="date" value="${sessionScope.get('data')}"/>--%>
<%--<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>--%>

<%--格式化数字--%>


<%--国际化--%>
<%--basename：国际化文件基础名--%>
<%--<fmt:bundle basename="">--%>
<%--    &lt;%&ndash;key：国际化文件中的key properties文件中存储中文，要将中文转成unicode编码才能写进去&ndash;%&gt;--%>
<%--    <fmt:message key="driver"/>--%>
<%--</fmt:bundle>--%>

</body>
</html>
