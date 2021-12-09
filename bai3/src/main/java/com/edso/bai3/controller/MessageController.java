package com.edso.bai3.controller;

import com.edso.bai3.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public void message() throws InterruptedException {
        service.message();
    }
}
