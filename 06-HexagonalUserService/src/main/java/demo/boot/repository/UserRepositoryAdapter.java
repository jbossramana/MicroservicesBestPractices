package demo.boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import demo.boot.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryAdapter extends JpaRepository<User, Long>, UserRepositoryPort {
    // Additional methods can be defined if needed
}