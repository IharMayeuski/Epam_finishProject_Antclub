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

<p class="text-left text-info font-weight-bold">${requestScope.All_is_ok}</p>
<p class="text-left text-danger font-weight-bold">${requestScope.error}</p>

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
                            <img src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${findUser.id}.jpg" width="250">
                            <h3>${findUser.firstname} ${findUser.familyname}</h3>
                            <small class="label label-warning">${findUser.banned}</small>
                            <c:if test="${role eq 'admin'}">
                                <p>Роль: ${findUser.role}, Cтатус: ${findUser.deleted}</p>
                                <a class="btn btn-outline-success" href="#" role="button">Блок</a>
                                <a class="btn btn-outline-success" href="#" role="button">Разблок</a>
                                <a class="btn btn-outline-success" href="#" role="button">Восстановить</a>
                                <a class="btn btn-outline-success" href="#" role="button">Удалить</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8 col-md-8 col-xs-12">
                <div class="panel">
                    <div class="panel-body">
                        <ul id="myTab2" class="nav nav-pills">
                            <li class="active"><a href="#detail" data-toggle="tab">О пользователе</a></li>
                            <li class=""><a href="#contact" data-toggle="tab">Отправить сообщение</a></li>
                        </ul>
                        <div id="myTabContent2" class="tab-content">
                            <hr>
                            <div class="tab-pane fade active in" id="detail">
                                <h4>История профиля</h4>
                                <table class="table table-th-block">
                                    <tbody>
                                    <tr>
                                        <td class="active">Зарегистрирован:</td>
                                        <td>${findUser.date_registration}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Последняя активность:</td>
                                        <td>${findUser.date_activity}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Имя:</td>
                                        <td>${findUser.firstname}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Фамилия:</td>
                                        <td>${findUser.familyname}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="contact">
                                <p></p>
                                <form role="form">
                                    <form action="controller" method="post" role="form">
                                        <input type="hidden" name="command" value="send_letter">
                                        <input type="hidden" name="find_user_id" value="${findUser.id}">

                                        <div class="form-group">
                                            <label>Тема сообщения</label>
                                            <input type="text" class="form-control rounded" name="title"
                                                   placeholder="Title">
                                        </div>

                                        <div class="form-group">
                                            <label>Текст Вашего сообщения</label>
                                            <textarea class="form-control rounded" name="text"
                                                      style="height: 100px;"></textarea>
                                            <p class="help-block">Текст сообщения будет отправлен пользователю</p>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success" data-original-title=""
                                                    title="">Отправить
                                            </button>
                                        </div>
                                    </form>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
<footer><c:import url="footer.jsp"/></footer>
</html>