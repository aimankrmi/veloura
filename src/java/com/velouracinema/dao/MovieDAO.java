/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.velouracinema.dao;

/**
 *
 * @author sitif
 */
import com.velouracinema.model.Movie;
import com.velouracinema.util.DBUtil;
import com.velouracinema.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieDAO {

    public static List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT * FROM movies";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setLanguage(rs.getString("language"));
                movie.setDuration(rs.getInt("duration"));
                movie.setDescription(rs.getString("description"));
                movie.setPrice(rs.getFloat("price"));
                movie.setImagePath(rs.getString("image_path"));

                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public static Movie getMovieById(int id) {
        String sql = "SELECT * FROM movies WHERE id = ?";
        Movie movie = new Movie();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                movie.setMovieId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setLanguage(rs.getString("language"));
                movie.setDuration(rs.getInt("duration"));
                movie.setDescription(rs.getString("description"));
                movie.setPrice(rs.getFloat("price"));
                movie.setImagePath(rs.getString("image_path"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        return movie;
    }

    public static double getMoviePriceById(int id) {
        String sql = "SELECT price FROM movies WHERE id = ?";
        Connection conn = null;
        double price = 0;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }

    public static List<String> getFormattedMovieDateById(int id) {
        String sql = "SELECT DISTINCT show_date FROM showtimes WHERE movie_id = ?";
        List<String> movieShowdatesList = new ArrayList<>();
        String movieDateString = "";
        try (Connection conn = DBUtil.getConnection();) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                movieShowdatesList.add(Utils.formatDate(rs.getString(1)));

            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieShowdatesList;
    }
    
    public static List<Movie> getMoviesByStatus(String status) {
    List<Movie> movies = new ArrayList<>();
    String sql = status.equalsIgnoreCase("now") 
        ? "SELECT * FROM movies WHERE release_date <= CURDATE()"
        : "SELECT * FROM movies WHERE release_date > CURDATE()";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Movie movie = new Movie();
            movie.setMovieId(rs.getInt("id"));
            movie.setTitle(rs.getString("title"));
            movie.setGenre(rs.getString("genre"));
            movie.setLanguage(rs.getString("language"));
            movie.setDuration(rs.getInt("duration"));
            movie.setDescription(rs.getString("description"));
            movie.setPrice(rs.getFloat("price"));
            movie.setImagePath(rs.getString("image_path"));
            movies.add(movie);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return movies;
}

}
