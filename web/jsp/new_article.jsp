<%--
  Created by IntelliJ IDEA.
  User: Maevskiy
  Date: 6/27/2019
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
<fmt:message bundle="${loc}" key="locale.new_article.unicname" var="unicname"/>
<fmt:message bundle="${loc}" key="locale.new_article.articletext" var="articletext"/>
<fmt:message bundle="${loc}" key="locale.new_article.filename" var="filename"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.addphoto" var="addphoto"/>
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
<br>

<style>
    body {
        background: url(http://bootstraptema.ru/images/bg/bg-1.png)
    }
</style>

<c:import url="header.jsp"/>

<div class="text-message">
    <script> $('.text-message').delay(2000).animate({'opacity': '0'}, 500);</script>
    ${requestScope.All_is_ok}
    ${requestScope.error}
</div>

<div class="container">
    <section>
        <div id="container_demo">
            <div id="wrapper">
                <div id="login" class="animate form">
                    <form action="UploadImage" method="POST" enctype="multipart/form-data"
                          id="myFormFormUpdating">
                        <input type="hidden" name="article" value="new">
                        <div class="form-group">
                            <label>${unicname}</label>
                            <input type="text" class="form-control rounded" name="title" required="required"
                                   placeholder="">
                        </div>
                        <div class="form-group">
                            <label>${articletext}</label>
                            <textarea class="form-control rounded" name="text" required="required"
                                      style="height: 100px;"></textarea>
                        </div>
                        <tr>
                            <td class="active">${addphoto}:</td>
                            <td><input type="file" id="tF" required="required" name="image"/><br>
                        </tr>
                        <div class="form-group">
                            <label>${filename}</label>
                            <input type="text" class="form-control rounded" required="required" name="fileName"
                                   placeholder="">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success" data-original-title=""
                                    title="">${submit}</button>
                        </div>
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

