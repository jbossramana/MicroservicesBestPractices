package demo.boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import demo.boot.model.User;

// Repository Interface - UserRepository:

public interface UserRepository extends JpaRepository<User, Long> {
}