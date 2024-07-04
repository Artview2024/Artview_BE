package com.backend.Artview.domain.test.service;

import com.backend.Artview.domain.test.repository.Repository;
import com.backend.Artview.domain.test.domain.Tests;
import com.backend.Artview.domain.test.exception.TestErrorCode;
import com.backend.Artview.domain.test.exception.TestException;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceImpl implements Service {

    private final Repository testRepository;

    @Override
    public String getTest(Long id) {
        Tests tests = testRepository.findById(id)
                .orElseThrow(() -> new TestException(TestErrorCode.TEST_NOT_FOUND));

        return tests.getName();
    }

}
