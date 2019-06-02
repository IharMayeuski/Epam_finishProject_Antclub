<%--
  Created by IntelliJ IDEA.
  User: Maevskiy
  Date: 5/26/2019
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>

    <style type="text/css">
        body {
            background-size: cover;
            background-image: url("<c:url value="/img/TitlePage.jpg"/>");
        }
    </style>
</head>
<body>

<c:import url="header.jsp"/>

User page
<c:out value="${requestScope.error}"/>

<c:import url="footer.jsp"/>
</body>
</html>
