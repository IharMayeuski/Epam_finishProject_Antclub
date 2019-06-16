<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.registration" var="registration_button"/>

<link rel="stylesheet" type="text/css" href="../../css/demo.css"/>
<link rel="stylesheet" type="text/css" href="../../css/style3.css"/>
<link rel="stylesheet" type="text/css" href="../../css/animate-custom.css"/>

<html>

<head><title>AntClub</title>
    <style type="text/css">
        body, footer {
            color: white;
        }

        body {
            font: 12px 'Lucida Sans Unicode', 'Trebuchet MS', Arial, Helvetica;
            background-size: cover;
            /* background-image: url("

        <c:url value="/img/TitlePage.jpg"/>  ");*/
        }
    </style>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="../../css/other_css/style.css">


</head>

<body>

<c:import url="header.jsp"/>

<%--Планируется таблица статей--%>
<table>
    <tbody>
    <c:forEach items="${types}" var="types" varStatus="theCount">
        <tr>
            <td><%--<img class="mr-5" src="${pageContext.request.contextPath}/TakePictureFromDB/type-${types.id}.jpg" alt=""
                     style="height:50px;"><br><br></td>--%>
            <td>${types.typeNews}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<div class="container">

    <section>
        <div id="container_demo">
            <a class="hiddenanchor" id="toregister"></a>
            <a class="hiddenanchor" id="tologin"></a>
            <div id="wrapper">
                <div id="login" class="animate form">
                    <form action="mysuperscript.php" autocomplete="on">
                        <h1>Вход</h1>
                        <p>
                            <label for="username" class="uname" data-icon="u"> Ваш e-mail или логин</label>
                            <input id="username" name="username" required="required" type="text"
                                   placeholder="sitehere или sitehere.ru@my.com"/>
                        </p>
                        <p>
                            <label for="password" class="youpasswd" data-icon="p"> Ваш пароль </label>
                            <input id="password" name="password" required="required" type="password"
                                   placeholder="например 123456"/>
                        </p>
                        <p class="keeplogin">
                            <input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping"/>
                            <label for="loginkeeping">Запомнить меня</label>
                        </p>
                        <p class="login button">
                            <input type="submit" value="Войти"/>
                        </p>
                        <p class="change_link">
                            Не зарегистрированы еще ?
                            <a href="#toregister" class="to_register">Присоединяйтесь</a>
                        </p>
                        <p class="change_link">
                           Забыли пароль?
                            <a href="#toforgetpassword" class="to_register">Восстановление</a>
                        </p>
                    </form>
                </div>

                <div id="register" class="animate form">
                    <form action="mysuperscript.php" autocomplete="on">
                        <h1> Регистрация </h1>
                        <p>
                            <label for="usernamesignup" class="uname" data-icon="u">Ваш логин</label>
                            <input id="usernamesignup" name="usernamesignup" required="required" type="text"
                                   placeholder="myname1"/>
                        </p>
                        <p>
                            <label for="emailsignup" class="youmail" data-icon="e"> Ваш e-mail</label>
                            <input id="emailsignup" name="emailsignup" required="required" type="email"
                                   placeholder="sitehere.ru@my.com"/>
                        </p>
                        <p>
                            <label for="passwordsignup" class="youpasswd" data-icon="p">Ваш пароль </label>
                            <input id="passwordsignup" name="passwordsignup" required="required" type="password"
                                   placeholder="123456"/>
                        </p>
                        <p>
                            <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Подтвердите ваш
                                пароль </label>
                            <input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required"
                                   type="password" placeholder="123456"/>
                        </p>
                        <p class="signin button">
                            <input type="submit" value="Регистрация"/>
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


<%--АВТОРИЗАЦИЯ--%>
<%--<c:if test="${empty role}">
    <div class="container">
        <form action="controller" method="post" role="form" class="form-horizontal" id="login4">
            <input type="hidden" name="command" value="find_user">
            <h1>Sign In</h1>
            <fieldset id="inputs">
                <input id="username4" type="text" name="login" placeholder="Логин" autofocus required>

                <input id="password2" type="password" name="password" placeholder="Пароль" autofocus required
                       pattern=".{4,}">
            </fieldset>
            <fieldset id="actions">
                <button type="submit" class="btn btn-secondary">Submit</button>
                <a href="#forgotPassword">Forgot passport?</a>

                <a href="controller?command=go_to_registration_page">${registration_button}</a>
            </fieldset>
            <fieldset>
                <p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
                <p class="text-left text-info font-weight-bold">${requestScope.account_deleted}</p>
                <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
            </fieldset>


        </form>
    </div>
</c:if>--%>

<%--Восстановление пароля--%>
<div class="tab-pane fade" id="forgotPassword">
    <div class="container">
        <form action="controller" method="post" role="form" class="form-horizontal" id="registration">
            <input type="hidden" name="command" value="new_password">
            <h4>Forgot password:</h4>
            <fieldset id="inputEMAIL">
                <input id="email" type="email" name="email" data-icon="u" placeholder="Email" autofocus required
                       pattern=".{4,45}">
            </fieldset>
            <fieldset id="action">
                <button type="submit" class="btn btn-secondary">Submit</button>

            </fieldset>
            <fieldset>
                <p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
                <p class="text-left text-info font-weight-bold">${requestScope.account_deleted}</p>
                <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
            </fieldset>
        </form>
    </div>
</div>


<%--ПРОФИЛЬ НАЙДЕННОГО ЮЗЕРА--%>
<c:if test="${not empty findUser}">
    <div class="container">
        <div id="login2">
            <div class="row" id="real-estates-detail">
                <div class="col-lg-4 col-md-4 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <header class="panel-title">
                                <div class="text-center">
                                    <strong>Role: ${findUser.role}</strong>
                                </div>
                            </header>
                        </div>
                        <div class="panel-body">
                            <div class="text-center" id="user">
                                <fieldset class="boarder p-2  rounded col-sm-1">
                                    <legend class="TakePictureFromDB"></legend>
                                    <div class="card mx-auto" style="...">
                                        <img src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${findUser.id}.jpg"
                                             alt="a" class="img-fluid">
                                    </div>
                                </fieldset>
                                <h3>${findUser.login}</h3>
                                <small class="label label-warning">${findUser.banned}</small>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-8 col-xs-12">
                    <div class="panel">
                        <div class="panel-body">
                            <div id="myTabContent" class="tab-content">
                                <hr>
                                <div class="tab-content" id="detail">
                                    <h4>История профиля</h4>
                                    <table class="table table-th-block">
                                        <tbody>
                                        <tr>
                                            <td class="tab-content">Зарегистрирован:</td>
                                            <td>${findUser.date_registration}</td>
                                        </tr>
                                        <tr>
                                            <td class="tab-content">Последняя активность:</td>
                                            <td>12-06-2016 / 09:11</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <ul id="myTab" class="nav nav-pills">
                                        <%--    <li class="active"><a href="#detail" data-toggle="tab">О пользователе</a></li>--%>
                                    <li class=""><a href="#contact" data-toggle="tab">Отправить сообщение</a></li>
                                </ul>
                                <div class="tab-pane fade" id="contact">
                                    <p></p>
                                    <form role="form">
                                        <div class="form-group">
                                            <label>Title</label>
                                            <input type="text" class="form-control rounded" style="height: 30px;"
                                                   placeholder="Title">
                                        </div>
                                        <div class="form-group">
                                            <label>Текст Вашего сообщения</label>
                                            <textarea class="form-control rounded" style="height: 80px;"></textarea>
                                            <p class="help-block">Текст сообщения будет отправлен пользователю</p>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success" data-original-title=""
                                                    title="">
                                                Отправить
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
</c:if>

<%--МОй ПРОФИЛЬ--%>
<div class="tab-pane fade" id="myProfile">
    <div class="container">
        <div id="login3">
            <div class="row" id="real-estates-detail1">
                <div class="col-lg-4 col-md-4 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <header class="panel-title">
                                <div class="text-center">
                                    <strong>Мой профиль</strong>
                                </div>
                            </header>
                        </div>
                        <div class="panel-body">
                            <div class="text-center" id="author2">
                                <fieldset class="boarder p-2  rounded col-sm-6">
                                    <legend class="TakePictureFromDB"></legend>
                                    <img src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${user.id}.jpg"
                                         alt="a" class="img-fluid">
                                </fieldset>
                                <h3>${user.login}</h3>
                                <small class="label label-warning">${user.banned}</small>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-8 col-xs-12">
                    <div class="panel">
                        <div class="panel-body">
                            <div id="myTabContent2" class="tab-content">
                                <hr>
                                <div class="tab-content" id="detail2">
                                    <h4>История профиля</h4>
                                    <table class="table table-th-block">
                                        <tbody>
                                        <tr>
                                            <td class="tab-content">Зарегистрирован:</td>
                                            <td>${user.date_registration}</td>
                                        </tr>
                                        <tr>
                                            <td class="tab-content">Последняя активность:</td>
                                            <td>12-06-2016 / 09:11</td>
                                        </tr>
                                        </tbody>

                                        <form action="UploadImage" method="POST" enctype="multipart/form-data">
                                            <input type="hidden" name="userId" value="${user.id}"/>
                                            <table boarder="2" align="center" width="50%">

                                                <tr>
                                                    <th align="right">Change Image:</th>
                                                    <td><input type="file" name="image"/></td>
                                                </tr>

                                                <tr>
                                                    <td><input type="submit" value="Upload"></td>
                                                    <td><input type="submit" value="Reset"></td>
                                                </tr>
                                            </table>
                                            <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
                                        </form>

                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap.bundle.js"></script>--%>

<%--ОТЛИЧНЫЙ ДЛЯ МЕНЯ ПРИМЕР ВАЛИДАЦИИ ДАННЫХ ВНУТРИ ФОРМЫ!!!!
<form action="#" class="form" method="post" >
    <div class="form__field">
        <input type="text" name="name" placeholder="Имя*" required />
    </div>
    <div class="form__field">
        <input type="email" name="name" placeholder="E-Mail" />
        <span class="form__error">Это поле должно содержать E-Mail в формате example@site.com</span>
    </div>
    <div class="form__field">
        <input type="tel" name="name" placeholder="Телефон" pattern="[\+]\d{1}\s[\(]\d{3}[\)]\s\d{3}[\-]\d{2}[\-]\d{2}" minlength="18" maxlength="18" />
        <span class="form__error">Это поле должно содержать телефон в формате +7 (123) 456-78-90</span>
    </div>
    <div class="form__field">
        <input type="url" name="name" placeholder="Ваш сайт" />
        <span class="form__error">Это поле должно содержать URL в формате http://mysite.ru</span>
    </div>
    <div class="form__field">
        <input type="number" name="name" placeholder="Ваш рост (см)" min="100" max="250" />
        <span class="form__error">Ваш рост должен быть не меньше 100 и не больше 250 см</span>
    </div>
    <button type="submit">Отправить</button>
</form>--%>
<%--<c:if test="${empty role}">
    <div class="p-x-1 p-y-3">
        <form class="card card-block m-x-auto bg-faded form-width">
        <form action="controller" method="post" role="form" class="card card-block m-x-auto bg-faded form-width">
            <input type="hidden" name="command" value="find_user">
            <legend class="m-b-1 text-xs-center">Sign In</legend>
            <div class="form-group input-group">
             <span class="has-float-label">
                 <label for="Login">Login:</label>
                 <input type="text" name="login" class="form-control" id="Login"></span></div>
            <div class="form-group input-group">
             <span class="has-float-label">
                 <label for="Password">Password:</label>
                 <input type="password" name="password" class="form-control" id="Password"></span></div>
            <div class="text-xs-center">
                <button type="submit" class="btn btn-secondary">Submit</button>
                <p class="text-left text-info font-weight-bold">${requestScope.registration}</p>
                <p class="text-left text-info font-weight-bold">${requestScope.account_deleted}</p>
                <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
            </div>
        </form>
        </form>
    </div>
</c:if>--%>
<%--
<div class="p-x-1 p-y-3">
    <form class="card card-block m-x-auto bg-faded form-width">
        <legend class="m-b-1 text-xs-center">Регистрация</legend>
        <div class="form-group input-group">
             <span class="has-float-label">
                     <input class="form-control" id="first" type="text" placeholder=""/>
                     <label for="first">Логин</label> </span>
        </div>
        <div class="form-group input-group">
            <span class="input-group-addon">@</span>
            <span class="has-float-label">
             <input class="form-control" id="email" type="email" placeholder=""/>
             <label for="email">E-mail</label> </span>
        </div>
        <div class="form-group input-group">
             <span class="has-float-label">
                    <input class="form-control" id="password1" type="password" placeholder=""/>
            <label for="password1">Пароль</label></span>
        </div>
        <div class="form-group input-group">
             <span class="has-float-label">
                    <input class="form-control" id="password2" type="password" placeholder=""/>
            <label for="password2">Пароль повторно</label></span>
        </div>
        <div class="text-xs-center">
            <button class="btn btn-block btn-primary" type="submit">Регистрация</button>
        </div>
    </form>
</div>--%>
<%--<p> ${requestScope.values().size()}</p>
<p> ${requestScope.keySet().iterator()}</p>
<p> ${requestScope.values().stream()}</p>
<p> ${requestScope.get("error")}</p>
<p>${requestScope.error}</p>
${sessionScope.error}
${pageScope.error}--%>
<%--<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"> 1212212</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="do?command=to_sign_up"><span class="glyphicon glyphicon-user">

                </span> 34234234324</a></li>
                <li><a href="#myModal" data-toggle="modal"><span class="glyphicon glyphicon-log-in">

                </span> 454534243423</a></li>
                <li><a href="do?command=lang&page=${page}">43ц432</a></li>
            </ul>
        </div>
    </div>
</nav>
<p>
    &nbsp;
</p>
<p>
    &nbsp;
</p>--%>
<%--
<div id="myModal" class="modal fade">
<div class="container">
    <form action="controller" method="post" role="form" class="form-horizontal">
        <input type="hidden" name="command" value="registration">
        <div class="form-group col-md-3">
            <label for="Email">Email:</label>
            <input type="email" name="email" class="form-control" id="Email">
            <label for="Login2">Login:</label>
            <input type="text" name="login" class="form-control" id="Login2">
            <label for="Password1">Password:</label>
            <input type="password" name="password1" class="form-control" id="Password1">
            <label for="Password2">Repeat your password:</label>
            <input type="password" name="password2" class="form-control" id="Password2"><br>
            <button type="submit" class="btn btn-secondary">Submit</button>
            <p class="text-left text-danger font-weight-bold">${requestScope.error}</p>
        </div>
    </form>
</div>
</div>
--%>
<%--
<div id="myModal" class="modal fade">
    <div class="modal-dialog modal-login">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">6/></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form action="do" method="post">
                    <input type="hidden" name="command" value="login">
                    <div class="form-group">
                        <i class="fa fa-at"></i>
                        <input type="text" name="email" class="form-control" placeholder="Email" required="required">
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock"></i>
                        <input type="password" name="password" class="form-control" placeholder="Password"
                               required="required">
                    </div>
                    <div class="form-group small clearfix">
                        <label class="checkbox-inline"><input type="checkbox">&nbsp;5/>
                        </label>
                        <a href="#" class="forgot-link">4/></a>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary btn-block btn-lg"
                               value="3"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">22222/> &nbsp;<a href="do?command=to_sign_up">11111/></a></div>
        </div>
    </div>
</div>--%>
<%--
<script src="http:"></script>
<script src="../js/authorization.js"></script>--%>

</body>
<footer>
    <c:import url="../../jsp/footer.jsp"/></footer>

</html>