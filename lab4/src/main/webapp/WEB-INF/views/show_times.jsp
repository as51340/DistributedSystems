<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DNet-Tickets</title>
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <link rel="stylesheet" href="/css/index.css" />
    <link rel="stylesheet" href="/css/show_times.css" />
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="grid">
    <div class="item">
        <img src="${show.image}"/>
        <div class="itemText">
            <div class="itemName">${show.name}</div>
            <div class="itemLocation">${show.location}</div>
        </div>
    </div>
    <div>
        <c:forEach items="${showTimes}" var="showTime">
            <div class="time">
                <div>${showTime.format(DateTimeFormatter.ofPattern("d MMM uuuu  H:mm"))}</div>
                <a href="/shows/${show.company}/${show.showId}/${showTime.format(DateTimeFormatter.ISO_DATE_TIME)}">
                    <div class="bookButton">Book now</div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
