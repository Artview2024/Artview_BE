package com.backend.Artview.global.abstractTest;

public class S3 implements StorageBase {
    @Override
    public String upload() {
        return "S3에 업로드합니다.";
    }

    @Override
    public void delete(String fileName) {
        System.out.println("S3에서 "+fileName+"을 삭제하였습니다.");
    }
}
