package demo.boot.repository;

import java.util.List;
import java.util.Optional;

import demo.boot.model.User;

public interface UserRepositoryPort {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    void deleteById(Long id);
}