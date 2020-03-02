package org.wcci.blog.storage;

import org.wcci.blog.models.Blog;

public interface BlogStorage {
    Blog findBlogById(long id);

    void store(Blog blogToStore);
}
