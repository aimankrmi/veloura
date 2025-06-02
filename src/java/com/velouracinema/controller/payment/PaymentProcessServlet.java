/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.velouracinema.controller.payment;

import com.velouracinema.dao.BookingDAO;
import com.velouracinema.dao.PaymentDAO;
import com.velouracinema.dao.ShowtimeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aiman
 */
public class PaymentProcessServlet extends HttpServlet {

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
            out.println("<title>Servlet PaymentProcess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentProcess at " + request.getContextPath() + "</h1>");
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
        int status = 0;
        if (request.getParameter("bookingId") != null) {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            String paymentMethod = request.getParameter("paymentMethod");
            request.setAttribute("paymentMethod", paymentMethod);
            request.setAttribute("bookingId", bookingId);
            status = PaymentDAO.updatePaymentMethod(bookingId, paymentMethod);
            if (paymentMethod.equals("online")) {
                status = PaymentDAO.updateStatusSuccess(bookingId);
                System.out.println("UPDATE PAYMENT");
                if (status > 0) {
                    status = BookingDAO.updateBookingStatus(bookingId);
                    System.out.println("UPDATE BOOKING");
                    if (status > 0) {
                        request.getRequestDispatcher("views/success-payment.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("booking");
                    }
                }

            } else {
                int showtimeId = Integer.parseInt(request.getParameter("showtimeId"));
                LocalDateTime showtimeTime = ShowtimeDAO.getShowtimeDateTime(showtimeId);
                if (LocalDateTime.now().plusHours(3).isAfter(showtimeTime)) {
                    // Too close to showtime, reject
                    request.setAttribute("error", "Cannot choose Pay at Counter for showtimes within 3 hours.");
                    request.getRequestDispatcher("payment.jsp").forward(request, response);
                    return;
                }

                status = BookingDAO.updateBookingStatus(bookingId);
                if (status > 0) {
                    request.getRequestDispatcher("views/success-payment.jsp").forward(request, response);

                }
            }

        }
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
