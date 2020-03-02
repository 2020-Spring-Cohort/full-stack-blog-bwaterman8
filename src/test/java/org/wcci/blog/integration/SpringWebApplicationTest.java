package org.wcci.blog.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.models.Food;
import org.wcci.blog.storage.FoodStorage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
public class SpringWebApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FoodStorage foodStorage;

    @Test
    public void shouldReceiveOkayFromHomePage() throws Exception {
        mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void shouldReceiveOkayFromSingleFoodEndpoint() throws Exception {
        Food testFood = new Food("perogi");
        when(foodStorage.findFoodByType("perogi")).thenReturn(testFood);
        mockMvc.perform(get("/home/perogi"))
                .andExpect(status().isOk());


    }

}
