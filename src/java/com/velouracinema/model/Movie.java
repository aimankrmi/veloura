package com.velouracinema.model;

/**
 *
 * @author sitif
 */
import com.velouracinema.dao.MovieDAO;
import com.velouracinema.util.Utils;

public class Movie implements java.io.Serializable {

    private int movieId;
    private String title;
    private String genre;
    private int duration;
    private String description;
    private String language;
    private double price;
    private String imagePath;

    // Getters and Setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getPreviousMovieId() {
        if (this.movieId > 1) {
            return this.movieId - 1;
        } else {
            return MovieDAO.getAllMovies().size();
        }
    }

    public int getNextMovieId() {
        if (this.movieId + 1 > MovieDAO.getAllMovies().size()) {
            return 1;
        } else {
            return this.movieId + 1;
        }
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
    return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getHours() {
        return Utils.calcDurationHours(this.duration);
    }

    public int getMinutes() {
        return Utils.calcDurationMinutes(this.duration);
    }

}
