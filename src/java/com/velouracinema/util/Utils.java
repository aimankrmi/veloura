/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.velouracinema.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aiman
 */
public class Utils {
//    public class TimeCalculator {

    private Utils() {
    }

    public static int calcDurationHours(int totalMinutes) {
        int hours = totalMinutes / 60; // Calculate hours
        return hours;
    }

    public static int calcDurationMinutes(int totalMinutes) {
        int minutes = totalMinutes % 60; // Calculate hours
        return minutes;
    }

    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static String formatDate(String str) {
        try{
        LocalDate formattedDate = LocalDate.parse(str);
        // Get day from date
        int day = formattedDate.getDayOfMonth();

        // Get month from date
        Month month = formattedDate.getMonth();

        // Get year from date
        int year = formattedDate.getYear();
        
        String dayString = formattedDate.getDayOfWeek().toString();
        return String.valueOf(day) + " " + Utils.capitalize(month.toString()) + " " + String.valueOf(year) + " ("+ Utils.capitalize(dayString)+")";
        }catch(NullPointerException ex){
        }
        return "";
    }

    public static String formatHours(String str){
        LocalTime time = LocalTime.parse(str);
        
//        int hour = time.getHour();
//        int minute = time.getMinute();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
            
        return time.format(formatter);
        
    }
//    public static List<String> formatDate(List<String> date){
//        List<String> formattedDate = new ArrayList();
//        
//        formattedDate.f
//        
//        return formattedDate;
//    }
    
}
