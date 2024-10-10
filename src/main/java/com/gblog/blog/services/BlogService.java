package com.gblog.blog.services;

import com.gblog.blog.entity.Blog;
import com.gblog.blog.entity.User;
import com.gblog.blog.repository.BlogRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserService userService;

    public Blog create(Blog blog, String username){

        User user=userService.getUser(username);
        blogRepository.save(blog);
        user.getBlogs().add(blog);
        userService.save(user);

        return blog;
    }

    public Blog findOne(String id){
        return blogRepository.findById(id).orElse(null);
    }

    public Blog updateById(String id, Blog blog){
        try{
            Blog b1=blogRepository.findById(id).orElse(null);
            if(b1==null)return null;
            b1.setTitle(blog.getTitle());
            b1.setContent(blog.getContent()!=null?blog.getContent():b1.getContent());
            blogRepository.save(b1);
            return b1;

        }catch (Exception e){
            return null;
        }
    }

    public boolean deleteById(String id, String username){
        try{
            Blog b=blogRepository.findById(id).orElse(null);
            if(b==null)return false;
            User user=userService.getUser(username);
            user.getBlogs().remove(b);
            blogRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
