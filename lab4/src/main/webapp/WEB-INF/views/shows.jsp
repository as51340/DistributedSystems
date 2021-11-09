<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DNet-Tickets</title>
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <link rel="stylesheet" href="/css/index.css" />
    <link rel="stylesheet" href="/css/shows.css" />
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="grid">
    <div>
        <h1>Shows</h1>
    </div>
    <c:choose>
        <c:when test="${shows.size() != 0}">
            <div class="items">
                <c:forEach items="${shows}" var="show">
                    <a href="/shows/${show.company}/${show.showId}">
                        <div class="item">
                            <img src="${show.image}"/>
                            <div class="itemText">
                                <div class="itemName">${show.name}</div>
                                <div class="itemLocation">${show.location}</div>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            <div>
        </c:when>
        <c:otherwise>
            No shows planned
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
