package org.wcci.blog.storage;

import org.wcci.blog.models.Food;

import java.util.Collection;


public interface FoodStorage {
    Collection<Food> findAllFood();

    void store(Food food);

    Food findFoodByType(String foodByType);
}
