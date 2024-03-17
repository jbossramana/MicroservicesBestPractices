package demo.boot.service;

import java.util.List;
import java.util.Optional;

import demo.boot.model.User;

// Service Interface - UserService:

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
}