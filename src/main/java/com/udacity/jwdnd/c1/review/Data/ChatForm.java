package com.udacity.jwdnd.c1.review.Data;

import com.udacity.jwdnd.c1.review.Services.ChatHistoryService;

import java.util.List;

public class ChatForm {
    String username;
    String messageText;
    String messageType;
    List<String> messageTypeOptions;


    public void setUsername(String username) {
        this.username = username;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    public void setMessageTypeOptions(List<String> messageTypeOptions) { this.messageTypeOptions = messageTypeOptions; }

    public String getUsername() {
        return username;
    }
    public String getMessageText() {
        return messageText;
    }
    public String getMessageType() {
        return messageType;
    }
    public List<String> getMessageTypeOptions() { return ChatHistoryService.messageTypeOptions; }
}
