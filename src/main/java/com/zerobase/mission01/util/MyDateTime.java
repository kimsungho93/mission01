package com.zerobase.mission01.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MyDateTime {
    public static String getNow() {
        return LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()).toString();
    }
}
