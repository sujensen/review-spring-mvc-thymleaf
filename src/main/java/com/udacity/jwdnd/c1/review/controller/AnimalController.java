package com.udacity.jwdnd.c1.review.controller;

import com.udacity.jwdnd.c1.review.data.AnimalForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    @GetMapping
    public String getAnimalPage(@ModelAttribute("animalForm") AnimalForm animalForm) {
        return "animal";
    }

    @PostMapping
    public String postAnimalPage(@ModelAttribute("animalForm") AnimalForm animalForm) {
        return "animal";
    }
}
