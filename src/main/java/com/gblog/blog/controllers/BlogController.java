package com.gblog.blog.controllers;

import com.gblog.blog.entity.Blog;
import com.gblog.blog.services.BlogService;
import com.gblog.blog.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity<?> create(@RequestBody Blog blog) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Blog newBlog = blogService.create(blog, authentication.getName());
            return new ResponseEntity<>(newBlog, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    ResponseEntity<?> getAll() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            List<Blog> blogs = userService.getUserBlogs(authentication.getName());
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable String id) {
        try{
            Blog blog=blogService.findOne(id);
            if(blog==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(blog, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@RequestBody Blog blog,@PathVariable String id) {
        try{
            Blog b=blogService.updateById(id, blog);
            if(b==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(b, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable String id) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            boolean flag=blogService.deleteById(id,authentication.getName());
            if(!flag)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>("Deleted Successfully!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
