package org.wcci.blog.storage;

import org.wcci.blog.models.Author;

public interface AuthorStorage {
    void store(Author authorToStore);

}
