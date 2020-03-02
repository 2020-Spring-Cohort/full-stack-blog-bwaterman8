package org.wcci.blog.storage;

import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Food;
import org.wcci.blog.storage.repositories.FoodRepository;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class FoodStorageJpaImplTest {

    @Test
    public void shouldFindAllFood() {
        FoodRepository mockFoodRepo = mock(FoodRepository.class);
        Food testFood = new Food("kebab");
        FoodStorage underTest = new FoodStorageJpaImpl(mockFoodRepo);
        when(mockFoodRepo.findAll()).thenReturn(Collections.singletonList(testFood));
        underTest.store(testFood);
        verify(mockFoodRepo).save(testFood);
        assertThat(underTest.findAllFood()).contains(testFood);
    }
    @Test
    public void shouldGetSingleFoodByType() {
        FoodRepository mockFoodRepo = mock(FoodRepository.class);
        Food testFood1 = new Food("pierogi");
        Food testFood2 = new Food("kebab");
        FoodStorage underTest = new FoodStorageJpaImpl(mockFoodRepo);
        underTest.store(testFood1);
        underTest.store(testFood2);

        Optional<Food> testFood1Optional = Optional.of(testFood1);
        when(mockFoodRepo.findByType("pierogi")).thenReturn(testFood1Optional);

        Optional<Food> testFood2Optional = Optional.of(testFood2);
        when(mockFoodRepo.findByType("kebab")).thenReturn(testFood2Optional);

        Food retrievedFood1 = underTest.findFoodByType("pierogi");
        Food retrievedFood2 = underTest.findFoodByType("kebab");
        assertThat(retrievedFood1).isEqualTo(testFood1);
        assertThat(retrievedFood2).isEqualTo(testFood2);
    }
}
