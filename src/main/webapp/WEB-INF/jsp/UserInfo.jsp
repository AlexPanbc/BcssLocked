<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2016/10/6
  Time: 1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">var pageContext = '${contextPath}';</script>
<html>
<head>
    <title>用户详细信息页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h1>${userJson}</h1>
序号：${user.id}
名称：${user.name}
密码：${user.pass}
电话：${user.phone}
邮箱：${user.mailbox}
诞生：${user.createdon}
变更：${user.modifiedon}
<br/><br/>
</body>
</html>

