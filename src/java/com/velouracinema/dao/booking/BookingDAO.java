/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.velouracinema.dao.booking;

import com.velouracinema.dao.payment.PaymentDAO;
import com.velouracinema.model.Booking;
import com.velouracinema.model.Payment;
import com.velouracinema.model.Seat;
import com.velouracinema.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aiman
 */
public class BookingDAO {

    public static List<Booking> getAllBookings() {

        List<Booking> bookings = new ArrayList<>();

        String sql = "SELECT "
                + "b.id AS booking_id,"
                + "b.showtime_id AS showtime_id,"
                + "u.id AS member_id,"
                + "b.booking_date as booking_date,"
                + "b.status AS booking_status,"
                + "p.payment_method AS payment_method,"
                + "p.amount AS payment_amount,"
                + "p.status AS payment_status"
                + " FROM ((bookings b INNER JOIN payments p ON b.id = p.booking_id) INNER JOIN users u ON b.member_id = u.id) WHERE b.status = 'confirmed';";
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Booking booking = new Booking();
                Payment payment = new Payment();
                booking.setId(rs.getInt("booking_id"));
                booking.setShowtimeId(rs.getInt("showtime_id"));
                booking.setMemberId(rs.getInt("member_id"));
                booking.setBookingDate(rs.getDate("booking_date"));
                booking.setStatus(rs.getString("booking_status"));

                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setStatus(rs.getString("payment_status"));
                payment.setAmount(rs.getDouble("payment_amount"));

                booking.setPayment(payment);

                bookings.add(booking);
            }
        } catch (SQLException ex) {
        }
        return bookings;
    }

    public static Booking getBookingById(int bookingId, int memberId) {
        String sql = "SELECT * FROM bookings WHERE id = ? AND member_id = ?";
        Connection conn = null;
        Booking booking = new Booking();

        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookingId);
            stmt.setInt(2, memberId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                booking.setId(rs.getInt("id"));
                booking.setMemberId(rs.getInt("member_id"));
                booking.setShowtimeId(rs.getInt("showtime_id"));
                booking.setBookingDate(rs.getDate("booking_date"));
                booking.setStatus(rs.getString("status"));
            }
            booking.setPayment(PaymentDAO.getPaymentByBookingId(bookingId));
            List<Seat> seats = new ArrayList<>();
            for (Integer seatId : BookingDAO.getBookedSeatByBooking(bookingId)) {
                seats.add(SeatDAO.getSeatById(seatId));
            }
            booking.setSeats(seats);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return booking;

    }

    public static int insertBooking(int memberId, int showtimeId) {
        String sql = "INSERT INTO bookings (member_id, showtime_id, booking_date, expires_at) VALUES (?, ?, NOW(), DATE_ADD(NOW(), INTERVAL 15 MINUTE))";
        Connection conn = null;
        int bookingId = -1;

        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, memberId);
            stmt.setInt(2, showtimeId);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting booking failed, no rows affected.");
            }

            // Get the generated bookingId
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    bookingId = generatedKeys.getInt(1); // Auto-incremented bookingId
                } else {
                    throw new SQLException("Inserting booking failed, no ID obtained.");
                }
            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bookingId;
    }

    public static Date getBookingDateById(int id) {
        String sql = "SELECT booking_date FROM bookings WHERE id = ?";
        Connection conn = null;
        Date bookingDate = null;

        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("booking_date");
                if (timestamp != null) {
                    bookingDate = new Date(timestamp.getTime());
                }
            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bookingDate;
    }

    // To insert booked seat (edit booking)
    public static int insertBookedSeat(int bookingId, int seatId) {
        String sql = "INSERT INTO booking_seats (booking_id, seat_id) VALUES (?, ?)";
        Connection conn = null;
        int status = 0;

        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookingId);
            stmt.setInt(2, seatId);

            status = stmt.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    // To remove booked seat (edit booking)
    public static int removeBookedSeat(int bookingId, int seatId) {
        String sql = "DELETE FROM booking_seats WHERE booking_id = ? AND seat_id ?";
        Connection conn = null;
        int status = 0;

        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookingId);
            stmt.setInt(2, seatId);

            status = stmt.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public static List<Integer> getBookedSeatByBooking(int bookingId) {
        String sql = "SELECT seat_id FROM booking_seats WHERE booking_id = ?";

        List<Integer> bookedSeats = new ArrayList<>();

        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookingId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookedSeats.add(rs.getInt("seat_id"));
            }

            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookedSeats;
    }

    // Update booking status to confirm
    public static int updateBookingStatus(int bookingId) {
        String sql = "UPDATE bookings SET status = 'confirmed' WHERE id = ?";
        int status = 0;

        Connection conn = null;
        try {

            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookingId);

            status = stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    // To list the booking history
    public static List<Booking> getBookingByMemberId(int memberId) {
        String sql = "SELECT * FROM bookings WHERE member_id = ?";
        List<Booking> bookings = new ArrayList<>();

        Connection conn = null;

        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setMemberId(rs.getInt("member_id"));
                booking.setShowtimeId(rs.getInt("showtime_id"));
                booking.setStatus(rs.getString("status"));
                booking.setBookingDate(rs.getDate("booking_date"));

                booking.setPayment(PaymentDAO.getPaymentByBookingId(rs.getInt("id")));
                List<Seat> seats = new ArrayList<>();
                for (Integer seatId : BookingDAO.getBookedSeatByBooking(rs.getInt("id"))) {
                    seats.add(SeatDAO.getSeatById(seatId));
                }
                booking.setSeats(seats);

                bookings.add(booking);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookings;
    }

    public static int removeBookedSeatsByBookingId(int bookingId) {
        String sql = "DELETE FROM booking_seats WHERE booking_id = ?";

        int status = 0;
        Connection conn = null;

        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookingId);

            status = stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;

    }

}
