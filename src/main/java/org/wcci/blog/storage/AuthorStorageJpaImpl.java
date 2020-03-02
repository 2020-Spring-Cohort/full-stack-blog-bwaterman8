package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.repositories.AuthorRepository;

@Service
public class AuthorStorageJpaImpl implements AuthorStorage{
    private final AuthorRepository authorRepository;

    public AuthorStorageJpaImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void store(Author authorToStore) {
        authorRepository.save(authorToStore);
    }
}
