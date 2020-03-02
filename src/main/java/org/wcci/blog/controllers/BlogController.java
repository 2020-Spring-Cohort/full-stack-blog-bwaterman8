package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.storage.BlogStorage;
import org.wcci.blog.models.HashTag;
import org.wcci.blog.storage.repositories.HashTagRepository;
import org.wcci.blog.models.Blog;

import java.util.Optional;

@Controller
public class BlogController {
    private BlogStorage blogStorage;
    private HashTagRepository hashTagRepository;

    public BlogController(BlogStorage blogStorage, HashTagRepository hashTagRepository) {
        this.blogStorage = blogStorage;
        this.hashTagRepository = hashTagRepository;
    }

    @RequestMapping("/blog/{id}")
    public String displayBlog(@PathVariable Long id, Model model) {
        Blog retrievedBlog = blogStorage.findBlogById(1L);
        model.addAttribute("blog", retrievedBlog);
        return "blog-view";
    }

    @PostMapping("/blog/{id}/add-hashtag")
     public String addHashTagToBlog(@RequestParam String tagName, @PathVariable Long id) {
        HashTag hashTagToAdd;
        Optional<HashTag> hashTagOptional = hashTagRepository.findByName(tagName);
        if (hashTagOptional.isEmpty()) {
            hashTagToAdd = new HashTag(tagName);
            hashTagRepository.save(hashTagToAdd);
        } else {
            hashTagToAdd = hashTagOptional.get();
        }
        Blog blogWithAddedHashTag = blogStorage.findBlogById(1L);
        blogWithAddedHashTag.addHashTag(hashTagToAdd);
        blogStorage.store(blogWithAddedHashTag);
        return "redirect:/blog/" + id;
    }
}
