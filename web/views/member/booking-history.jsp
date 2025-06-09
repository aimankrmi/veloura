<%-- 
    Document   : booking-history
    Created on : 9 Jun 2025, 6:51:30 pm
    Author     : Aiman
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking History</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&family=Vibur&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/header.css" />
        <link rel="stylesheet" href="assets/css/footer.css" />

    </head>
    <body>
        <jsp:include page="../../includes/header.jsp" flush="true" />


        <div class="container-fluid px-5 my-4">
            <div class="row">
                <div class="col-auto">
                    <a href="${pageContext.request.contextPath}/member"><i class="fa-solid fa-circle-chevron-left fa-xl me-2" style="color: var(--color-light-gold);"></i></a>
                    <h1 class="glow-gold d-inline-block">Booking List</h1>
                </div>
            </div>

            <c:if test="${param.message!=null}">
                <div class="row">
                    <div class="alert alert-success" role="alert">
                        <c:out value="${message}"/>
                    </div>
                </div>
            </c:if>
            
            <div class="row">



                <table class="table table-dark ">
                    <thead>
                        <tr>
                            <th scope="col">Booking ID</th>
                            <th scope="col">Showtime ID</th>
                            <th scope="col">Booking Date</th>
                            <th scope="col">Booking Status</th>
                            <th scope="col">Booking Seats</th>
                            <th scope="col">Payment Method</th>
                            <th scope="col">Payment Amount</th>
                            <th scope="col">Payment Status</th>
                            <th scope="col">Action</th>

                        </tr>
                    </thead>
                    <tbody class="table-hover">

                        <c:forEach items="${bookings}" var="booking">

                            <tr>
                                <th scope="row"><c:out value="${booking.id}"/></th>
                                <td><c:out value="${booking.showtimeId}"/></td>
                                <td><c:out value="${booking.bookingDate}"/></td>
                                <td class="text-capitalize"><c:out value="${booking.status}"/></td>
                                <td> 
                                    |
                                    <c:forEach items="${booking.seats}" var="seat">
                                        <c:out value="${seat.seatNumber}"/>|
                                    </c:forEach>
                                </td>
                                <td class="text-capitalize"><c:out value="${booking.payment.paymentMethod}"/></td>
                                <td><c:out value="${booking.payment.amount}"/></td>
                                <td class="text-capitalize">
                                    <c:out value="${booking.payment.status}"/></td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/editBooking" method="POST">
                                        <input type="hidden" name="bookingId" value="${booking.id}">
                                        <input class="btn btn-primary" type="submit" value="Edit Booking" ${(!booking.showtime.upcoming || booking.payment.status != 'paid') ? 'disabled' : ''}>
                                    </form>
                            </tr>

                        </c:forEach>


                    </tbody>
                </table>
            </div>
        </div>

        <jsp:include page="../../includes/footer.jsp" flush="true" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
