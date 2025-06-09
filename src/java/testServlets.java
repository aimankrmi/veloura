/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.velouracinema.dao.movie.MovieDAO;
import com.velouracinema.dao.booking.SeatDAO;
import com.velouracinema.model.Movie;
import com.velouracinema.model.Seat;
import com.velouracinema.util.DBUtil;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

/**
 *
 * @author Aiman
 */
public class testServlets extends HttpServlet {

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
            out.println("<title>Servlet testServlets</title>");
            out.println("</head>");
            out.println("<body>");

           out.print(MovieDAO.getMovieById(1));
//                
            
            
            
            
            
//            String url = "jdbc:mysql://localhost:3306/cinema_db"; // Replace with your database URL
//            String username = "root"; // Replace with your MySQL username
//            String password = "aiman123"; // Replace with your MySQL password

//            try {
//            Connection connection = null;
//            List<Seat> seats = new ArrayList();
////            try {
//                seats = SeatDAO.getSeatsByShowtimes(39);
//                for(Seat seat: seats){
//                    out.println(seat.getSeatNumber() + " : " + seat.getIsAvailable());
//                }
//                
//            } catch (ClassNotFoundException e) {
//            }
//                // Load the MySQL JDBC driver (if not automatically loaded)
//                Class.forName("com.mysql.cj.jdbc.Driver");  // Not always needed, check your driver version
//
//                // Establish the connection
//                connection = DriverManager.getConnection(url, username, password);
//                connection = DBUtil.getConnection();
//
//                if (connection != null) {
//                    String sql = "SELECT * FROM seats WHERE showtime_id = ?";
//
//                    int id = 39;
//                    // Create statement
//                    PreparedStatement stmt = connection.prepareStatement(sql);
//                    stmt.setInt(1, id);
//
//                    ResultSet rs = stmt.executeQuery();
//
//                    while (rs.next()) {
//
//                        out.println(rs.getInt("showtime_id"));
//                        out.println(rs.getString("seat_number"));
//                        out.println(rs.getBoolean("is_available"));
//
////Seat seat = new Seat();
////                        seat.setShowtimeId(rs.getInt("showtime_id"));
////                        seat.setSeatNumber(rs.getString("seat_number"));
////                        seat.setIsAvailable(rs.getBoolean("is_available"));
////                        
////                        seats.add(seat);
//                    }
////                    out.println(movie.getTitle());
//
//                } else {
//                    out.println("Failed to connect to MySQL.");
//                }
//
//            } catch (SQLException e) {
//                out.println("error connecting to MySQL: " + e.getMessage());
//            } finally {
//                try {
//                    if (connection != null && !connection.isClosed()) {
//                        connection.close();
//                    }
//                } catch (SQLException e) {
//                    out.println("Error closing connection: " + e.getMessage());
//                }
//            }

            out.println("<h1>Servlet testServlets at " + request.getContextPath() + "</h1>");
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
