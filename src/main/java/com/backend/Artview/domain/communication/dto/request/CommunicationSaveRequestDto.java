package com.backend.Artview.domain.communication.dto.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record CommunicationSaveRequestDto(
        Long myReviewId,
        String name, //전시회명
        String rate, //별점
        String date,  //방문날짜
        String gallery, //장소명
        Map<String,String> imageAndTitle,
//        List<String> imageTitle,
        String content, //글쓰기 내용
        List<String> keyword //감상 키워드
) {
}
