<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2016/10/6
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<c:forEach items="${AllUserInfo}" var="user">
    序号：${user.id}
    名称：${user.name}
    密码：${user.pass}
    电话：${user.phone}
    邮箱：${user.mailbox}
    诞生：${user.createdon}
    变更：${user.modifiedon}
    <br/><br/>
</c:forEach>
</body>
</html>
