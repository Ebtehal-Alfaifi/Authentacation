package com.example.blog.Service;

import com.example.blog.Api.ApiException;
import com.example.blog.Model.Blog;
import com.example.blog.Model.User;
import com.example.blog.Repository.AuthRepository;
import com.example.blog.Repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogService {
    private final AuthRepository authRepository;

    private final BlogRepository blogRepository;
    public List<Blog> getMyBlogs(Integer userId) {
        User myUser = authRepository.findUserById(userId);
        if (myUser == null) {
            throw new ApiException("Invalid user ID");
        }
        return blogRepository.findAllByUser(myUser);
    }

    public void addBlog(Integer userId, Blog blog) {
        User myUser = authRepository.findUserById(userId);
        if (myUser == null) {
            throw new ApiException("Invalid user ID");
        }
        blog.setUser(myUser);
        blogRepository.save(blog);
    }

    public void updateBlog(User myUser, Integer blogId, Blog blog) {
        User myUser1=authRepository.findUserById(myUser.getId());
        Blog oldBlog=blogRepository.findBlogById(blogId);
        if(oldBlog==null){
            throw new ApiException("there is not any  blog found");
        }
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setDescription(blog.getDescription());
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer blogId, Integer userId) {
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null) {
            throw new ApiException("Blog not found");
        } else if (blog.getUser().getId()!=userId) {
            throw new ApiException("You do not have permission to delete this blog");
        }
        blogRepository.delete(blog);
    }

    public Blog getBlogByTitle(String title) {
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog == null) {
            throw new ApiException("Blog with the given title not found");
        }
        return blog;
    }

    public Blog getBlogById(Integer blogId) {
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null) {
            throw new ApiException("Blog not found");
        }
        return blog;
    }

}
