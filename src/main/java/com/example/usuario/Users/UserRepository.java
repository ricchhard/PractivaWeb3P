package com.example.usuario.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<UserApplication, Long>{
    Optional<UserApplication> findUserAplicationByName(String name);
}
