<%-- Document : selectSeats Created on : 16 May 2025, 6:54:46 pm Author : Aiman --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Booking</title>
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
        <link rel="stylesheet" href="assets/css/booking.css"/>
        <script src="assets/js/booking.js" type="text/javascript" defer></script>
    </head>
    <body>
        <jsp:useBean id="movie" class="com.velouracinema.model.Movie" scope="request"/>
        <jsp:useBean id="movieShowtime" class="com.velouracinema.model.Showtime" scope="request"/>
        <jsp:setProperty name="movieShowtime" property="movie" value="${movie}"/>
        <jsp:setProperty name="movieShowtime" property="showDate" value="${param.date}"/>
        <jsp:setProperty name="movieShowtime" property="showTime" value="${param.time}"/>

        <jsp:include page="../includes/header.jsp"  flush="true"/>


        <!--Form to book-->
        <form action="payment" method="POST">
            <input type="hidden" name="bookingToken" value="${bookingToken}" />
            <input type="hidden" name="bookingTokenTime" value="${bookingTokenTime}" />
            <div class="fluid-container pt-3">
                <div class="row w-100 m-0">
                    <!--MOVIE PICTURE-->
                    <section class="w-100 mt-3 mx-auto px-0 movie-picture-section d-flex align-items-center justify-content-center">
                        <a href="booking?id=${movie.previousMovieId}" class="btn bg-transparent border-none btn-left"><i class="fa-solid fa-chevron-left fa-lg" style="color: #d9a93f;"></i></a>
                        <div class=" movie-picture-wrapper  overflow-hidden ">
                            <img src="assets/images/${movie.imagePath!=null? movie.imagePath:'default'}" class="img-fluid movie-img" alt="Movie Image">
                        </div>
                        <a href="booking?id=${movie.nextMovieId}" class="btn bg-transparent btn-right"><i class="fa-solid fa-chevron-right fa-lg" style="color: #d9a93f;"></i></a>
                    </section>

                    <!-- MOVIE DESCRIPTION -->
                    <section class="movie-desc-wrapper mt-3 d-flex flex-sm-column flex-column mb-4 justify-content-center align-items-center">
                        <div class="movie-title">
                            <h1 class="glow-gold fs-1 text-start text-capitalize" >${movie.title}</h1>
                            <input type="hidden" name="showtime-movie-id" value="${movieShowtime.movie.movieId}">
                        </div>
                        <div class="movie-desc d-flex">
                            <div class="movie-genre border-end pe-3 glow-white fs-5">${movie.genre}</div>
                            <div class="movie-duration border-end px-3 glow-white fs-5">${movie.hours}h ${movie.minutes}min</div>
                            <div class="movie-lang text-capitalize ps-3 glow-white fs-5">${movie.language}</div>
                        </div>
                    </section>

                    <!--Movie time/date and seat legend-->
                    <section class="row justify-content-center mx-auto">

                        <!--                            Form to book
                                                    <form action="booking">-->
                        <div class="col-12 col-sm-12 col-lg-7 d-flex flex-column justify-content-evenly align-items-center">


                            <!-- MOVIE DATE -->
                            <section class="showtimes-wrapper d-flex flex-column justify-content-center align-items-center mx-auto">
                                <div class="showtimes-title">
                                    <h1 class="glow-gold mt-3 fs-1 " >Available Date</h1>
                                </div>
                                <div class="showtimes-list mt-1 gap-2 d-flex flex-sm-row flex-column">

                                    <c:forEach items="${movieShowtime.showDates}" var="showtimeDate" varStatus="status">
                                        <fmt:parseDate value="${showtimeDate}" type="both" var="parsedDate" pattern="yyyy-MM-dd"/>
                                        <input onchange="checkDate()" type="radio" class="btn-check date-input" name="date" id="showtime_date_${status.index + 1}" value="${showtimeDate}" data-date="${showtimeDate}" autocomplete="off">
                                        <label class="btn btn-secondary showtimes-item date-label" for="showtime_date_${status.index + 1}"><fmt:formatDate value="${parsedDate}" pattern="dd MMMM yyyy (EEEE)"/></label>
                                    </c:forEach>

                                </div>
                            </section>
                            <c:if test="${param.date!=null}">
                                <!-- MOVIE SHOWTIMES -->
                                <section class="showtimes-wrapper d-flex flex-column justify-content-center align-items-center my-3">
                                    <div class="showtimes-title">
                                        <h1 class="glow-gold mt-3 fs-1 " >Available Showtimes</h1>
                                    </div>
                                    <div class="showtimes-list mt-1 gap-2 d-flex flex-sm-row flex-column">

                                        <c:forEach items="${movieShowtime.showTimes}" var="time" varStatus="status">

                                            <fmt:parseDate value="${time}" type="time" var="parsedTime" pattern="HH:mm:ss"/>
                                            <input onclick="checkTime()" type="radio" class="btn-check time-input" name="time" id="showtime_time_${status.index+1}" value="${time}" autocomplete="off" data-time="${time}">
                                            <label class="btn btn-secondary showtimes-item time-label" for="showtime_time_${status.index+1}" <c:if test="${param.time==time}">
                                                   disabled checked
                                                </c:if>><fmt:formatDate value="${parsedTime}" pattern="h:mm a"/></label>
                                        </c:forEach>
                                        <c:if test="${param.time!=null && param.date!=null}">
                                            <input type="hidden" id="time" name="time-show"value="${param.time}">
                                            <input type="hidden" id="date" name="date-show"value="${param.date}">
                                        </c:if>
                                    </div>
                                </section>

                            </c:if>
                        </div>
                        <c:if test="${param.date!=null && param.time!=null}">
                            <jsp:include page="../includes/seat-legend.jsp" flush="true"/>

                        </c:if>


                    </section>
                    <c:if test="${param.date!=null && param.time!=null}">
                        <input type="hidden" name="showtime-id" value="${movieShowtime.id}">
                        <!-- SEAT SELECTION -->
                        <section class="seat-selection-section w-100 mt-4 px-5 ">
                            <div class="seat-selection-title">
                                <h1 class="glow-gold mt-3 fs-1 text-center">Select Your Seats</h1>
                            </div>
                            <div class="screen mx-auto my-4 pt-4 text-center">Screen</div>
                            <!--<div class="seat-selection-wrapper pb-2 row mx-auto overflow-auto row seat-row mx-4 flex-nowrap">-->
                            <!--<div class="seat-selection-wrapper pb-2 fluid-container mx-auto overflow-auto">-->
                            <div class="seat-selection-wrapper pb-2 fluid-container mx-auto overflow-auto">   
                                <c:forEach items="${movieShowtime.seatRowByShowtime}" var="row">
                                    <c:set var="seatRow" value="${row.key}"/>
                                    <div class="row seat-row mx-4 flex-nowrap" data-seat="${seatRow}">
                                        <c:forEach items="${row.value}" var="seat" varStatus="status">
                                            <c:set var="seatNumber" value="${seat.seatNumber}" />

                                            <div class="col-1">
                                                <input type="checkbox" class="btn-check"
                                                       name="seat" value="${seatNumber}" id="${seatNumber}"
                                                       onchange="addSeat(this.id)" data-price="${movieShowtime.movie.price}"
                                                       ${seat.isAvailable ? "" : "disabled"} />
                                                <label class="btn seat-icon ${seat.isAvailable ? 'available' : 'reserved'}"
                                                       for="${seatNumber}">${seatNumber}</label> 

                                            </div>
                                            <c:if test="${status.index==1 || status.index==6}">
                                                <!--aisle-->
                                                <div class="col-1">
                                                </div>
                                            </c:if>


                                        </c:forEach>
                                    </div>
                                </c:forEach> 

                            </div>




                            <!-- Modal -->
                            <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content bg-dark">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-2 fw-bold text-center display-3 text-light" id="confirmModalLabel">Selected Seat</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <table class="table table-dark table-hover">
                                                <tbody>
                                                <span id="seat-selected-list" class="text-light"></span>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Edit</button>
                                            <!--<button type="button" class="btn btn-primary">Confirm</button>-->
                                            <input class="btn btn-primary" id="confirmButton" type="submit" value="Checkout">
                                        </div>
                                    </div>
                                </div>
                            </div>





                            <!--Selected Seat-->
                            <section class="text-center my-4 selected-seat-text">
                                <p class="fs-6">You selected: <span id="seat-selected-list-text" class="seat-selected-list"></span></p>
                            </section>

                        </section>

                        <section class="w-100 d-flex justify-content-center">
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" id="submitButton" data-bs-toggle="modal" data-bs-target="#confirmModal">
                                Book Now
                            </button>
                        </section>
                    </c:if>
                </div>
            </div>
        </form>
        <jsp:include page="../includes/footer.jsp" flush="true"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
