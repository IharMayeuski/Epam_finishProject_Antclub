<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.default.welcome_message" var="welcome_message"/>
<fmt:message bundle="${loc}" key="locale.default.welcome_message_guest" var="welcome_message_guest"/>
<fmt:message bundle="${loc}" key="locale.default.about" var="about_project"/>
<fmt:message bundle="${loc}" key="locale.default.search" var="search"/>
<fmt:message bundle="${loc}" key="locale.default.search_login" var="search_login"/>
<fmt:message bundle="${loc}" key="locale.default.language" var="language"/>
<fmt:message bundle="${loc}" key="locale.default.i_am_a_guest" var="guest"/>

<fmt:message bundle="${loc}" key="locale.default.articles" var="articles"/>
<fmt:message bundle="${loc}" key="locale.default.registration" var="registration_button"/>



<html>
<head>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">


    <title>AntClub</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <c:choose>
        <c:when test="${role eq 'admin'}">
            <a class="navbar-brand" href="controller?command=go_to_default_page">AntClub</a>
        </c:when>
        <c:otherwise>
            <a class="navbar-brand" href="controller?command=go_to_default_page">AntClub</a>
        </c:otherwise>
    </c:choose>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${not empty role}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink1" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">${articles}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <c:forEach items="${types}" var="types" varStatus="theCount">
                            <a class="dropdown-item" name="id"
                               href="controller?command=article&link_id=${types.id}">${types.typeNews}</a>
                        </c:forEach>
                    </div>
                </li>
            </c:if>

            <li class="nav-item">
                <a class="nav-link mr-sm-8" href="#">${about_project}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link">
                    <c:choose>
                        <c:when test="${local eq 'rus'}">
                            <fmt:setLocale value="ru-RU"/>Сегодня: <fmt:formatDate value="${now}"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:setLocale value="en-EN"/>Today: <fmt:formatDate value="${now}"/>
                        </c:otherwise>
                    </c:choose></a>
            </li>
        </ul>


        <%--  <c:if test="${not empty user and role eq 'admin'}">--%>
        <div>
            <form class="form-inline my-2 my-lg-0" method="post">
                <input type="hidden" name="command" value="find_user_by_login">
                <input class="form-control mr-sm-2" type="search" name="search" placeholder="${search_login}"
                       aria-label="Search">
                <button class="btn btn-outline-secondary my-4 my-sm-0 mr-sm-4" type="submit">${search}</button>
            </form>
        </div>
        <%--</c:if>--%>
        <ul class="navbar-nav">
            <li class="nav-item dropdown ">
                <c:if test="${not empty user and user.deleted eq 'not deleted'}">
                <a class="nav-link dropdown-toggle form-inline my-2 my-lg-0 my-sm-0 mr-sm-4"
                   href="javascript:void(0)" id="navbarDropdownMenuLink4" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">${welcome_message}, ${sessionScope.user.login}
                </a>
                <div class="dropdown-menu form-inline my-2 my-lg-0 my-sm-0 mr-sm-4"
                     aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Date registration: ${sessionScope.user.date_registration}"/></a>
                    <a class="dropdown-item" href="#myProfile" data-toggle="tab" id="login_pop">Профиль</a>

                    <a class="dropdown-item" href="controller?command=confirm_delete">Delete account</a>
                    <a class="dropdown-item" href="controller?command=logOut">Logout</a>
                </div>
                </c:if>
                <c:if test="${empty user}">
                <a class="nav-link" href="#login_form" id="join_pop">${registration_button}</a>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=go_to_registration_page">${registration_button}</a>
            </li>
            </c:if>

            <c:if test="${empty role}">
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=i_am_guest">${guest}</a>
                </li>
            </c:if>
            <c:if test="${not empty user }">
                <li class="nav-item">
                    <img class="mr-3" src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${user.id}.jpg" alt="" style="height:50px;">
                </li>
            </c:if>
            <c:if test="${role eq 'guest'}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button"
                       data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">${welcome_message}, ${welcome_message_guest}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="controller?command=logOut">Logout</a>
                    </div>
                </li>
            </c:if>
            <%--<c:if test="${empty role}">--%>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${language}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item"
                       href="controller?command=change_locale&locale=2&path=${pageContext.request.servletPath}">Русский</a>
                    <a class="dropdown-item"
                       href="controller?command=change_locale&locale=1&path=${pageContext.request.servletPath}">English</a>
                </div>
            </li>
            <%--</c:if>--%>
        </ul>
    </div>
</nav>




<script src="../../js/jquery.min.js"></script>
<script src="../../js/bootstrap.js"></script>
<script src="../../js/bootstrap.bundle.js"></script>

</body>
</html>