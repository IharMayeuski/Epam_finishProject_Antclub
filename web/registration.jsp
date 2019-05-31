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

<head><title>AntClub</title></head>

<body>
<c:import url="header.jsp"/>

<a class="btn btn-secondary float-right" href="controller?command=go_to_guest_Page" role="button"> I am a guest </a>

<br>


<div class="container">
    <form action="controller" method="post" role="form" class="form-horizontal">
        <input type="hidden" name="command" value="registration">
        <div class="form-group col-md-3">
            <label for="Email">Email:</label>
            <input type="email" name="email" class="form-control" id="Email">
            <label for="Login">Login:</label>
            <input type="text" name="login" class="form-control" id="Login">
            <label for="Password1">Password:</label>
            <input type="password" name="password1" class="form-control" id="Password1">
            <label for="Password2">Repeat your password:</label>
            <input type="password" name="password2" class="form-control" id="Password2"><br>
            <button type="submit" class="btn btn-secondary">Submit</button>
        </div>
    </form>
</div>

<p class="text-left text-danger font-weight-bold">${requestScope.error}</p>

</body>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<c:import url="footer.jsp"/>

</html>