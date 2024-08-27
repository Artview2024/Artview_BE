package com.backend.Artview.domain.myReviews.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TestDto {
    Long id;
    String name;
    String date;
    String gallery;
    MultipartFile mainImage;
    String rating;
//    List<RequestArtList> artList;
}

