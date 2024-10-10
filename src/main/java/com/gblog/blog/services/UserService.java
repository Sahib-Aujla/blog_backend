package com.gblog.blog.services;

import com.gblog.blog.entity.User;
import com.gblog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void createUser(User user) throws RuntimeException {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException("Unable to create a new user: "+e.getMessage());
        }
    }
}
