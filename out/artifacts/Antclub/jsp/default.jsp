<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.registration.registration" var="registration_button"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.read" var="look_article"/>
<fmt:message bundle="${loc}" key="locale.default.signin" var="sign_in"/>
<fmt:message bundle="${loc}" key="locale.default.login" var="login"/>
<fmt:message bundle="${loc}" key="locale.default.password" var="pass"/>
<fmt:message bundle="${loc}" key="locale.default.forgetpass" var="forget"/>
<fmt:message bundle="${loc}" key="locale.default.restorepass" var="restore"/>
<fmt:message bundle="${loc}" key="locale.header.submit" var="submit"/>
<fmt:message bundle="${loc}" key="locale.default.haveaccount" var="haveaccount"/>
<fmt:message bundle="${loc}" key="locale.default.email" var="email"/>
<fmt:message bundle="${loc}" key="locale.default.cometosite" var="cometosite"/>


<html lang="en" class="no-js">
<head>
    <meta charset="UTF-8"/>
    <title>AntClub</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style3.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate-custom.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/font/2016/pe-icon-stroke/Pe-icon-7-stroke.css">
    <link href="http://bootstraptema.ru/_sf/3/391.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/masonry/3.3.1/masonry.pkgd.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/masonry.js"></script>
    <script rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap.js"></script>

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
    ${requestScope.registration}
    ${requestScope.account_deleted}
    ${requestScope.All_is_ok}
    ${requestScope.error}
</div>
<br>
<%--Все статьи на главном экране--%>
<c:if test="${not empty role}">
    <div class="wrapper">
        <div class="masonry-container">
                <%--@elvariable id="types" type="java.util.List"--%>
            <c:forEach items="${types}" var="types" varStatus="theCount">
            <div class="card-box col-md-4 col-sm-6">
                <div class="card card-with-border" data-background="image"
                     data-src="${pageContext.request.contextPath}/TakePictureFromDB/type-${types.id}.jpg">
                    <div class="header">
                    </div>

                    <div class="content text-center">
                        <p class="description">${types.typeNews}</p>
                    </div>
                     <div class="footer text-center">
                        <form action="controller" method="post" role="form">
                            <input type="hidden" name="link_id" value="${types.id}">
                            <button class="btn btn-danger btn-fill btn-round" type="submit" name="command"
                                    value="article">${look_article}
                            </button>
                        </form>
                    </div>
                    <div class="filter"></div>
                </div> <!-- end card -->
            </div>
        </div>
        </c:forEach>
    </div>
</c:if>

<%--ВХОД--%>
<%--@elvariable id="role" type=""--%>
<c:if test="${empty role}">
    <div class="container">
        <section>
            <div id="container_demo">
                <a class="hiddenanchor" id="toregister"></a>
                <a class="hiddenanchor" id="tologin"></a>

                <div id="wrapper">
                    <div id="login" class="animate form">
                        <form action="controller" method="post" role="form" class="form-horizontal" id="login4"
                              autocomplete="on">
                            <input type="hidden" name="command" value="find_user">
                            <h1>${sign_in}</h1>
                            <p>
                                <label for="username" class="uname">${login}</label>
                                <input id="username" name="login" required="required" type="text"
                                       minlength="3" maxlength="45"/>
                            </p>
                            <p>
                                <label for="password" class="youpasswd">${pass}</label>
                                <input id="password" name="password" required="required" type="password"
                                       minlength="3" maxlength="45"/>
                            </p>
                            <p class="login button">
                                <input type="submit" value=${submit}>
                            </p>
                            <p class="change_link">${forget}
                                <a href="#toregister" class="to_forget_password">${restore}</a>
                            </p>
                            <p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
                            <p class="text-left text-info font-weight-bold">${requestScope.account_deleted}</p>
                            <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
                        </form>
                    </div>

                    <div id="register" class="animate form">
                        <h1>${forget}</h1>
                        <form action="controller" method="post" role="form" class="form-horizontal" id="registration"
                              autocomplete="on">
                            <input type="hidden" name="command" value="new_password">
                            <p>
                                <label for="username" class="uname"> ${email}</label>
                                <input id="username2" name="email" required="required" type="text"
                                       minlength="3" maxlength="45"/>
                            </p>
                            <p class="login button"><input type="submit" value=${submit}></p>
                            <p class="change_link">${haveaccount}
                                <a href="#tologin" class="to_register">${cometosite}</a>
                            </p>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</c:if>

<script type="text/javascript">
    $(document).ready(function () {
        window_width = $(window).width();
        // Make the images from the card fill the hole space
        hipster_cards.fitBackgroundForCards();
    });
    hipster_cards = {
        misc: {
            navbar_menu_visible: 0
        },
        fitBackgroundForCards: function () {
            $('[data-background="image"]').each(function () {
                $this = $(this);

                background_src = $this.data("src");

                if (background_src !== "undefined") {
                    new_css = {
                        "background-image": "url('" + background_src + "')",
                        "background-position": "center center",
                        "background-size": "cover"
                    };
                    $this.css(new_css);
                }
            });

            $('.card .header img').each(function () {
                $card = $(this).parent().parent();
                $header = $(this).parent();
                background_src = $(this).attr("src");
                if (background_src !== "undefined") {
                    new_css = {
                        "background-image": "url('" + background_src + "')",
                        "background-position": "center center",
                        "background-size": "cover"
                    };
                    $header.css(new_css);
                }
            });
        }
    }
</script>
<script type="text/javascript">
    document.onkeydown = function (e) {
        if (e.keyCode === 116) {
            return false;
        }
    };
</script>

</body>
<footer>
    <p><c:import url="footer.jsp"/></p>
</footer>
</html>