package com.backend.Artview.domain.myReviews.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


//public record MyReviewsSaveRequestDto(
//        Long id,
//        String name,
//        String date,
//        String gallery,
//        String rating,
//        List<SaveRequestArtList> artList
//
//) {
//}

@Getter
@Setter
public class MyReviewsSaveRequestDto {
    Long id;
    String name;
    String date;
    String gallery;
    MultipartFile mainImage;
    String rating;
    List<SaveRequestArtList> artList;
}
