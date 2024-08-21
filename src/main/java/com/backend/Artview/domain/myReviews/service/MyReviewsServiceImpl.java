package com.backend.Artview.domain.myReviews.service;


import com.backend.Artview.domain.myReviews.domain.MyExhibitionImages;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import com.backend.Artview.domain.myReviews.domain.MyReviewsContents;
import com.backend.Artview.domain.myReviews.dto.ArtList;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsModifyRequestDto;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsSaveReqeustDto;
import com.backend.Artview.domain.myReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.dto.response.DetailMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.exception.MyReviewsException;
import com.backend.Artview.domain.myReviews.repository.MyReviewsRepository;
import com.backend.Artview.domain.users.exception.UserException;
import com.backend.Artview.domain.users.repository.UsersRepository;
import com.backend.Artview.domain.users.domain.Users;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.backend.Artview.domain.myReviews.exception.MyReviewsErrorCode.MY_REVIEWS_NOT_FOUND;
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
        int number = 0; //전시기록 내용 number

        Users user = usersRepository.findById(requestDto.id()).orElseThrow(() -> new UserException(USER_NOT_FOUND));
        MyReviews myReviews = MyReviews.toEntity(requestDto, user); //생성

        for (ArtList artList : requestDto.artList()){
            MyReviewsContents myReviewsContents = addMyReviewsContentToMyReviews(myReviews, artList, number++);
            addMyExhibitionImagesToMyReviewsContent(artList,myReviewsContents);
        }

        return myReviewsRepository.save(myReviews).getId();
    }

    @Override
    @Transactional
    public void refactorMyReviews(MyReviewsModifyRequestDto requestDto) {
        MyReviews myReviews = findDetailMyReviewsByReviewsId(requestDto.myReviewsId());
        List<MyReviewsContents> myReviewsContents = myReviews.getMyReviewsContents();
        List<ArtList> artLists = requestDto.artList();

        checkUpdateType(myReviews, myReviewsContents, artLists);
        myReviews.updateMyReviews(requestDto);
    }

    private void checkUpdateType(MyReviews myReviews, List<MyReviewsContents> myReviewsContents, List<ArtList> artLists){
        int artListsSize = artLists.size();
        int myReviewsContentsSize = myReviewsContents.size();
        int contentNumber;

        if (myReviewsContentsSize == artListsSize){ //content를 수정만 하는 경우
            updateContents(myReviews.getMyReviewsContents(), artLists);
        }else if(myReviewsContentsSize==0){ //content를 추가만 하는 경우
            contentNumber = 1;
            saveAllMyContentsAndMyExhibitionImages(myReviews,myReviewsContentsSize,artLists,contentNumber);
        }else if(myReviewsContentsSize<artLists.size()){ //content를 수정하고 추가하는 경우
            contentNumber = myReviewsContents.get(myReviewsContentsSize-1).getNumber()+1; //마지막 번호
            saveAllMyContentsAndMyExhibitionImages(myReviews,myReviewsContentsSize,artLists,contentNumber);
        }
    }

    private void saveAllMyContentsAndMyExhibitionImages(MyReviews myReviews,int myReviewsContentsSize, List<ArtList> artLists, int contentNumber){
        for (int i = myReviewsContentsSize; i < artLists.size(); i++) {
            saveMyContentsAndMyExhibitionImages(myReviews, artLists.get(i), contentNumber++);
        }
    }

    private void updateContents(List<MyReviewsContents> myReviewsContents, List<ArtList> artLists) {
        for (int i = 0; i <myReviewsContents.size(); i++) {
            MyReviewsContents myReviewsContent = myReviewsContents.get(i);
            ArtList artList = artLists.get(i);
            myReviewsContent.getMyExhibitionImage().updateMyExhibitionImages(artList.image());
            myReviewsContent.updateMyReviewsContents(artList);
        }
    }
    public void saveMyContentsAndMyExhibitionImages(MyReviews myReviews, ArtList artList, int number){
        MyReviewsContents myReviewsContents = addMyReviewsContentToMyReviews(myReviews, artList, number++);
        addMyExhibitionImagesToMyReviewsContent(artList,myReviewsContents);
    }

    public MyReviewsContents addMyReviewsContentToMyReviews(MyReviews myReviews, ArtList artList, int contentNumber) {
        MyReviewsContents myReviewsContents = MyReviewsContents.toEntity(myReviews, artList, contentNumber); //MyReviewsContents 엔티티 생성
        myReviews.addContents(myReviewsContents);
        myReviewsContents.belongsToMyReviews(myReviews);
        return myReviewsContents;
    }

    public void addMyExhibitionImagesToMyReviewsContent(ArtList artList, MyReviewsContents myReviewsContents) {
        MyExhibitionImages myExhibitionImages = MyExhibitionImages.toEntity(artList.image(), myReviewsContents);
        myReviewsContents.addImages(myExhibitionImages);
    }

    public MyReviews findDetailMyReviewsByReviewsId(Long reviewsId) {
        return myReviewsRepository.findById(reviewsId).orElseThrow(() -> new MyReviewsException(MY_REVIEWS_NOT_FOUND));
    }

    public List<MyReviews> findAllMyReviewsFromRepository(Long userId) {
        return myReviewsRepository.findAllByUsersId(userId);
    }
}
