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
    <title>Admin</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <style type="text/css">
        body,footer {
            color: white;
        }
        body {
            background-size: cover;
            background-image: url("<c:url value="/static/img/TitlePage.jpg"/>");
        }

    </style>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="controller?command=to_admin_page">AntClub</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Topics
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Topic_1</a>
                    <a class="dropdown-item" href="#">Topic_2</a>
                    <a class="dropdown-item" href="#">Topic_3</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">About project</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=logOut">LogOut</a>
            </li>
        </ul>
    </div>
</nav>
<a  class="text float-right ">Login:
    <c:out value="${sessionScope.user.login}"/>
</a><br>
<a  class="text float-right ">Date registration:
    <c:out value="${sessionScope.user.date_registration}"/>

</a><br>


<c:out value="${requestScope.error}"/>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<footer>
    <div align=center id="footer">© Epam, Minsk 2019 Designed by Maevski Igor</div>
</footer>

</html>