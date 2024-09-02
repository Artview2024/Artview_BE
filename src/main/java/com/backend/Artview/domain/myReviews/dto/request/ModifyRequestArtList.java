package com.backend.Artview.domain.myReviews.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ModifyRequestArtList{
    MultipartFile addImage;
    String image;
    String title;
    String artist;
    String contents;
}

