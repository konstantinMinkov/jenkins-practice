package com.zakedy.repo;

import com.zakedy.domain.User;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserDetailsRepository extends Repository<User, String> {

    Optional<User> findByUsername(String username);

    User save(User user);

    boolean existsByUsername(String username);
}
