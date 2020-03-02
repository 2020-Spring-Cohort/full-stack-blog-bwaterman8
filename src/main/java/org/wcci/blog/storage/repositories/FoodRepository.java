package org.wcci.blog.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Food;

import java.util.Optional;

public interface FoodRepository extends CrudRepository<Food, Long> {
    Optional<Food> findByType(String foodType);

}
