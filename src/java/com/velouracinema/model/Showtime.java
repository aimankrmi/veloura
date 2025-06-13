/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.velouracinema.model;

import com.velouracinema.dao.booking.SeatDAO;
import static com.velouracinema.dao.booking.SeatDAO.getSeatsByShowtimes;
import com.velouracinema.dao.booking.ShowtimeDAO;
import com.velouracinema.util.Utils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aiman
 */
public class Showtime implements java.io.Serializable {

    private int id;
//    private int movieId;
    private String showDate;
    private String showTime;
    private String screen;
    private Movie movie;
    List<Seat> seats = new ArrayList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

//    public int getMovieId() {
//        return movieId;
//    }
//
//    public void setMovieId(int movieId) {
//        this.movieId = movieId;
//    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getFormattedShowDate() {
        return Utils.formatDate(this.showDate);
    }
    public String getFormattedShowTime() {
        return Utils.formatTime(this.showTime);
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {

        this.showDate = showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        Showtime st = ShowtimeDAO.getShowtimeByMovieId(this.getMovie().getMovieId(), this.getShowDate(), showTime);
        this.id = st.getId();
        this.screen = st.getScreen();
        this.seats = SeatDAO.getSeatsByShowtimes(this.id);
        this.showTime = showTime;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Map<String, List<Seat>> getSeatRowByShowtime() {

        Map<String, List<Seat>> seatMap = new LinkedHashMap<>();

        for (Seat seat : SeatDAO.getSeatsByShowtimes(this.id)) {
            String row = seat.getSeatNumber().substring(0, 1);
            seatMap.computeIfAbsent(row, k -> new ArrayList<>()).add(seat);
        }

        return seatMap;

    }

    // Return the original showdates
    public List<String> getShowDates() {
        return ShowtimeDAO.getMovieDatesById(this.movie.getMovieId());
    }

//    Return the formatted the showdates
    public List<String> getFormattedShowDates() {
        List<String> formattedDatesList = new ArrayList();

        for (String date : this.getShowDates()) {
            formattedDatesList.add(Utils.formatDate(date));
        }

        return formattedDatesList;
    }

//    This method return the time for a movie and a date
    public List<String> getShowTimesByDate() {
        return ShowtimeDAO.getMovieTimeById(this.movie.getMovieId(), this.showDate);
    }

//    This method return the formatted time for a movie and a date
    public List<String> getFormattedShowTimes() {
        List<String> formattedShowTimes = new ArrayList();

        for (String time : this.getShowTimesByDate()) {
            formattedShowTimes.add(Utils.formatTime(time));
        }
        return formattedShowTimes;
    }

    public boolean isUpcoming() {
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try {
            LocalDate date = LocalDate.parse(showDate, dateFormatter);
            LocalTime time = LocalTime.parse(showTime, timeFormatter);
            LocalDateTime showDateTime = LocalDateTime.of(date, time);
            
            return showDateTime.isAfter(LocalDateTime.now());
        } catch (Exception e) {
            return false;
        }
    }

}
