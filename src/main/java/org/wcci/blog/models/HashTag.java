package org.wcci.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class HashTag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public HashTag(String name) {
        this.name = name;
    }

    protected HashTag() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashTag)) return false;
        HashTag hashTag = (HashTag) o;
        return Objects.equals(getId(), hashTag.getId()) &&
                Objects.equals(getName(), hashTag.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}