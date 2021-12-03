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
    <link rel="stylesheet" href="/css/cart.css" />
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="grid">
    <div>
        <h1>Shopping cart</h1>
    </div>
    <div>
        <c:forEach items="${quotes}" var="quote">
            <div class="quote">
                <div>${shows.get(quote.showId).name}</div>
                <div class="quoteTime">${seats.get(quote.seatId).time.format(DateTimeFormatter.ofPattern("d MMM uuuu  H:mm"))}</div>
                <div class="quoteSeatType">${seats.get(quote.seatId).type}</div>
                <div class="quoteSeatName">${seats.get(quote.seatId).name}</div>
                <form action="/api/removeFromCart" method="POST">
                    <input name="company" value="${quote.company}" type="hidden">
                    <input name="showId" value="${quote.showId}" type="hidden">
                    <input name="seatId" value="${quote.seatId}" type="hidden">
                    <button class="removeFromCartButton" type="submit">Remove</button>
                </form>
            </div>
        </c:forEach>
        <c:choose>
            <c:when test="${cartLength != \"0\"}">
                <form action="/api/confirmCart" method="POST">
                    <button class="confirmCartButton" type="submit">Book all</button>
                </form>
            </c:when>
            <c:otherwise>
                Your shopping cart is empty
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
