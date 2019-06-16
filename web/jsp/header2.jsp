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

<jsp:useBean id="now" class="java.util.Date"/>

<html>
<head>

    <title>AntClub</title>
</head>
<body>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script>
    function disp(form) {
        if (form.style.display == "none") {
            form.style.display = "block";
        } else {
            form.style.display = "none";
        }
    }
</script>


<style>
    @media only screen and (min-width: 168px) {
        .dropdown:hover .dropdown-menu {
            display: block;
            margin-top: 0;
        }
    }
</style>


<link rel="stylesheet" href="http://bootstraptema.ru/snippets/style/2015/bootswatch/bootstrap-spacelab-v-3.3.6.css"
      media="screen">
<link rel="stylesheet" href="http://bootstraptema.ru/snippets/style/2015/bootswatch/custom.min.css">


<nav class="navbar navbar-expand-lg  bg-light navbar-default navbar-fixed-top">
    <div class="col-lg-12">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="controller?command=go_to_default_page">AntClub</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">About us <span
                            class="sr-only">(current)</span></a>
                    </li>

                </ul>

                <c:if test="${not empty user }">
                    <form class="navbar-form navbar-right" role="search" action="controller" method="post">
                        <input type="hidden" name="command" value="find_user_by_login">
                        <div class="form-group">
                            <input type="text" name="search" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </c:if>


                <ul class="navbar-nav">
                    <li class="nav-item dropdown ">
                        <c:if test="${not empty user and user.deleted eq 'not deleted'}">
                        <a class="nav-link dropdown-toggle form-inline my-2 my-lg-0 my-sm-0 mr-sm-4"
                           href="javascript:void(0)" id="navbarDropdownMenuLink4" data-toggle="dropdown"
                           aria-haspopup="true"
                           aria-expanded="false">${welcome_message}, ${sessionScope.user.login}
                        </a>
                        <div class="dropdown-menu form-inline my-2 my-lg-0 my-sm-0 mr-sm-4"
                             aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">Date
                                registration: ${sessionScope.user.date_registration}"/></a>
                            <a class="dropdown-item" href="#myProfile" data-toggle="tab"
                               id="login_pop">Профиль</a>

                            <a class="dropdown-item" href="controller?command=confirm_delete">Delete account</a>
                            <a class="dropdown-item" href="controller?command=logOut">Logout</a>
                        </div>
                        </c:if>
                        <c:if test="${empty role}">
                    <li class="nav-item">
                        <a class="nav-link"
                           href="controller?command=go_to_registration_page">${registration_button}</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=i_am_guest">${guest}</a>
                    </li>
                    </c:if>
                    <c:if test="${not empty user }">
                        <li class="nav-item">
                            <img class="mr-3"
                                 src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${user.id}.jpg"
                                 alt="" style="height:50px;">
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

                    <li class="nav-item dropdown ">
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
                    <c:if test="${role eq 'admin'}">
                        <li class="nav navbar-nav"><a onclick="disp(document.getElementById('form1'))">Input new
                            type<span class="sr-only">(current)</span></a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</nav>




<%--<input type="button" value="казать/Скрыть" onclick="disp(document.getElementById('form1'))" />--%>

</body>


<script>
    $('.dropdown-toggle').click(function (e) {
        if ($(document).width() > 768) {
            e.preventDefault();
            var url = $(this).attr('href');
            if (url !== '#') {
                window.location.href = url;
            }
        }
    });
</script>


<script src="http://bootstraptema.ru/snippets/style/2015/bootswatch/jquery-1.10.2.min.js"></script>
<script src="http://bootstraptema.ru/snippets/style/2015/bootswatch/bootstrap.min.js"></script>
<script src="http://bootstraptema.ru/snippets/style/2015/bootswatch/custom.js"></script>

</html>
