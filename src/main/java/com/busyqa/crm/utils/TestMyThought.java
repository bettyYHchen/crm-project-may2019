package com.busyqa.crm.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TestMyThought {

    public static void main(String[] args) {
        Child c = new Child(1,"hello");
        Parent p = c;
        System.out.println((p));
        System.out.println(120/6);

        //
        LocalDateTime myDateObj = LocalDateTime.now();
        LocalDateTime next2Week = myDateObj.plus(2, ChronoUnit.WEEKS);
        System.out.println("Before formatting: " + next2Week );
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        String formattedDate = next2Week.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);

        String dateString = "2018/12/01";
        LocalDate date = LocalDate.parse(dateString, myFormatObj);
        System.out.println(date.plus(1, ChronoUnit.WEEKS).format(myFormatObj));

        LocalDate event = LocalDate.parse(dateString, myFormatObj);
        LocalDate today = LocalDate.now();
        LocalDate weekBeforeToday = today.minusWeeks(1);
        System.out.println(weekBeforeToday.compareTo(event)); // compare if event is before weekBeforeToday
    }

}

