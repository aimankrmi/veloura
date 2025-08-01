/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.velouracinema.model;

import com.velouracinema.dao.movie.MovieDAO;

/**
 *
 * @author Aiman
 */
public class TopMovie {

    private int position;
    private int movieId;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Movie getMovie() {
        return MovieDAO.getMovieById(movieId);
    }

}
