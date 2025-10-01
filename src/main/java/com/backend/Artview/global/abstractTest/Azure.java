package com.backend.Artview.global.abstractTest;

public class Azure implements StorageBase {

    @Override
    public String upload() {
        return "Azure에 업로드합니다.";
    }

    @Override
    public void delete(String fileName) {
        System.out.println("Azure에서 "+fileName+"을 삭제하였습니다.");
    }
}
