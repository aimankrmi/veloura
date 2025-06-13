/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package com.velouracinema.listener;

import com.velouracinema.dao.booking.BookingDAO;
import java.sql.*;
import com.velouracinema.util.DBUtil;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.TimerTask;
import java.util.concurrent.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Aiman
 */
public class BookingCleanupTaskListener implements ServletContextListener {

    private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                BookingDAO.removePendingBooking();
            }
        }, 0, 5 * 60 * 1000); // Every 5 minutes
    }

//    private void markBookingAsExpired(Connection conn, int bookingId) throws SQLException {
//        // 1. Set seats as available
//        String updateSeats = "UPDATE seats SET is_available = TRUE WHERE id IN "
//                + "(SELECT seat_id FROM booking_seats WHERE booking_id = ?)";
//        try (PreparedStatement seatStmt = conn.prepareStatement(updateSeats)) {
//            seatStmt.setInt(1, bookingId);
//            seatStmt.executeUpdate();
//        }
//
//        // 2. Delete related payment
//        String deletePayment = "DELETE FROM payments WHERE booking_id = ?";
//        try (PreparedStatement payStmt = conn.prepareStatement(deletePayment)) {
//            payStmt.setInt(1, bookingId);
//            payStmt.executeUpdate();
//        }
//
//        // 3. Mark booking as expired
//        String updateBooking = "UPDATE bookings SET status = 'expired' WHERE id = ?";
//        try (PreparedStatement bookStmt = conn.prepareStatement(updateBooking)) {
//            bookStmt.setInt(1, bookingId);
//            bookStmt.executeUpdate();
//        }
//    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (timer != null) {
            timer.cancel();
        }
    }
}
