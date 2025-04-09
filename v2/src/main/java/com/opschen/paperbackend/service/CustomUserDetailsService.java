package com.opschen.paperbackend.service;

import com.opschen.paperbackend.entity.User;
import com.opschen.paperbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User appUser = userRepository.findByAccount(account);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        // 这里将用户角色添加前缀 "ROLE_"
        return org.springframework.security.core.userdetails.User.withUsername(appUser.getAccount())
                   .password(appUser.getPassword())
                   .authorities("ROLE_" + appUser.getRole())
                   .build();
    }
}