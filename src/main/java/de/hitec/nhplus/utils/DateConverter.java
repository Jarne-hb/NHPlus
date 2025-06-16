package de.hitec.nhplus.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Utility class for converting between {@link String}, {@link LocalDate}, and {@link LocalTime}.
 * <p>
 * Uses standard date and time formats:
 * <ul>
 *     <li>Date format: <code>yyyy-MM-dd</code></li>
 *     <li>Time format: <code>HH:mm</code></li>
 * </ul>
 * </p>
 */
public class DateConverter {

    /**
     * The standard format used for converting dates: <code>yyyy-MM-dd</code>.
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * The standard format used for converting times: <code>HH:mm</code>.
     */
    private static final String TIME_FORMAT = "HH:mm";

    /**
     * Converts a date string to a {@link LocalDate} using the predefined date format.
     *
     * @param date the date string in the format <code>yyyy-MM-dd</code>
     * @return the corresponding <code>LocalDate</code> object
     * @throws java.time.format.DateTimeParseException if the input string is not properly formatted
     */
    public static LocalDate convertStringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Converts a time string to a {@link LocalTime} using the predefined time format.
     *
     * @param time the time string in the format <code>HH:mm</code>
     * @return the corresponding <code>LocalDate</code> object
     * @throws java.time.format.DateTimeParseException if the input string is not properly formatted
     */
    public static LocalTime convertStringToLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    /**
     * Converts a {@link LocalDate} object to a string using the predefined date format.
     *
     * @param date the <code>LocalDate</code> to be formatted
     * @return the formatted date string in the format <code>yyyy-MM-dd</code>
     */
    public static String convertLocalDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Converts a {@link LocalTime} object to a string using the predefined time format.
     *
     * @param time the <code>LocalDate</code> to be formatted
     * @return the formatted time string in the format <code>HH:mm</code>
     */
    public static String convertLocalTimeToString(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }
}
