package demo.hexagon.adapter.persistence.jpa.mapper;

import java.util.function.Function;

import demo.hexagon.adapter.persistence.jpa.model.UserModel;
import demo.hexagon.domain.model.User;

public class UserModelToDomainMapper implements Function<UserModel, User> {

    @Override
    public User apply(final UserModel userModel) {
        return new User.UserBuilder()
                .id(userModel.getId().intValue())
                .userName(userModel.getUserName())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .age(userModel.getAge())
                .build();
    }
}
