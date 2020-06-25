<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
</head>
<body>

	<form action="${pageContext.request.contextPath }/queryItem.do"
		method="post">
		<!-- 查询条件： -->
		<table width="100%" border=1>
			<tr>
				<td>
					商品名称：<input type="text" name="item.name" value=""/>
					<input type="submit" value="查询" />
				</td>
			</tr>
		</table>
		商品列表：
		<table width="100%" border=1>
			<tr>
				<td>商品ID</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
				<td>操作</td>
			</tr>
				<c:forEach items="${itemList}" var="item" varStatus="vs">
					<%-- 测试数组类型绑定 时使用 --%>
					<tr>
						<td><input type="checkbox" name="ids" value="${item.id}"/></td>
						<td>${item.name }</td>
						<td>${item.price }</td>
						<td><fmt:formatDate value="${item.createtime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${item.detail }</td>
						<td><a
							href="${pageContext.request.contextPath }/queryItemById.do?id=${item.id}">修改</a></td>
				
					</tr>
				</c:forEach>
			<tr>
				<!-- 测试数组类型绑定 -->
				<td colspan="6">
					<input type="submit" value="批量删除" />
				</td> 
			</tr>
		</table>
		
	</form>
	
	<form action="${pageContext.request.contextPath }/queryItem.do"
		method="post">
		商品列表：
		<table width="100%" border=1>
			<tr>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
			</tr>
			<c:forEach items="${itemList}" var="item" varStatus="vs">
				<%-- 测试list集合类型绑定 时使用 --%>
				<!-- itemList:要绑定的商品集合的属性
					itemList[0]:商品集合属性中第一个商品对象
					itemList[0].id：第一个商品对象的id属性
					
					varStatus：当前遍历对象的状态
					vs.index：当前对象的索引 -->
				<tr>
					<td><input type="text" name="itemList[${vs.index}].name" value="${item.name}"/></td>
					<td><input type="text" name="itemList[${vs.index}].price" value="${item.price}"/></td>
					<td><input type="text" name="itemList[${vs.index}].createtime" 
					value='<fmt:formatDate value="${item.createtime}"
							pattern="yyyy-MM-dd HH:mm:ss" />'/></td>
					<td><input type="text" name="itemList[${vs.index}].detail" value="${item.detail}"/></td>
				</tr> 
			</c:forEach>
			<tr>
				<!-- 测试list集合类型绑定  -->
				<td colspan="6">
					<input type="submit" value="批量修改" />
				</td> 
			</tr>
		</table>
		
	</form>

</body>

</html>
