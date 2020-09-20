<%@page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<body>
<!-- 入门案例的请求 -->
<a href="${pageContext.request.contextPath}/hello">SpringMVC基于servlet3.0规范纯注解开发的入门</a>
<hr/>
<!-- RequestMapping注解的使用 -->
<%--<a href="${pageContext.request.contextPath}/springmvc/useRequestMapping?name">RequestMapping注解的使用</a>
<form action="${pageContext.request.contextPath}/springmvc/useRequestMapping" method="post">
    <input type="text" name="name" value="123">
    <input type="submit" value="RequestMapping注解的使用 Post方式请求">
</form>--%>
<!-- 如果不使用pageContext.request.contextPath获取当前项目名称，在写请求url最前面不要带"/"即可 -->
<%--
<a href="springmvc/useRequestMapping?name">RequestMapping注解的使用</a>
<form action="springmvc/useRequestMapping" method="post">
    <input type="text" name="name" value="123">
    <input type="submit" value="RequestMapping注解的使用 Post方式请求">
</form>
<hr/>
--%>

<!-- RequestParam注解的使用
<a href="useParam1?name=test&age=18">RequestParam注解的使用之基本类型和String类型的入参</a>
<a href="useRequestParam?page=1">RequestParam注解的使用</a> -->
<form action="useParam2" method="post">
    用户名：<input type="text" name="username"><br/>
    密码：<input type="text" name="password"><br/>
    年龄：<input type="text" name="age"><br/>
    性别：<input type="text" name="gender"><br/>
    <%--生日：<input type="text" name="birthday"><br/>--%>
    <input type="submit" value="提交">
</form>
<hr/>

<%--ModelAttribute注解的使用--%>
<a href="useModelAttribute?name=test">ModelAttribute注解的使用</a>
<hr/>

<%--SessionAttribute和SessionAttributes注解的使用--%>
<a href="useSessionAttributes">SessionAttributes注解的使用</a>
<br/>
<a href="useSessionAttribute">SessionAttribute注解的使用</a>

<%--ExceptionHandler注解的使用--%>
<a href="useExceptionHandler">ExcpetionHandler注解的使用</a>
<hr/>


</body>
</html>
