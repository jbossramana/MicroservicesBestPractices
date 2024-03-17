package demo.hexagon.application.port;

import java.util.List;

import demo.hexagon.domain.model.User;

public interface DisplayUser {
    List<User> listAll();

    User findByUserName(String userName);
}
