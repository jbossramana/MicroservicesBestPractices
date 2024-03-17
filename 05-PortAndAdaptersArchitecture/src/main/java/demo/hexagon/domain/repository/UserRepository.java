package demo.hexagon.domain.repository;

import java.util.List;
import java.util.Optional;

import demo.hexagon.domain.model.User;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUserName(String userName);

    List<User> findAll();

    void deleteByUserName(String userName);

}
