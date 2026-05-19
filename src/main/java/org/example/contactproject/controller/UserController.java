package org.example.contactproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class UserController {

    private final String[] names = {"Alice", "Bob", "Charlie", "David"};

    @GetMapping("/user/random")
    public String randomUser() {
        return names[new Random().nextInt(names.length)];
    }
}