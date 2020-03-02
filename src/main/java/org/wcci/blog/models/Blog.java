package org.wcci.blog.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Blog {

    @Id
    @GeneratedValue
    private Long id;
    private String body;
    LocalDateTime date;
    @ManyToMany
    private Collection<Author> authors;
    @ManyToOne
    private Food food;
    private String title;
    @ManyToMany
    private Set<HashTag> hashTags;

    public Blog (String title, String body, Food food, Author... authors) {
        this.title = title;
        this.authors = new ArrayList<>(Arrays.asList(authors));
        this.body = body;
        this.food = food;
        this.hashTags = new HashSet<>();
    }
    public Blog() {}
    public String getTitle() {
        return title;
    }

    public Collection<Author> getAuthors() {
        return authors;
    }

    public Food getFood() {
        return food;
    }
    public String getBody() {
        return body;
    }
    public Long getId() {
        return id;
    }

    public void addHashTag(HashTag hashTagAdd) {
        hashTags.add(hashTagAdd);
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", food=" + food +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Blog)) return false;
        Blog blog = (Blog) o;
        return Objects.equals(getId(), blog.getId()) &&
                Objects.equals(getBody(), blog.getBody()) &&
                Objects.equals(date, blog.date) &&
                Objects.equals(getFood(), blog.getFood()) &&
                Objects.equals(getTitle(), blog.getTitle()) &&
                Objects.equals(hashTags, blog.hashTags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBody(), date, getFood(), getTitle(), hashTags);
    }

    public Collection<HashTag> getHashTags() {
        return hashTags;
    }
}
