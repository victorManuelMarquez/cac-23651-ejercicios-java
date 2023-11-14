package ar.com.codoacodo.personasv4.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateFormatter {

    public static final String DATE_PATTERN = "dd-MM-yyyy";

    public static String localDateToString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public static LocalDate fromStringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

}
