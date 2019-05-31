<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>

<html>
<link rel="stylesheet" href="css/stvle.css">
<script type="text/javascript" src="js/js-topper.js"></script>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>AntClub</title>
    <style type="text/css">
        body, footer {
            color: white;
        }
        body {
            background-size: cover;
            background-image: url("<c:url value="/static/img/TitlePage.jpg"/>");
        }
    </style>

    <fmt:message bundle="${loc}" key="locale.default.welcome_message" var="welcome_message"/>
    <div align="center">${welcome_message}</div>

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

        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-secondary my-4 my-sm-0 mr-sm-4" type="submit">Search</button>
        </form>

        <ul class="navbar-nav">
            <li class="nav-item dropdown ">
                <c:if test="${not empty user}">
                    <a class="nav-link dropdown-toggle form-inline my-2 my-lg-0 my-sm-0 mr-sm-4" href="#"
                       id="navbarDropdownMenuLink4" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">Hello, ${sessionScope.user.login}
                    </a>
                    <div class="dropdown-menu form-inline my-2 my-lg-0 my-sm-0 mr-sm-4"
                         aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Date registration: ${sessionScope.user.date_registration}"/></a>
                        <a class="dropdown-item" href="#">English</a>
                    </div>
                </c:if>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Language
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Русский</a>
                    <a class="dropdown-item" href="#">English</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<br>
</body>
</html>
