<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<style type="text/css">
    body, footer {
        color: white;
    }

    body {
        background-size: cover;
        background-image: url("<c:url value="/img/TitlePage.jpg"/>");
    }

</style>
<c:out value="${requestScope.articles}"/>

</body>
</html>
