package org.wcci.blog.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Blog;
import org.wcci.blog.models.Food;
import org.wcci.blog.models.HashTag;
import org.wcci.blog.storage.repositories.AuthorRepository;
import org.wcci.blog.storage.repositories.BlogRepository;
import org.wcci.blog.storage.repositories.FoodRepository;
import org.wcci.blog.storage.repositories.HashTagRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private HashTagRepository hashTagRepository;

    @Test
    public void foodShouldHaveBlogs() {
        Food testFood = new Food("kebab");
        Author testAuthor1 = new Author("Henry Schmidt");
        Blog testBlog = new Blog("kebab", "Schmidt", testFood, testAuthor1);
        foodRepository.save(testFood);
        authorRepository.save(testAuthor1);
        blogRepository.save(testBlog);

        entityManager.flush();
        entityManager.clear();

        Optional<Food> retrievedFoodOptional = foodRepository.findByType(testFood.getType());
        Food retrievedFood = retrievedFoodOptional.get();
        Blog retrievedBlog = blogRepository.findById(testBlog.getId()).get();

        assertThat(retrievedFood.getBlogs()).contains(testBlog);
    }
    @Test
    public void blogCanHaveMultipleAuthors() {
        Author testAuthor1 = new Author("Shannon Shannon");
        Author testAuthor2 = new Author("Shon Shon");
        Food testFood = new Food("kebab");
        Blog testBlog1 = new Blog("Babushka1", "Test", testFood, testAuthor1, testAuthor2);
        Blog testBlog2 = new Blog("Babushka2", "Test", testFood, testAuthor2);
        Blog testBlog3 = new Blog("Babushka3", "Test", testFood, testAuthor1);
        authorRepository.save(testAuthor1);
        authorRepository.save(testAuthor2);
        foodRepository.save(testFood);
        blogRepository.save(testBlog1);
        blogRepository.save(testBlog2);
        blogRepository.save(testBlog3);

        entityManager.flush();
        entityManager.clear();

        Blog retrievedBlog = blogRepository.findById(testBlog1.getId()).get();
        Author retrievedAuthor1 = authorRepository.findById(testAuthor1.getId()).get();
        Author retrievedAuthor2 = authorRepository.findById(testAuthor2.getId()).get();
        assertThat(retrievedBlog.getAuthors()).contains(testAuthor1, testAuthor2);
        assertThat(retrievedAuthor1.getBlogs()).contains(testBlog1, testBlog3);
        assertThat(retrievedAuthor2.getBlogs()).contains(testBlog1, testBlog2);

    }
    @Test
    public void blogsShouldHaveHashTags() {
        Author testAuthor1 = authorRepository.save(new Author("Shannon Shannon"));
        Food testFood = foodRepository.save(new Food("kebab"));
        Blog testBlog1 = blogRepository.save(new Blog("title", "body1", testFood, testAuthor1));
        Blog testBlog2 = blogRepository.save(new Blog("title2", "body2", testFood, testAuthor1));
        HashTag testHashTag1 = hashTagRepository.save(new HashTag("#Polish"));
        HashTag testHashTag2 = hashTagRepository.save(new HashTag("#Babushka"));
        testBlog1.addHashTag(testHashTag1);
        testBlog1.addHashTag(testHashTag2);
        blogRepository.save(testBlog1);
        testBlog2.addHashTag(testHashTag2);
        blogRepository.save(testBlog2);
        entityManager.flush();
        entityManager.clear();

        Blog retrievedBlog1 = blogRepository.findById(testBlog1.getId()).get();
        Blog retrievedBlog2 = blogRepository.findById(testBlog2.getId()).get();
        assertThat(retrievedBlog1.getHashTags()).contains(testHashTag1, testHashTag2);
        assertThat(retrievedBlog2.getHashTags()).contains(testHashTag2);


    }
}

