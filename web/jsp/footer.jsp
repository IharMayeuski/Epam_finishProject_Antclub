<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="ctg" uri="customtags" %>
<jsp:useBean id="now" class="java.util.Date"/>

<header>

    <style type="text/css">
        footer {
            position: fixed; /* Фиксированное положение */
            left: 0;
            bottom: 0; /* Левый нижний угол */
            /*    padding: 10px; !* Поля вокруг текста *!*/
            background: #e9e5ff; /* Цвет фона */
            color: black; /* Цвет текста */
            width: 100%; /* Ширина слоя */
            font-size: 11pt;
        }

    </style>
</header>
<html>
<body>
</body>

<footer>
    <div align=center id="footer">
        <c:choose>
            <c:when test="${local eq 'rus'}">
                <fmt:setLocale value="ru-RU"/>Сегодня: <fmt:formatDate value="${now}"/>
            </c:when>
            <c:otherwise>
                <fmt:setLocale value="en-EN"/>Today: <fmt:formatDate value="${now}"/>
            </c:otherwise>
        </c:choose>
        / <ctg:footer-info/>
    </div>
</footer>
</html>
