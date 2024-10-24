package com.backend.Artview.domain.users.service;

import com.backend.Artview.domain.communication.Repository.CommunicationsRepository;
import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import com.backend.Artview.domain.myReviews.repository.MyReviewsRepository;
import com.backend.Artview.domain.users.dto.request.FollowRequestDto;
import com.backend.Artview.domain.users.domain.Follow;
import com.backend.Artview.domain.users.dto.response.MyPageMyFollowListResponseDto;
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
import java.util.stream.Collectors;

import static com.backend.Artview.domain.users.exception.UserErrorCode.*;

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
        int following = followRepository.countByGiveFollowUsers(user);
        int follower = followRepository.countByTakeFollowUsers(user);
        int numberOfReviews = myReviewsRepository.countMyReview(userId);
        return MyPageUserInfoResponseDto.of(user, following, follower, numberOfReviews);
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
    public void registerFollow(Long userId , FollowRequestDto dto) {
        Users giveFollowUser = findUsersById(userId);
        Users takeFollowUser = findUsersById(dto.takeFollow());

        if (validateUsersAlreadyFollow(giveFollowUser, takeFollowUser)) throw new UserException(USER_ALREADY_FOLLOW);
        followRepository.save(Follow.toEntity(giveFollowUser, takeFollowUser));
    }

    @Override
    @Transactional
    public void deleteFollow(Long userId, FollowRequestDto dto) {
        Users giveFollowUser = findUsersById(userId);
        Users takeFollowUser = findUsersById(dto.takeFollow());

        if (!validateUsersAlreadyFollow(giveFollowUser, takeFollowUser)) throw new UserException(USER_ALREADY_UNFOLLOW);
        followRepository.deleteByGiveFollowUsersAndTakeFollowUsers(giveFollowUser,takeFollowUser);
    }

    @Override
    @Transactional
    public MyPageMyFollowListResponseDto findMyPageMyFollowingList(Long userId) {
        Users users = findUsersById(userId);
        List<Users> followingList= followRepository.findMyFollowingList(users);
        return MyPageMyFollowListResponseDto.of(followingList);
    }

    @Override
    @Transactional
    public MyPageMyFollowListResponseDto findMyPageMyFollowerList(Long userId) {
        Users users = findUsersById(userId);
        List<Users> myFollowerList = followRepository.findMyFollowerList(users);
        return MyPageMyFollowListResponseDto.of(myFollowerList);
    }

    private boolean validateUsersAlreadyFollow(Users giveFollowUser, Users takeFollowUser) {
            return followRepository.existsByGiveFollowUsersAndTakeFollowUsers(giveFollowUser, takeFollowUser);
    }

    private Users findUsersById(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }

    private Long findUsersIdByJwtProvider(String authorizationHeader) {
        String accessToken = jwtProvider.getTokenFromHeader(authorizationHeader);
        return jwtProvider.getUserId(accessToken);
    }
}
