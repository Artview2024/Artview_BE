package com.backend.Artview.global.util;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadAbstract {
    public String uploadFileToS3Bucket(MultipartFile file);

    //S3업로드에 필요한 ObjectMetadata 생성
    public ObjectMetadata getObjectMetadata(MultipartFile file);

    //파일명 중복 방지를 위한 UUID 추가
    public String generatedFileName(MultipartFile file);


    //S3 URL에서 filename 추출
    public String getObjectUrl(String fileName);
}