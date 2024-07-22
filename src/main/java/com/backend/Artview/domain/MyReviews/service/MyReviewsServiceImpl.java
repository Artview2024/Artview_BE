package com.backend.Artview.domain.MyReviews.service;


import com.backend.Artview.domain.MyReviews.domain.MyExhibitionImages;
import com.backend.Artview.domain.MyReviews.domain.MyReviews;
import com.backend.Artview.domain.MyReviews.domain.MyReviewsContents;
import com.backend.Artview.domain.MyReviews.dto.ArtList;
import com.backend.Artview.domain.MyReviews.dto.request.MyReviewsSaveReqeustDto;
import com.backend.Artview.domain.MyReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.MyReviews.dto.response.DetailMyReviewsResponseDto;
import com.backend.Artview.domain.MyReviews.exception.MyReviewsException;
import com.backend.Artview.domain.MyReviews.repository.MyReviewsRepository;
import com.backend.Artview.domain.users.exception.UserException;
import com.backend.Artview.domain.users.repository.UsersRepository;
import com.backend.Artview.domain.users.domain.Users;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.backend.Artview.domain.MyReviews.exception.MyReviewsErrorCode.MY_REVIEWS_NOT_FOUND;
import static com.backend.Artview.domain.users.exception.UserErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyReviewsServiceImpl implements MyReviewsService {

    private final MyReviewsRepository myReviewsRepository;
    private final UsersRepository usersRepository;

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
        return DetailMyReviewsResponseDto.of(myReview, myReview.getMyReviewsContents());
    }

    @Override
    @Transactional
    public Long saveMyReviews(MyReviewsSaveReqeustDto requestDto) {
        Users user = usersRepository.findById(requestDto.id()).orElseThrow(() -> new UserException(USER_NOT_FOUND));
        MyReviews myReviews = MyReviews.toEntity(requestDto, user); //생성
        requestDto.artList().stream().map(artList->{
            MyReviewsContents myReviewsContents = addMyReviewsContentToMyReviews(myReviews, artList);
            return addMyExhibitionImagesToMyReviewsContent(artList,myReviewsContents);
        }).collect(Collectors.toList());
        return myReviewsRepository.save(myReviews).getId();
    }

    public MyReviewsContents addMyReviewsContentToMyReviews(MyReviews myReviews, ArtList artList) {
        MyReviewsContents myReviewsContents = MyReviewsContents.toEntity(myReviews, artList); //MyReviewsContents 엔티티 생성
        myReviews.addContents(myReviewsContents);
        myReviewsContents.belongsToMyReviews(myReviews);
        return myReviewsContents;
    }

    public boolean addMyExhibitionImagesToMyReviewsContent(ArtList artList, MyReviewsContents myReviewsContents) {
        MyExhibitionImages myExhibitionImages = MyExhibitionImages.toEntity(artList.image(), myReviewsContents);
        myReviewsContents.addImages(myExhibitionImages);
        return true;
    }


    public MyReviews findDetailMyReviewsByReviewsId(Long reviewsId) {
        return myReviewsRepository.findById(reviewsId).orElseThrow(() -> new MyReviewsException(MY_REVIEWS_NOT_FOUND));
    }

    public List<MyReviews> findAllMyReviewsFromRepository(Long userId) {
        return myReviewsRepository.findAllByUsersId(userId);
    }
}
