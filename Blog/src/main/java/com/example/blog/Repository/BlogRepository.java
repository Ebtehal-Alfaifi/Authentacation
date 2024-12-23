package com.example.blog.Repository;

import com.example.blog.Model.Blog;
import com.example.blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Integer> {

    Blog findBlogById(Integer id);
    List <Blog> findAllByUser(User user);
    Blog findBlogByTitle(String title);
}
