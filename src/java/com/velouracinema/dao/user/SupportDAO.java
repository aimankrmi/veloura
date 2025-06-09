/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.velouracinema.dao;

import com.velouracinema.model.SupportMessage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SupportDAO {
    // Modify these based on your database config
    private final String jdbcURL = "jdbc:mysql://localhost:3306/cinema_db";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "admin";

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public boolean insertSupportMessage(SupportMessage msg) {
        boolean success = false;
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO support_messages (name, email_username, issue_type, message) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, msg.getName());
            ps.setString(2, msg.getEmailUsername());
            ps.setString(3, msg.getIssueType());
            ps.setString(4, msg.getMessage());

            int rows = ps.executeUpdate();
            success = rows > 0;

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
