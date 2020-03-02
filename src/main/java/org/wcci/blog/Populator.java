package org.wcci.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Blog;
import org.wcci.blog.models.Food;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.BlogStorage;
import org.wcci.blog.storage.FoodStorage;

@Component
public class Populator implements CommandLineRunner {
    private final FoodStorage foodStorage;
    private final AuthorStorage authorStorage;
    private final BlogStorage blogStorage;

    public Populator(FoodStorage foodStorage, AuthorStorage authorStorage, BlogStorage blogStorage) {
        this.foodStorage = foodStorage;
        this.authorStorage = authorStorage;
        this.blogStorage = blogStorage;
    }

    @Override
    public void run(String... args) {
        Food Pierogi = new Food("Pierogi");
        foodStorage.store(Pierogi);
        Food Golabki = new Food("Golabki");
        foodStorage.store(Golabki);
        Food Kabanosy = new Food("Kabanosy");
        foodStorage.store(Kabanosy);
        Author Lou = new Author("Lou Webster");
        authorStorage.store(Lou);
        Author Dan = new Author("Dan Dravillas");
        authorStorage.store(Dan);
        Author Paul = new Author("Paul Spinks");
        authorStorage.store(Paul);
        Blog pierogiTime = new Blog("Pierogi Time", "Pierogi's are one of the most tasty, yet most commercialized.", Pierogi, Lou);
        blogStorage.store(pierogiTime);
        Blog golabkiTime = new Blog("Golabki Goliath", "Golabki at the Mayhem Market are the biggest in the city.", Golabki, Dan);
        blogStorage.store(golabkiTime);
        Blog kabanosyTime = new Blog("First Ever Kabanosy", "Kabanosy is very good.", Kabanosy,
                Paul);
        blogStorage.store(kabanosyTime);
    }
}
