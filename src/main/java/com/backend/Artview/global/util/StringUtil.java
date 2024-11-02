package com.backend.Artview.global.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class StringUtil {

    public LocalDate stringToLocalDate(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.");
        LocalDate otherDate=null;
        try {
            otherDate = LocalDate.parse(dateString, formatter); // String -> LocalDate 변환
        } catch (DateTimeParseException e) {
            System.out.println("날짜 형식이 올바르지 않습니다: " + e.getMessage());
        }
        return otherDate;
    }

}
