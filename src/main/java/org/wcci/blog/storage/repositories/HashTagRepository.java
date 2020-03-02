package org.wcci.blog.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.HashTag;

import java.util.Optional;

public interface HashTagRepository extends CrudRepository<HashTag, Long> {
    Optional<HashTag> findByName(String tagName);
}
