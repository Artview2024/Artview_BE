package com.backend.Artview.global;

import com.backend.Artview.global.constant.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthCheck {

    @GetMapping
    public String HealthCheck() {
        return "Artview HealthCheck";
    }


}
