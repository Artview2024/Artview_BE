package com.backend.Artview.domain.exhibition.service;

import com.backend.Artview.domain.communication.Repository.CommunicationsRepository;
import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import com.backend.Artview.domain.exhibition.dto.response.ExhibitionDetailInfoResponseDto;
import com.backend.Artview.domain.exhibition.dto.response.ExhibitionDetailReviewResponseDto;
import com.backend.Artview.domain.exhibition.dto.response.ExhibitionInfo;
import com.backend.Artview.domain.exhibition.dto.response.ExhibitionResponseDto;
import com.backend.Artview.domain.exhibition.exception.ExhibitionException;
import com.backend.Artview.domain.exhibition.repository.CrawlingExhibitionRepository;
import com.backend.Artview.global.util.PaginationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.backend.Artview.domain.exhibition.domain.ExhibitionType.*;
import static com.backend.Artview.domain.exhibition.exception.ExhibitionErrorCode.EXHIBITION_NOT_FOUND;
import static com.backend.Artview.domain.exhibition.exception.ExhibitionErrorCode.EXHIBITION_REVIEWS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements ExhibitionService {

    private final CrawlingExhibitionRepository crawlingExhibitionRepository;
    private final CommunicationsRepository communicationsRepository;
    private final PaginationUtil paginationUtil;
    private final int DEFAULT_PAGE_SIZE = 6;

    @Override
    @Transactional
    public ExhibitionResponseDto findOngoingExhibition(Long cursor) {
        return findExhibitionsByType(cursor, ONGOING.getCode());
    }

    @Override
    @Transactional
    public ExhibitionResponseDto findFreeExhibition(Long cursor) {
        return findExhibitionsByType(cursor, FREE.getCode());
    }

    @Override
    @Transactional
    public ExhibitionDetailInfoResponseDto findExhibitionDetailInfo(Long exhibitionId) {
        CrawlingExhibition exhibition = findExhibitionById(exhibitionId);
        return ExhibitionDetailInfoResponseDto.of(exhibition);
    }


    @Override
    @Transactional
    public List<ExhibitionDetailReviewResponseDto> findExhibitionDetailReview(Long exhibitionId) {

        List<Communications> communications = findCommunicationsByExhibitionId(exhibitionId);
        return communications.stream().map(ExhibitionDetailReviewResponseDto::of).collect(Collectors.toList());
    }

    private List<Communications> findCommunicationsByExhibitionId(Long exhibitionId) {
        return communicationsRepository.findAllByCrawlingExhibitionId(exhibitionId);
    }

    private CrawlingExhibition findExhibitionById(Long exhibitionId) {
        return crawlingExhibitionRepository.findById(exhibitionId).orElseThrow(() -> new ExhibitionException(EXHIBITION_NOT_FOUND));
    }

    private ExhibitionResponseDto findExhibitionsByType(Long cursor, String progressType) {
        PageRequest pageRequest = createPageRequest();

        Slice<CrawlingExhibition> crawlingExhibitionList = cursor == 0 ? crawlingExhibitionRepository.findCrawlingExhibitionTopByProgressTypeOrderByStartDateDesc(pageRequest, progressType)
                : crawlingExhibitionRepository.findCrawlingExhibitionByCursorTopByAndProgressTypeOrderByStartDateDesc(cursor, pageRequest, progressType);

        List<ExhibitionInfo> exhibitionInfoList = crawlingExhibitionList.stream().map(data -> ExhibitionInfo.of(data)).collect(Collectors.toList());

        Long nextCursor = checkHaveNextCursor(crawlingExhibitionList);
        return ExhibitionResponseDto.of(exhibitionInfoList, crawlingExhibitionList, nextCursor);
    }

    private PageRequest createPageRequest() {
        return paginationUtil.createPageRequest(DEFAULT_PAGE_SIZE, "id");
    }

    private Long checkHaveNextCursor(Slice<CrawlingExhibition> exhibitionsList) {
        return exhibitionsList.hasNext() ? exhibitionsList.getContent().get(exhibitionsList.getSize() - 1).getId() : null;
    }
}
