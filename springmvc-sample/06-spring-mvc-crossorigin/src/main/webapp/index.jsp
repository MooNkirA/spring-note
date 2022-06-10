<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--异步请求-->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {//加载事件
        $("#testAjax").click(function () {
            $.ajax({
                type: "POST",
                url: "http://www.moon.com:8080/useCrossOrigin",
                dataType: "text",
                data: '{"username":"MooNkirA","password":"1234","gender":"男","age":"18"}',
                contentType: "application/json",
                success: function (data) {
                    alert(data);
                }
            });
        });
    });
</script>

<a href="#" id="testAjax">发送异步请求</a>

<img src="http://www.moon.com:8080/getImage" alt=""/>

<link href="http://www.moon.com:8080/getCss" type="text/css" rel="stylesheet">
<div>我只是一个有一点点样式的div</div>
</body>
</html>
