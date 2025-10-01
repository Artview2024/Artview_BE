package com.backend.Artview.global.abstractTest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Service2 {
    private final Parent parent;

    public void hiService(){
        String s = parent.printHello();
        System.out.println(s);
    }

    public void hiService2(){
        parent.printName("지수");
    }
}
