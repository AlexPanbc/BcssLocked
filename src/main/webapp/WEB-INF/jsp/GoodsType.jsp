<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2016/10/6
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品类型详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h1>${goodsTypejson}</h1>
序号：${goodsType.id}
名称：${goodsType.name}
诞生：${goodsType.createdon}
变更：${goodsType.modifiedon}
<br/><br/>
</body>
</html>
