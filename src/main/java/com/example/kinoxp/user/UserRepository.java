package com.example.kinoxp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByRole(String role);

    User findByUsernameAndPassword(String username, String password);

}
