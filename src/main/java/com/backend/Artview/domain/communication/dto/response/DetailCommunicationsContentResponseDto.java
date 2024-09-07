package com.backend.Artview.domain.communication.dto.response;

import com.backend.Artview.domain.communication.domain.CommunicationImages;
import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.communication.domain.CommunicationsKeyword;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record DetailCommunicationsContentResponseDto(
        Long communicationsId,
        List<String> contentImage,
        String name, //전시회명
        String rate, //별점
        String date, //방문날짜
        String gallery, //장소명
        String content, // 글쓰기 내용
        List<String> keyword, //감상 키워드
        boolean isHeartClicked, //좋아요 클릭 여부
        boolean isScrapClicked //스크랩 클릭 여부
) {
    public static DetailCommunicationsContentResponseDto of(Communications communications,boolean isHeartClicked, boolean isScrapClicked) {
        return DetailCommunicationsContentResponseDto.builder()
                .communicationsId(communications.getId())
                .contentImage(communications.getCommunicationImagesList().stream().map(CommunicationImages::getImageUrl).collect(Collectors.toList()))
                .name(communications.getName())
                .rate(communications.getRate())
                .date(communications.getDate())
                .gallery(communications.getGallery())
                .content(communications.getContent())
                .keyword(communications.getCommunicationsKeywordsList().stream().map(CommunicationsKeyword::getKeyword).collect(Collectors.toList()))
                .isHeartClicked(isHeartClicked)
                .isScrapClicked(isScrapClicked)
                .build();
    }
}
