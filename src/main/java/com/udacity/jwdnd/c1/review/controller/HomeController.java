package com.udacity.jwdnd.c1.review.controller;

import com.udacity.jwdnd.c1.review.data.AnimalForm;
import com.udacity.jwdnd.c1.review.data.MessageForm;
import com.udacity.jwdnd.c1.review.services.MessageListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
HomeController has a dependency on MessageListService.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    // Spring bean dependency
    private MessageListService messageListService;

    public HomeController(MessageListService messageListService) {
        System.out.println("...in HomeController constructor");
        this.messageListService = messageListService;
    }

    @GetMapping
    public String getHomePage(@ModelAttribute("newMessage") MessageForm newMessage,
                              @ModelAttribute("newAnimal") AnimalForm newAnimal,
                              Model model) {

        model.addAttribute("welcomeMessage", "You did a GET request for the home page");

        model.addAttribute("greetings", this.messageListService.getMessages());

        return "home";
    }

    @PostMapping
    public String setHomePage(@ModelAttribute("newAnimal") AnimalForm animalForm,
                              @ModelAttribute("newMessage") MessageForm messageForm,
                              Model model) {
        messageListService.addMessage(messageForm.getText());
        model.addAttribute("greetings", messageListService.getMessages());

        return "home";
    }
}
