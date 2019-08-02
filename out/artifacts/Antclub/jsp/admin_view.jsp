<%--
  Created by IntelliJ IDEA.
  User: Maevskiy
  Date: 6/25/2019
  Time: 11:46 AM
  To change this template use File | Settings | File Templates.
--%>
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
<fmt:message bundle="${loc}" key="locale.adminview.deleted" var="deleted"/>
<fmt:message bundle="${loc}" key="locale.adminview.blocked" var="blocked"/>
<fmt:message bundle="${loc}" key="locale.adminview.allarticles" var="allarticles"/>
<fmt:message bundle="${loc}" key="locale.search_user.role" var="roleuser"/>
<fmt:message bundle="${loc}" key="locale.adminview.allarticles" var="allarticles"/>
<fmt:message bundle="${loc}" key="locale.adminview.addnewarticle" var="addnewarticle"/>
<fmt:message bundle="${loc}" key="locale.adminview.photo" var="photo"/>
<fmt:message bundle="${loc}" key="locale.adminview.type" var="typetext"/>
<fmt:message bundle="${loc}" key="locale.adminview.alltypes" var="alltypes"/>
<fmt:message bundle="${loc}" key="locale.adminview.allusers" var="allusers"/>
<fmt:message bundle="${loc}" key="locale.adminview.addnewtype" var="addnewtype"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.read" var="readarticle"/>

<fmt:message bundle="${loc}" key="locale.search_user.block" var="block"/>
<fmt:message bundle="${loc}" key="locale.search_user.unblock" var="unblock"/>

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

<%--ADMINKA--%>
<div class="container" id="dialog">
    <div id="main2">
        <div class="row" id="real-estates-detail2">
            <div class="col-lg-12 col-md-12 col-xs-12">
                <div class="panel">
                    <div class="panel-body">
                        <ul id="myTab" class="nav nav-pills">
                            <li class="active"><a href="#all_user" data-toggle="tab">${allusers}</a></li>
                            <li class=""><a href="#deleted" data-toggle="tab">${deleted}</a></li>
                            <li class=""><a href="#banned" data-toggle="tab">${blocked}</a></li>
                            <li class=""><a href="#all_types" data-toggle="tab">${alltypes}</a></li>
                            <li class="">
                               <%-- <a method="POST" href="controller?command=go_to_new_typenews">${addnewtype}<span class="sr-only">(current)</span></a>
--%>
                              <a>  <form action="controller" method="post" role="form"
                                      style="display: inline-block;">
                                    <button class="btn-link" type="submit"
                                            name="command" value="go_to_new_typenews">${addnewtype}
                                    </button>
                              </form></a>


                            </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <hr>
                            <div class="tab-pane fade active in" id="all_user">
                                <table class="table table-th-block">
                                    <tbody>
                                    <td>${photo}</td>
                                    <td class="active">${roleuser}:</td>
                                    <td class="active">${login}:</td>
                                    <td class="active">${email}:</td>
                                    <td class="active">${lastactivity}</td>
                                    <td class="active">${blocked}</td>
                                    <td class="active">${deleted}</td>
                                    <td class="active">${block}</td>
                                    <td class="active">${unblock}</td>
                                    <td class="active">${delete}</td>
                                    <td class="active">${restore}</td>
                                    <c:forEach items="${ALL_users}" var="user" varStatus="theCount">
                                        <tr>
                                            <td><img class="mr-3"
                                                     src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${user.id}.jpg"
                                                     alt="" width="50ps" style="height:50px;"></td>
                                            <td>${user.role}</td>
                                            <td>
                                                <%--<a href="controller?command=find_user_by_login&search=${user.login}">${user.login}</a>   --%>
                                                <a><form action="controller" method="post" role="form"
                                                      style="display: inline-block;">
                                                    <input type="hidden" name="search" value="${user.login}">
                                                    <button class="btn-link" type="submit"
                                                            name="command" value="find_user_by_login">${user.login}
                                                    </button>
                                                </form></a>

                                            </td>
                                            <td>${user.email}</td>
                                            <td>${user.date_activity}</td>
                                            <td>${user.banned}</td>
                                            <td>${user.deleted}</td>
                                            <td>
                                               <%-- <a class="btn btn-outline-success" href="controller?command=blocked_user&userId=${user.id}" role="button">${block}</a>--%>
                                                <a><form action="controller" method="post" role="form"
                                                         style="display: inline-block;">
                                                    <input type="hidden" name="userId" value="${user.id}">
                                                    <button class="btn btn-outline-success" type="submit"
                                                            name="command" value="blocked_user">${block}
                                                    </button>
                                                </form></a>
                                            </td>
                                            <td>
                                               <%-- <a class="btn btn-outline-success" href="controller?command=unblocked_user&userId=${user.id}" role="button">${unblock}</a>--%>

                                                <a><form action="controller" method="post" role="form"
                                                         style="display: inline-block;">
                                                    <input type="hidden" name="userId" value="${user.id}">
                                                    <button class="btn btn-outline-success" type="submit"
                                                            name="command" value="unblocked_user">${unblock}
                                                    </button>
                                                </form></a>

                                            </td>
                                            <td>
                                               <%-- <a class="btn btn-outline-success"  href="controller?command=delete_user&userId=${user.id}"
                                                   role="button">${delete}</a>
--%>
                                                <a><form action="controller" method="post" role="form"
                                                         style="display: inline-block;">
                                                    <input type="hidden" name="userId" value="${user.id}">
                                                    <button class="btn btn-outline-success" type="submit"
                                                            name="command" value="delete_user">${delete}
                                                    </button>
                                                </form></a>

                                            </td>
                                            <td>
                                               <%-- <a class="btn btn-outline-success" href="controller?command=undelete_user&userId=${user.id}"
                                                   role="button">${restore}</a>--%>

                                                <a><form action="controller" method="post" role="form"
                                                         style="display: inline-block;">
                                                    <input type="hidden" name="userId" value="${user.id}">
                                                    <button class="btn btn-outline-success" type="submit"
                                                            name="command" value="undelete_user">${restore}
                                                    </button>
                                                </form></a>


                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="deleted">
                                <table class="table table-th-block">
                                    <tbody>
                                    <td> ${photo}</td>
                                    <td class="active">${roleuser}:</td>
                                    <td class="active">${login}:</td>
                                    <td class="active">${email}:</td>
                                    <td class="active">${lastactivity}</td>
                                    <td class="active">${restore}</td>
                                    <c:forEach items="${Deleted_Users}" var="user" varStatus="theCount">
                                        <tr>
                                            <td><img class="mr-3"
                                                     src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${user.id}.jpg"
                                                     alt="" width="50ps" style="height:50px;"></td>
                                            <td>${user.role}</td>
                                            <td>
                                               <%-- <a href="controller?command=find_user_by_login&search=${user.login}">${user.login}</a>--%>

                                                <a><form action="controller" method="post" role="form"
                                                         style="display: inline-block;">
                                                    <input type="hidden" name="search" value="${user.login}">
                                                    <button class="btn-link" type="submit"
                                                            name="command" value="find_user_by_login">${user.login}
                                                    </button>
                                                </form></a>

                                            </td>
                                            <td>${user.email}</td>
                                            <td>${user.date_activity}</td>
                                            <td>
                                              <%--  <a class="btn btn-outline-success" href="controller?command=undelete_user&userId=${user.id}"
                                                   role="button">${restore}</a>--%>

                                                <a><form action="controller" method="post" role="form"
                                                         style="display: inline-block;">
                                                    <input type="hidden" name="userId" value="${user.id}">
                                                    <button class="btn btn-outline-success" type="submit"
                                                            name="command" value="undelete_user">${restore}
                                                    </button>
                                                </form></a>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="banned">
                                <table class="table table-th-block">
                                    <tbody>
                                    <td> ${photo}</td>
                                    <td class="active">${roleuser}:</td>
                                    <td class="active">${login}:</td>
                                    <td class="active">${email}:</td>
                                    <td class="active">${lastactivity}</td>
                                    <td class="active">${restore}</td>
                                    <c:forEach items="${Banned_Users}" var="user" varStatus="theCount">
                                        <tr>
                                            <td><img class="mr-3"
                                                     src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${user.id}.jpg"
                                                     alt="" width="50ps" style="height:50px;"></td>
                                            <td>${user.role}</td>
                                            <td>
                                               <%-- <a href="controller?command=find_user_by_login&search=${user.login}">${user.login}</a>--%>

                                                <a><form action="controller" method="post" role="form"
                                                         style="display: inline-block;">
                                                    <input type="hidden" name="search" value="${user.login}">
                                                    <button class="btn-link" type="submit"
                                                            name="command" value="find_user_by_login">${user.login}
                                                    </button>
                                                </form></a>

                                            </td>
                                            <td>${user.email}</td>
                                            <td>${user.date_activity}</td>
                                            <td>
                                               <%-- <a class="btn btn-outline-success" href="controller?command=unblocked_user&userId=${user.id}"
                                                   role="button">${restore}</a>--%>

                                                <a><form action="controller" method="post" role="form"
                                                         style="display: inline-block;">
                                                    <input type="hidden" name="userId" value="${user.id}">
                                                    <button class="btn btn-outline-success" type="submit"
                                                            name="command" value="unblocked_user">${restore}
                                                    </button>
                                                </form></a>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="all_types">
                                <table class="table table-th-block">
                                    <tbody>
                                    <td class="active">${photo}</td>
                                    <td class="active">${typetext}:</td>
                                    <td class="active">${deleted}:</td>
                                    <td class="active">${delete}</td>
                                    <td class="active">${restore}</td>
                                    <td class="active"></td>
                                    <c:forEach items="${ALL_types}" var="type" varStatus="theCount">
                                        <tr>
                                            <td><img class="mr-3"
                                                     src="${pageContext.request.contextPath}/TakePictureFromDB/type-${type.id}.jpg"
                                                     alt="" width="50ps" style="height:50px;"></td>

                                            <td>${type.typeNews}</td>
                                            <td>${type.deleted}</td>
                                            <td>
                                               <%-- <a class="btn btn-outline-success" href="controller?command=delete_type&link_id=${type.id}"
                                                   role="button">${delete}</a>--%>

                                                <a><form action="controller" method="post" role="form"
                                                         style="display: inline-block;">
                                                    <input type="hidden" name="link_id" value="${type.id}">
                                                    <button class="btn btn-outline-success" type="submit"
                                                            name="command" value="delete_type">${delete}
                                                    </button>
                                                </form></a>

                                            </td>
                                            <td>
                                                <%--<a class="btn btn-outline-success" href="controller?command=undelete_type&link_id=${type.id}"
                                                   role="button">${restore}</a>--%>

                                                <a><form action="controller" method="post" role="form"
                                                         style="display: inline-block;">
                                                    <input type="hidden" name="link_id" value="${type.id}">
                                                    <button class="btn btn-outline-success" type="submit"
                                                            name="command" value="undelete_type">${restore}
                                                    </button>
                                                </form></a>

                                            </td>
                                            <td>
                                               <%-- <a href="controller?command=article&link_id=${type.id}">${readarticle}</a>--%>

                                                <a><form action="controller" method="post" role="form"
                                                         style="display: inline-block;">
                                                    <input type="hidden" name="link_id" value="${type.id}">
                                                    <button class="btn btn-outline-success" type="submit"
                                                            name="command" value="article">${readarticle}
                                                    </button>
                                                </form></a>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
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
                /*  alert("Вы не выбрали файл");*/
                return false;
            } else {
                return true;
            }

        });

    });/*end  ready*/
</script>
<script>
    document.onkeydown = function (e) {
        if (e.keyCode === 116) {
            return false;
        }
    };
</script>

</body>

</html>