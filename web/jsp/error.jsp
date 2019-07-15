<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 12.05.2019
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>

<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Show Error Page</title>
    <style type="text/css">
        body, footer {
            color: white;
        }

        body {
            background-size: cover;
            background-image: url("<c:url value="../img/TitlePage.jpg"/>");
        }

    </style>
</head>

<body>
<div class="container">
    <h1>Opps...</h1>

    Request from ${pageContext.errorData.requestURI} is failed<br>
    Servlet name: ${pageContext.errorData.servletName} <br>
    Status code: ${pageContext.errorData.statusCode}<br>
    Message from Exception: ${pageContext.errorData.throwable}
</div>
</body>
</html>
