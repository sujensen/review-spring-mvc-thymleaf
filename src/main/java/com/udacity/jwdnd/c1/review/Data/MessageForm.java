package com.udacity.jwdnd.c1.review.Data;

/*
Class to store the form data from our request (in the home page view).
 */
public class MessageForm {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String txt) {
        this.text = txt;
    }
}
