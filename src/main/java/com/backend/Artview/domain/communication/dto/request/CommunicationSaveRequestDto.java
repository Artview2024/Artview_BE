package com.backend.Artview.domain.communication.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CommunicationSaveRequestDto(
        Long myReviewId,
        String name, //전시회명
        String rate, //별점
        String date,  //방문날짜
        String gallery, //장소명
        List<String> images,
        String content, //글쓰기 내용
        List<String> keyword //감상 키워드
) {
}
