package com.backend.Artview.domain.myReviews.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

//@Builder
//public record TestDto(
//    @JsonProperty("id") Long id,
//    @JsonProperty("name") String name,
//    @JsonProperty("date") String date,
//    @JsonProperty("gallery") String gallery,
//    @JsonProperty("mainImage") MultipartFile mainImage,
//    @JsonProperty("rating") String rating,
//    @JsonProperty("artList") List<RequestArtList> artList){
//
////    public TestDto(Long id, String name, String date, String gallery, MultipartFile mainImage, String rating, List<RequestArtList> artList){
////        this.id = id;
////        this.name = name;
////        this.date = date;
////        this.gallery = gallery;
////        this.mainImage = mainImage;
////        this.rating = rating;
////        this.artList = artList;
////    }
//}

@Getter
@Setter
public class TestDto {
        Long id;
        String name;
        String date;
        String gallery;
        MultipartFile mainImage;
        String rating;
        List<TestRequestArtList> artList;
}

