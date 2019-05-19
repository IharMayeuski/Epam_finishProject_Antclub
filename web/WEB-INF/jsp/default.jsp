<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 15.05.2019
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>AntClub</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <style type="text/css">
        body,footer {
            color: white;
        }
        body {
            background-size: cover;
            background-image: url("<c:url value="/img/TitlePage.jpg"/>");
        }
    </style>
</head>

<body>
<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="controller?command=go_to_index">Home</a></li>
        <li class="breadcrumb-item"><a href="#">About us</a></li>
    </ol>
</nav>

<a class="btn btn-primary float-right" href="controller?command=go_to_registrationPage"
   role="button">Registration</a><br><br>
<a class="btn btn-primary float-right" href="controller?command=go_to_guestPage" role="button"> I am a guest </a>

<div class="container">
    <form action="controller" method="post" role="form" class="form-horizontal">
        <input type="hidden" name="command" value="find_user">
        <div class="form-group col-md-4">
            <label for="Login">Login:</label>
            <input type="text" name="login" class="form-control" id="Login">
            <label for="Password">Password:</label>
            <input type="password" name="password" class="form-control" id="Password"><br>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>

</body>
<br><br><br><br><br><br><br><br><br><br><br><br>
<footer>
    <div align=center id="footer">© Epam, Minsk 2019 Designed by Maevski Igor</div>
</footer>

</html>