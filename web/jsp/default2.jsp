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

<c:import url="header2.jsp"/>

<form id="form1" style="display: none;">
    <input type="text" value="Я тут">
</form>


<li class="nav navbar-nav"><a onclick="disp(document.getElementById('form1'))">Input new
    type<span class="sr-only">(current)</span></a>
</li><br>


<%--Все статьи на главном экране--%>
<c:if test="${not empty 'role'}">
<div class="wrapper">
    <div class="masonry-container">
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
                    <a href="controller?command=article&link_id=${types.id}" class="btn btn-danger btn-fill btn-round">Read
                        article</a>
                </div>
                <div class="filter"></div>
            </div> <!-- end card -->
        </div>
    </div>
    </c:forEach>
</div>
</c:if>


${requestScope}<br><br>
${sessionScope}

<p class="text-left text-info font-weight-bold">${sessionScope.registration}</p>
<p class="text-left text-info font-weight-bold">${sessionScope.account_deleted}</p>
<p class="text-left text-danger font-weight-bold">${sessionScope.error}</p>


<br>


<%--ВХОД--%>
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

<%--Найденный юзер--%>
<c:if test="${not empty findUser}">
    <div class="container">
        <div id="main2">
            <div class="row" id="real-estates-detail2">
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
                            <div class="text-center" id="author2">
                                <img src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${findUser.id}.jpg">
                                <h3>${findUser.firstname} ${findUser.familyname}</h3>
                                <small class="label label-warning">${findUser.banned}</small>
                                <p>МОЙ СТАТУС</p>
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
                                <div class="tab-pane fade active in" id="detail2">
                                    <h4>История профиля</h4>
                                    <table class="table table-th-block">
                                        <tbody>
                                        <tr>
                                            <td class="active">Зарегистрирован:</td>
                                            <td>${findUser.date_registration}</td>
                                        </tr>
                                        <tr>
                                            <td class="active">Последняя активность:</td>
                                            <td>12-06-2016 / 09:11</td>
                                        </tr>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane fade" id="contact">
                                    <p></p>
                                    <form role="form">
                                        <div class="form-group">
                                            <label>Тема сообщения</label>
                                            <input type="text" class="form-control rounded"
                                                   placeholder="Укажите Ваше Имя">
                                        </div>

                                        <div class="form-group">
                                            <label>Текст Вашего сообщения</label>
                                            <textarea class="form-control rounded"
                                                      style="height: 100px;"></textarea>
                                            <p class="help-block">Текст сообщения будет отправлен пользователю</p>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success" data-original-title=""
                                                    title="">Отправить
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
            <%-- </div>--%>
    </div>
</c:if>

<%--ПОЛЬЗОВАТЕЛЬ--%>
<div class="tab-pane fade" id="myProfile">
    <div class="container">
        <div id="main">
            <div class="row" id="real-estates-detail">
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
                            <div class="text-center" id="author">
                                <img src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${user.id}.jpg">
                                <h3>${user.firstname} ${user.familyname}</h3>
                                <small class="label label-warning">${user.banned}</small>
                                <p>МОЙ СТАТУС</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-8 col-xs-12">
                    <div class="panel">
                        <div class="panel-body">
                            <ul id="myTab" class="nav nav-pills">
                                <li class="active"><a href="#detail" data-toggle="tab">О пользователе</a></li>
                                <li class=""><a href="#update" data-toggle="tab">Изменить данные</a></li>
                                <li class=""><a href="#delete" data-toggle="tab">Удалить аккаунт</a></li>

                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <hr>
                                <div class="tab-pane fade active in" id="detail">
                                    <h4>История профиля</h4>
                                    <table class="table table-th-block">
                                        <tbody>
                                        <tr>
                                            <td class="active">Зарегистрирован:</td>
                                            <td>${user.date_registration}</td>
                                        </tr>
                                        <tr>
                                            <td class="active">Последняя активность:</td>
                                            <td>12-06-2016 / 09:11</td>
                                        </tr>

                                        <form action="UploadImage" method="POST" enctype="multipart/form-data">
                                            <input type="hidden" name="userId" value="${user.id}"/>

                                            <tr>
                                                <td align="right">Change Image:</td>
                                                <td><input type="file" name="image"/>
                                                    <input type="submit" value="Upload"></td>
                                            </tr>
                                            <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
                                        </form>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane fade active in" id="update">
                                    <h4>История профиля</h4>
                                    <table class="table table-th-block">
                                        <tbody>
                                        <tr>
                                            <td class="active">Зарегистрирован:</td>
                                            <td>${user.date_registration}</td>
                                        </tr>
                                        <tr>
                                            <td class="active">Последняя активность:</td>
                                            <td>12-06-2016 / 09:11</td>
                                        </tr>

                                        <form action="UploadImage" method="POST" enctype="multipart/form-data">
                                            <input type="hidden" name="userId" value="${user.id}"/>

                                            <tr>
                                                <td align="right">Change Image:</td>
                                                <td><input type="file" name="image"/>
                                                    <input type="submit" value="Upload"></td>
                                            </tr>
                                            <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
                                        </form>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane fade" id="delete">
                                    <p>DELETE This ACCOUNT?</p>
                                    <form action="controller" method="post" role="form">
                                        <div class="form-group">
                                            <input type="hidden" name="command" value="account_delete">
                                          <%--  <label>Тема сообщения</label>
                                            <input type="text" class="form-control rounded"
                                                   placeholder="Укажите Ваше Имя">
--%>
                                            <label for="Email2">E-mail:</label>
                                            <input type="text" name="email" class="form-control" id="Email2">
                                        </div>

                                        <div class="form-group">
                                           <%-- <label>Текст Вашего сообщения</label>
                                            <textarea class="form-control rounded"
                                                      style="height: 100px;"></textarea>
                                            <p class="help-block">Текст сообщения будет отправлен пользователю</p>
--%>
                                            <label for="Password2">Password:</label>
                                            <input type="password" name="password" class="form-control" id="Password2"><br>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success" data-original-title=""
                                                    title="">Submit
                                            </button>
                                            <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>

                                        <%--DELETE--%>
                                           <%-- <div class="container">
                                                <form action="controller" method="post" role="form" class="form-horizontal">
                                                    <input type="hidden" name="command" value="account_delete">
                                                    <div class="form-group col-md-3">
                                                        <p class="text-left text-danger font-weight-bold">
                                                            Are you sure in deleting your account? Please to input<br>
                                                        </p>
                                                        <label for="Email">E-mail:</label>
                                                        <input type="text" name="email" class="form-control" id="Email">
                                                        <label for="Password">Password:</label>
                                                        <input type="password" name="password" class="form-control" id="Password"><br>
                                                        <button type="submit" class="btn btn-secondary">Submit</button>


                                                        &lt;%&ndash;  <p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
                                                          <p class="text-left text-info font-weight-bold">${requestScope.account_deleted}</p>&ndash;%&gt;
                                                        <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>

                                                    </div>
                                                </form>
                                            </div>--%>
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
</div>






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

                if (background_src != "undefined") {
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

                if (background_src != "undefined") {
                    new_css = {
                        "background-image": "url('" + background_src + "')",
                        "background-position": "center center",
                        "background-size": "cover"
                    };

                    $header.css(new_css);
                }
            });

        },
    }
</script>


</body>
<footer>
    <c:import url="footer.jsp"/></footer>
</footer>
</html>