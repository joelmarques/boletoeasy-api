package com.boletoeasy.util;

import com.boletoeasy.dto.Boleto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class BoletoUtils {

    public static String getCurrentDate() {
        return BoletoUtils.format(LocalDate.now());
    }

    public static String format(LocalDate localDate) {
        return DateTimeFormatter.ofPattern(Boleto.DATE_PATTERN).format(localDate);
    }

    public static LocalDate parse(String dateAsString) {
        return LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern(Boleto.DATE_PATTERN));
    }
}
