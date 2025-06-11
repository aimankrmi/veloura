/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.velouracinema.controller.booking;

import com.velouracinema.dao.booking.BookingDAO;
import com.velouracinema.model.Booking;
import com.velouracinema.model.User;
import com.velouracinema.util.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aiman
 */
public class EditBookingServlet extends HttpServlet {

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
//        response.sendRedirect(request.getContextPath()+"/member");
        response.sendError(401);
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
        HttpSession session = request.getSession();
        
        User userSession = (User) session.getAttribute("user");
        
        
        
        if (!Utils.authorizeUser(request, response, "member")) {
            response.sendError(401, "Unauthorized.");
            return;
        }

//            TEMPORARY USER AND BOOKING ID
        int member_id = userSession.getId();
        int booking_id = Integer.parseInt(request.getParameter("bookingId"));
        
        Booking booking = BookingDAO.getBookingById(booking_id, member_id);
        
        if(booking.getShowtime().getMovie().getStatus().equalsIgnoreCase("Expired")){
               response.sendError(501, "Expired movie to be update");
        }
        
        request.setAttribute("booking", booking);
        request.getRequestDispatcher("WEB-INF/views/booking/edit-booking.jsp").forward(request, response);
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
