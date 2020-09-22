<%--
  Created by IntelliJ IDEA.
  User: MoonZero
  Date: 2020-9-22
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>测试拦截器成功跳转页面</h2>

<h3>message: ${requestScope.message}<h3>
<%System.out.println("message.jsp执行了");%>
</body>
</html>
