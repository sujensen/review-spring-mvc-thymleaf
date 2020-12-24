package com.udacity.jwdnd.c1.review.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageListService {
    private List<String> messages;

    /*
    MessageListService is a bean that will be initialized by Spring,
    so better to use PostConstruct rather than a normal,
    custom constructor method.
     */
    @PostConstruct
    public void postConstruct() {
        System.out.println("...in the PostConstruct for MessageListService");
        this.messages = new ArrayList<>();
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public List<String> getMessages() {
        return new ArrayList<>(this.messages);
    }
}
