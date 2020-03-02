package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Food;
import org.wcci.blog.storage.repositories.FoodRepository;

import java.util.Collection;

@Service
public class FoodStorageJpaImpl implements FoodStorage {

    private final FoodRepository foodRepository;

    public FoodStorageJpaImpl(FoodRepository foodRepository) {

        this.foodRepository = foodRepository;
    }

    @Override
    public Collection<Food> findAllFood() {
        return (Collection<Food>) foodRepository.findAll();
    }

    @Override
    public void store(Food food) {
        foodRepository.save(food);
    }

    @Override
    public Food findFoodByType(String foodByType) {
        Food retrievedFood;
        try {
            retrievedFood = foodRepository.findByType(foodByType).get();
        } catch (Exception e) {
            throw new FoodNotFoundException(e.getMessage());
        }
        return retrievedFood;
    }


}
