<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.registration.registration" var="registration_button"/>
<fmt:message bundle="${loc}" key="locale.search_user.block" var="block"/>
<fmt:message bundle="${loc}" key="locale.search_user.unblock" var="unblock"/>
<fmt:message bundle="${loc}" key="locale.search_user.delete" var="delete"/>
<fmt:message bundle="${loc}" key="locale.search_user.restore" var="restore"/>
<fmt:message bundle="${loc}" key="locale.search_user.markuser" var="markuser"/>
<fmt:message bundle="${loc}" key="locale.search_user.markadmin" var="markadmin"/>
<fmt:message bundle="${loc}" key="locale.search_user.role" var="roleuser"/>
<fmt:message bundle="${loc}" key="locale.search_user.name" var="name"/>
<fmt:message bundle="${loc}" key="locale.search_user.familyname" var="familyname"/>
<fmt:message bundle="${loc}" key="locale.search_user.registrated" var="registrated"/>
<fmt:message bundle="${loc}" key="locale.search_user.lastactivity" var="lastactivity"/>
<fmt:message bundle="${loc}" key="locale.search_user.aboutclient" var="aboutclient"/>
<fmt:message bundle="${loc}" key="locale.search_user.sendmessage" var="sendmessage"/>
<fmt:message bundle="${loc}" key="locale.search_user.titlemessage" var="titlemessage"/>
<fmt:message bundle="${loc}" key="locale.search_user.textmessage" var="textmessage"/>
<fmt:message bundle="${loc}" key="locale.search_user.profilehistory" var="profilehistory"/>
<fmt:message bundle="${loc}" key="locale.search_user.textwillsendclient" var="textwillsend"/>
<fmt:message bundle="${loc}" key="locale.search_user.status" var="status"/>

<fmt:message bundle="${loc}" key="locale.header.submit" var="submit"/>

<html lang="en" class="no-js">
<head>
    <meta charset="UTF-8"/>
    <title>AntClub</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style3.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate-custom.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>

</head>
<body>
<br><br><br>

<style>
    body {
        background: url(http://bootstraptema.ru/images/bg/bg-1.png)
    }

</style>

<c:import url="header.jsp"/>


<div class="text-message">
    <script> $('.text-message').delay(3000).animate({'opacity': '0'}, 500);</script>
    ${requestScope.All_is_ok}
    ${requestScope.error}
</div>
<%--Найденный юзер--%>
<div class="container">
    <div id="main">
        <div class="row" id="real-estates-detail">
            <div class="col-lg-4 col-md-4 col-xs-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <header class="panel-title">
                            <div class="text-center">
                                <strong>${findUser.login}</strong>
                            </div>
                        </header>
                    </div>
                    <div class="panel-body">
                        <div class="text-center" id="author">
                            <img src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${findUser.id}.jpg"
                                 width="250">
                            <h3>${findUser.firstname} ${findUser.familyname}</h3>
                            <small class="label label-warning">${findUser.banned}</small>
                            <c:if test="${role eq 'admin'}">
                                <p>${roleuser}: ${findUser.role}, ${status}: ${findUser.deleted}</p>
                              <%--  <a class="btn btn-outline-success" href="controller?command=blocked_user" role="button">${block}</a>--%>
                               <a><form action="controller" method="post" role="form"
                                      style="display: inline-block;">
                                    <button class="btn btn-outline-success" type="submit"
                                            name="command" value="blocked_user">${block}
                                    </button>
                               </form></a>

                                <%--<a class="btn btn-outline-success" href="controller?command=unblocked_user"
                                   role="button">${unblock}</a>--%>

                                <a><form action="controller" method="post" role="form"
                                         style="display: inline-block;">
                                    <button class="btn btn-outline-success" type="submit"
                                            name="command" value="unblocked_user">${unblock}
                                    </button>
                                </form></a>

                                <%--<a class="btn btn-outline-success" href="controller?command=undelete_user"
                                   role="button">${restore}</a>--%>

                                <a><form action="controller" method="post" role="form"
                                         style="display: inline-block;">
                                    <button class="btn btn-outline-success" type="submit"
                                            name="command" value="undelete_user">${restore}
                                    </button>
                                </form></a>


                               <%-- <a class="btn btn-outline-success" href="controller?command=delete_user" role="button">${delete}</a>--%>

                                <a><form action="controller" method="post" role="form"
                                         style="display: inline-block;">
                                    <button class="btn btn-outline-success" type="submit"
                                            name="command" value="delete_user">${delete}
                                    </button>
                                </form></a>


                                <%--<a class="btn btn-outline-success" href="controller?command=mark_admin" role="button">${markadmin}</a>--%>

                                <a><form action="controller" method="post" role="form"
                                         style="display: inline-block;">
                                    <button class="btn btn-outline-success" type="submit"
                                            name="command" value="mark_admin">${markadmin}
                                    </button>
                                </form></a>

                               <%-- <a class="btn btn-outline-success" href="controller?command=mark_user" role="button">${markuser}</a>--%>

                                <a><form action="controller" method="post" role="form"
                                         style="display: inline-block;">
                                    <button class="btn btn-outline-success" type="submit"
                                            name="command" value="mark_user">${markuser}
                                    </button>
                                </form></a>

                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8 col-md-8 col-xs-12">
                <div class="panel">
                    <div class="panel-body">
                        <ul id="myTab2" class="nav nav-pills">
                            <li class="active"><a href="#detail" data-toggle="tab">${aboutclient}</a></li>
                            <c:if test="${findUser.deleted eq 'not deleted'}">
                                <li class=""><a href="#contact" data-toggle="tab">${sendmessage}</a></li>
                            </c:if>
                        </ul>
                        <div id="myTabContent2" class="tab-content">
                            <hr>
                            <div class="tab-pane fade active in" id="detail">
                                <h4>${profilehistory}</h4>
                                <table class="table table-th-block">
                                    <tbody>
                                    <tr>
                                        <td class="active">${registrated}</td>
                                        <td>${findUser.date_registration}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">${lastactivity}</td>
                                        <td>${findUser.date_activity}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">${name}</td>
                                        <td>${findUser.firstname}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">${familyname}</td>
                                        <td>${findUser.familyname}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <c:if test="${findUser.deleted eq 'not deleted'}">
                                <div class="tab-pane fade" id="contact">
                                    <p></p>
                                    <form action="controller" method="post" role="form">
                                        <input type="hidden" name="command" value="send_letter">
                                        <input type="hidden" name="find_user_id" value="${findUser.id}">

                                        <div class="form-group">
                                            <label>${titlemessage}</label>
                                            <input type="text" class="form-control rounded" name="title">
                                        </div>
                                        <div class="form-group">
                                            <label>${textmessage}</label>
                                            <textarea class="form-control rounded" name="text"
                                                      style="height: 100px;"></textarea>
                                            <p class="help-block">${textwillsend}</p>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success" data-original-title=""
                                                    title="">${submit}</button>
                                        </div>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    document.onkeydown = function (e) {
        if (e.keyCode === 116) {
            return false;
        }
    };
</script>
</body>

</html>