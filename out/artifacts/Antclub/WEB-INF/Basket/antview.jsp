<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resource.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.default.welcome_message" var="welcome_message"/>
<fmt:message bundle="${loc}" key="locale.default.welcome_message_guest" var="welcome_message_guest"/>
<fmt:message bundle="${loc}" key="locale.default.about" var="about_project"/>
<fmt:message bundle="${loc}" key="locale.default.search" var="search"/>
<fmt:message bundle="${loc}" key="locale.default.search_login" var="search_login"/>
<fmt:message bundle="${loc}" key="locale.default.language" var="language"/>
<fmt:message bundle="${loc}" key="locale.default.i_am_a_guest" var="guest"/>

<fmt:message bundle="${loc}" key="locale.default.articles" var="articles"/>
<fmt:message bundle="${loc}" key="locale.default.registration" var="registration_button"/>

<jsp:useBean id="now" class="java.util.Date"/>

<html>
<head>

    <title>AntClub</title>
</head>
<body>


<link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
<link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
<link rel="stylesheet" href="http://bootstraptema.ru/snippets/font/2016/pe-icon-stroke/Pe-icon-7-stroke.css">
<link href="http://bootstraptema.ru/_sf/3/391.css" rel="stylesheet"/>
<script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/masonry/3.3.1/masonry.pkgd.min.js"></script>


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
                        <a href="controller?command=article&link_id=${types.id}" class="btn btn-danger btn-fill btn-round">Read article</a>
                    </div>
                    <div class="filter"></div>
                </div> <!-- end card -->
            </div>
        </div>
        </c:forEach>
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
</html>