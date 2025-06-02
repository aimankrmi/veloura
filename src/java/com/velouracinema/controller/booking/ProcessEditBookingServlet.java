/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.velouracinema.controller.booking;

import com.velouracinema.dao.BookingDAO;
import com.velouracinema.dao.SeatDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aiman
 */
@WebServlet(name = "ProcessEditBookingServlet", urlPatterns = {"/processEditBooking"})
public class ProcessEditBookingServlet extends HttpServlet {

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
            out.println("<title>Servlet ProcessEditBookingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessEditBookingServlet at " + request.getContextPath() + "</h1>");
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
        String paymentStatus = request.getParameter("payment-status");
        if ("paid".equals(paymentStatus)) {

            int showtimeId = Integer.parseInt(request.getParameter("showtime-id"));
            int bookingId = Integer.parseInt(request.getParameter("booking-id"));
            String[] newSeats = request.getParameterValues("seat");
            String[] bookedSeats = request.getParameterValues("booked-seat");

            BookingDAO.removeBookedSeatsByBookingId(bookingId);

            for (String seat : bookedSeats) {
                int seatId = Integer.parseInt(seat);
                SeatDAO.changeStatusById(seatId);
                BookingDAO.removeBookedSeat(bookingId, seatId);
            }
            for (String seat : newSeats) {
                int seatId = SeatDAO.getSeatId(showtimeId, seat);
                SeatDAO.changeStatusById(seatId);
                BookingDAO.insertBookedSeat(bookingId, seatId);
            }

        } else {
            response.sendError(401, "Unauthorized.");
            return;
        }

        response.sendRedirect("index.jsp");
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
        response.sendRedirect(request.getContextPath() + "/booking");
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
