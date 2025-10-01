package com.backend.Artview.global.abstractTest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Service1 {
    private final StorageBase storageBase;

    public void uploadFile(){
        String upload = storageBase.upload();
        System.out.println(upload);
    }

    public void deleteFile(){
        storageBase.delete("Service1-파일");
    }
}