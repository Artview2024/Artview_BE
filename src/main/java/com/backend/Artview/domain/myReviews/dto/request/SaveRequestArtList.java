package com.backend.Artview.domain.myReviews.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

//@Builder
//public record SaveRequestArtList(
//        @JsonProperty("image") MultipartFile image,
//        @JsonProperty("title") String title,
//        @JsonProperty("artist") String artist,
//        @JsonProperty("contents") String contents
//) {
//    public SaveRequestArtList(MultipartFile image, String title, String artist, String contents) {
//        this.image = image;
//        this.title = title;
//        this.artist = artist;
//        this.contents = contents;
//    }
//}

@Getter
@Setter
public class SaveRequestArtList {
    MultipartFile image;
    String title;
    String artist;
    String contents;
}
