<%--
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 15.05.2019
  Time: 20:22
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.registration" var="registration_button"/>

<html>
<head>
    <title>admin</title>
</head>

<body>
<c:import url="header.jsp"/>
<c:out value="${requestScope.error}"/>

ADMIN PAGE
<c:out value="${requestScope.types}"/>

</body>
<footer>
    <c:import url="footer.jsp"/>
</footer>

</html>--%>
