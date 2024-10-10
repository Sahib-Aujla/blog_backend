package com.gblog.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("blog")
@Data
@NoArgsConstructor
public class Blog {
    @Id
    String id;
    @NonNull
    String title;

    String content;


}
