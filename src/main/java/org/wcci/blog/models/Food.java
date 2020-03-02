package org.wcci.blog.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Food {

    @Id
    @GeneratedValue
    Long id;
    private String type;
    @OneToMany(mappedBy = "food")
    private Collection<Blog> blogs;

    public Food(String type) {
        this.type = type;
    }
    public Food() {}

    public String getType() {
        return type;
    }
    public Long getId() {
        return id;
    }
    public Collection<Blog> getBlogs() {
        return blogs;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return Objects.equals(getId(), food.getId()) &&
                Objects.equals(getType(), food.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType());
    }
}
