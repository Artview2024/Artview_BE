package com.backend.Artview.domain.MyReviews.service;


import com.backend.Artview.domain.MyReviews.domain.MyReviews;
import com.backend.Artview.domain.MyReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.MyReviews.dto.response.DetailMyReviewsResponseDto;
import com.backend.Artview.domain.MyReviews.exception.MyReviewsException;
import com.backend.Artview.domain.MyReviews.repository.MyReviewsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.backend.Artview.domain.MyReviews.exception.MyReviewsErrorCode.MY_REVIEWS_NOT_FOUND;

@Service
@RequiredArgsConstructor
 public class MyReviewsServiceImpl implements MyReviewsService {

    private final MyReviewsRepository myReviewsRepository;

    @Override
    @Transactional
    public List<AllMyReviewsResponseDto> findAllMyReviews(Long userId) {
        List<MyReviews> allMyReviewsFromRepository = findAllMyReviewsFromRepository(userId);
        return allMyReviewsFromRepository.stream().map(v -> AllMyReviewsResponseDto.of(v)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DetailMyReviewsResponseDto findDetailMyReviews(Long reviewsId) {
        MyReviews myReview = findDetailMyReviewsByReviewsId(reviewsId);
        return DetailMyReviewsResponseDto.of(myReview,myReview.getMyReviewsContents());
    }

    public MyReviews findDetailMyReviewsByReviewsId(Long reviewsId){
        return myReviewsRepository.findById(reviewsId).orElseThrow(()->new MyReviewsException(MY_REVIEWS_NOT_FOUND));
    }

    public List<MyReviews> findAllMyReviewsFromRepository(Long userId) {
        return myReviewsRepository.findAllByUsersId(userId);
    }
}
