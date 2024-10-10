package com.gblog.blog.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private ObjectId id;

    @NonNull
    @Indexed(unique = true)
    private String username;

    @NonNull
    private String password;

    private List<String> roles;

}
