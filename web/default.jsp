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


<div class="container" align="right">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="locale" value="rus"> <input
            type="submit" name="submit" value="рус"/>
    </form>

    <form action="controller" method="post">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="locale" value="en"> <input
            type="submit" name="submit" value="eng"/>
    </form>
</div>

<a class="btn btn-secondary float-right" href="controller?command=go_to_registration_page"
   role="button">${registration_button}</a><br><br>
<a class="btn btn-secondary float-right" href="controller?command=go_to_guest_page" role="button"> I am a guest </a>


<div class="container">
    <form action="controller" method="post" role="form" class="form-horizontal">
        <input type="hidden" name="command" value="find_user">
        <div class="form-group col-md-3">
            <label for="Login">Login:</label>
            <p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
            <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
            <input type="text" name="login" class="form-control" id="Login">
            <label for="Password">Password:</label>
            <input type="password" name="password" class="form-control" id="Password"><br>
            <button type="submit" class="btn btn-secondary">Submit</button>
        </div>
    </form>
</div>
</body>

<c:import url="footer.jsp"/>

</html>