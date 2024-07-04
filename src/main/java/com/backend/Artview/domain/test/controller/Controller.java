package com.backend.Artview.domain.test.controller;

import com.backend.Artview.domain.test.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class Controller {
    private final Service testService;

    @GetMapping("/{id}")
    public String getTestController(@PathVariable("id") Long id){
        String data = testService.getTest(id);
        return data;
    }
}
