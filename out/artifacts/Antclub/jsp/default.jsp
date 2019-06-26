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
    <%--<link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>--%>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <%--<link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>--%>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/font/2016/pe-icon-stroke/Pe-icon-7-stroke.css">
    <link href="http://bootstraptema.ru/_sf/3/391.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/masonry/3.3.1/masonry.pkgd.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery.min.js"/>">

</head>

<body>
<%--Появляющаяся узкая форма, не подходит--%>
<%--<div id="dialog" title="Basic dialog">
   54654646
</div>
<div><input type='button' value='форма'></div>
&lt;%&ndash;<script src="http://code.jquery.com/jquery-1.10.2.js"></script>&ndash;%&gt;
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script>
    var dialog = $("#dialog").dialog({autoOpen: false});
    $("input[type=button]").click(function ()
    {
        dialog.dialog( "open" );
    });
</script>--%>
<br><br><br>
<style>
    body {
        background: url(http://bootstraptema.ru/images/bg/bg-1.png)
    }
</style>
<c:import url="header.jsp"/>

<%--Спрятать форму--%>
<%--<form id="form1" style="display: none;">
    <input type="text" value="Я тут">
</form>

<li class="nav navbar-nav"><a onclick="disp(document.getElementById('form1'))">Input new
    type<span class="sr-only">(current)</span></a>
</li>
<br>--%>

<p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
<p class="text-left text-info font-weight-bold">${requestScope.account_deleted}</p>
<p class="text-left text-info font-weight-bold">${requestScope.All_is_ok}</p>
<p class="text-left text-danger font-weight-bold">${requestScope.error}</p>

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
                        <a href="controller?command=article&link_id=${types.id}"
                           class="btn btn-danger btn-fill btn-round">Read
                            article</a>
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
                                <%-- <form action="mysuperscript.php" autocomplete="on">--%>
                            <h1>Вход</h1>
                            <p>
                                <label for="username" class="uname" data-icon="u"> Ваш логин</label>
                                <input id="username" name="login" required="required" type="text"
                                       placeholder="sitehere "/>
                            </p>
                            <p>
                                <label for="password" class="youpasswd" data-icon="p"> Ваш пароль </label>
                                <input id="password" name="password" required="required" type="password"
                                       placeholder="например 123456"/>
                            </p>

                            <p class="login button">
                                <input type="submit" value="Войти"/>
                            </p>
                            <p class="change_link">
                                Забыли пароль?
                                <a href="#toregister" class="to_forget_password">Восстановление</a>
                            </p>
                            <p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
                            <p class="text-left text-info font-weight-bold">${requestScope.account_deleted}</p>
                            <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
                        </form>
                    </div>

                    <div id="register" class="animate form">
                        <h1>Забыли пароль</h1>
                        <form action="controller" method="post" role="form" class="form-horizontal" id="registration"
                              autocomplete="on">
                            <input type="hidden" name="command" value="new_password">


                            <p>
                                <label for="username" class="uname" data-icon="u"> Ваш емэйл</label>
                                <input id="username2" name="email" required="required" type="text"
                                       placeholder="sitehere "/>
                            </p>

                            <p class="login button">
                                <input type="submit" value="Войти"/>
                            </p>
                            <p class="change_link">
                                Уже зарегистрированы ?
                                <a href="#tologin" class="to_register"> Войдите на сайт </a>
                            </p>
                            <p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
                            <p class="text-left text-info font-weight-bold">${requestScope.account_deleted}</p>
                            <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</c:if>

<script>
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
</body>
<footer>
    <%--<c:import url="footer.jsp"/></footer>--%>
</footer>
</html>