package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Food;
import org.wcci.blog.storage.BlogStorage;
import org.wcci.blog.storage.FoodStorage;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FoodControllerTest {
    private MockMvc mockMvc;
    private FoodController underTest;
    private FoodStorage mockStorage;
    private Model mockModel;
    private BlogStorage blogStorage;

    @BeforeEach
    public void setUp() {
        mockModel = mock(Model.class);
        mockStorage = mock(FoodStorage.class);
        blogStorage = mock(BlogStorage.class);
        underTest = new FoodController(mockStorage, blogStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldReturnViewWithOneFood() {
        Food testFood = new Food("pierogi");
        when(mockStorage.findFoodByType("kebab")).thenReturn(testFood);
        underTest.displaySingleFood("kebab", mockModel);
        verify(mockStorage).findFoodByType("kebab");
        verify(mockModel).addAttribute("food", testFood);
    }

    @Test
    public void shouldReturnViewNamedFoodViewWhenDisplayingSingleFoodIsCalled() {
        String viewName = underTest.displaySingleFood("kebab", mockModel);
        assertThat(viewName).isEqualTo("food-view");
    }

    @Test
    public void shouldHitSingleEndpoint() throws Exception {
        Food testFood = new Food("kielbasa");
        when(mockStorage.findFoodByType("kebab")).thenReturn(testFood);
        mockMvc.perform(get("/home/kebab"))
                .andExpect(status().isOk())
                .andExpect(view().name("food-view"))
                .andExpect(model().attributeExists("food"))
                .andExpect(model().attribute("food", testFood));
    }

    @Test
    public void homeShouldDisplayAllFoods() throws Exception {
        Food testFood = new Food("kielbasa");
        List<Food> foodCollection = Collections.singletonList(testFood);
        when(mockStorage.findAllFood()).thenReturn(foodCollection);
        mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("home-view"))
                .andExpect(model().attributeExists("home"))
                .andExpect(model().attribute("home", foodCollection));
    }

}
