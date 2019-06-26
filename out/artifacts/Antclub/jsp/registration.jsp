<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.registration" var="registration_button"/>

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

<p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
<p class="text-left text-info font-weight-bold">${requestScope.account_deleted}</p>
<p class="text-left text-danger font-weight-bold">${requestScope.error}</p>

<%--Таблица регистрация--%>
<%--@elvariable id="role" type=""--%>
<c:if test="${empty role}">
    <div class="container">
        <section>
            <div id="container_demo">
                <a class="hiddenanchor" id="toregister"></a>
                <a class="hiddenanchor" id="tologin"></a>
                <div id="wrapper">
                    <div id="login" class="animate form">
                        <form action="controller" method="post" role="form" class="form-horizontal" id="registration1"
                              autocomplete="on">
                            <input type="hidden" name="command" value="registration">

                            <h1> Регистрация </h1>
                            <p>
                                <label for="usernamesignup" class="uname" data-icon="u">Ваш логин</label>
                                <input id="usernamesignup1" name="login" required="required" type="text"
                                       placeholder="myname1"/>
                            </p>
                            <p>
                                <label for="emailsignup" class="youmail" data-icon="e"> Ваш e-mail</label>
                                <input id="emailsignup1" name="email" required="required" type="email"
                                       placeholder="sitehere.ru@my.com"/>
                            </p>
                            <p>
                                <label for="passwordsignup" class="youpasswd" data-icon="p">Ваш пароль </label>
                                <input id="passwordsignup1" name="password1" required="required" type="password"
                                       placeholder="123456"/>
                            </p>
                            <p>
                                <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Подтвердите ваш
                                    пароль </label>
                                <input id="passwordsignup_confirm1" name="password2" required="required"
                                       type="password" placeholder="123456"/>
                            </p>
                            <p class="signin button">
                                <input type="submit" value="Регистрация"/>
                            </p>
                        </form>
                    </div>
                    <div id="register" class="animate form">
                        <form action="controller" method="post" role="form" class="form-horizontal" id="registration"
                              autocomplete="on">
                            <input type="hidden" name="command" value="registration">

                            <h1> Регистрация </h1>
                            <p>
                                <label for="usernamesignup" class="uname" data-icon="u">Ваш логин</label>
                                <input id="usernamesignup" name="login" required="required" type="text"
                                       placeholder="myname1"/>
                            </p>
                            <p>
                                <label for="emailsignup" class="youmail" data-icon="e"> Ваш e-mail</label>
                                <input id="emailsignup" name="email" required="required" type="email"
                                       placeholder="sitehere.ru@my.com"/>
                            </p>
                            <p>
                                <label for="passwordsignup" class="youpasswd" data-icon="p">Ваш пароль </label>
                                <input id="passwordsignup" name="password1" required="required" type="password"
                                       placeholder="123456"/>
                            </p>
                            <p>
                                <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Подтвердите ваш
                                    пароль </label>
                                <input id="passwordsignup_confirm" name="password2" required="required"
                                       type="password" placeholder="123456"/>
                            </p>
                            <p class="signin button">
                                <input type="submit" value="Регистрация"/>
                            </p>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</c:if>

</body>
<footer>
    <c:import url="footer.jsp"/></footer>

