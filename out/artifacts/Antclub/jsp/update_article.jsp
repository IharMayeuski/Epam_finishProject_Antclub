<%--
  Created by IntelliJ IDEA.
  User: Maevskiy
  Date: 7/7/2019
  Time: 4:53 PM
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
<fmt:message bundle="${loc}" key="locale.header.submit" var="submit"/>
<fmt:message bundle="${loc}" key="locale.new_comment" var="comment"/>
<fmt:message bundle="${loc}" key="locale.update.article" var="updatearticle"/>
<fmt:message bundle="${loc}" key="locale.update.comment" var="updatecomment"/>


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
    <script> $('.text-message').delay(3000).animate({'opacity': '0'}, 500);</script>
    ${requestScope.All_is_ok}
    ${requestScope.error}
</div>
<div class="container">
    <section>
        <div id="container_demo">
            <div id="wrapper">
                <div id="login" class="animate form">
                    <form action="controller" method="post" role="form" class="form-horizontal" id="login4"
                          autocomplete="on">
                        <input type="hidden" name="command" value="update_article_command">
                        <div class="form-group">
                            <label>${updatearticle}</label>
                            <textarea class="form-control rounded" name="text" required="required"
                                      style="height: 100px;">${text}</textarea>
                            <input type="hidden" name="articleId" value="${articleId}"/>
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

