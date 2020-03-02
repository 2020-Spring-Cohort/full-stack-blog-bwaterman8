package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Blog;
import org.wcci.blog.models.Food;
import org.wcci.blog.storage.repositories.BlogRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BlogStorageJpaImplTest {
    private BlogRepository blogRepository;
    private BlogStorage underTest;
    private Blog testBlog;

    @BeforeEach
    void setUp() {
        blogRepository = mock(BlogRepository.class);
        underTest = new BlogStorageJpaImpl(blogRepository);
        Food testFood = new Food("kebab");
        Author testAuthor = new Author("shannon");
        testBlog = new Blog("title","body", testFood, testAuthor);
    }

    @Test
    public void shouldFindBlogById() {
        when(blogRepository.findById(1L)).thenReturn(Optional.of(testBlog));
        Blog retrievedBlog = underTest.findBlogById(1L);
        assertThat(retrievedBlog).isEqualTo(testBlog);
    }

    @Test
    public void shouldStoreBlog() {
        underTest.store(testBlog);
        verify(blogRepository).save(testBlog);
    }
}
