<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 15.05.2019
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.button.registration" var="registration_button"/>

<html>
<link rel="stylesheet" href="css/stvle.css">
<script type="text/javascript" src="js/js-topper.js"></script>

<head>
    <title>AntClub</title>
</head>

<body>
<c:import url="header.jsp"/>

<a  class="text float-right ">Login:
    <c:out value="${sessionScope.user.login}"/>
</a><br>
<a  class="text float-right ">Date registration:
    <c:out value="${sessionScope.user.date_registration}"/>

</a><br>


<c:out value="${requestScope.error}"/>

Для проверки if:
<c:if test="${sessionScope.user.login eq 'admin' }">
    ADMIN YA!
</c:if>

</body>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<c:import url="footer.jsp"/>

</html>