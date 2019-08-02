<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.deletearticle" var="deletearticle"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.deletephoto" var="deletephoto"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.addarticle" var="addarticle"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.addphoto" var="addphoto"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.addcomment" var="addcomment"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.deletecomment" var="deletecomment"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.updatecomment" var="updatecomment"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.updatearticle" var="updatearticle"/>
<fmt:message bundle="${loc}" key="locale.lookarticle.comment" var="commentarticle"/>
<fmt:message bundle="${loc}" key="locale.voted" var="voted"/>
<fmt:message bundle="${loc}" key="locale.cantvote" var="cantVoted"/>

<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/look_article.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css"/>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


    <style type="text/css">
        body, footer {
            color: black;
        }

        body {
            background: url(http://bootstraptema.ru/images/bg/bg-1.png)
        }

    </style>
</head>
<body>
<c:import url="header.jsp"/>
<br>

<div class="content text-center">
    <p class="description">${types.typeNews}</p>
    <c:if test="${role eq 'admin' or role eq 'user'}">
        <%--<a class="btn btn-primary" method="POST" href="controller?command=go_to_new_article&type=${types.id}"
           role="button">${addarticle}</a>--%>
        <form action="controller" method="post" role="form"
              style="display: inline-block;">
            <input type="hidden" name="type" value="${types.id}">
            <button class="btn btn-primary" type="submit"
                    name="command" value="go_to_new_article">${addarticle}
            </button>
        </form>
    </c:if>
    <div class="filter"></div>
</div>

<div>
    <c:forEach items="${articles}" var="articles" varStatus="theCount">
        <div class="media col-sm-12">
            <div class="media col-sm-10">
                <img class="mr-3 media-object img-circle"
                     src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${articles.userId}.jpg"
                     alt="" style="height:50px;" width="50px">
                <div class="media-body">
                    <div class="panel-heading">
                        <div class="author">
                              <%--  <a href="controller?command=find_user_by_login&search=${articles.userLogin}">${articles.userLogin}</a>--%>
                            <form action="controller" method="post" role="form"
                                  style="display: inline-block;">
                                <input type="hidden" name="search" value="${articles.userLogin}">
                                <button class="btn-link" type="submit"
                                        name="command" value="find_user_by_login">${articles.userLogin}
                                </button>
                            </form>
                        </div>
                        <div class="metadata"><span class="date">${articles.date_registration}</span></div>
                        <br><br>
                        <h4 class="mt-0">${articles.title}</h4>${articles.text}
                    </div>
                    <div class="footer-comment"><span class="vote plus" title="Нравится"><i
                            class="fa fa-thumbs-up"></i> </span> +
                        <span id="like" class="vote plus" title="Like"><b>${articles.positiveRating}</b>
                         <i class="fa fa-thumbs-down"></i> </span> | -

                        <span id="dislike" class="vote minus" title="Не нравится"><b>${articles.negativeRating}</b>
                         <i class="fa fa-thumbs-down"></i> </span>
                        <input type="hidden" id="id_news" value="${articles.id}"/>
                        <input type="hidden" id="id_user" value="${user.id}"/>
                        <input type="hidden" id="positiveRating" value="${articles.positiveRating}"/>
                        <input type="hidden" id="negativeRating" value="${articles.negativeRating}"/>
                    </div>
                    <c:if test="${role eq 'admin'}">
                        <%--<a class="btn btn-outline-success"
                           href="controller?command=delete_article&articles=${articles.id}"
                           role="button">${deletearticle}</a>--%>
                        <form action="controller" method="post" role="form"
                              style="display: inline-block;">
                            <input type="hidden" name="articles" value="${articles.id}">
                            <button class="btn btn-outline-success" type="submit"
                                    name="command" value="delete_article">${deletearticle}
                            </button>
                        </form>

                    </c:if>
                    <c:if test="${role eq 'admin' or user.login eq articles.userLogin}">
                        <%--<a class="btn btn-outline-success"
                           href="controller?command=add_pic_to_article&articles=${articles.id}"
                           role="button">${addphoto}</a>--%>
                        <form action="controller" method="post" role="form"
                              style="display: inline-block;">
                            <input type="hidden" name="articles" value="${articles.id}">
                            <button class="btn btn-outline-success" type="submit"
                                    name="command" value="add_pic_to_article">${addphoto}
                            </button>
                        </form>
                        <%--<a class="btn btn-outline-success"
                           href="controller?command=to_update_article&article=${articles.id}&text=${articles.text}"
                           role="button">${updatearticle}</a>--%>

                        <form action="controller" method="post" role="form"
                              style="display: inline-block;">
                            <input type="hidden" name="article" value="${articles.id}">
                            <input type="hidden" name="text" value="${articles.text}">
                            <button class="btn btn-outline-success" type="submit"
                                    name="command" value="to_update_article">${updatearticle}
                            </button>
                        </form>
                    </c:if><br>
                    <c:if test="${role eq 'admin' or role eq 'user'}">
                        <div class="pull-left">
                           <%-- <a class="btn btn-info"
                               href="controller?command=go_to_new_comment&articleId=${articles.id}">${addcomment}</a>--%>

                            <form action="controller" method="post" role="form"
                                  style="display: inline-block;">
                                <input type="hidden" name="articleId" value="${articles.id}">
                                <button class="btn btn-info" type="submit"
                                        name="command" value="go_to_new_comment">${addcomment}
                                </button>
                            </form>


                            <br><br><br><br>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="media col-sm-2">
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <div class="teaser2">
                        <div class="carousel-inner">
                            <div class="item active">
                                <img class="mr-2"
                                     src="${pageContext.request.contextPath}/TakePictureFromDB/type-${types.id}.jpg"
                                     alt="" style="height:200px;" width="150px">
                                <p class="description text-center">${types.typeNews}</p>
                            </div>
                            <c:forEach var="picture" items="${articles.pictures}">
                                <div class="item">
                                    <div class="img">
                                        <img src="${pageContext.request.contextPath}/TakePictureFromDB/picture-${picture.id}.jpg"
                                             alt="" style="height:150px;" width="150px"> <br>
                                        <h5 class="text-center"> ${picture.name}</h5>
                                        <c:if test="${role eq 'admin' or user.login eq articles.userLogin}">
                                            <%--<a class="btn btn-outline-success text-center"
                                               href="controller?command=delete_picture&picture=${picture.id}"
                                               role="button">${deletephoto}</a>--%>

                                            <form action="controller" method="post" role="form"
                                                  style="display: inline-block;">
                                                <input type="hidden" name="picture" value="${picture.id}">
                                                <button class="btn btn-outline-success text-center" type="submit"
                                                        name="command" value="delete_picture">${deletephoto}
                                                </button>
                                            </form>

                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <c:if test="${ not empty articles.comments}">
                <h5 class="title-comments text-left pl-sm-5"> ${commentarticle} ${articles.commentQuantity}</h5>
                <c:forEach items="${articles.comments}" var="comment" varStatus="theCount">
                    <div class="comments col-sm-12">
                        <ul class="media-list">
                            <li class="media">
                                <div class="media-left">
                                    <a href="#">
                                        <img class="media-object img-circle"
                                             src="${pageContext.request.contextPath}/TakePictureFromDB/profile-${comment.userId}.jpg"
                                             alt="" style="height:50px;" width="50px">
                                    </a>
                                </div>
                                <div class="media-body">
                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            <div class="author">
                                                   <%--<a href="controller?command=find_user_by_login&search=${comment.userLogin}">${comment.userLogin}</a>--%>

                                                <form action="controller" method="post" role="form"
                                                      style="display: inline-block;">
                                                    <input type="hidden" name="search" value="${comment.userLogin}">
                                                    <button class="btn-link" type="submit"
                                                            name="command" value="find_user_by_login">${comment.userLogin}
                                                    </button>
                                                </form>

                                            </div>
                                            <div class="metadata"><span class="date">${comment.dateRegistration}</span>
                                            </div>
                                        </div>
                                        <div class="panel-body">
                                            <div class="media-text text-justify">${comment.text}</div>
                                        </div>
                                        <c:if test="${role eq 'admin' or user.login eq comment.userLogin}">
                                           <%-- <a class="btn btn-outline-success"
                                               href="controller?command=delete_comment&comment=${comment.id}"
                                               role="button">${deletecomment}</a>--%>

                                            <form action="controller" method="post" role="form"
                                                  style="display: inline-block;">
                                                <input type="hidden" name="comment" value="${comment.id}">
                                                <button class="btn btn-outline-success" type="submit"
                                                        name="command" value="delete_comment">${deletecomment}
                                                </button>
                                            </form>

                                            <%--<a class="btn btn-outline-success"
                                               href="controller?command=update_page_comment&comment=${comment.id}&text=${comment.text}"
                                               role="button">${updatecomment}</a>--%>

                                            <form action="controller" method="post" role="form"
                                                  style="display: inline-block;">
                                                <input type="hidden" name="comment" value="${comment.id}">
                                                <input type="hidden" name="text" value="${comment.text}">
                                                <button class="btn btn-outline-success" type="submit"
                                                        name="command" value="update_page_comment">${updatecomment}
                                                </button>
                                            </form>
                                        </c:if>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </c:forEach>
</div>


<script>
    $(document).ready(function () {
        $('span#like').click(function () {
            setVote('like', $(this));
        });
        $('span#dislike').click(function () {
            setVote('dislike', $(this));
        });
    });

    function setVote(type, element) {
        var id_user = $('#id_user').val();
        var id_news = element.parent().find('#id_news').val();
        var positiveRating = element.parent().find('#positiveRating').val();
        var negativeRating = element.parent().find('#negativeRating').val();
        $.ajax({
            type: "GET",
            url: "someservlet",
            data: {
                'id_user': id_user,
                'id_news': id_news,
                'positiveRating': positiveRating,
                'negativeRating': negativeRating,
                'type': type
            },
            dataType: "json",
            success: function (data) {
                if (data == 'success') {
                    var count = parseInt(element.find('b').html());
                    element.find('b').html(count + 1);
                } else if (data == 'no') {
                    swal("${voted}");
                } else if (data == 'you cant vote') {
                    swal("${cantVoted}");
                }
            }
        });
    }
</script>
<%--Вставлено временно--%>
<script>
    document.onkeydown = function (e) {
        if (e.keyCode === 116) {
            return false;
        }
    };
</script>


<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>

</html>
