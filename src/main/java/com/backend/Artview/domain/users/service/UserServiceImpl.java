package com.backend.Artview.domain.users.service;

import com.backend.Artview.domain.communication.Repository.CommunicationsRepository;
import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import com.backend.Artview.domain.myReviews.repository.MyReviewsRepository;
import com.backend.Artview.domain.users.domain.UsersInterest;
import com.backend.Artview.domain.users.dto.request.FollowRequestDto;
import com.backend.Artview.domain.users.domain.Follow;
import com.backend.Artview.domain.users.dto.request.ModifyMyPageInfoRequestDto;
import com.backend.Artview.domain.users.dto.response.*;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.domain.users.exception.UserException;
import com.backend.Artview.domain.users.repository.FollowRepository;
import com.backend.Artview.domain.users.repository.UsersInterestRepository;
import com.backend.Artview.domain.users.repository.UsersRepository;
import com.backend.Artview.global.jwt.JwtProvider;
import com.backend.Artview.global.util.S3Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final S3Util s3Util;
    private final UsersInterestRepository usersInterestRepository;

    @Override
    @Transactional
    public MyPageUserInfoResponseDto getMyPageUserInfo(Long userId) {
        Users user = findUsersById(userId);
        return MyPageUserInfoResponseDto.of(user);
    }

    @Override
    @Transactional
    public MyPageFollowAndMyReviewsNumberInfoResponseDto getMyPageTotalNumber(Long userId) {
        Users user = findUsersById(userId);
        int following = followRepository.countByGiveFollowUsers(user);
        int follower = followRepository.countByTakeFollowUsers(user);
        int numberOfReviews = myReviewsRepository.countMyReview(userId);
        return MyPageFollowAndMyReviewsNumberInfoResponseDto.of(following, follower, numberOfReviews);
    }

    @Override
    @Transactional
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageCommunication(Long userId) {
        List<Communications> communicationsList = communicationsRepository.findAllByUsersId(userId);
        return communicationsList.stream().map(MyPageMyReviewsAndCommunicationsResponseDto::of).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageMyReview(Long userId) {
        List<MyReviews> myReviewsList = myReviewsRepository.findAllByUsersIdOrderByCreateDateDesc(userId);
        return myReviewsList.stream().map(MyPageMyReviewsAndCommunicationsResponseDto::of).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void registerFollow(Long userId, FollowRequestDto dto) {
        Users giveFollowUser = findUsersById(userId);
        Users takeFollowUser = findUsersById(dto.takeFollow());

        if (validateUsersFollow(giveFollowUser, takeFollowUser)) throw new UserException(USER_ALREADY_FOLLOW);
        followRepository.save(Follow.toEntity(giveFollowUser, takeFollowUser));
    }

    @Override
    @Transactional
    public void deleteFollow(Long userId, FollowRequestDto dto) {
        Users giveFollowUser = findUsersById(userId);
        Users takeFollowUser = findUsersById(dto.takeFollow());

        if (!validateUsersFollow(giveFollowUser, takeFollowUser)) throw new UserException(USER_ALREADY_UNFOLLOW);
        followRepository.deleteByGiveFollowUsersAndTakeFollowUsers(giveFollowUser, takeFollowUser);
    }

    @Override
    @Transactional
    public List<MyPageFollowInfoDto> findMyPageMyFollowingList(Long userId) {
        Users users = findUsersById(userId);
        List<Users> followingList = followRepository.findMyFollowingList(users);
        return followingList.stream().map(myFollowing -> MyPageFollowInfoDto.of(myFollowing,
                validateUsersFollow(myFollowing, users))).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<MyPageFollowInfoDto> findMyPageMyFollowerList(Long userId) {
        Users users = findUsersById(userId);
        List<Users> myFollowerList = followRepository.findMyFollowerList(users);
        return myFollowerList.stream().map(myFollower -> MyPageFollowInfoDto.of(myFollower,
                validateUsersFollow(users, myFollower))).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean checkUsersFollow(Long userId, Long writerId) {
        Users users = findUsersById(userId);
        Users writer = findUsersById(writerId);
        return validateUsersFollow(users, writer);
    }

    @Override
    @Transactional
    public void modifyMyPageInfo(Long userId, ModifyMyPageInfoRequestDto dto) {
        Users users = findUsersById(userId);

        if (dto.userName() != null) users.updateUserName(dto.userName());

        if (dto.userImageUrl() != null) {
            if (dto.userImageUrl() instanceof MultipartFile) {
                users.updateUserInfo(dto.userName(), s3Util.uploadFileToS3Bucket((MultipartFile) dto.userImageUrl()));
            } else users.updateUserInfo(dto.userName());
        }

        List<String> usersNewInterest = dto.usersInterest();

        if (usersNewInterest!=null) {

            if (usersNewInterest.size() > 3) {
                throw new UserException(INTEREST_LENGTH_EXCEED);
            }

            List<UsersInterest> updatedInterests = usersNewInterest.stream().map(newInterest -> UsersInterest.of(newInterest, users))
                    .collect(Collectors.toList());

            if (usersInterestRepository.existsByUsers(users)) {
                usersInterestRepository.deleteAllByUsers(users);
            }

            usersInterestRepository.saveAll(updatedInterests);
        }
    }

    public boolean validateUsersFollow(Users giveFollowUser, Users takeFollowUser) {
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
