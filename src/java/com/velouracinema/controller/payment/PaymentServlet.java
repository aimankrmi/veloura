/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.velouracinema.controller.payment;

import com.velouracinema.dao.BookingDAO;
import com.velouracinema.dao.MovieDAO;
import com.velouracinema.dao.PaymentDAO;
import com.velouracinema.dao.SeatDAO;
import com.velouracinema.dao.ShowtimeDAO;
import com.velouracinema.model.Booking;
import com.velouracinema.model.Payment;
import com.velouracinema.model.Seat;
import com.velouracinema.model.Showtime;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aiman
 */
public class PaymentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();
        if ("/payment".equals(path)) {

            String formToken = request.getParameter("bookingToken");
            HttpSession session = request.getSession();

            String sessionToken = (String) session.getAttribute("bookingToken");
            Long tokenTime = (Long) session.getAttribute("bookingTokenTime");

            if (sessionToken == null || !sessionToken.equals(formToken) || tokenTime == null) {
                response.sendError(409, "Duplicate or expired booking token.");
                return;
            }
        }
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int showtimeId;

        String formToken = request.getParameter("bookingToken");
        HttpSession session = request.getSession();

        String sessionToken = (String) session.getAttribute("bookingToken");
        Long tokenTime = (Long) session.getAttribute("bookingTokenTime");

        if (tokenTime == null) {
            response.sendError(409, "Duplicate or expired booking token.");
            return;
        }

        // Check if token is expired (e.g., after 15 minutes = 900000 ms)
        long now = System.currentTimeMillis();
        if (now - tokenTime > 15 * 60 * 1000) {
            session.removeAttribute("bookingToken");
            session.removeAttribute("bookingTokenTime");
//                    response.sendRedirect("error.jsp?msg=TokenExpired");
            response.sendError(409, "Duplicate or expired booking token.");
            return;
        }

        if (sessionToken == null || !sessionToken.equals(formToken) || tokenTime == null) {
            response.sendError(409, "Duplicate or expired booking token.");
            return;
        }

        List<Seat> seatsBooked = new ArrayList<>();

        String date = request.getParameter("date-show");
        String time = request.getParameter("time-show");
        request.setAttribute("date", date);
        request.setAttribute("time", time);

        double totalAmount = 0;
        int movieId = Integer.parseInt(request.getParameter("showtime-movie-id"));

        int memberId = 5; //Just for temporary
        showtimeId = Integer.parseInt(request.getParameter("showtime-id"));
        String[] seats = request.getParameterValues("seat");
        int seatId;
        int bookingId = BookingDAO.insertBooking(memberId, showtimeId); // Obtain booking ID while insert booking
        System.out.println("CREATE BOOKING");
        // Store bookingId in session or pass as parameter to payment page
//                session.setAttribute("currentBookingId", bookingId);

        // Invalidate token now - no more booking insertions allowed with this token
//                session.removeAttribute("bookingToken");
        Date bookingDate = BookingDAO.getBookingDateById(bookingId);
        for (String seat : seats) {
            seatId = SeatDAO.getSeatId(showtimeId, seat);
            SeatDAO.changeStatusById(seatId);
            seatsBooked.add(SeatDAO.getSeatById(seatId));
            BookingDAO.insertBookedSeat(bookingId, seatId);
            totalAmount += MovieDAO.getMoviePriceById(movieId);
        }

        int paymentId = PaymentDAO.insertPayment(bookingId, totalAmount);// Obtain booking ID while insert payment
        System.out.println("CREATE PAYMENT");
        session.removeAttribute("bookingToken");
        session.removeAttribute("bookingTokenTime");
        Payment payment = new Payment();
        payment.setId(paymentId);
        payment.setAmount(totalAmount);
        payment.setBookingId(bookingId);

        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setBookingDate(bookingDate);
        booking.setMemberId(memberId);
        booking.setShowtimeId(showtimeId);
        booking.setSeats(seatsBooked);
        booking.setPayment(payment);

        Showtime showtime = new Showtime();
        showtime.setMovie(MovieDAO.getMovieById(movieId));
        showtime.setShowDate(date);
        showtime.setShowTime(time);

        // To check if the showtime is less than 3 hours
        LocalDateTime showtimeTime = ShowtimeDAO.getShowtimeDateTime(showtimeId);
        LocalDateTime nowTime = LocalDateTime.now();

        boolean allowCounterPayment = nowTime.plusHours(3).isBefore(showtimeTime);
        // Store flag in request/session
        request.setAttribute("allowCounterPayment", allowCounterPayment);

        // Hantar dekat Payment
//        request.setAttribute("movie_name", MovieDAO.getMovieById(movieId).getTitle());
        request.setAttribute("showtime", showtime);
//        request.setAttribute("payment", payment);
//        request.setAttribute("total_amount", totalAmount);
//        request.setAttribute("member_id", memberId);
//        request.setAttribute("showtime_id", showtimeId);
//        request.setAttribute("seat", seats);
        request.setAttribute("booking", booking);

        request.getRequestDispatcher("views/payment.jsp").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
