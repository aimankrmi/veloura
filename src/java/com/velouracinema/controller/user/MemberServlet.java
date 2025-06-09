/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.velouracinema.controller.user;

import com.velouracinema.dao.booking.BookingDAO;
import com.velouracinema.model.Booking;
import com.velouracinema.model.User;
import java.io.IOException;
import java.io.PrintWriter;
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
public class MemberServlet extends HttpServlet {

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
        String path = request.getServletPath();

        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        
        if (userSession == null || !userSession.getRole().equalsIgnoreCase("member")) {
            response.sendError(401);
            return;
        }

        if (path.equals("/member")) {
            String error = request.getParameter("error");
            String message = request.getParameter("message");
            if(error!=null){
                request.setAttribute("error", error);
            }
            if(message!=null){
                request.setAttribute("message", message);
            }
            request.setAttribute("member", userSession);
            request.getRequestDispatcher("views/member/member-dashboard.jsp").forward(request, response);
        } else if (path.equals("/viewBookingHistory")) {
            List<Booking> bookings = BookingDAO.getBookingByMemberId(userSession.getId());
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("views/member/booking-history.jsp").forward(request, response);
            
        }

//        else {
//            if (user.getRole().equalsIgnoreCase("member")) {
//            } else {
//                response.sendError(401);
//            }
//        }
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
        processRequest(request, response);
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
