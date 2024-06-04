package com.backend.Artview.domain.test.controller;

import com.backend.Artview.domain.test.service.TestService;
import com.backend.Artview.global.constant.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestApiController {
    private final TestService testService;

    @GetMapping("/{id}")
    public String getTestController(@PathVariable("id") Long id){
        String data = testService.getTest(id);
        return data;
    }
}
