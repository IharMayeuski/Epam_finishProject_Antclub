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
                            <p>Роль: ${user.role}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8 col-md-8 col-xs-12">
                <div class="panel">
                    <div class="panel-body">
                        <ul id="myTab" class="nav nav-pills">
                            <li class="active"><a href="#detail2" data-toggle="tab">О пользователе</a></li>
                            <li class=""><a href="#letterToMe" data-toggle="tab">Вход. сообщения</a></li>
                            <li class=""><a href="#letterFromMe" data-toggle="tab">Исход. сообщения</a></li>
                            <li class=""><a href="#update" data-toggle="tab">Изменить данные</a></li>
                            <li class=""><a href="#delete" data-toggle="tab">Удалить аккаунт</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <hr>
                            <div class="tab-pane fade active in" id="detail2">
                                <h4>История профиля</h4>
                                <table class="table table-th-block">
                                    <tbody>
                                    <tr>
                                        <td class="active">Зарегистрирован:</td>
                                        <td>${user.date_registration}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Последний вход:</td>
                                        <td>${user.date_activity}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Имя:</td>
                                        <td>${user.firstname}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Фамилия:</td>
                                        <td>${user.familyname}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="update">
                                <h4>Изменить профиль</h4>

                                <table class="table table-th-block">
                                    <tbody>
                                    <form action="controller" method="post" role="form">
                                        <input type="hidden" name="command" value="account_update">
                                        <tr>
                                            <td class="active">Имя:</td>
                                            <td><label>
                                                <input type="text" name="firstname" placeholder="${user.firstname}">
                                            </label></td>
                                        </tr>
                                        <tr>
                                            <td class="active">Фамилия:</td>
                                            <td><label>
                                                <input type="text" name="familyname"
                                                       placeholder="${user.familyname}">
                                            </label></td>
                                        </tr>
                                        <tr>
                                            <td class="active">Логин:</td>
                                            <td><label>
                                                <input type="text" name="login" placeholder="${user.login}">
                                            </label></td>
                                        </tr>
                                        <tr>
                                            <td class="active">Эл. ящик</td>
                                            <td><label>
                                                <input type="email" name="email" placeholder="${user.email}">
                                            </label></td>
                                        </tr>

                                        <tr>
                                            <td class="active">Изменить пароль:</td>
                                            <td><label>
                                                <input type="password" name="password1" placeholder="***** ">
                                            </label></td>
                                        </tr>
                                        <tr>
                                            <td class="active">Повторите пароль:</td>
                                            <td><label>
                                                <input type="password" name="password2" placeholder="*****">
                                            </label></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <button type="submit" class="btn btn-success" data-original-title=""
                                                        title="">Submit
                                                </button>
                                            </td>
                                        </tr>
                                    </form>

                                    <form action="UploadImage" method="POST" enctype="multipart/form-data"
                                          id="myFormFormUpdating">
                                        <input type="hidden" name="userId" value="${user.id}"/>

                                        <tr>
                                            <td class="active">Изменить фото:</td>
                                            <td><input type="file" id="tF" name="image"/><br>
                                                <input type="submit" class="btn btn-success" value="Submit"></td>
                                        </tr>
                                    </form>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="letterToMe">
                                <h4>Входящие сообщения</h4>
                                <div class="tab-pane fade active in" id="toMe">
                                    <table class="table table-th-block">
                                        <tbody>
                                        <td class="active">От кого письмо:</td>
                                        <td class="active">Название:</td>
                                        <td class="active">Текст</td>
                                        <td class="active">Дата</td>
                                        <td class="active">Удалить</td>
                                        <c:forEach items="${letter_to}" var="letter" varStatus="theCount">
                                            <tr>
                                                <td>
                                                    <a href="controller?command=find_user_by_login&search=${letter.fromUser}">${letter.fromUser}</a>
                                                </td>
                                                <td>${letter.title}</td>
                                                <td>${letter.text}</td>
                                                <td>${letter.date}</td>
                                                <td><a class="btn btn-outline-success" href="#"
                                                       role="button">Удалить</a></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="letterFromMe">
                                <h4>Исходящие сообщения</h4>
                                <div class="tab-pane fade active in" id="fromMe">
                                    <table class="table table-th-block">
                                        <tbody>
                                        <td>Кому письмо:</td>
                                        <td>Название:</td>
                                        <td>Текст</td>
                                        <td>Дата</td>
                                        <td>Удалить</td>
                                        <c:forEach items="${letter_from}" var="letter" varStatus="theCount">
                                            <tr>
                                                <td>
                                                    <a href="controller?command=find_user_by_login&search=${letter.toUser}">${letter.toUser}</a>
                                                </td>
                                                <td>${letter.title}</td>
                                                <td>${letter.text}</td>
                                                <td>${letter.date}</td>
                                                <td><a class="btn btn-outline-success" href="#"
                                                       role="button">Удалить</a></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="delete">
                                <p>DELETE This ACCOUNT?</p>
                                <form action="controller" method="post" role="form">
                                    <div class="form-group">
                                        <input type="hidden" name="command" value="account_delete">
                                        <label for="Email2">E-mail:</label>
                                        <input type="text" name="email" class="form-control" id="Email2">
                                    </div>

                                    <div class="form-group">
                                        <label for="Password2">Password:</label>
                                        <input type="password" name="password" class="form-control"
                                               id="Password2"><br>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-success" data-original-title=""
                                                title="">Submit
                                        </button>
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
                /*  alert("Вы не выбрали файл");*/
                return false;
            } else {
                return true;
            }

        });

    });/*end  ready*/
</script>

</body>
<footer><c:import url="footer.jsp"/></footer>
</html>