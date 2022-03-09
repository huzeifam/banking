package com.example.banking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hallo")
    public String hallo(){
        return "Hello World Test";
    }

}
