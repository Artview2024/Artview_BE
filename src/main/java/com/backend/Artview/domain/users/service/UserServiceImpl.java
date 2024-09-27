package com.backend.Artview.domain.users.service;

import com.backend.Artview.domain.communication.Repository.CommunicationsRepository;
import com.backend.Artview.domain.communication.domain.Communications;
import com.backend.Artview.domain.myReviews.domain.MyReviews;
import com.backend.Artview.domain.myReviews.repository.MyReviewsRepository;
import com.backend.Artview.domain.users.dto.response.MyPageUserInfoResponseDto;
import com.backend.Artview.domain.users.domain.Users;
import com.backend.Artview.domain.users.dto.response.MyPageMyReviewsAndCommunicationsResponseDto;
import com.backend.Artview.domain.users.exception.UserException;
import com.backend.Artview.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.backend.Artview.domain.users.exception.UserErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UsersRepository usersRepository;
    private final MyReviewsRepository myReviewsRepository;
    private final CommunicationsRepository communicationsRepository;

    @Override
    public MyPageUserInfoResponseDto getMyPageUserInfo(Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(()->new UserException(USER_NOT_FOUND));
        int followers = 0;
        int followees = 0;
        int numberOfReviews = myReviewsRepository.countMyReview(userId);
        return MyPageUserInfoResponseDto.of(user,followers,followees,numberOfReviews);
    }

    @Override
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageMyReview(Long userId) {
        List<MyReviews> myReviewsList = myReviewsRepository.findAllByUsersId(userId);
        return myReviewsList.stream().map(MyPageMyReviewsAndCommunicationsResponseDto::of).collect(Collectors.toList());
    }

    @Override
    public List<MyPageMyReviewsAndCommunicationsResponseDto> getMyPageCommunication(Long userId) {
        List<Communications> communicationsList = communicationsRepository.findAllByUsersId(userId);
        return communicationsList.stream().map(MyPageMyReviewsAndCommunicationsResponseDto::of).collect(Collectors.toList());
    }
}
