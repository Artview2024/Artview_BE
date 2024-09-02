package com.backend.Artview.domain.myReviews.service;


import com.backend.Artview.domain.myReviews.domain.MyExhibitionImages;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import com.backend.Artview.domain.myReviews.domain.MyReviewsContents;
import com.backend.Artview.domain.myReviews.dto.request.ModifyRequestArtList;
import com.backend.Artview.domain.myReviews.dto.request.SaveRequestArtList;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsModifyRequestDto;
import com.backend.Artview.domain.myReviews.dto.request.MyReviewsSaveRequestDto;
import com.backend.Artview.domain.myReviews.dto.response.AllMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.dto.response.DetailMyReviewsResponseDto;
import com.backend.Artview.domain.myReviews.exception.MyReviewsException;
import com.backend.Artview.domain.myReviews.repository.MyReviewsRepository;
import com.backend.Artview.domain.users.exception.UserException;
import com.backend.Artview.domain.users.repository.UsersRepository;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.global.util.S3Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.backend.Artview.domain.myReviews.exception.MyReviewsErrorCode.MY_REVIEWS_NOT_FOUND;
import static com.backend.Artview.domain.users.exception.UserErrorCode.USER_NOT_FOUND;
import static java.rmi.server.LogStream.log;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyReviewsServiceImpl implements MyReviewsService {

    private final MyReviewsRepository myReviewsRepository;
    private final UsersRepository usersRepository;
    private final S3Util s3Util;

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

//    @Override
//    @Transactional
//    public Long saveMyReviews(MyReviewsSaveRequestDto requestDto, MultipartFile mainImage, List<MultipartFile> contentImages) {
//
//        Users user = usersRepository.findById(requestDto.id()).orElseThrow(() -> new UserException(USER_NOT_FOUND));
//        MyReviews myReviews = MyReviews.toEntity(requestDto, uploadImageUrlToS3(mainImage) ,user);
//
//        for(int i = 0; i<requestDto.artList().size(); i++) {
//            MyReviewsContents myReviewsContents = addMyReviewsContentToMyReviews(myReviews, requestDto.artList().get(i));
//            addMyExhibitionImagesToMyReviewsContent(myReviewsContents,contentImages.get(i));
//        }
//
//        return myReviewsRepository.save(myReviews).getId();
//    }

    @Override
    @Transactional
    public Long saveMyReviews(MyReviewsSaveRequestDto requestDto) {

        Users user = usersRepository.findById(requestDto.getId()).orElseThrow(() -> new UserException(USER_NOT_FOUND));
        MyReviews myReviews = MyReviews.toEntity(requestDto, uploadImageUrlToS3(requestDto.getMainImage()) ,user);

        for(int i = 0; i<requestDto.getArtList().size(); i++) {
            MyReviewsContents myReviewsContents = addMyReviewsContentToMyReviews(myReviews, requestDto.getArtList().get(i));
            addMyExhibitionImagesToMyReviewsContent(myReviewsContents, requestDto.getArtList().get(i).getImage());
        }

        return myReviewsRepository.save(myReviews).getId();
    }

//    @Override
//    @Transactional
//    public void refactorMyReviews(MyReviewsModifyRequestDto requestDto, MultipartFile mainImage, List<MultipartFile> contentImages) {
//        MyReviews myReviews = findDetailMyReviewsByReviewsId(requestDto.myReviewsId());
//        List<SaveRequestArtList> artLists = requestDto.artList();
//
//        updateAccordingToType(myReviews,artLists,contentImages); //update타입에 따라 update를 진행
//        myReviews.updateMyReviews(requestDto,uploadImageUrlToS3(mainImage));
//    }

    @Override
    @Transactional
    public void refactorMyReviews(MyReviewsModifyRequestDto requestDto) {
        MyReviews myReviews = findDetailMyReviewsByReviewsId(requestDto.getMyReviewsId());
        List<ModifyRequestArtList> artLists = requestDto.getArtList();

        updateAccordingToType(myReviews,artLists); //update타입에 따라 update를 진행
        myReviews.updateMyReviews(requestDto,uploadImageUrlToS3(requestDto.getMainImage()));
    }

    private String uploadImageUrlToS3(MultipartFile multipartFileImage) {
        if(!multipartFileImage.isEmpty()) {
            return s3Util.uploadFileToS3Bucket(multipartFileImage);
        }
        else return null;
    }

    private void updateAccordingToType(MyReviews myReviews, List<ModifyRequestArtList> artLists){
        if(myReviews.getMyReviewsContents().size() == artLists.size()){ //content를 수정만 하는 경우
            updateContents(myReviews.getMyReviewsContents(), artLists);
        } else saveAllMyContentsAndMyExhibitionImages(myReviews,artLists); //content를 수정 + 추가하는 경우 -> 추가하는 건 multipartFIle 저장필요
    }

    private void saveAllMyContentsAndMyExhibitionImages(MyReviews myReviews, List<ModifyRequestArtList> artLists){
        for (int i = myReviews.getMyReviewsContents().size(); i < artLists.size(); i++) {
            MyReviewsContents myReviewsContents = addMyReviewsContentToMyReviews(myReviews, artLists.get(i));
            addMyExhibitionImagesToMyReviewsContent(myReviewsContents, artLists.get(i).getAddImage());
        }
    }

    private void updateContents(List<MyReviewsContents> myReviewsContents, List<ModifyRequestArtList> artLists) {
        for (int i = 0; i <myReviewsContents.size(); i++) {
            MyReviewsContents myReviewsContent = myReviewsContents.get(i);
            myReviewsContent.updateMyReviewsContents(artLists.get(i));
            if (artLists.get(i).getAddImage() != null) { //추가된 이미지가 있는 경우 추가
                addMyExhibitionImagesToMyReviewsContent(myReviewsContent, artLists.get(i).getAddImage());
            }
        }
    }

    public <T> MyReviewsContents addMyReviewsContentToMyReviews(MyReviews myReviews, T artList) {
        MyReviewsContents myReviewsContents = MyReviewsContents.toEntity(myReviews, artList); //MyReviewsContents 엔티티 생성
        myReviews.addContents(myReviewsContents);
        myReviewsContents.belongsToMyReviews(myReviews);
        return myReviewsContents;
    }

    public void addMyExhibitionImagesToMyReviewsContent(MyReviewsContents myReviewsContents, MultipartFile contentImages) {
        System.out.println("addMyExhibitionImagesToMyReviewsContent 진입 : "+contentImages.getOriginalFilename());
        MyExhibitionImages myExhibitionImages = MyExhibitionImages.toEntity(uploadImageUrlToS3(contentImages), myReviewsContents);
        myReviewsContents.addImages(myExhibitionImages);
    }

    public MyReviews findDetailMyReviewsByReviewsId(Long reviewsId) {
        return myReviewsRepository.findById(reviewsId).orElseThrow(() -> new MyReviewsException(MY_REVIEWS_NOT_FOUND));
    }

    public List<MyReviews> findAllMyReviewsFromRepository(Long userId) {
        return myReviewsRepository.findAllByUsersId(userId);
    }
}
