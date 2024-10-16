package com.backend.Artview.domain.myReviews.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Getter
@Setter
public class MyReviewsSaveRequestDto {
    String name;
    String date;
    String gallery;
    MultipartFile mainImage;
    String rating;
    List<SaveRequestArtList> artList;
}
