/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.velouracinema.controller.movie;

import com.velouracinema.dao.movie.MovieDAO;
import com.velouracinema.model.Movie;
import com.velouracinema.model.User;
import com.velouracinema.util.Utils;
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
public class ManageMovieServlet extends HttpServlet {

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
        String movieIdString;
        String title;
        String genre;
        String language;
        String durationString;
        String releaseDateString;
        String priceString;
        String description;
        String imagePath;
        int status;
        Movie movie;

        if(!Utils.authorizeUser(request, response, "staff")){
            response.sendError(401);
            return;
        }
        

        switch (path) {

            case "/viewMovies":
                List<Movie> movies = MovieDAO.getAllMovies();
                request.setAttribute("movies", movies);
                request.getRequestDispatcher("WEB-INF/views/staff/manage-movies.jsp").forward(request, response);
                break;
            case "/addMovie":

                title = request.getParameter("movieTitle");
                genre = String.join(",", request.getParameterValues("genre"));
                language = request.getParameter("language");
                durationString = request.getParameter("duration");
                releaseDateString = request.getParameter("releaseDate");
                priceString = request.getParameter("price");
                description = request.getParameter("description");
                imagePath = request.getParameter("imagePath");

                movie = new Movie();
                try {
                    int duration = Integer.parseInt(durationString);
                    double price = Double.parseDouble(priceString);

                    movie.setTitle(title);
                    movie.setGenre(genre);
                    movie.setLanguage(language);
                    movie.setDuration(duration);
                    movie.setReleaseDateFromString(releaseDateString);
                    movie.setPrice(price);
                    movie.setDescription(description);
                    movie.setImagePath(imagePath);
                    status = MovieDAO.insertMovie(movie);
                    if (status == 0) {
                        System.out.println("MOVIE INSERT FAILED");
                    } else {
                        System.out.println("MOVIE INSERT SUCCESS");
                    }
                    response.sendRedirect(request.getContextPath() + "/viewMovies");
                } catch (Exception e) {
                }
                break;

            case "/editMovie":

                movieIdString = request.getParameter("movieId");
                title = request.getParameter("movieTitle");
                genre = String.join(",", request.getParameterValues("genre"));
                language = request.getParameter("language");
                durationString = request.getParameter("duration");
                releaseDateString = request.getParameter("releaseDate");
                priceString = request.getParameter("price");
                description = request.getParameter("description");
                imagePath = request.getParameter("imagePath");

                movie = new Movie();
                try {
                    int id = Integer.parseInt(movieIdString);
                    int duration = Integer.parseInt(durationString);
                    double price = Double.parseDouble(priceString);
                    movie.setMovieId(id);
                    movie.setTitle(title);
                    movie.setGenre(genre);
                    movie.setLanguage(language);
                    movie.setDuration(duration);
                    movie.setReleaseDateFromString(releaseDateString);
                    movie.setPrice(price);
                    movie.setDescription(description);
                    movie.setImagePath(imagePath);

                    status = MovieDAO.updateMovie(movie);

                    if (status == 0) {
                        System.out.println("MOVIE UPDATE FAILED");
                    } else {
                        System.out.println("MOVIE UPDATE SUCCESS");
                    }

                    response.sendRedirect(request.getContextPath() + "/viewMovies");

                } catch (Exception e) {
                }
                break;

            case "/deleteMovie":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    status = MovieDAO.deleteMovieById(id);

                    if (status == 0) {
                        System.out.println("Movie delete failed.");
                    } else {
                        System.out.println("Movie delete successful.");
                    }

                    response.sendRedirect(request.getContextPath() + "/viewMovies");
                } catch (NumberFormatException e) {
                    System.out.println("Movie ID does not found.");
                }
                break;

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
