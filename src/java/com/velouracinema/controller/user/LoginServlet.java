/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.velouracinema.controller.user;

import com.velouracinema.dao.user.UserDAO;
import com.velouracinema.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aiman
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else {
            if (user.getId() != 0) {
                response.sendRedirect(request.getContextPath());
                return;
            }
        }
//        
        if (request.getParameter("error") != null) {
            request.setAttribute("error", request.getParameter("error"));
        }
        if (request.getParameter("message") != null) {
            request.setAttribute("message", request.getParameter("message"));
        }
        response.sendRedirect(request.getContextPath() + "/views/user/login.jsp");
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = UserDAO.getUser(username, password);
        boolean urlRedirect = Boolean.parseBoolean(request.getParameter("urlRedirect"));
        String time = request.getParameter("time-show");
        String date = request.getParameter("date-show");
        String movieId = request.getParameter("movieId");
        if (user.getId() == 0) {

            if (!date.equals("") && !time.equals("") && !movieId.equals("")) {
                request.setAttribute("movieId", movieId);
                request.setAttribute("time", time);
                request.setAttribute("date", date);
                String message = "Please login first";
                request.getRequestDispatcher("views/user/login.jsp?message=" + message).forward(request, response);
            } else {
                String error = "User does not exists";
                response.sendRedirect(request.getContextPath() + "/views/user/login.jsp?error=" + error);
            }
        } else {

            HttpSession session = request.getSession();

            session.setAttribute("user", user);
            // Redirect user based on role
            if ("admin".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else if ("staff".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/staff");
            } else if ("member".equalsIgnoreCase(user.getRole())) {
                if (!date.equals("") && !time.equals("") && !movieId.equals("")) {
                    response.sendRedirect(request.getContextPath() + "/booking?id=" + movieId + "&date=" + date + "&time=" + time);
                    return;
                }
                System.out.println("LOGGED IN");
                response.sendRedirect(request.getContextPath() + "/member"); // default for member 
            } else {
                String error = "User does not exists";
                response.sendRedirect(request.getContextPath() + "/login?error=" + error);
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
