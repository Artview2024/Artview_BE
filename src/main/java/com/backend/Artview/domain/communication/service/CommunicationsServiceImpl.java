package com.backend.Artview.domain.communication.service;

import com.backend.Artview.domain.communication.Repository.CommentRepository;
import com.backend.Artview.domain.communication.Repository.CommunicationsRepository;
import com.backend.Artview.domain.communication.Repository.LikeRepository;
import com.backend.Artview.domain.communication.Repository.ScrapRepository;
import com.backend.Artview.domain.communication.domain.*;
import com.backend.Artview.domain.communication.dto.request.CommunicationSaveRequestDto;
import com.backend.Artview.domain.communication.dto.request.CommunicationsCommentRequestDto;
import com.backend.Artview.domain.communication.dto.request.LikeRequestDto;
import com.backend.Artview.domain.communication.dto.response.CommunicationRetrieveResponseDto;
import com.backend.Artview.domain.communication.dto.response.DetailCommunicationsCommentResponseDto;
import com.backend.Artview.domain.communication.dto.response.DetailCommunicationsContentResponseDto;
import com.backend.Artview.domain.communication.exception.CommunicationException;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import com.backend.Artview.domain.myReviews.exception.MyReviewsException;
import com.backend.Artview.domain.myReviews.repository.MyReviewsRepository;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.domain.users.exception.UserException;
import com.backend.Artview.domain.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.backend.Artview.domain.communication.exception.CommunicationErrorCode.*;
import static com.backend.Artview.domain.myReviews.exception.MyReviewsErrorCode.MY_REVIEWS_NOT_FOUND;
import static com.backend.Artview.domain.myReviews.exception.MyReviewsErrorCode.USER_MY_REVIEWS_NOT_FOUND;
import static com.backend.Artview.domain.users.exception.UserErrorCode.*;

@Service
@RequiredArgsConstructor
public class CommunicationsServiceImpl implements CommunicationsService {

    private final MyReviewsRepository myReviewsRepository;
    private final CommunicationsRepository communicationsRepository;
    private final UsersRepository usersRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final ScrapRepository scrapRepository;


    @Override
    @Transactional
    public CommunicationRetrieveResponseDto retrieveMyReviews(Long reviewsId, Long userId) {

        MyReviews myReviews = myReviewsRepository.findByIdAndUsersId(reviewsId, userId).orElseThrow(() -> new MyReviewsException(USER_MY_REVIEWS_NOT_FOUND));

        List<String> imagesList = new ArrayList<>(myReviews.getMyReviewsContents().stream().map(myReviewsContents -> myReviewsContents.getMyExhibitionImage().getMyExhibitionImagesUrl()).toList());
        imagesList.add(myReviews.getMainImageUrl());

        return CommunicationRetrieveResponseDto.of(myReviews, imagesList);
    }

    @Override
    @Transactional
    public Long saveCommunications(CommunicationSaveRequestDto dto, Long userId) {
        verifyMyReviewsIdExists(dto.myReviewId());
        Communications communications = Communications.toEntity(dto, findUsersByUserId(userId));

        List<CommunicationImages> communicationImagesList = dto.images().stream().map(image -> CommunicationImages.toEntity(image, communications)).toList();
        List<CommunicationsKeyword> communicationsKeywordList = dto.keyword().stream().map(keyword -> CommunicationsKeyword.toEntity(keyword, communications)).toList();

        communications.addCommunicationImages(communicationImagesList);
        communications.addCommunicationsKeyword(communicationsKeywordList);

        return communicationsRepository.save(communications).getId();
    }

    @Override
    @Transactional
    public Long saveComment(CommunicationsCommentRequestDto dto, Long userId) {
        Users user = findUsersByUserId(userId);
        Communications communications = findCommunicationsByCommunicationsId(dto.communicationsId());
        Comment parentContent = (dto.parentContentId() != null) ? findCommentByCommentId(dto.parentContentId()) : null;
        Comment comment = Comment.toEntity(dto, user, communications, parentContent);
        return commentRepository.save(comment).getId();
    }

    @Override
    @Transactional
    public DetailCommunicationsContentResponseDto detailCommunicationsContent(Long communicationsId, Long userId) {
        Communications communications = findCommunicationsByCommunicationsId(communicationsId);
        boolean isHeartClicked = verifyUserSaveLike(communicationsId, userId);
        boolean isScrapClicked = scrapRepository.existsByCommunicationsIdAndUsersId(communicationsId, userId);
        return DetailCommunicationsContentResponseDto.of(communications, isHeartClicked, isScrapClicked, communications.getUsers());
    }

    @Override
    @Transactional
    public List<DetailCommunicationsCommentResponseDto> detailCommunicationsComment(Long communicationsId, Long userId) {
        List<Comment> commentList = commentRepository.findCommentByCommunicationsId(communicationsId);

        //comment.getParentContent가 null이면 내가 가장 상위 댓글 -> replies가 없는거
        //comment.getParentContent가 null이 아니면 내가 대댓글인거 -> replies로 들어가야함

        Map<Long, DetailCommunicationsCommentResponseDto> parentCommentMap = new HashMap<>();

        commentList.stream()
                .filter(comment -> comment.getParentContent() == null)
                .forEach(comment -> {
                    DetailCommunicationsCommentResponseDto dto = DetailCommunicationsCommentResponseDto.of(comment);
                    parentCommentMap.put(comment.getId(), dto);
                });

        // 대댓글들을 부모 댓글의 replies 리스트에 추가
        commentList.stream()
                .filter(comment -> comment.getParentContent() != null)
                .forEach(comment -> {
                    Long parentId = comment.getParentContent().getId();
                    DetailCommunicationsCommentResponseDto parentDto = parentCommentMap.get(parentId);
                    if (parentDto != null) {
                        parentDto.replies().add(DetailCommunicationsCommentResponseDto.of(comment));
                    }
                });

        return new ArrayList<>(parentCommentMap.values());
    }

    @Override
    @Transactional
    public void toggleLike(LikeRequestDto dto, Long userId) {
        Like like = getLike(dto, userId);
        // 유저가 좋아요를 눌렀는지 확인
        if (verifyUserSaveLike(dto.communicationsId(), userId)) //유저가 이미 like를 눌렀음
            deleteLike(dto.isUserClickLick(),like);
        else
            saveLike(dto.isUserClickLick(), like);
    }

    public void saveLike(boolean isUserClickLike, Like like) {
        if (isUserClickLike) throw new CommunicationException(USER_ALREADY_REGISTER_LIKE);
        likeRepository.save(like);
    }

    public void deleteLike(boolean isUserClickLlike, Like like) {
        if (!isUserClickLlike) throw new CommunicationException(USER_NOT_REGISTER_LIKE);
        likeRepository.deleteByCommunicationsIdAndUsersId(like.getCommunications().getId(), like.getUsers().getId());
    }

    public boolean verifyUserSaveLike(Long communicationsId, Long userId) {
        return likeRepository.existsByCommunicationsIdAndUsersId(communicationsId, userId);
    }

    private Like getLike(LikeRequestDto dto, Long userId) {
        Communications communications = findCommunicationsByCommunicationsId(dto.communicationsId());
        Users user = findUsersByUserId(userId);
        return Like.toEntity(communications, user);
    }


    public Comment findCommentByCommentId(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommunicationException(COMMENT_NOT_FOUND));
    }

    public Users findUsersByUserId(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }

    public Communications findCommunicationsByCommunicationsId(Long communicationsId) {
        return communicationsRepository.findById(communicationsId).orElseThrow(() -> new CommunicationException(COMMUNICATION_NOT_FOUND));
    }

    public void verifyMyReviewsIdExists(Long myReviewsId) {
        if (!(myReviewsRepository.existsById(myReviewsId))) throw new MyReviewsException(MY_REVIEWS_NOT_FOUND);
    }

}
