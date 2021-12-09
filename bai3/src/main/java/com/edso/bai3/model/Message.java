package com.edso.bai3.model;

import lombok.Data;

@Data
public class Message {
    private Long id;
    private String content;

    public Message(String content) {
        this.id = System.currentTimeMillis();
        this.content = content;
    }

    @Override
    public String toString() {
        return id + " - " + content;
    }
}
