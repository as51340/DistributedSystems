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
    <link rel="stylesheet" href="/css/show_seats.css" />
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="grid">
    <div class="item">
        <img src="${show.image}"/>
        <div class="itemText">
            <div class="itemName">${show.name}</div>
            <div class="itemLocation">${show.location}</div>
            <div class="itemTime">${time}</div>
        </div>
    </div>
    <div>
        <c:forEach items="${seats}" var="seatsPerType">
            <div class="seatType">
                <div class="seatName">${seatsPerType.key}</div>
                <div class="seats">
                    <c:forEach items="${seatsPerType.value}" var="seat">
                        <div class="seat">
                            <form action="/api/addToCart" method="POST">
                                <input name="company" value="${seat.company}" type="hidden">
                                <input name="showId" value="${seat.showId}" type="hidden">
                                <input name="seatId" value="${seat.seatId}" type="hidden">
                                <button class="addToCartButton" type="submit">${seat.name}</button>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
