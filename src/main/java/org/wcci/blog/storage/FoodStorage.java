package org.wcci.blog.storage;

import org.wcci.blog.controllers.FoodController;

import java.util.Collection;

public interface FoodStorage {
    Collection<FoodController> findAllFood();
}
