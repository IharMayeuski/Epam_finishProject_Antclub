<!DOCTYPE html>
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
<fmt:message bundle="${loc}" key="locale.default.registration" var="registration_button"/>


<link href="../../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/bootstrap.js"></script>
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css"/>--%>
<meta http-equiv="Content-Type" content="text/html"/>
<%--<link rel="stylesheet" href="css/style_header.css">--%>



<html>
<c:import url="header.jsp"/><br>

<head><title>Registration</title>
    <style type="text/css">
        body, footer {
            color: black;
        }
        body {
            background-size: cover;
            background-image: url("<c:url value="/img/TitlePage.jpg"/>");
        }
    </style>
</head>

<body>

<div class="container">
    <form action="controller" method="post" role="form" class="form-horizontal" id="registration">
        <input type="hidden" name="command" value="registration">
        <h1>Registration</h1>
        <fieldset id="inputs">
            <input id="email" type="email" name="email" data-icon="u" placeholder="Email" autofocus required pattern=".{4,45}">
            <input id="username" type="text" name="login" placeholder="Логин" autofocus required pattern=".{4,45}">
            <input id="password1" type="password" name="password1" placeholder="Пароль" autofocus required pattern=".{4,45}">
            <input id="password2" type="password" name="password2" placeholder="Повторите пароль" autofocus required pattern=".{4,45}">
        </fieldset>
        <fieldset id="actions">
            <button type="submit" class="btn btn-secondary">Submit</button>
            <a href="controller?command=go_to_default_page">У меня есть логин</a>

        </fieldset>
        <fieldset>
            <p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
            <p class="text-left text-info font-weight-bold">${requestScope.account_deleted}</p>
            <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
        </fieldset>
    </form>
</div>

<script src="../../js/jquery.min.js"></script>
<script src="../../js/bootstrap.js"></script>
<script src="../../js/bootstrap.bundle.js"></script>

</body>

<c:import url="../../jsp/footer.jsp"/>

</html>