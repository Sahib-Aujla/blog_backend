package com.gblog.blog.services;

import com.gblog.blog.entity.User;
import com.gblog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("username not found: " + username);

        return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
                .password(user.getPassword()).roles(user.getRoles().toArray(new String[0])).build();
    }
}
