<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.registration" var="registration_button"/>

<html>
<c:import url="header2.jsp"/><br>

<head><title>AntClub</title>
    <style type="text/css">
        body, footer {
            color: white;
        }

        body {
            background-size: cover;
           /* background-image: url("<c:url value="/img/TitlePage.jpg"/>");*/
            background: url(http://bootstraptema.ru/images/bg/bg-1.png)
        }
    </style>
</head>

<body>


<div class="container">
    <form action="controller" method="post" role="form" class="form-horizontal">
        <input type="hidden" name="command" value="account_delete">
        <div class="form-group col-md-3">
            <p class="text-left text-danger font-weight-bold">
                Are you sure in deleting your account? Please to input<br>
            </p>
            <label for="Email">E-mail:</label>
            <input type="text" name="email" class="form-control" id="Email">
            <label for="Password">Password:</label>
            <input type="password" name="password" class="form-control" id="Password"><br>
            <button type="submit" class="btn btn-secondary">Submit</button>


          <%--  <p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
            <p class="text-left text-info font-weight-bold">${requestScope.account_deleted}</p>--%>
            <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>

        </div>
    </form>
</div>


</body>
<footer>
    <c:import url="footer.jsp"/></footer>

</html>