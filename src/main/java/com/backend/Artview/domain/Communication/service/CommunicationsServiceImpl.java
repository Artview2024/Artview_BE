package com.backend.Artview.domain.Communication.service;

import com.backend.Artview.domain.Communication.dto.CommunicationRetrieveResponseDto;
import com.backend.Artview.domain.MyReviews.domain.MyReviews;
import com.backend.Artview.domain.MyReviews.exception.MyReviewsException;
import com.backend.Artview.domain.MyReviews.repository.MyReviewsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static com.backend.Artview.domain.MyReviews.exception.MyReviewsErrorCode.MY_REVIEWS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommunicationsServiceImpl implements CommunicationsService{

    private final MyReviewsRepository myReviewsRepository;


    @Override
    @Transactional
    public CommunicationRetrieveResponseDto retrieveMyReviews(@PathVariable Long reviewsId) {

        if(!(myReviewsRepository.existsById(reviewsId)))
            throw new MyReviewsException(MY_REVIEWS_NOT_FOUND);

        Long userId = 10001L;
        MyReviews myReviews = myReviewsRepository.findByIdAndUsersId(reviewsId,userId).orElseThrow();

        List<String> imagesList = new ArrayList<>(myReviews.getMyReviewsContents().stream().map(myReviewsContents -> myReviewsContents.getMyExhibitionImage().getMyExhibitionImagesUrl()).toList());
        imagesList.add(myReviews.getMainImageUrl());

        return CommunicationRetrieveResponseDto.of(myReviews,imagesList);
    }

}
