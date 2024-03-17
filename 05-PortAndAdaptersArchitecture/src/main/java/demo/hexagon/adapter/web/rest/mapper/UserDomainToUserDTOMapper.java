package demo.hexagon.adapter.web.rest.mapper;

import hu.dev.hexagon.rest.model.UserDTO;

import java.util.function.Function;

import demo.hexagon.domain.model.User;

public class UserDomainToUserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(final User user) {
        return new UserDTO()
                .id(user.getId().longValue())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge());
    }
}
