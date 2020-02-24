package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wcci.blog.storage.FoodStorage;

@Controller
public class FoodController {

    private final FoodStorage foodStorage;

    public FoodController(FoodStorage foodStorage) {
        this.foodStorage = foodStorage;
    }

    @RequestMapping("/home")
    public String displayHome(Model model) {
    model.addAttribute("home", foodStorage.findAllFood());
    return "home-view";
    }
}
