package com.backend.Artview.global.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

@Component
public class StringUtil {

    public LocalDate stringToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate otherDate = null;

        try {
            otherDate = LocalDate.parse(dateString, formatter); // String -> LocalDate 변환
        } catch (DateTimeParseException e) {
            System.out.println("날짜 형식이 올바르지 않습니다: " + e.getMessage());
        }
        return otherDate;
    }

    public static boolean checkDateType(String dateString) {
        return dateString.matches("\\d{4}\\.\\d{2}\\.\\d{2}");
    }

    public static List<String> removeTextFromSentence(String sentence, String removeText) {
        return Arrays.asList(sentence.split(removeText));
    }
}
