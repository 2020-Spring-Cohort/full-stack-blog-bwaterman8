package org.wcci.blog.models;
import java.time.LocalDateTime;

public class Blog {
    private String title;
    private String author;
    LocalDateTime date;
    private String body;
    private String hashTag;

    public Blog (String title, String author, String body, String hashTag) {
        this.title = title;
        this.author = author;
        this.body = body;
        this.hashTag = hashTag;
    }
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public String getHashTag() {
        return hashTag;
    }
}
