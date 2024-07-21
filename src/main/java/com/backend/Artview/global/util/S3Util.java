package com.backend.Artview.global.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.backend.Artview.global.exception.S3exception.S3Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static com.backend.Artview.global.exception.S3exception.S3UtilErrorCode.S3_UPLOAD_FAILED;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Util {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;


    public String uploadFileToS3Bucket(MultipartFile file) {
        String fileName = generatedFileName(file);
        try {
            amazonS3.putObject(bucketName, fileName, file.getInputStream(), getObjectMetadata(file));
            return getObjectUrl(fileName);
        } catch (IOException e) {
            log.error("S3Util : " + e.getMessage());
            throw new S3Exception(S3_UPLOAD_FAILED);
        }
    }

    //S3업로드에 필요한 ObjectMetadata 생성
    public ObjectMetadata getObjectMetadata(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        return objectMetadata;
    }

    //파일명 중복 방지를 위한 UUID 추가
    private String generatedFileName(MultipartFile file) {
        return UUID.randomUUID() + "_" + file.getOriginalFilename();
    }


    //S3 URL에서 filename 추출
    private String getObjectUrl(String fileName) {
        return URLDecoder.decode(amazonS3.getUrl(bucketName, fileName).toString(), StandardCharsets.UTF_8);
    }
}
