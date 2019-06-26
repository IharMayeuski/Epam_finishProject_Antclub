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
<fmt:message bundle="${loc}" key="locale.default.registration" var="registration_button"/>

<html lang="en" class="no-js">
<head>
    <meta charset="UTF-8"/>
    <title>AntClub</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style3.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate-custom.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery.min.js"/>
    ">

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

<p class="text-left text-info font-weight-bold">${requestScope.All_is_ok}</p>
<p class="text-left text-danger font-weight-bold">${requestScope.error}</p>


<%--ADMINKA--%>
<div class="container" id="dialog">
    <div id="main2">
        <div class="row" id="real-estates-detail2">
            <div class="col-lg-12 col-md-12 col-xs-12">
                <div class="panel">
                    <div class="panel-body">
                        <ul id="myTab" class="nav nav-pills">
                            <li class="active"><a href="#all_user" data-toggle="tab">Все пользователи</a></li>
                            <li class=""><a href="#deleted" data-toggle="tab">Удаленные</a></li>
                            <li class=""><a href="#banned" data-toggle="tab">Заблокированные</a></li>
                            <li class=""><a href="#all_types" data-toggle="tab">Все разделы</a></li>
                            <li class=""><a method="POST" href="controller?command=go_to_new_typenews">Добавить новый раздел <span class="sr-only">(current)</span></a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <hr>
                            <div class="tab-pane fade active in" id="all_user">
                                <table class="table table-th-block">
                                    <tbody>
                                    <td> Photo</td>
                                    <td class="active">Role:</td>
                                    <td class="active">Логин:</td>
                                    <td class="active">Email:</td>
                                    <td class="active">Activity</td>
                                    <td class="active">Banned</td>
                                    <td class="active">Deleted</td>
                                    <td class="active">Block</td>
                                    <td class="active">Unblock</td>
                                    <td class="active">Delete</td>
                                    <td class="active">Include</td>
                                    <c:forEach items="${ALL_users}" var="user" varStatus="theCount">
                                        <tr>
                                            <td><img class="mr-3"
                                                     src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${user.id}.jpg"
                                                     alt="" width="50ps" style="height:50px;"></td>
                                            <td>${user.role}</td>
                                            <td><a href="controller?command=find_user_by_login&search=${user.login}">${user.login}</a></td>
                                            <td>${user.email}</td>
                                            <td>${user.date_activity}</td>
                                            <td>${user.banned}</td>
                                            <td>${user.deleted}</td>
                                            <td><a class="btn btn-outline-success" href="#" role="button">Блок</a></td>
                                            <td><a class="btn btn-outline-success" href="#" role="button">Разблок</a>
                                            </td>
                                            <td><a class="btn btn-outline-success" href="#"
                                                   role="button">Удалить</a></td>
                                            <td><a class="btn btn-outline-success" href="#"
                                                   role="button">Восстановить</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="deleted">
                                <table class="table table-th-block">
                                    <tbody>
                                    <td> Photo</td>
                                    <td class="active">Role:</td>
                                    <td class="active">Логин:</td>
                                    <td class="active">Email:</td>
                                    <td class="active">Activity</td>
                                    <td class="active">Include</td>
                                    <c:forEach items="${Deleted_Users}" var="user" varStatus="theCount">
                                        <tr>
                                            <td><img class="mr-3"
                                                     src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${user.id}.jpg"
                                                     alt="" width="50ps" style="height:50px;"></td>
                                            <td>${user.role}</td>
                                            <td><a href="controller?command=find_user_by_login&search=${user.login}">${user.login}</a></td>
                                            <td>${user.email}</td>
                                            <td>${user.date_activity}</td>
                                            <td><a class="btn btn-outline-success" href="#"
                                                   role="button">Восстановить</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="banned">
                                <table class="table table-th-block">
                                    <tbody>
                                    <td> Photo</td>
                                    <td class="active">Role:</td>
                                    <td class="active">Логин:</td>
                                    <td class="active">Email:</td>
                                    <td class="active">Activity</td>
                                    <td class="active">Include</td>
                                    <c:forEach items="${Banned_Users}" var="user" varStatus="theCount">
                                        <tr>
                                            <td><img class="mr-3"
                                                     src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${user.id}.jpg"
                                                     alt="" width="50ps" style="height:50px;"></td>
                                            <td>${user.role}</td>
                                            <td><a href="controller?command=find_user_by_login&search=${user.login}">${user.login}</a></td>
                                            <td>${user.email}</td>
                                            <td>${user.date_activity}</td>
                                            <td><a class="btn btn-outline-success" href="#"
                                                   role="button">Восстановить</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="all_types">
                                <table class="table table-th-block">
                                    <tbody>
                                    <td> Photo</td>
                                    <td class="active">Раздел:</td>
                                    <td class="active">Deleted:</td>
                                    <td class="active">Delete</td>
                                    <td class="active">Include</td>
                                    <td class="active"></td>
                                    <c:forEach items="${ALL_types}" var="type" varStatus="theCount">
                                        <tr>
                                            <td><img class="mr-3"
                                                     src="${pageContext.request.contextPath}/TakePictureFromDB/type-${type.id}.jpg"
                                                     alt="" width="50ps" style="height:50px;"></td>

                                            <td>${type.typeNews}</td>
                                            <td>${type.deleted}</td>
                                            <td><a class="btn btn-outline-success" href="#"
                                                   role="button">Удалить</a></td>
                                            <td><a class="btn btn-outline-success" href="#"
                                                   role="button">Восстановить</a>
                                            </td>
                                            <td><a href="controller?command=article&link_id=${type.id}">Read
                                                article</a></td>
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

</body>
<%--<footer><c:import url="footer.jsp"/></footer>--%>
</html>