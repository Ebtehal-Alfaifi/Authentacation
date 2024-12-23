package com.example.blog.Controller;

import com.example.blog.Api.ApiResponse;
import com.example.blog.Model.Blog;
import com.example.blog.Model.User;
import com.example.blog.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/get")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(blogService.getMyBlogs(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog) {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog added successfully"));
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @PathVariable Integer blogId, @RequestBody @Valid Blog blog) {
        blogService.updateBlog(user, blogId, blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog updated successfully"));
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer blogId) {
        blogService.deleteBlog(blogId, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Blog deleted successfully"));
    }


    @GetMapping("/get/{blogId}")
    public ResponseEntity getBlogById(@PathVariable Integer blogId) {
        return ResponseEntity.status(200).body(blogService.getBlogById(blogId));
    }

    @GetMapping("/get-title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }
}
