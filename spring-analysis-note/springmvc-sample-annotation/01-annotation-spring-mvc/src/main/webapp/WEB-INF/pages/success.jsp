<%@page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<body>
<h2>执行成功！</h2>

请求域username：<%=request.getAttribute("username")%><br/>
请求域password：<%=request.getAttribute("password")%><br/>
请求域age：<%=request.getAttribute("age")%>
<hr/>
会话域username：<%=session.getAttribute("username")%><br/>
会话域password：<%=session.getAttribute("password")%><br/>
会话域age：<%=session.getAttribute("age")%>

</body>
</html>
