package com.gblog.blog.repository;

import com.gblog.blog.entity.Blog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<Blog, String> {
}
