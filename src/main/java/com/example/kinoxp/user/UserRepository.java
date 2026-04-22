package com.example.kinoxp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<KinoUser,Long> {

    List<KinoUser> findByRole(String role);

    KinoUser findByUsernameAndPassword(String username, String password);

    Optional<KinoUser> findByUsername(String username);

}
