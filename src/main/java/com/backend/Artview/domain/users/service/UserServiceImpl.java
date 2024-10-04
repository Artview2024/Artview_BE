package com.backend.Artview.domain.users.service;

import com.backend.Artview.domain.communication.Repository.CommunicationsRepository;
import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import com.backend.Artview.domain.myReviews.repository.MyReviewsRepository;
import com.backend.Artview.domain.users.controller.followRequestDto;
import com.backend.Artview.domain.users.domain.Follow;
import com.backend.Artview.domain.users.dto.response.MyPageUserInfoResponseDto;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.domain.users.dto.response.MyPageMyReviewsAndCommunicationsResponseDto;
import com.backend.Artview.domain.users.exception.UserException;
import com.backend.Artview.domain.users.repository.FollowRepository;
import com.backend.Artview.domain.users.repository.UsersRepository;
import com.backend.Artview.global.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.backend.Artview.domain.users.exception.UserErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final MyReviewsRepository myReviewsRepository;
    private final CommunicationsRepository communicationsRepository;
    private final FollowRepository followRepository;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public MyPageUserInfoResponseDto getMyPageUserInfo(Long userId) {
        Users user = findUsersById(userId);
        int followers = 0;
        int followees = 0;
        int numberOfReviews = myReviewsRepository.countMyReview(userId);
        return MyPageUserInfoResponseDto.of(user, followers, followees, numberOfReviews);
    }

    @Override
    @Transactional
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageMyReview(Long userId) {
        List<MyReviews> myReviewsList = myReviewsRepository.findAllByUsersId(userId);
        return myReviewsList.stream().map(MyPageMyReviewsAndCommunicationsResponseDto::of).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageCommunication(Long userId) {
        List<Communications> communicationsList = communicationsRepository.findAllByUsersId(userId);
        return communicationsList.stream().map(MyPageMyReviewsAndCommunicationsResponseDto::of).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void registerFollow(String authorizationHeader, followRequestDto dto) {
        Long usersId = findUsersIdByJwtProvider(authorizationHeader);
        Users follower = findUsersById(usersId);
        Users followee = findUsersById(dto.UserWantToFollow());
        if (validateUsersAlreadyFollow(follower, followee)) throw new UserException(USER_NOT_FOUND);
        followRepository.save(Follow.toEntity(follower, followee));
    }

    private boolean validateUsersAlreadyFollow(Users follower, Users followee) {
        return followRepository.existsByFollowerAndFollowee(follower, followee);
    }

    private Users findUsersById(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }

    private Long findUsersIdByJwtProvider(String authorizationHeader) {
        return jwtProvider.getUserId(authorizationHeader);
    }
}
