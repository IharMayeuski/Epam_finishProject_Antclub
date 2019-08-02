<%--
  Created by IntelliJ IDEA.
  User: Maevskiy
  Date: 6/30/2019
  Time: 11:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Maevskiy
  Date: 6/23/2019
  Time: 11:47 PM
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
<fmt:message bundle="${loc}" key="locale.lookarticle.addphoto" var="addphoto"/>
<fmt:message bundle="${loc}" key="locale.header.submit" var="submit"/>
<fmt:message bundle="${loc}" key="locale.new_article_picture.name" var="name"/>

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

<%--Форма нового типа главы о муравьях--%>
<div class="container">
    <section>
        <div id="container_demo">
            <div id="wrapper">
                <div id="login" class="animate form">
                    <form action="UploadImage" method="POST" enctype="multipart/form-data"
                          id="myFormFormUpdating">
                        <input type="hidden" name="article" value="new_photo">
                        <h1>${addphoto}</h1>
                        <p>
                            <label class="uname" >${name}</label>
                            <input id="usernamesignup1" name="text" required="required" type="text" placeholder="name"/>
                        </p>
                        <tr>
                            <td class="active">${addphoto}</td>
                            <td><input type="file" id="tF" required="required" name="image"/><br>
                        </tr>
                        <p class="signin button">
                            <input type="submit" value=${submit}>
                        </p>
                    </form>
                </div>
            </div>
        </div>

    </section>
</div>
<script>
    document.onkeydown = function (e) {
        if (e.keyCode === 116) {
            return false;
        }
    };
</script>

</body>

