<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Anouncement</title>
</head>
<body>
<br>
<div style="text-align: center">
    <c:if test="${not empty mess}">
        <div class="alert alert-${rs == 1 ? "success" : "danger"}" role="alert" style="display: inline">
                ${mess}
        </div>
    </c:if>
</div>
</body>
</html>
