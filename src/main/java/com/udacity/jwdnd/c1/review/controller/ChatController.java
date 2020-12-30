package com.udacity.jwdnd.c1.review.controller;

import com.udacity.jwdnd.c1.review.data.ChatForm;
import com.udacity.jwdnd.c1.review.services.ChatHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/chat")
public class ChatController {

    ChatHistoryService chatHistoryService;

    public ChatController(ChatHistoryService chatHistoryService) {
        this.chatHistoryService = chatHistoryService;
    }

    @GetMapping
    public String getChatPage(@ModelAttribute("chatForm") ChatForm chatForm, Model model) {
        model.addAttribute("getTheChatHistory", chatHistoryService.getChatHistory());
        model.addAttribute("getThePrettyChatHistory", chatHistoryService.getPrettyChatHistory());
        return "chat";
    }

    @PostMapping
    public String postChatPage(@ModelAttribute("chatForm") ChatForm chatForm, Principal principal, Model model) {
        chatForm.setUsername(principal.getName());
        chatHistoryService.addChat(chatForm);
        chatForm.setMessageText("");
        model.addAttribute("getTheChatHistory", chatHistoryService.getChatHistory());
        model.addAttribute("getThePrettyChatHistory", chatHistoryService.getPrettyChatHistory());
        return "chat";
    }
}
