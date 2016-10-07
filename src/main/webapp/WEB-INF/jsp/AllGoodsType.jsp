<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2016/10/6
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">var pageContext = '${contextPath}'</script>
<html>
<head>
    <title>商品类型管理</title>
</head>
<body>
<c:forEach items="${allGoodsType}" var="goodtype">
    序号：${goodtype.id}
    商品类型：${goodtype.name}
    创建时间：${goodtype.CreateOn}
    修改时间：${goodtype.ModifedOn}
</c:forEach>
</body>
</html>
