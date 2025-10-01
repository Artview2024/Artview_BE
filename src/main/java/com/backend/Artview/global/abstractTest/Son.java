package com.backend.Artview.global.abstractTest;

import org.springframework.stereotype.Service;

public class Son implements Parent {
    @Override
    public String printHello() {
        return "Hello I'm Son";
    }

    @Override
    public void printName(String name) {
        System.out.println("My name is " + name);
    }
}
