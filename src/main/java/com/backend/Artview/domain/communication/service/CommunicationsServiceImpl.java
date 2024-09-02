package com.backend.Artview.domain.communication.service;

import com.backend.Artview.domain.communication.Repository.CommunicationsRepository;
import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.communication.domain.CommunicationImages;
import com.backend.Artview.domain.communication.domain.CommunicationsKeyword;
import com.backend.Artview.domain.communication.dto.request.CommunicationSaveRequestDto;
import com.backend.Artview.domain.communication.dto.request.CommunicationsCommentRequestDto;
import com.backend.Artview.domain.communication.dto.response.CommunicationRetrieveResponseDto;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import com.backend.Artview.domain.myReviews.exception.MyReviewsException;
import com.backend.Artview.domain.myReviews.repository.MyReviewsRepository;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.domain.users.exception.UserException;
import com.backend.Artview.domain.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.backend.Artview.domain.myReviews.exception.MyReviewsErrorCode.MY_REVIEWS_NOT_FOUND;
import static com.backend.Artview.domain.myReviews.exception.MyReviewsErrorCode.USER_MY_REVIEWS_NOT_FOUND;
import static com.backend.Artview.domain.users.exception.UserErrorCode.*;

@Service
@RequiredArgsConstructor
public class CommunicationsServiceImpl implements CommunicationsService {

    private final MyReviewsRepository myReviewsRepository;
    private final CommunicationsRepository communicationsRepository;
    private final UsersRepository usersRepository;


    @Override
    @Transactional
    public CommunicationRetrieveResponseDto retrieveMyReviews(Long reviewsId,Long userId) {

        MyReviews myReviews = myReviewsRepository.findByIdAndUsersId(reviewsId, userId).orElseThrow(() -> new MyReviewsException(USER_MY_REVIEWS_NOT_FOUND));

        List<String> imagesList = new ArrayList<>(myReviews.getMyReviewsContents().stream().map(myReviewsContents -> myReviewsContents.getMyExhibitionImage().getMyExhibitionImagesUrl()).toList());
        imagesList.add(myReviews.getMainImageUrl());

        return CommunicationRetrieveResponseDto.of(myReviews, imagesList);
    }

    @Override
    @Transactional
    public Long saveCommunications(CommunicationSaveRequestDto dto, Long userId) {
        verifyMyReviewsIdExists(dto.myReviewId());
        Communications communications = Communications.toEntity(dto,findUsersById(userId));

        List<CommunicationImages> communicationImagesList = dto.images().stream().map(image -> CommunicationImages.toEntity(image, communications)).toList();
        List<CommunicationsKeyword> communicationsKeywordList = dto.keyword().stream().map(keyword -> CommunicationsKeyword.toEntity(keyword, communications)).toList();

        communications.addCommunicationImages(communicationImagesList);
        communications.addCommunicationsKeyword(communicationsKeywordList);

        return communicationsRepository.save(communications).getId();
    }

    @Override
    @Transactional
    public Long saveComment(CommunicationsCommentRequestDto dto, Long userId) {
        return 0L;
    }

    public Users findUsersById(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }

    public void verifyMyReviewsIdExists(Long myReviewsId){
        if (!(myReviewsRepository.existsById(myReviewsId))) throw new MyReviewsException(MY_REVIEWS_NOT_FOUND);
    }

}
