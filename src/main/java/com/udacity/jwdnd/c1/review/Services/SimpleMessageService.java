package com.udacity.jwdnd.c1.review.Services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SimpleMessageService {

    String simpleMessage;

    @PostConstruct
    public void simpleMessagePostConstruct() {
        simpleMessage = "Hey dude.";
    }
    public String uppercase() {
        return simpleMessage.toUpperCase();
    }

    public String lowercase() {
        return simpleMessage.toLowerCase();
    }
}
