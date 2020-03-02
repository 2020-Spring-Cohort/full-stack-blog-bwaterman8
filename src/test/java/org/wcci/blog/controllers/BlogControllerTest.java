package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.storage.BlogStorage;
import org.wcci.blog.storage.repositories.HashTagRepository;
import org.wcci.blog.controllers.BlogController;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Blog;
import org.wcci.blog.models.Food;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BlogControllerTest {
    private BlogController underTest;
    private Model model;
    private BlogStorage mockStorage;
    private Blog testBlog;
    private HashTagRepository hashTagRepository;

    @BeforeEach
    void setUp() {
        mockStorage = mock(BlogStorage.class);
        hashTagRepository = mock(HashTagRepository.class);
        underTest = new BlogController(mockStorage, hashTagRepository);
        model = mock(Model.class);
        Food testFood = new Food("Oscypek");
        Author testAuthor = new Author("Bob Saget");
        testBlog = new Blog("pierogi", "they good", testFood, testAuthor);
        when(mockStorage.findBlogById(1L)).thenReturn(testBlog);
    }

    @Test
    public void blogReturnsBlogTemplate() {
        String result = underTest.displayBlog(1L, model);
        assertThat(result).isEqualTo("blog-view");
    }

    @Test
    public void blogInteractsWithDependencyCorrectly() {
        underTest.displayBlog(1L, model);
        verify(mockStorage).findBlogById(1L);
        verify(model).addAttribute("blog", testBlog);
    }

    @Test
    public void BlogMapsCorrectly() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/blog/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog-view"))
                .andExpect(model().attributeExists("blog"))
                .andExpect(model().attribute("blog", testBlog));
    }
}
