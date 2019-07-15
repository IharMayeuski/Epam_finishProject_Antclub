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
<fmt:message bundle="${loc}" key="locale.search_user.role" var="role"/>
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
<fmt:message bundle="${loc}" key="locale.default.login" var="login"/>
<fmt:message bundle="${loc}" key="locale.default.email" var="email"/>
<fmt:message bundle="${loc}" key="locale.default.passwordagain" var="passagain"/>
<fmt:message bundle="${loc}" key="locale.user_profile.inputmessage" var="inputmessage"/>
<fmt:message bundle="${loc}" key="locale.user_profile.outputmessage" var="outputmessage"/>
<fmt:message bundle="${loc}" key="locale.user_profile.changedata" var="changedata"/>
<fmt:message bundle="${loc}" key="locale.user_profile.changephoto" var="changephoto"/>
<fmt:message bundle="${loc}" key="locale.user_profile.deleteaccount" var="deleteaccount"/>
<fmt:message bundle="${loc}" key="locale.user_profile.changeprofile" var="changeprofile"/>
<fmt:message bundle="${loc}" key="locale.user_profile.changepassword" var="changepassword"/>
<fmt:message bundle="${loc}" key="locale.default.password" var="pass"/>
<fmt:message bundle="${loc}" key="locale.header.submit" var="submit"/>
<fmt:message bundle="${loc}" key="locale.user_profile.messagefor" var="messagefor"/>
<fmt:message bundle="${loc}" key="locale.user_profile.messagefrom" var="messagefrom"/>
<fmt:message bundle="${loc}" key="locale.user_profile.name" var="name"/>
<fmt:message bundle="${loc}" key="locale.user_profile.text" var="text"/>
<fmt:message bundle="${loc}" key="locale.user_profile.date" var="date"/>
<fmt:message bundle="${loc}" key="locale.user_profile.delete" var="delete"/>
<fmt:message bundle="${loc}" key="locale.user_profile.deletethisaccount" var="deletethisaccount"/>


<html lang="en" class="no-js">
<head>
    <meta charset="UTF-8"/>
    <title>AntClub</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style3.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate-custom.css"/>
    <link href="${pageContext.request.contextPath}/js/jquery.min.js"/>

    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/font/2016/pe-icon-stroke/Pe-icon-7-stroke.css">
    <link href="http://bootstraptema.ru/_sf/3/391.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/masonry/3.3.1/masonry.pkgd.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>
<body>
<br><br><br>

<style>
    body {
        background: url(http://bootstraptema.ru/images/bg/bg-1.png)
    }

    #footer {
        grid-row-start: 2;
        grid-row-end: 3;

    }
</style>

<c:import url="header.jsp"/>

<div class="text-message">
    <script> $('.text-message').delay(3000).animate({'opacity': '0'}, 500);</script>
    ${requestScope.All_is_ok}
    ${requestScope.error}
</div>


<%--ПОЛЬЗОВАТЕЛЬ--%>
<div class="container" id="dialog">
    <div id="main2">
        <div class="row" id="real-estates-detail2">
            <div class="col-lg-4 col-md-4 col-xs-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <header class="panel-title">
                            <div class="text-center">
                                <strong>${user.login}</strong>
                            </div>
                        </header>
                    </div>
                    <div class="panel-body">
                        <div class="text-center" id="author2">
                            <img src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${user.id}.jpg"
                                 width="250"
                                 alt="">
                            <h3>${user.firstname} ${user.familyname}</h3>
                            <small class="label label-warning">${user.banned}</small>
                            <p>${role} ${user.role}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8 col-md-8 col-xs-12">
                <div class="panel">
                    <div class="panel-body">
                        <ul id="myTab" class="nav nav-pills">
                            <li class="active"><a href="#detail2" data-toggle="tab">${aboutclient}</a></li>
                            <li class=""><a href="#letterToMe" data-toggle="tab">${inputmessage}</a></li>
                            <li class=""><a href="#letterFromMe" data-toggle="tab">${outputmessage}</a></li>
                            <li class=""><a href="#update" data-toggle="tab">${changedata}</a></li>
                            <li class=""><a href="#update_pic" data-toggle="tab">${changephoto}</a></li>
                            <li class=""><a href="#delete" data-toggle="tab">${deleteaccount}</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <hr>
                            <div class="tab-pane fade active in" id="detail2">
                                <h4>${profilehistory}:</h4>
                                <table class="table table-th-block">
                                    <tbody>
                                    <tr>
                                        <td class="active">${registrated}:</td>
                                        <td>${user.date_registration}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">${lastactivity}:</td>
                                        <td>${user.date_activity}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">${name}:</td>
                                        <td>${user.firstname}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">${familyname}:</td>
                                        <td>${user.familyname}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="update">
                                <h4>${changeprofile}</h4>
                                <table class="table table-th-block">
                                    <tbody>
                                    <form action="controller" method="post" role="form">
                                        <input type="hidden" name="command" value="account_update">
                                        <tr>
                                            <td class="active">${name}:</td>
                                            <td><label>
                                                <input type="text" name="firstname" placeholder="${user.firstname}">
                                            </label></td>
                                        </tr>
                                        <tr>
                                            <td class="active">${familyname}:</td>
                                            <td><label>
                                                <input type="text" name="familyname"
                                                       placeholder="${user.familyname}">
                                            </label></td>
                                        </tr>
                                        <tr>
                                            <td class="active">${login}:</td>
                                            <td><label>
                                                <input type="text" name="login" placeholder="${user.login}">
                                            </label></td>
                                        </tr>
                                        <tr>
                                            <td class="active">${email}:</td>
                                            <td><label>
                                                <input type="email" name="email" placeholder="${user.email}">
                                            </label></td>
                                        </tr>

                                        <tr>
                                            <td class="active">${changepassword}:</td>
                                            <td><label>
                                                <input type="password" name="password1" placeholder="***** ">
                                            </label></td>
                                        </tr>
                                        <tr>
                                            <td class="active">${passagain}:</td>
                                            <td><label>
                                                <input type="password" name="password2" placeholder="*****">
                                            </label></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <button type="submit" class="btn btn-success" data-original-title=""
                                                        title="">${submit}</button>
                                            </td>
                                        </tr>
                                    </form>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="update_pic">
                                <h4>${changephoto}</h4>
                                <table class="table table-th-block">
                                    <tbody>
                                    <form action="UploadImage" method="POST" enctype="multipart/form-data"
                                          id="myFormFormUpdating">
                                        <input type="hidden" name="userId" value="${user.id}"/>
                                        <tr>
                                            <td class="active">${changephoto}:</td>
                                            <td><input type="file" id="tF" name="image"/><br>
                                                <input type="submit" class="btn btn-success" value=${submit}></td>
                                        </tr>
                                    </form>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="letterToMe">
                                <h4>${inputmessage}</h4>
                                <div class="tab-pane fade active in" id="toMe">
                                    <table class="table table-th-block">
                                        <tbody>
                                        <td class="active">${messagefrom}:</td>
                                        <td class="active">${name}:</td>
                                        <%--<td class="active">${text}</td>--%>
                                        <td class="active">${date}</td>
                                        <td class="active">${delete}</td>
                                        <c:forEach items="${letter_to}" var="letter" varStatus="theCount">
                                            <tr>
                                                <td>
                                                        <%-- <a href="controller?command=find_user_by_login&search=${letter.fromUser}">${letter.fromUser}</a>
     --%>
                                                    <a>
                                                        <form action="controller" method="post" role="form"
                                                              style="display: inline-block;">
                                                            <input type="hidden" name="search"
                                                                   value="${letter.fromUser}">
                                                            <button class="btn-link" type="submit"
                                                                    name="command"
                                                                    value="find_user_by_login">${letter.fromUser}
                                                            </button>
                                                        </form>
                                                    </a>

                                                </td>
                                                <td>
                                                    <c:if test="${letter.title eq ''}">
                                                        <a href="#" onclick="swal('${letter.text}')">;)</a>
                                                    </c:if>
                                                    <c:if test="${not empty letter.title}">
                                                       <a href="#" onclick="swal('${letter.text}')">${letter.title}</a>
                                                    </c:if>
                                                </td>
                                                    <%--<td>${letter.text}</td>--%>
                                                <td>${letter.date}</td>
                                                <td>
                                                    <a>
                                                        <form action="controller" method="post" role="form"
                                                              style="display: inline-block;">
                                                            <input type="hidden" name="letter_id" value="${letter.id}">
                                                            <button class="btn btn-outline-success" type="submit"
                                                                    name="command" value="delete_letter">${delete}
                                                            </button>
                                                        </form>
                                                    </a>

                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="letterFromMe">
                                <h4>${outputmessage}</h4>
                                <div class="tab-pane fade active in" id="fromMe">
                                    <table class="table table-th-block">
                                        <tbody>
                                        <td>${messagefor}:</td>
                                        <td>${name}:</td>

                                        <td>${date}</td>
                                        <td>${delete}</td>
                                        <c:forEach items="${letter_from}" var="letter" varStatus="theCount">
                                            <tr>
                                                <td>
                                                        <%-- <a href="controller?command=find_user_by_login&search=${letter.toUser}">${letter.toUser}</a>--%>

                                                    <a>
                                                        <form action="controller" method="post" role="form"
                                                              style="display: inline-block;">
                                                            <input type="hidden" name="search" value="${letter.toUser}">
                                                            <button class="btn-link" type="submit"
                                                                    name="command"
                                                                    value="find_user_by_login">${letter.toUser}
                                                            </button>
                                                        </form>
                                                    </a>

                                                </td>

                                                <td>
                                                    <c:if test="${letter.title eq ''}">
                                                        <a href="#" onclick="swal('${letter.text}')">;)</a>
                                                    </c:if>
                                                    <c:if test="${not empty letter.title}">
                                                        <a href="#" onclick="swal('${letter.text}')">${letter.title}</a>
                                                    </c:if>
                                                </td>

                                                <td>${letter.date}</td>
                                                <td>
                                                        <%-- <a class="btn btn-outline-success"
                                                            href="controller?command=delete_letter&letter_id=${letter.id}"
                                                            role="button">${delete}</a>--%>

                                                    <a>
                                                        <form action="controller" method="post" role="form"
                                                              style="display: inline-block;">
                                                            <input type="hidden" name="letter_id" value="${letter.id}">
                                                            <button class="btn btn-outline-success" type="submit"
                                                                    name="command" value="delete_letter">${delete}
                                                            </button>
                                                        </form>
                                                    </a>

                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="delete">
                                <p>${deletethisaccount}</p>
                                <form action="controller" method="post" role="form">
                                    <div class="form-group">
                                        <input type="hidden" name="command" value="account_delete">
                                        <label for="Email2">${email}:</label>
                                        <input type="text" name="email" class="form-control" id="Email2">
                                    </div>

                                    <div class="form-group">
                                        <label for="Password2">${pass}:</label>
                                        <input type="password" name="password" class="form-control"
                                               id="Password2"><br>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-success" data-original-title=""
                                                title="">${submit}</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#myFormFormUpdating").submit(function () {
            var ddd = $("#tF").val();
            if (ddd == "") {
                return false;
            } else {
                return true;
            }

        });

    });
</script>
<script>
    document.onkeydown = function (e) {
        if (e.keyCode === 116) {
            return false;
        }
    };
</script>

<script>
    function myletter() {
        swal("e");
    }
</script>

</body>

</html>