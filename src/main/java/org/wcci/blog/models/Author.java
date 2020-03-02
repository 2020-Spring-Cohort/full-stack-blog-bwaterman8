package org.wcci.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "authors")
    private Collection<Blog> blogs;

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public Collection<Blog> getBlogs() {
        return blogs;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (id != null ? !id.equals(author.id) : author.id != null) return false;
        if (name != null ? !name.equals(author.name) : author.name != null) return false;
        return name != null ? name.equals(author.name) : author.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}