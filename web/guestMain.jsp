<%--
  Created by IntelliJ IDEA.
  User: Maevskiy
  Date: 5/26/2019
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.button.registration" var="registration_button"/>

<html>
<head>
    <title>Main</title>
</head>

<c:import url="header.jsp"/>

<c:import url="footer.jsp"/>

</body>

</html>
