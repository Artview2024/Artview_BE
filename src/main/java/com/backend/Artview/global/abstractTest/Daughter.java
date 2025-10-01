package com.backend.Artview.global.abstractTest;

import org.springframework.stereotype.Service;

public class Daughter implements Parent {

    @Override
    public String printHello() {
        return "Hello I'm Daughter";
    }

    @Override
    public void printName(String name) {
        System.out.println("I'm not a " + name);
    }
}
