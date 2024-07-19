package com.backend.Artview.domain.MyReviews.service;


import com.backend.Artview.domain.MyReviews.domain.MyExhibitionImages;
import com.backend.Artview.domain.MyReviews.domain.MyReviews;
import com.backend.Artview.domain.MyReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.MyReviews.repository.MyReviewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyReviewsServiceImpl implements MyReviewsService {

    private final MyReviewsRepository myReviewsRepository;

    @Override
    public List<AllMyReviewsResponseDto> findAllMyReviews(Long userId) {
        List<MyReviews> allMyReviewsFromRepository = findAllMyReviewsFromRepository(userId);
        return allMyReviewsFromRepository.stream().map(v -> AllMyReviewsResponseDto.of(v, findMyReviewsMainImages(v))).collect(Collectors.toList());
    }

    public List<MyReviews> findAllMyReviewsFromRepository(Long userId) {
        return myReviewsRepository.findAllByUsersId(userId);
    }

    public String findMyReviewsMainImages(MyReviews myReviews) {
        return findMyExhibitionImages(myReviews).stream().filter(v -> v.isIsMainImage()).toString();
    }

    public List<MyExhibitionImages> findMyExhibitionImages(MyReviews myReviews) {
        return myReviews.getMyExhibitionImages();
    }
}
