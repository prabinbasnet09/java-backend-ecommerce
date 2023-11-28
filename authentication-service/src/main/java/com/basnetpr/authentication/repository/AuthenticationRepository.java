package com.basnetpr.authentication.repository;

import com.basnetpr.authentication.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
