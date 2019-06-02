<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.welcome_message" var="welcome_message"/>
<fmt:message bundle="${loc}" key="locale.button.registration" var="registration_button"/>


<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

    <title>AntClub</title>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="controller?command=go_to_default_page">AntClub</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink1" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Articles
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <c:forEach items="${types}" var="types" varStatus="theCount">
                        <a class="dropdown-item" name="id"
                           href="controller?command=article&link_id=${types.id}">${types.typeNews}</a>
                    </c:forEach>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">About project</a>
            </li>
        </ul>
        <c:if test="${not empty user and user.role eq 'admin'}">
            <div>
                <form class="form-inline my-2 my-lg-0" formmethod="post">
                    <input type="hidden" name="command" value="find_user_by_login">
                    <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search"
                           aria-label="Input login">
                    <button class="btn btn-outline-secondary my-4 my-sm-0 mr-sm-4" type="submit">Search user</button>
                </form>
            </div>

        </c:if>
        <ul class="navbar-nav">
            <li class="nav-item dropdown ">
                <c:if test="${not empty user and user.deleted eq 'not deleted'}">
                    <a class="nav-link dropdown-toggle form-inline my-2 my-lg-0 my-sm-0 mr-sm-4" href="#"
                       id="navbarDropdownMenuLink4" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">${welcome_message}, ${sessionScope.user.login}
                    </a>
                    <div class="dropdown-menu form-inline my-2 my-lg-0 my-sm-0 mr-sm-4"
                         aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Date
                            registration: ${sessionScope.user.date_registration}"/></a>
                        <a class="dropdown-item" href="#">Something still</a>
                        <a class="dropdown-item" href="controller?command=logOut">Logout</a>
                    </div>
                </c:if>
                <c:if test="${empty user}">
            <li class="nav-item">
                <a class="nav-link" href="controller?command=go_to_registration_page">${registration_button}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=go_to_guest_page">I am guest</a>
            </li>
            </c:if>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Language
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href=controller?command=change_locale&locale=2>Русский</a>
                    <a class="dropdown-item" href=controller?command=change_locale&locale=1>English</a>
                </div>
            </li>
        </ul>
    </div>
</nav>


<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="bootstrap/js/bootstrap.bundle.js"></script>

</body>

</html>
