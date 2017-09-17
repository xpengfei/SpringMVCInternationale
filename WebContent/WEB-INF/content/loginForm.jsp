<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMVC国际化</title>
</head>
<body>
	<!-- 用户可自行切换语言环境 -->
	<a href="loginForm?request_locale=zh_CN">中文</a>|
	<a href="loginForm?request_locale=en_US">英文</a>
	<!-- 使用message标签来输出国际化信息 -->
	<h3><spring:message code="title"/></h3>
	
	<form:form modelAttribute="user" method="post" action="login">
		<table>
			<tr>
				<td>
					<spring:message code="loginname"/>
				</td>
				<td><form:input path="loginname"/> </td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="password"/>
				</td>
				<td><form:password path="password"/> </td>
			</tr>
			
			<tr>
		 		<td>
		 			<input type="submit" value="<spring:message code="submit"/>" />	
 				</td>
			</tr>
		
		</table>
	</form:form>
	
	
</body>
</html>