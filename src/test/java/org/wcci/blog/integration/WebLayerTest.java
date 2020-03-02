package org.wcci.blog.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.models.Food;
import org.wcci.blog.storage.FoodStorage;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class WebLayerTest {
    @MockBean
    FoodStorage mockStorage;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void foodShouldBeOkAnReturnTheFoodViewWithFoodModelAttribute() throws Exception {
    mockMvc.perform(get("/home"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("home-view"))
            .andExpect(model().attributeExists("home"));
}
    @Test
    public void shouldReceiveOkFromSingleFoodEndpoint() throws Exception {
        Food testFood = new Food("pierogi");
        when(mockStorage.findFoodByType("pierogi")).thenReturn(testFood);
        mockMvc.perform(get("/home/pierogi"))
                .andExpect(status().isOk())
                .andExpect(view().name("food-view"))
                .andExpect(model().attributeExists("food"));
    }

}
