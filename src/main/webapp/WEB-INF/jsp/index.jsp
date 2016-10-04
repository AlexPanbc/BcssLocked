<%--
  Created by IntelliJ IDEA.
  User: LiuHuiChao
  Date: 2016/9/24
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">var pageContext ='${contextPath}';</script>
<html>
<head>
    <title>测试页：测试ssm的CURD操作</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

</head>
<body>
        <h1>测试ok</h1>
<h2>${person.id}</h2>
</body>
</html>
