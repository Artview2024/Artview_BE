package com.backend.Artview.domain.exhibition.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

@RestController
@RequestMapping("/api/exhibition")
public class ExhibitionController {

    @GetMapping
    public ResponseEntity<String> getExhibition() throws IOException {
        String serviceKey = "0FkN8IDSwQCddztVJ984YIyMq1TkN7Rg+/NrjtHO98aml2zS8mydFqMblg4zrWI7AAJ3B4fIBRlGSvh1SdgI3A==";
        String serviceKey_encoded = URLEncoder.encode(serviceKey.toString(),"UTF-8");

        String requestUrl = "http://www.culture.go.kr/openapi/rest/publicperformancedisplays/period?serviceKey=";

        StringBuilder url = new StringBuilder(requestUrl);
        url.append("?").append("serviceKey").append("=").append(serviceKey_encoded);
        url.append("&").append("solYear").append("=").append(1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "*/*;q=0.9"); // HTTP_ERROR 방지
        HttpEntity<String> httpRequest = new HttpEntity<>(null, headers);

        RestTemplate restTemplate = new RestTemplate();

        HttpStatus httpStatus = null;
        try {
            URI uri = new URI(url.toString()); // service key is not registered 오류 방지
            return restTemplate.exchange(uri, HttpMethod.GET, httpRequest, new ParameterizedTypeReference<String>() {
            });

        } catch (UnknownContentTypeException | URISyntaxException e) {
            throw new IOException();
        }

    }
}
