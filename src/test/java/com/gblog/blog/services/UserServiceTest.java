package com.gblog.blog.services;

import com.gblog.blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void loadByUsernameTest(){
        assertNotNull(userRepository.findByUsername("jatt111"));
         //userRepository.findByUsername("jatt111");
    }

}
