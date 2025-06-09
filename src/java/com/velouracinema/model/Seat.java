/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.velouracinema.model;

import static com.velouracinema.dao.booking.SeatDAO.getSeatsByShowtimes;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aiman
 */
public class Seat implements java.io.Serializable {

    int seatId;
    int showtimeId;
    String seatNumber;
    boolean isAvailable;

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    
    
    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public static Map<String, List<Seat>> getSeatRowByShowtime(int showtimeId) {

        Map<String, List<Seat>> seatMap = new LinkedHashMap<>();

        for (Seat seat : getSeatsByShowtimes(showtimeId)) {
            String row = seat.getSeatNumber().substring(0, 1);
            seatMap.computeIfAbsent(row, k -> new ArrayList<>()).add(seat);
        }

        return seatMap;

    }
//    public String getSeatStatus(Seat seat){
//        
//    }
//    
}
