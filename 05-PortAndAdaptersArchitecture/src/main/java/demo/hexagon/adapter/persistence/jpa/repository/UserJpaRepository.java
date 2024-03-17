package demo.hexagon.adapter.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.hexagon.adapter.persistence.jpa.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUserName(String userName);

    UserModel save(UserModel userModel);

    List<UserModel> findAll();

    void deleteByUserName(String userName);
}
