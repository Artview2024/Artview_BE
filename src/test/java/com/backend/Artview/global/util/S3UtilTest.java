package com.backend.Artview.global.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class S3UtilTest {


    private S3Util s3Util;

    @Test
    @DisplayName("S3 bucket에 이미지 저장을 성공해야 한다")
    void uploadFileToS3Bucket() {
        File file = new File("C:/Users/justi/Artview_BE/src/main/resources/images/눈사람짱구.jpeg");
        String s = s3Util.uploadFileToS3Bucket((MultipartFile) file);
        assertThat(s).isNotNull();
    }
}