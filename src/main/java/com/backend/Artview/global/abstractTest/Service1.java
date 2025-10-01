package com.backend.Artview.global.abstractTest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Service1 {
    private final Parent parent;

    public void helloService(){
        String s = parent.printHello();
        System.out.println(s);
    }

    public void helloService2(){
        parent.printName("제니");
    }
}