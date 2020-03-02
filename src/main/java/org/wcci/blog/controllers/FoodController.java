package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Food;
import org.wcci.blog.storage.FoodStorage;

@Controller
public class FoodController {

    private FoodStorage foodStorage;

    public FoodController(FoodStorage foodStorage) {
        this.foodStorage = foodStorage;
    }

    @RequestMapping({"/home", "/", ""})
    public String displayHome(Model model) {
    model.addAttribute("home", foodStorage.findAllFood());
    return "home-view";
    }
    @GetMapping("/home/{foodType}")
    public String displaySingleFood(@PathVariable String foodType, Model model) {
    Food retrievedFood = foodStorage.findFoodByType(foodType);
    model.addAttribute("food", retrievedFood);
    return "food-view";
    }
    @PostMapping("/add-food")
    public String addFood(@RequestParam String type) {
        foodStorage.store(new Food(type));
        return "redirect:food";
    }
}
