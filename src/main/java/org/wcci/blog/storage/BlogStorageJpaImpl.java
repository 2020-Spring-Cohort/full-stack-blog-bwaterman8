package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Blog;
import org.wcci.blog.storage.repositories.BlogRepository;

@Service
public class BlogStorageJpaImpl  implements BlogStorage{
    private final BlogRepository blogRepository;

    public BlogStorageJpaImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog findBlogById(long id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public void store(Blog blogToStore) {
        blogRepository.save(blogToStore);
    }
}
