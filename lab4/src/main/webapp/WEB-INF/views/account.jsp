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
    <link rel="stylesheet" href="/css/account.css" />
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="grid">
    <div>
        <h1>Bookings</h1>
    </div>
    <c:choose>
        <c:when test="${bookings.size() != 0}">
            <div>
                <c:forEach items="${bookings}" var="booking">
                    <div class="booking">
                        <div class="bookingHeader">
                            <div>Booking reference: ${booking.id}</div>
                            <div>${booking.time.format(DateTimeFormatter.ofPattern("d MMM uuuu  H:mm"))}</div>
                        </div>
                        <c:forEach items="${booking.tickets}" var="ticket">
                            <div class="ticket">
                                <div>${shows.get(ticket.showId).name}</div>
                                <div>${seats.get(ticket.seatId).time.format(DateTimeFormatter.ofPattern("d MMM uuuu  H:mm"))}</div>
                                <div>${seats.get(ticket.seatId).type}</div>
                                <div>${seats.get(ticket.seatId).name}</div>
                                <div>€ ${seats.get(ticket.seatId).price}</div>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            You have no bookings yet
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
