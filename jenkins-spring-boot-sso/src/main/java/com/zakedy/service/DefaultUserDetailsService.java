package com.zakedy.service;

import com.zakedy.domain.User;
import com.zakedy.repo.UserDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private UserDetailsRepository repository;

    @Autowired
    public DefaultUserDetailsService(UserDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public User loadUserByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user with name: " + username));
    }

    public User createUser(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException();
        }
        return repository.save(user);
    }
}
