<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.registration.registration" var="registration"/>
<fmt:message bundle="${loc}" key="locale.default.login" var="login"/>
<fmt:message bundle="${loc}" key="locale.default.password" var="pass"/>
<fmt:message bundle="${loc}" key="locale.default.passwordagain" var="pass2"/>
<fmt:message bundle="${loc}" key="locale.header.submit" var="submit"/>
<fmt:message bundle="${loc}" key="locale.default.email" var="email"/>

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
    ${requestScope.error}
    ${requestScope.account_deleted}
    ${requestScope.registration}
</div>

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
                            <h1>${registration}</h1>
                            <p>
                                <label for="usernamesignup" class="uname" >${login}</label>
                                <input id="usernamesignup1" name="login" required="required" type="text"
                                       placeholder="myname1"/>
                            </p>
                            <p>
                                <label for="emailsignup" class="youmail" >${email}</label>
                                <input id="emailsignup1" name="email" required="required" type="email"
                                       placeholder="sitehere.ru@my.com"/>
                            </p>
                            <p>
                                <label for="passwordsignup" class="youpasswd" >${pass}</label>
                                <input id="passwordsignup1" name="password1" required="required" type="password"
                                       placeholder="******"/>
                            </p>
                            <p>
                                <label for="passwordsignup_confirm" class="youpasswd" >${pass2}</label>
                                <input id="passwordsignup_confirm1" name="password2" required="required"
                                       type="password" placeholder="******"/>
                            </p>
                            <p class="signin button">
                                <input type="submit" value=${registration}>
                            </p>
                        </form>
                    </div>
                    <div id="register" class="animate form">
                        <form action="controller" method="post" role="form" class="form-horizontal" id="registration"
                              autocomplete="on">
                            <input type="hidden" name="command" value="registration">

                            <h1>${registration}</h1>
                            <p>
                                <label for="usernamesignup" class="uname" >${login}</label>
                                <input id="usernamesignup" name="login" required="required" type="text"
                                       placeholder="myname1"/>
                            </p>
                            <p>
                                <label for="emailsignup" class="youmail" >${email}</label>
                                <input id="emailsignup" name="email" required="required" type="email"
                                       placeholder="sitehere.ru@my.com"/>
                            </p>
                            <p>
                                <label for="passwordsignup" class="youpasswd" >${pass}</label>
                                <input id="passwordsignup" name="password1" required="required" type="password"
                                       placeholder="******"/>
                            </p>
                            <p>
                                <label for="passwordsignup_confirm" class="youpasswd" >${pass2}</label>
                                <input id="passwordsignup_confirm" name="password2" required="required"
                                       type="password" placeholder="******"/>
                            </p>
                            <p class="signin button">
                                <input type="submit" value=${registration}>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
</c:if>

<script>
    document.onkeydown = function (e) {
        if (e.keyCode === 116) {
            return false;
        }
    };
</script>
</body>


