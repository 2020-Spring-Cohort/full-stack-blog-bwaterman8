package org.wcci.blog.storage;

import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.repositories.AuthorRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuthorStorageJpaImplTest {
    @Test
    public void shouldStoreAuthor() {
        AuthorRepository authorRepository = mock(AuthorRepository.class);
        AuthorStorage underTest = new AuthorStorageJpaImpl(authorRepository);
        Author testAuthor = new Author("shannon");
        underTest.store(testAuthor);
        verify(authorRepository).save(testAuthor);
    }
}
