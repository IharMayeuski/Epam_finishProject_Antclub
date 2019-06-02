<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.button.registration" var="registration_button"/>

<html>

<c:import url="header.jsp"/>

<head><title>AntClub</title>

    <style type="text/css">
        body, footer {
            color: white;
        }

        body {
            background-size: cover;
            background-image: url("<c:url value="/img/TitlePage.jpg"/>");
        }

    </style>

</head>

<body>

<c:if test="${empty user }">

    <div class="container">
        <form action="controller" method="post" role="form" class="form-horizontal">
            <input type="hidden" name="command" value="find_user">
            <div class="form-group col-md-3">
                <p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
                <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
                <label for="Login">Login:</label>
                <input type="text" name="login" class="form-control" id="Login">
                <label for="Password">Password:</label>
                <input type="password" name="password" class="form-control" id="Password"><br>
                <button type="submit" class="btn btn-secondary">Submit</button>
            </div>
        </form>

    </div>
</c:if>

</body>
<br><br><br><br><br><br><br><br><br>
<c:import url="footer.jsp"/>

</html>