<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.header.welcome_message" var="welcome_message"/>
<fmt:message bundle="${loc}" key="locale.header.welcome_message_guest" var="welcome_message_guest"/>
<fmt:message bundle="${loc}" key="locale.header.about" var="about_project"/>
<fmt:message bundle="${loc}" key="locale.header.search" var="search"/>
<fmt:message bundle="${loc}" key="locale.header.search_login" var="search_login"/>
<fmt:message bundle="${loc}" key="locale.header.language" var="language"/>
<fmt:message bundle="${loc}" key="locale.header.i_am_a_guest" var="guest"/>
<fmt:message bundle="${loc}" key="locale.header.submit" var="submit"/>
<fmt:message bundle="${loc}" key="locale.header.profile" var="profile"/>
<fmt:message bundle="${loc}" key="locale.header.adminpanel" var="adminpanel"/>
<fmt:message bundle="${loc}" key="locale.header.logout" var="logout"/>

<fmt:message bundle="${loc}" key="locale.header.articles" var="articles"/>
<fmt:message bundle="${loc}" key="locale.registration.registration" var="registration_button"/>

<jsp:useBean id="now" class="java.util.Date"/>

<html>
<head>
    <title>AntClub</title>
</head>
<body>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://bootstraptema.ru/snippets/style/2015/bootswatch/bootstrap-spacelab-v-3.3.6.css"
      media="screen">
<link rel="stylesheet" href="http://bootstraptema.ru/snippets/style/2015/bootswatch/custom.min.css">

<style>
    @media only screen and (min-width: 168px) {
        .dropdown:hover .dropdown-menu {
            display: block;
            margin-top: 0;
        }
    }
</style>

<nav class="navbar navbar-expand-lg  bg-light navbar-default navbar-fixed-top">
    <div class="col-lg-12">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand">
                    <form action="controller" method="post" role="form"
                          style="display: inline-block;">
                        <button class="btn-link" type="submit"
                                name="command" value="go_to_default_page">AntClub
                        </button>
                    </form>
                </a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <c:if test="${not empty user }">
                    <form class="navbar-form navbar-right" role="search" action="controller" method="post">
                        <input type="hidden" name="command" value="find_user_by_login">
                        <div class="form-group">
                            <input type="text" name="search" class="form-control" placeholder=${search_login}>
                        </div>
                        <button type="submit" class="btn btn-success">${submit}</button>
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
                                   <a>
                                    <form action="controller" method="post" role="form"
                                          style="display: inline-block;">
                                        <button class="btn-link" type="submit"
                                                name="command" value="profile_user">${profile}
                                        </button>
                                    </form>
                                </a>


                                <c:if test="${role eq 'admin'}">
                                      <a>
                                        <form action="controller" method="post" role="form"
                                              style="display: inline-block;">
                                            <button class="btn-link" type="submit"
                                                    name="command" value="go_admin_control">${adminpanel}
                                            </button>
                                        </form>
                                    </a>
                                </c:if>
                                 <br>
                                <a>
                                    <form action="controller" method="post" role="form"
                                          style="display: inline-block;">
                                        <button class="btn-link" type="submit"
                                                name="command" value="logOut">${logout}
                                        </button>
                                    </form>
                                </a>
                            </div>
                        </c:if>
                    </li>
                    <c:if test="${empty role}">
                        <li class="nav-item">
                            <a class="nav-link">
                               <form action="controller" method="post" role="form"
                                      style="display: inline-block;">
                                    <button class="btn-link" type="submit"
                                            name="command" value="go_to_registration_page">${registration_button}
                                    </button>
                                </form>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">
                                <form action="controller" method="post" role="form"
                                      style="display: inline-block;">
                                    <button class="btn-link" type="submit"
                                            name="command" value="i_am_guest">${guest}
                                    </button>
                                </form>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${not empty user and user.deleted eq 'not deleted'}">
                        <li class="nav-item dropdown">
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
                                <a class="dropdown-item">
                                   <form action="controller" method="post" role="form"
                                          style="display: inline-block;">
                                        <button class="btn-link" type="submit"
                                                name="command" value="logOut">${logout}
                                        </button>
                                    </form>
                                </a>
                            </div>
                        </li>
                    </c:if>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                           data-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false">${language}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item">
                                <form action="controller" method="post" role="form"
                                      style="display: inline-block;">
                                    <input type="hidden" name="path" value="${pageContext.request.servletPath}">
                                    <input type="hidden" name="locale" value="2">
                                    <button class="btn-link" type="submit"
                                            name="command" value="change_locale">Русский
                                    </button>
                                </form>
                            </a>
                            <a class="dropdown-item">
                                  <form action="controller" method="post" role="form"
                                      style="display: inline-block;">
                                    <input type="hidden" name="path" value="${pageContext.request.servletPath}">
                                    <input type="hidden" name="locale" value="1">
                                    <button class="btn-link" type="submit"
                                            name="command" value="change_locale">English
                                    </button>
                                </form>
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>

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
