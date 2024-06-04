package com.backend.Artview.domain.test.service;

import com.backend.Artview.domain.test.repository.TestRepository;
import com.backend.Artview.domain.test.domain.Tests;
import com.backend.Artview.domain.test.exception.TestErrorCode;
import com.backend.Artview.domain.test.exception.TestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public String getTest(Long id) {
        Tests tests = testRepository.findById(id)
                .orElseThrow(() -> new TestException(TestErrorCode.TEST_NOT_FOUND));

        return tests.getName();
    }

}
