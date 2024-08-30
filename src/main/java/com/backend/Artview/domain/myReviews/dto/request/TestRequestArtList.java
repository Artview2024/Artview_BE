package com.backend.Artview.domain.myReviews.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

//@Builder
@Getter
@Setter
public class TestRequestArtList{
    MultipartFile image;
    String title;
    String artist;
    String content;
}