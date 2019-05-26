<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 12.05.2019
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>

<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>ERROR</title>
</head>
<body>
    Request from ${pageContext.errorData.requestURI} is failed<br>
    Servlet name: ${pageContext.errorData.servletName} <br>
    Status code: ${pageContext.errorData.statusCode}<br>
    Message from Exception: ${pageContext.errorData.throwable}
</body>
</html>
