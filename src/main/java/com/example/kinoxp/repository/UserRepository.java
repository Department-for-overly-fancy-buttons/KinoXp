package com.example.kinoxp.repository;

import com.example.kinoxp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByRole(String role);

    User findByUsernameAndPassword(String username, String password);

}
