package by.epam.club.tool;/*
<%--
        Created by IntelliJ IDEA.
        User:Администратор
        Date:15.05.2019
        Time:20:22
        To change this template use File|Settings|File Templates.
        --%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"prefix="fmt"%>

<html>
<head>
<title>Your Ant</title>
<link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"crossorigin="anonymous">
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
        footer,body{
        color:white;
        }
        body{
        background-size:cover;
        background-image:url("<c:url value="/img/TitlePage.jpg"/>");

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

<a class="btn btn-primary float-right"href="controller?command=go_to_registrationPage"role="button">Registration</a><br><br>
<a class="btn btn-primary float-right"href="controller?command=go_to_guestPage"role="button">I am a guest</a>

<div class="container">
<form action="controller"method="post"role="form"class="form-horizontal">
<input type="hidden"name="command"value="find_user">
<div class="form-group col-md-4">
<label for="Email">Email:</label>
<input type="email"name="login"class="form-control"id="Email">
<label for="Password">Password:</label>
<input type="password"name="password"class="form-control"id="Password"><br>
<button type="submit"class="btn btn-primary">Submit</button>
</div>
</form>
</div>


<%--<form action="default.jsp"method="POST"enctype="multipart/form-data">
        File:
<input type="file"name="image"><input type="submit"value="Upload">
</form>--%>
<form action="UploadImage"method="POST"enctype="multipart/form-data">
<table boarder="2"align="center"width="50%">
<tr>
<th align="center"bgcolor="orange"style="color:white"colspan="5">
<h2>Image Upload in Servlet:JSP</h2>
</th>
</tr>
<tr>
<th align="right">Select Image:</th>
<td><input type="file"name="image"/></td>
</tr>
<tr>
<th align="right">Select Name:</th>
<td><input type="text"name="name"/></td>
</tr>
<tr>
<td><input type="submit"value="Upload"></td>
<td><input type="submit"value="Reset"></td>
</tr>
</table>
</form>
<a href="<c:url value="file-list.jsp"/>">ViewList</a>
<br>
<a href="controller?command=test">Touch</a>
<%--<center>
<form action="UploadImage"method="post"enctype="multipart/form-data">
<table width="400px"align="center"border="2">
<tr>
<td align="center"colspan="2">Form Details</td>
</tr>
<tr>
<td>Select File</td>
<td>
<input type="file"required=""name="file">
</td>
</tr>
<tr>
<td></td>
<td><input type="submit"value="Submit"></td>
</tr>
</table>
</form>


<br><a href="file-list.jsp">View List</a>
</center>--%>


<%--<h2>Воспользуйся поиском!</h2>--%>

<%--<div class="content">
<form action="http://www.google.com/cse"accept-charset="UTF-8"method="get"id="google-cse-searchbox-form">
<div><input type="hidden"name="cx"id="edit-cx"value="006820727178538218960:myh38nfvpde"/>
<input type="hidden"name="cof"id="edit-cof"value="FORID:0"/>
<div class="form-item"id="edit-query-wrapper">
<input type="text"maxlength="128"name="query"id="edit-query"size="15"value=""
        title="Введите ключевые слова для поиска."class="form-text"/>
</div>
<input type="submit"name="op"id="edit-sa"value="Найти"class="form-submit"/>
<input type="hidden"name="form_build_id"id="form-LEF2o6n96aV51wXVul_vEi2FhvumAiW3NuldNExqVpk"
        value="form-LEF2o6n96aV51wXVul_vEi2FhvumAiW3NuldNExqVpk"/>
<input type="hidden"name="form_id"id="edit-google-cse-searchbox-form"value="google_cse_searchbox_form"/>

</div>
</form>
</div>--%>

</body>
<br><br><br><br><br><br><br><br><br><br><br><br>
<footer>
<div align=center id="footer">© Epam,Minsk 2019Designed by Maevski Igor</div>
</footer>
</html>
*/
